package io.ultratechies.ghala.orders.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdateOrderDTO {
    private Long id;
    private Date due;
    private String deliveryWindow;
    private Integer value;
    private String status;
    private List<OrderItemDTO>items;
}
