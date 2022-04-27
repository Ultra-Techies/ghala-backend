package io.ultratechies.ghala.deliverynotes.service;

import io.ultratechies.ghala.deliverynotes.domain.CreateNoteDTO;
import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.repository.DeliveryNoteRepository;
import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.enums.OrderStatus;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeliveryNoteService {
    @Autowired
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Map createDeliveryNote(CreateNoteDTO createNoteDTO){
        DeliveryNote note= new DeliveryNote();
        List<Orders> ordersList=orderRepository.findAllById(createNoteDTO.getOrderIds());
        note.setStatus(DeliveryNoteStatus.PENDING);
        note.setRoute(createNoteDTO.getRoute());
        note.setOrders(ordersList);
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

    public List<DeliveryNote> getAllNotes(){
        return deliveryNoteRepository.findAll();
    }

    public ResponseEntity changeNoteStatusToDispatched(DeliveryNote deliveryNote){
        deliveryNote.setStatus(DeliveryNoteStatus.DISPATCHED);
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity changeNoteStatusToDelivered(DeliveryNote deliveryNote){
        deliveryNote.setStatus(DeliveryNoteStatus.DELIVERED);
        deliveryNote.getOrders()
                .forEach(order ->{
                    order.setStatus(OrderStatus.DELIVERED);
                });
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }

    private String randomNo() {
        return new DecimalFormat("00")
                .format(new Random().nextInt(99));
    }
}
