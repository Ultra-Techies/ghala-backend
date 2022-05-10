package io.ultratechies.ghala.orders.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class AllOrdersController {
    @Autowired
    private final OrderService orderService;

    @ApiOperation("Get All Orders")
    @GetMapping
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }
}
