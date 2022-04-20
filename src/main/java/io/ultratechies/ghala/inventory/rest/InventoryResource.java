package io.ultratechies.ghala.inventory.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.domain.InventoryUpdateDTO;
import io.ultratechies.ghala.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryResource {
    @Autowired
    private final InventoryService inventoryService;

    @ApiOperation("Get All Inventory by Warehouse")
    @GetMapping("/{warehouseId}")
    public List<Inventory> getInventoryByWarehouse(@ApiParam(name="id", required = true,example = "1")
                                                   @PathVariable Long warehouseId){
        return inventoryService.getInventoryByWarehouse(warehouseId);
    }

    @ApiOperation("Create Inventory Item")
    @PostMapping
    public ResponseEntity createInventoryItem(@RequestBody Inventory inventoryItem){
        inventoryService.createInventoryItem(inventoryItem);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Delete Inventory Item")
    @DeleteMapping("/{sku}")
    public ResponseEntity deleteInventoryItem(@ApiParam(name="id", required = true,example = "1")
                                              @PathVariable Long sku){
        inventoryService.deleteInventoryItem(sku);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Update Inventory Item")
    @PutMapping
    public ResponseEntity updateInventoryItem(@RequestBody InventoryUpdateDTO inventoryUpdateDTO){
        inventoryService.updateInventoryItem(inventoryUpdateDTO);
        return ResponseEntity.ok().build();
    }

}
