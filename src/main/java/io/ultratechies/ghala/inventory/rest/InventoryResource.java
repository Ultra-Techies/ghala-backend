package io.ultratechies.ghala.inventory.rest;

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
@RequestMapping("/inventory")
public class InventoryResource {
    @Autowired
    private final InventoryService inventoryService;

    @GetMapping("{warehouseId}")
    public List<Inventory> getInventoryByWarehouse(@PathVariable Long warehouseId){
        return inventoryService.getInventoryByWarehouse(warehouseId);
    }

    @PostMapping
    public ResponseEntity createInventoryItem(@RequestBody Inventory inventoryItem){
        inventoryService.createInventoryItem(inventoryItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{sku}")
    public ResponseEntity deleteInventoryItem(@PathVariable Long sku){
        inventoryService.deleteInventoryItem(inventoryService.getInventoryItemBySku(sku));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateInventoryItem(@RequestBody InventoryUpdateDTO inventoryUpdateDTO){
        inventoryService.updateInventoryItem(inventoryUpdateDTO);
        return ResponseEntity.ok().build();
    }

}
