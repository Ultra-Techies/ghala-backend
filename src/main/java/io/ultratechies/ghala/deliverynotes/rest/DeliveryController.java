package io.ultratechies.ghala.deliverynotes.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.service.DeliveryNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveryNote")
public class DeliveryController {
    @Autowired
    private final DeliveryNoteService deliveryNoteService;

    @ApiOperation("Fetch a Delivery Note")
    @GetMapping("/{deliveryNoteId}")
    public Optional<DeliveryNote> getDeliveryNote(@ApiParam(name="delivery note id", required = true,example = "1")
                                                  @PathVariable Long deliveryNoteId){
        return deliveryNoteService.getDeliveryNoteById(deliveryNoteId);
    }

    @ApiOperation("Create Delivery Note")
    @PostMapping
    public Long createDeliveryNote(DeliveryNote deliveryNote){
        return deliveryNoteService.createDeliveryNote(deliveryNote);
    }

}
