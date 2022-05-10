package io.ultratechies.ghala.statistics.rest;

import io.ultratechies.ghala.statistics.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("api/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/{warehouseId}")
    public Map getStats(@PathVariable Long warehouseId){
        return statsService.findTotalOrderAndInventoryValue(warehouseId);
    }
}
