package io.ultratechies.ghala.orders.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
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
    private LocalDateTime created;
    private Date due;
    private String deliveryWindow;
    private String customerId;
    private Integer value;
    private String status;
    @OneToMany
    private List<OrderItemDTO> items;
    private String route;
    private Long warehouseId;

}
