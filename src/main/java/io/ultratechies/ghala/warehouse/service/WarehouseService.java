package io.ultratechies.ghala.warehouse.service;

import io.ultratechies.ghala.warehouse.domain.Warehouse;
import io.ultratechies.ghala.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    @Autowired
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();

    }

}
