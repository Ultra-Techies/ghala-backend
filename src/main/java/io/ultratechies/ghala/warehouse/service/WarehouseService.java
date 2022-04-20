package io.ultratechies.ghala.warehouse.service;

import io.ultratechies.ghala.warehouse.domain.UpdateWarehouseDTO;
import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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
        return warehouseRepository.findWarehouseById(id);
    }

    public ResponseEntity saveWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteWarehouse(Long id){
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public void updateWarehouse(UpdateWarehouseDTO updateWarehouseDTO){
        Warehouse warehouse= warehouseRepository.findById(updateWarehouseDTO.getId())
                .orElseThrow(() ->new IllegalStateException("Warehouse with ID "+ updateWarehouseDTO.getId()+" does not exist!"));
        if (updateWarehouseDTO.getName() != null &&
                updateWarehouseDTO.getName().length() > 0 &&
                !Objects.equals(warehouse.getName(),updateWarehouseDTO.getName())) {
            warehouse.setName(updateWarehouseDTO.getName());
        }
    }

}
