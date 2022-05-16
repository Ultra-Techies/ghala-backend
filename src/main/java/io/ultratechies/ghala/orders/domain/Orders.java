package io.ultratechies.ghala.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonType;
import io.ultratechies.ghala.enums.OrderStatus;
import io.ultratechies.ghala.statistics.domain.OrderStats;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
@NamedNativeQuery(name = "Orders.findOrderValues",
        query ="SELECT SUM(o.value) AS sum ,DATE_PART('month',o.created_date) AS month," +
                " DATE_PART('year',created_date) AS year "+
                " FROM orders AS o WHERE o.warehouse_id=:warehouseId " +
                "GROUP BY DATE_PART('month',o.created_date), DATE_PART('year',o.created_date)",resultSetMapping = "Mapping.OrderStats")

@SqlResultSetMapping(name="Mapping.OrderStats",
        classes = @ConstructorResult(targetClass = OrderStats.class,
                columns = { @ColumnResult(name="sum",type = BigInteger.class),
                        @ColumnResult(name = "month", type =Double.class),
                        @ColumnResult(name ="year", type = Double.class)}
        ))
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
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
    private Long id;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate createdDate;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime createdTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date due;
    private String deliveryWindow;
    private String customerName;
    private String orderCode;
    private Integer value;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<OrderItemDTO> items;
    private String route;
    private Long warehouseId;

}
