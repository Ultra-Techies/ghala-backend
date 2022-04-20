package io.ultratechies.ghala.inventory.service;

import io.ultratechies.ghala.inventory.domain.Inventory;
import io.ultratechies.ghala.inventory.domain.InventoryUpdateDTO;
import io.ultratechies.ghala.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
    public List<Inventory> getInventoryByWarehouse(Long warehouseId){
        return inventoryRepository.findByWarehouseId(warehouseId);
    }

    public Optional<Inventory> getInventoryItemBySku(Long sku){
        //Inventory inventoryItem=inventoryRepository.findBySku(sku);
        return Optional.ofNullable(Optional.ofNullable(inventoryRepository.findBySku(sku))
                .orElseThrow(() -> new IllegalStateException("Inventory Item With SKU code " + sku + " does not exist!")));
    }

    public void createInventoryItem(Inventory item){
        inventoryRepository.save(item);
        ResponseEntity.ok().build();
    }

    public void deleteInventoryItem(Long sku){
        if (!inventoryRepository.existsById(sku)){
            throw new IllegalArgumentException("Inventory Item With SKU code " + sku + " does not exist!");
        }
        inventoryRepository.deleteById(sku);
        ResponseEntity.ok().build();
    }

    public void updateInventoryItem(InventoryUpdateDTO updateInventoryDTO){
        Inventory inventoryItem = inventoryRepository.findBySku(updateInventoryDTO.getSku());
        if (updateInventoryDTO.getName() != null &&
                updateInventoryDTO.getName().length() > 0 &&
                !Objects.equals(inventoryItem.getName(),updateInventoryDTO.getName())) {
            inventoryItem.setName(updateInventoryDTO.getName());
        }
        if (updateInventoryDTO.getCategory() != null &&
                updateInventoryDTO.getCategory().length() > 0 &&
                !Objects.equals(inventoryItem.getCategory(),updateInventoryDTO.getCategory())) {
            inventoryItem.setCategory(updateInventoryDTO.getCategory());
        }
        if (updateInventoryDTO.getQuantity() != null &&
                !Objects.equals(inventoryItem.getQuantity(),updateInventoryDTO.getQuantity())) {
            inventoryItem.setQuantity(updateInventoryDTO.getQuantity());
        }
        if (updateInventoryDTO.getPpu() != null &&
                updateInventoryDTO.getPpu() > 0 &&
                !Objects.equals(inventoryItem.getPpu(),updateInventoryDTO.getPpu())) {
            inventoryItem.setPpu(updateInventoryDTO.getPpu());
        }

        if (updateInventoryDTO.getStatus() != null &&
                updateInventoryDTO.getStatus().length() > 0 &&
                !Objects.equals(inventoryItem.getStatus(),updateInventoryDTO.getStatus())) {
            inventoryItem.setStatus(updateInventoryDTO.getStatus());
        }
        inventoryRepository.save(inventoryItem);

        ResponseEntity.ok().build();
    }
}
