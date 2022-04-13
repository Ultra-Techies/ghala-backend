package io.ultratechies.ghala.orders.domain;

import lombok.*;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCreation;
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
    private Date created;
    private Date due;
    private String deliveryWindow;
    private String customerId;
    private Integer value;
    private String status;
    private List<OrderItemDTO> items;
    private String route;

}
