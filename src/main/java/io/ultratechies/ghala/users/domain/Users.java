package io.ultratechies.ghala.users.domain;

import io.ultratechies.ghala.roles.domain.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
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
    private String email;
    private String phoneNumber;
    private String assignedWarehouse;
    private String password;
    @ManyToMany
    private List<Roles> roles;
}
