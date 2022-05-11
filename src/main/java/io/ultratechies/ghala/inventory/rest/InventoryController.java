package io.ultratechies.ghala.inventory.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.domain.InventoryUpdateDTO;
import io.ultratechies.ghala.inventory.service.InventoryService;
import io.ultratechies.ghala.warehouse.service.WarehouseService;
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
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @ApiOperation("Get Inventory item by Id")
    @GetMapping("/{itemId}")
    public Optional<Inventory> getInventoryById(@ApiParam(name="id", required = true,example = "1")
                                                   @PathVariable Long itemId){
        return inventoryService.getInventoryItemBySku(itemId);
    }

    @ApiOperation("Create Inventory Item")
    @PostMapping
    public Map createInventoryItem(@RequestBody Inventory inventoryItem){
        return inventoryService.createInventoryItem(inventoryItem);
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

    @ApiOperation("Get All Inventory")
    @GetMapping("/all")
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventory();
    }

    @ApiOperation("Get All Inventory by Warehouse")
    @GetMapping("/wh/{warehouseId}")
    public List<Inventory> getInventoryByWarehouse(@ApiParam(name="id", required = true,example = "1")
                                                   @PathVariable Long warehouseId){
        warehouseService.getWarehouseById(warehouseId);
        return inventoryService.getInventoryByWarehouse(warehouseId);
    }
}
