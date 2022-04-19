package io.ultratechies.ghala.inventory.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/allinventory")
public class AllInventoryResource {
    @Autowired
    private final InventoryService inventoryService;

    @ApiOperation("Get All Inventory")
    @GetMapping()
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventory();
    }
}
