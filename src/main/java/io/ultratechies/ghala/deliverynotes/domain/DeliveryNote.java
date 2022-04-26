package io.ultratechies.ghala.deliverynotes.domain;

import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.orders.domain.Orders;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class DeliveryNote {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="users_sequence"
    )
    private Long id;
    private String Route;
    @OneToMany
    private List<Orders> orders;
    @Enumerated(EnumType.STRING)
    private DeliveryNoteStatus status;
    private String deliveryWindow;
    private Long warehouseId;
    private String noteCode;
}
