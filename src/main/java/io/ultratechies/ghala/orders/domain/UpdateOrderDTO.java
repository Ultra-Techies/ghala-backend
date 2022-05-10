package io.ultratechies.ghala.orders.domain;

import io.ultratechies.ghala.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateOrderDTO {
    private Long id;
    private Date due;
    private String deliveryWindow;
    private Integer value;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private List<OrderItemDTO>items;
}
