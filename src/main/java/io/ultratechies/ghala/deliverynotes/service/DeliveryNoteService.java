package io.ultratechies.ghala.deliverynotes.service;

import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import io.ultratechies.ghala.deliverynotes.repository.DeliveryNoteRepository;
import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeliveryNoteService {
    @Autowired
    private final DeliveryNoteRepository deliveryNoteRepository;

    @Transactional
    public Long createDeliveryNote(DeliveryNote deliveryNote){
        deliveryNote.setStatus(DeliveryNoteStatus.PENDING);
        DeliveryNote note =deliveryNoteRepository.save(deliveryNote);
        note.setNoteCode("GH"+ randomNo()+"DN"+note.getId());
        deliveryNoteRepository.save(note);
        return note.getId();
    }

    public Optional<DeliveryNote> getDeliveryNoteById(Long deliveyNoteId){
        return Optional.ofNullable(deliveryNoteRepository.findById(deliveyNoteId)
                .orElseThrow(() -> new IllegalArgumentException("Note with Requested Id does not exist!")));
    }

    public ResponseEntity changeNoteStatusToDispatched(DeliveryNote deliveryNote){
        deliveryNote.setStatus(DeliveryNoteStatus.DISPATCHED);
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity changeNoteStatusToDelivered(DeliveryNote deliveryNote){
        deliveryNote.setStatus(DeliveryNoteStatus.DELIVERED);
        deliveryNote.getOrders()
                .forEach(order ->{
                    order.setStatus(OrderStatus.DELIVERED);
                });
        deliveryNoteRepository.save(deliveryNote);
        return ResponseEntity.ok().build();
    }

    private String randomNo() {
        return new DecimalFormat("00")
                .format(new Random().nextInt(99));
    }
}
