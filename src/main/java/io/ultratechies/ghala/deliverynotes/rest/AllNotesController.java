package io.ultratechies.ghala.deliverynotes.rest;

import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.service.DeliveryNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/allnotes")
@RequiredArgsConstructor
public class AllNotesController {
    private final DeliveryNoteService deliveryNoteService;

    @GetMapping
    public List<DeliveryNote> getAllNotes(){
        return deliveryNoteService.getAllNotes();
    }
}
