package io.ultratechies.ghala.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonType;
import io.ultratechies.ghala.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
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
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime createdDate;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date due;
    private String deliveryWindow;
    private String customerId;
    private String orderCode;
    private Integer value;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<OrderItemDTO> items;
    private String route;
    private Long warehouseId;

}
