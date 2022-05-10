package io.ultratechies.ghala.statistics.service;

import io.ultratechies.ghala.inventory.repository.InventoryRepository;
import io.ultratechies.ghala.orders.repository.OrderRepository;
import io.ultratechies.ghala.statistics.domain.OrderStats;
import lombok.AllArgsConstructor;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
@AllArgsConstructor
public class StatsService {

    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public Map findTotalOrderAndInventoryValue(Long warehouseId){

        Integer inventoryValue = 0;
        List<OrderStats> orderValue;
        try {
            inventoryValue = inventoryRepository.totalValue(warehouseId);
            if(inventoryValue == null){
                inventoryValue=0;
            }
        }catch (AopInvocationException e){
            inventoryValue=0;
        }
        try {
            orderValue = orderRepository.findOrderValues(warehouseId);
            orderValue.forEach(value->{
                value.setMonthName(Month.of(value.getMonth().intValue()));
                value.setYearName(Year.of(value.getYear().intValue()));
            });
        }catch (AopInvocationException e){
            orderValue= Collections.emptyList();
        }

        Map stats = new HashMap<>();
        stats.put("orderValue",orderValue);
        stats.put("inventoryValue",inventoryValue);

        return stats;
    }
}
