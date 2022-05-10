package io.ultratechies.ghala.inventory.repository;

import io.ultratechies.ghala.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    Inventory findBySku(Long sku);

    List<Inventory> findByWarehouseId(Long warehouseId);

    @Query(value = "SELECT SUM(i.ppu * i.quantity) FROM inventory AS i " +
            "WHERE i.warehouse_id = :warehouseId", nativeQuery = true)
    Integer totalValue(@Param("warehouseId") Long warehouseId);
}
