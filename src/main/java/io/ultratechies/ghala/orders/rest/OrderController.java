package io.ultratechies.ghala.orders.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.domain.UpdateOrderDTO;
import io.ultratechies.ghala.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @ApiOperation("Get Order By Id")
    @GetMapping("/{orderId}")
    public Optional<Orders> getOrderById(@ApiParam(value="id", required = true,example = "1")
                                         @PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @ApiOperation("Create Order")
    @PostMapping
    public Map createOrder(@RequestBody Orders order){
        return orderService.createOrder(order);
    }

    @ApiOperation("Delete Order")
    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@ApiParam(name="id", required = true,example = "1")
                                      @PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Update Order")
    @PutMapping
    public ResponseEntity updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO){
        orderService.updateOrder(updateOrderDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get All Orders by Warehouse ID")
    @GetMapping("/wh/{warehouseId}")
    public List<Orders> getOrdersByWarehouse(@ApiParam(name="id", required = true,example = "1")
                                             @PathVariable Long warehouseId){
        return orderService.getOrdersByWarehouse(warehouseId);
    }

    @ApiOperation("Get All Orders")
    @GetMapping("/all")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

}
