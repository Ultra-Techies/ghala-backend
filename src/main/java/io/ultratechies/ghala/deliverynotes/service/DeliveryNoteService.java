package io.ultratechies.ghala.deliverynotes.service;

import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.repository.DeliveryNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryNoteService {
    @Autowired
    private final DeliveryNoteRepository deliveryNoteRepository;

    public ResponseEntity createDeliveryNote(DeliveryNote deliveryNote){
        deliveryNote.setStatus("CREATED");
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }

    public Optional<DeliveryNote> getDeliveryNoteById(Long deliveyNoteId){
        return deliveryNoteRepository.findById(deliveyNoteId);
    }

    public ResponseEntity changeNoteStatusToDispatched(DeliveryNote deliveryNote){
        deliveryNote.setStatus("DISPATCHED");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity changeNoteStatusToDelivered(DeliveryNote deliveryNote){
        deliveryNote.setStatus("DELIVERED");
        deliveryNote.getOrders()
                .forEach(order ->{
                    order.setStatus("DELIVERED");
                });
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }
}
