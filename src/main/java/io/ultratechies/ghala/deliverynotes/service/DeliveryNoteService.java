package io.ultratechies.ghala.deliverynotes.service;

import io.ultratechies.ghala.deliverynotes.domain.CreateNoteDTO;
import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.repository.DeliveryNoteRepository;
import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.enums.OrderStatus;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.repository.InventoryRepository;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.repository.OrderRepository;
import io.ultratechies.ghala.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeliveryNoteService {
    @Autowired
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;

    @Transactional
    public Map createDeliveryNote(CreateNoteDTO createNoteDTO){
        warehouseRepository.findById(createNoteDTO.getWarehouseId())
                .orElseThrow(()->new IllegalStateException("Warehouse with Id: "+ createNoteDTO.getWarehouseId()
                        +" Does not exist!"));
        DeliveryNote note= new DeliveryNote();
        List<Orders> ordersList=orderRepository.findAllById(createNoteDTO.getOrderIds());
        note.setStatus(DeliveryNoteStatus.PENDING);
        note.setRoute(createNoteDTO.getRoute());
        note.setCreatedTime(LocalTime.now());
        note.setOrders(ordersList);
        note.setDeliveryWindow(createNoteDTO.getDeliveryWindow());
        note.setWarehouseId(createNoteDTO.getWarehouseId());
        note.getOrders().stream()
                .forEach(order -> {
                    if (! order.getWarehouseId().equals(createNoteDTO.getWarehouseId())){
                        throw new IllegalArgumentException("Order with Code: "+ order.getOrderCode()
                                +" does not belong to WH with Id: "+ createNoteDTO.getWarehouseId()
                                +". Order's WH Id:"+ order.getWarehouseId());
                    }
                    order.setStatus(OrderStatus.PENDING);
                    orderRepository.save(order);});
        DeliveryNote savedNote=deliveryNoteRepository.save(note);
        savedNote.setNoteCode("GH"+ randomNo()+"DN"+savedNote.getId());
        DeliveryNote s=deliveryNoteRepository.save(savedNote);
        Map map = new HashMap<>();
        map.put("id",s.getId());
        return map;
    }

    public Optional<DeliveryNote> getDeliveryNoteById(Long deliveyNoteId){
        return Optional.ofNullable(deliveryNoteRepository.findById(deliveyNoteId)
                .orElseThrow(() -> new IllegalArgumentException("Note with Requested Id does not exist!")));
    }

    public List<DeliveryNote> getAllNotesByWarehouse(Long warehouseId){

        return deliveryNoteRepository.findAllByWarehouseId(warehouseId);
    }


    private String randomNo() {
        return new DecimalFormat("00")
                .format(new Random().nextInt(99));
    }

    public DeliveryNote changeStatusToProcessing(Long noteId){
        DeliveryNote note=deliveryNoteRepository.findById(noteId).get();
        note.setStatus(DeliveryNoteStatus.PROCESSING);
        note.getOrders().stream()
                .forEach(order -> { order.setStatus(OrderStatus.PROCESSING);
                                    orderRepository.save(order);});
        deliveryNoteRepository.save(note);
        return note;
    }

    public DeliveryNote changeStatusToDispatched(Long noteId){
        DeliveryNote note=deliveryNoteRepository.findById(noteId).get();
        note.setStatus(DeliveryNoteStatus.DISPATCHED);
        note.getOrders().stream()
                .forEach(order -> { order.setStatus(OrderStatus.DISPATCHED);
                                    order.getItems().forEach(item -> {
                                        Inventory inventoryItem = inventoryRepository.findBySku(item.getSku());
                                        inventoryItem.setQuantity(inventoryItem.getQuantity()- item.getQuantity());
                                        inventoryRepository.save(inventoryItem);
                                    });
                    orderRepository.save(order);});
        deliveryNoteRepository.save(note);
        return note;
    }

    @Scheduled(fixedRate =1000)
    public void mockDelivery(){
        List<DeliveryNote> notes = deliveryNoteRepository.findAll();
        notes.forEach(note -> {
            if(ChronoUnit.MINUTES.between(note.getCreatedTime(),LocalDateTime.now()) >60){
            changeStatusToCompleted(note.getId());}
        });
    }

    private void changeStatusToCompleted(Long noteId){
        DeliveryNote note=deliveryNoteRepository.findById(noteId).get();
        note.setStatus(DeliveryNoteStatus.COMPLETED);
        note.getOrders().stream()
                .forEach(order -> {
                    order.setStatus(OrderStatus.DELIVERED);
                    orderRepository.save(order);});
        deliveryNoteRepository.save(note);
    }
}
