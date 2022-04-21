package io.ultratechies.ghala.warehouse.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.warehouse.domain.UpdateWarehouseDTO;
import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseResource {
    private final WarehouseService warehouseService;

    @ApiOperation("Fetch Warehouse Info by ID")
    @GetMapping("/{warehouseId}")
    public ResponseEntity<Optional<Warehouse>> getWarehouseById(@ApiParam(name="id", required = true,example = "1")
                                                                @PathVariable Long warehouseId){
        var wh=warehouseService.getWarehouseById(warehouseId);
        return ResponseEntity.ok(wh);
    }

    @ApiOperation("Register a New Warehouse")
    @PostMapping()
    public Long registerWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.saveWarehouse(warehouse);
    }

    @ApiOperation("Delete Warehouse by Id")
    @DeleteMapping("/{warehouseId}")
    public ResponseEntity deleteWarehouse(@ApiParam(name="id", required = true,example = "1")
                                          @PathVariable Long warehouseId){
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Update Warehouse Details")
    @PutMapping()
    public ResponseEntity updateWarehouse(@RequestBody UpdateWarehouseDTO updateWarehouseDTO){
        warehouseService.updateWarehouse(updateWarehouseDTO);
        return ResponseEntity.ok().build();
    }


}
