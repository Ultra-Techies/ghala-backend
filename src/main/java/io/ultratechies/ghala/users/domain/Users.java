package io.ultratechies.ghala.users.domain;

import io.ultratechies.ghala.enums.RolesEnum;
import io.ultratechies.ghala.roles.domain.Roles;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
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
    private Integer assignedWarehouse;
    private String password;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    private String firstName;
    private String lastName;
    private ArrayList<Integer> profilePhoto;

}
