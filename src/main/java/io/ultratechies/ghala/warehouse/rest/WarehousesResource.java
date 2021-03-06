package io.ultratechies.ghala.warehouse.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/warehouses")
public class WarehousesResource {
    private final WarehouseService warehouseService;

    @ApiOperation("Get All Warehouses")
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses(){
        var whs = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(whs);
    }

}
