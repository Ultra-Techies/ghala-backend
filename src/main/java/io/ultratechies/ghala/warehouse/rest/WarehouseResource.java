package io.ultratechies.ghala.warehouse.rest;

import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController("/warehouse")
public class WarehouseResource {
    private final WarehouseService warehouseService;

    @GetMapping("{warehouseId}")
    public ResponseEntity<Optional<Warehouse>> getWarehouseById(@PathVariable Long warehouseId){
        var wh=warehouseService.getWarehouseById(warehouseId);
        return ResponseEntity.ok(wh);
    }

    @PostMapping()
    public ResponseEntity registerWarehouse(@RequestBody Warehouse warehouse){
        warehouseService.saveWarehouse(warehouse);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{warehouseId}")
    public ResponseEntity deleteWarehouse(@PathVariable Long warehouseId){
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.ok().build();
    }


}
