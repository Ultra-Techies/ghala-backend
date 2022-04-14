package io.ultratechies.ghala.inventory.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InventoryUpdateDTO {
    private Long sku;
    private String name;
    private String category;
    private Integer quantity;
    private Integer ppu;
    private String status;
}
