package io.ultratechies.ghala.orders.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderItemDTO {
    private Long sku;
    private Integer quantity;
    private String name;
    private Integer ppu;
    private Integer totalPrice;
}
