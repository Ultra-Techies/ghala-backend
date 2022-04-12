package io.ultratechies.ghala.warehouse.domain;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class UpdateWarehouseDTO {
    private Long id;
    private String name;
}
