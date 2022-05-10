package io.ultratechies.ghala.orders.repository;

import io.ultratechies.ghala.orders.domain.Orders;
import io.ultratechies.ghala.statistics.domain.OrderStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByWarehouseId(Long warehouseId);

    List<Orders> findByIdIn(List<Long> ids);

    @Query(nativeQuery = true)
    List<OrderStats> findOrderValues(@Param("warehouseId") Long warehouseId);

}
