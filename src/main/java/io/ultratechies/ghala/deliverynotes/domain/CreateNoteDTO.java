package io.ultratechies.ghala.deliverynotes.domain;

import io.ultratechies.ghala.enums.DeliveryNoteStatus;
import io.ultratechies.ghala.orders.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteDTO {
    private String route;
    private List<Long>orderIds;
    private String deliveryWindow;
    private Long warehouseId;
}
