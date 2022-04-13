package io.ultratechies.ghala.orders.repository;

import io.ultratechies.ghala.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByWarehouseId(Long warehouseId);
}
