package io.ultratechies.ghala.users.domain;

import io.ultratechies.ghala.enums.RolesEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private Integer assignedWarehouse;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    private String firstName;
    private String lastName;
    private ArrayList<Integer> profilePhoto;

}
