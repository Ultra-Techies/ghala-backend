package io.ultratechies.ghala.warehouse.rest;

import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/warehouses")
public class WarehousesResource {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehousesResource(WarehouseService warehouseService){
        this.warehouseService=warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses(){
        var whs = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(whs);
    }

}
