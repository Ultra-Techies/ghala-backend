package io.ultratechies.ghala.warehouse.repository;

import io.ultratechies.ghala.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

}
