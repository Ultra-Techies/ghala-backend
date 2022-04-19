package io.ultratechies.ghala.orders.domain;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderItemDTO implements Serializable {
    private Long sku;
    private Integer quantity;
    private String name;
    private Integer ppu;
    private Integer totalPrice;
}
