package io.ultratechies.ghala.warehouse.service;

import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    @Autowired
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();

    }

    public Optional<Warehouse> getWarehouseById(Long id){
        return warehouseRepository.findById(id);
    }

    public ResponseEntity saveWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteWarehouse(Long id){
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
