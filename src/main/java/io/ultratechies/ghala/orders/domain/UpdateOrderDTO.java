package io.ultratechies.ghala.orders.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
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
    private String status;
    private List<OrderItemDTO>items;
}
