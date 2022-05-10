package io.ultratechies.ghala.inventory.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
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
    private Long sku;
    private String name;
    private String category;
    private Integer quantity;
    private Integer ppu;
    private String status;
    private String skuCode;
    private Long warehouseId;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
}
