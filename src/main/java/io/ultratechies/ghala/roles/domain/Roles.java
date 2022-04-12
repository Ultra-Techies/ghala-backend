package io.ultratechies.ghala.roles.domain;

import io.ultratechies.ghala.users.domain.Users;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    private String name;
    @ManyToMany
    private List<Users> users;
}
