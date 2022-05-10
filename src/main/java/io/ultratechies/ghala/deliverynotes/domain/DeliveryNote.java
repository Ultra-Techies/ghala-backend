package io.ultratechies.ghala.deliverynotes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.orders.domain.Orders;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String route;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Orders> orders;
    @Enumerated(EnumType.STRING)
    private DeliveryNoteStatus status;
    private String deliveryWindow;
    private Long warehouseId;
    private String noteCode;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime createdTime;
}
