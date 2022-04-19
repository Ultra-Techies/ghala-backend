package io.ultratechies.ghala.orders.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.domain.UpdateOrderDTO;
import io.ultratechies.ghala.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @ApiOperation("Get Order By Id")
    @GetMapping("{orderId}")
    public Optional<Orders> getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @ApiOperation("Create Order")
    @PostMapping
    public ResponseEntity createOrder(@RequestBody Orders order){
        orderService.createOrder(order);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Delete Order")
    @DeleteMapping("{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Update Order")
    @PutMapping
    public ResponseEntity updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO){
        orderService.updateOrder(updateOrderDTO);
        return ResponseEntity.ok().build();
    }


}
