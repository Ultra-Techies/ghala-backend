package io.ultratechies.ghala.orders.service;

import io.ultratechies.ghala.enums.OrderStatus;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.repository.InventoryRepository;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.domain.UpdateOrderDTO;
import io.ultratechies.ghala.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByWarehouse(Long warehouseId){
        return orderRepository.findAllByWarehouseId(warehouseId);
    }

    public Optional<Orders> getOrderById(Long orderId){


        Orders order = orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("Order with Id "+orderId+" does not exist!"));
        return Optional.of(order);
    }

    @Transactional
    public Map createOrder(Orders order){
        order.setValue(0);
        order.getItems()
                .forEach(item ->{   Inventory inventoryItem=inventoryRepository.findBySku(item.getSku());
                                    item.setName(inventoryItem.getName());
                                    item.setPpu(inventoryItem.getPpu());
                                    item.setTotalPrice(item.getPpu()*item.getQuantity());
                                    order.setValue(order.getValue()+item.getTotalPrice());});
        order.setStatus(OrderStatus.SUBMITTED);
        order.setCreatedDate(LocalDate.now());
        order.setCreatedTime(LocalTime.now());
        Orders newOrder =orderRepository.save(order);
        newOrder.setOrderCode("GH"+ randomNo()+"OS"+newOrder.getId());
        Map map = new HashMap<>();
        map.put("id", newOrder.getId());
        return map;
    }
    public ResponseEntity deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity updateOrder(UpdateOrderDTO updateOrderDTO){
        Orders order=orderRepository.getById(updateOrderDTO.getId());
        if (updateOrderDTO.getDue()!=null&&
                !Objects.equals(updateOrderDTO.getDue(),order.getDue())){
            order.setDue(updateOrderDTO.getDue());
            order.setStatus(OrderStatus.UPDATED);
        }
        if (updateOrderDTO.getDeliveryWindow()!=null&&
                !Objects.equals(updateOrderDTO.getDeliveryWindow(),order.getDeliveryWindow())){
            order.setDeliveryWindow(updateOrderDTO.getDeliveryWindow());
            order.setStatus(OrderStatus.UPDATED);
        }
        if (updateOrderDTO.getStatus()!=null &&
        !Objects.equals(updateOrderDTO.getStatus(),order.getStatus())){
            order.setStatus(updateOrderDTO.getStatus());
            order.setStatus(OrderStatus.UPDATED);
        }
        if (updateOrderDTO.getItems()==null || updateOrderDTO.getItems().isEmpty()){
            throw new IllegalArgumentException("updateOrderDTO cannot have null/empty Items List, " +
                    "Cancel order instead");
        }
        if (updateOrderDTO.getItems()!=null &&
                !Objects.equals(updateOrderDTO.getItems(),order.getItems())){
            order.setItems(updateOrderDTO.getItems());
            order.setStatus(OrderStatus.UPDATED);
        }

        if (updateOrderDTO.getValue()!=null &&
                updateOrderDTO.getValue()>0 &&
                !Objects.equals(updateOrderDTO.getValue(),order.getValue())){
            order.setValue(updateOrderDTO.getValue());
            order.setStatus(OrderStatus.UPDATED);
        }
        order.setValue(0);
        order.getItems()
                .forEach(item ->{   Inventory inventoryItem=inventoryRepository.findBySku(item.getSku());
                    item.setName(inventoryItem.getName());
                    item.setPpu(inventoryItem.getPpu());
                    item.setTotalPrice(item.getPpu()*item.getQuantity());
                    order.setValue(order.getValue()+item.getTotalPrice());});
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    private String randomNo() {
        return new DecimalFormat("00")
                .format(new Random().nextInt(99));
    }
}
