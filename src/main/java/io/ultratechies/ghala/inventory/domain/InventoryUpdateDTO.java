package io.ultratechies.ghala.inventory.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@Getter
@Setter
@RequiredArgsConstructor
public class InventoryUpdateDTO {
    private Long sku;
    private String name;
    private String category;
    private Integer quantity;
    private Integer ppu;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
}
