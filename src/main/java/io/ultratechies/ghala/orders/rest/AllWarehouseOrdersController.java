package io.ultratechies.ghala.orders.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouseorders")
public class AllWarehouseOrdersController {
    @Autowired
    private final OrderService orderService;

    @ApiOperation("Get All Orders by Warehouse ID")
    @GetMapping("/{warehouseId}")
    public List<Orders> getOrdersByWarehouse(@ApiParam(name="id", required = true,example = "1")
                                             @PathVariable Long warehouseId){
        return orderService.getOrdersByWarehouse(warehouseId);
    }
}
