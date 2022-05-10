package io.ultratechies.ghala.inventory.repository;

import io.ultratechies.ghala.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    Inventory findBySku(Long sku);

    List<Inventory> findByWarehouseId(Long warehouseId);
}
