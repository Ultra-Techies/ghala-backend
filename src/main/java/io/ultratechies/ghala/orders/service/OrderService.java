package io.ultratechies.ghala.orders.service;

import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.domain.UpdateOrderDTO;
import io.ultratechies.ghala.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByWarehouse(Long warehouseId){
        return orderRepository.findAllByWarehouseId(warehouseId);
    }

    public Optional<Orders> getOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }

    public ResponseEntity createOrder(Orders order){
        orderRepository.save(order);
        return ResponseEntity.ok().build();
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
        }
        if (updateOrderDTO.getDeliveryWindow()!=null&&
                !Objects.equals(updateOrderDTO.getDeliveryWindow(),order.getDeliveryWindow())){
            order.setDeliveryWindow(updateOrderDTO.getDeliveryWindow());
        }
        if (updateOrderDTO.getStatus()!=null &&
        !Objects.equals(updateOrderDTO.getStatus(),order.getStatus())){
            order.setStatus(updateOrderDTO.getStatus());
        }
        if (updateOrderDTO.getItems()==null || updateOrderDTO.getItems().isEmpty()){
            throw new IllegalArgumentException("updateOrderDTO cannot have null/empty Items List, " +
                    "Cancel order instead");
        }
        if (updateOrderDTO.getItems()!=null &&
                !Objects.equals(updateOrderDTO.getItems(),order.getItems())){
            order.setItems(updateOrderDTO.getItems());
        }

        if (updateOrderDTO.getValue()!=null &&
                updateOrderDTO.getValue()>0 &&
                !Objects.equals(updateOrderDTO.getValue(),order.getValue())){
            order.setValue(updateOrderDTO.getValue());
        }

        return ResponseEntity.ok().build();
    }
}
