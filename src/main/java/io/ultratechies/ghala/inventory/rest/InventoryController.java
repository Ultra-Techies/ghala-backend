package io.ultratechies.ghala.inventory.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.service.InventoryService;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allWHinventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @ApiOperation("Get All Inventory by Warehouse")
    @GetMapping("/{warehouseId}")
    public List<Inventory> getInventoryByWarehouse(@ApiParam(name="id", required = true,example = "1")
                                                   @PathVariable Long warehouseId){
        warehouseService.getWarehouseById(warehouseId);
        return inventoryService.getInventoryByWarehouse(warehouseId);
    }
}
