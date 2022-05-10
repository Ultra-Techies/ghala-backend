package io.ultratechies.ghala.deliverynotes.repository;

import io.ultratechies.ghala.deliverynotes.domain.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}
