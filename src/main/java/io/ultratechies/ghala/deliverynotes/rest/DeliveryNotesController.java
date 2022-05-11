package io.ultratechies.ghala.deliverynotes.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.deliverynotes.domain.CreateNoteDTO;
import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.service.DeliveryNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliverynotes")
public class DeliveryNotesController {
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
    public Map createDeliveryNote(@RequestBody CreateNoteDTO createNoteDTO){
        return deliveryNoteService.createDeliveryNote(createNoteDTO);
    }

    @ApiOperation("Change Note Status")
    @PutMapping("/{noteId}/{operation}")
    public DeliveryNote changeNoteStatus(@PathVariable  Long noteId, @PathVariable Integer operation){
        if (operation == 1){
            return deliveryNoteService.changeStatusToDispatched(noteId);
        }else if (operation==0){
            return deliveryNoteService.changeStatusToProcessing(noteId);
        }else throw new IllegalArgumentException("Unknown operation"+operation);
    }
    @GetMapping("/all")
    public List<DeliveryNote> getAllNotes()
    {
        return deliveryNoteService.getAllNotes();
    }


}
