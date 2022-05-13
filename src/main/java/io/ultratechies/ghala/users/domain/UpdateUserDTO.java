package io.ultratechies.ghala.users.domain;
import io.ultratechies.ghala.enums.RolesEnum;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateUserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private Integer assignedWarehouse;
    private String password;
    private String firstName;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    private String lastName;
    private ArrayList<Integer> profilePhoto;
}
