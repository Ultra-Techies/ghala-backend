package io.ultratechies.ghala.users.domain;
import lombok.*;
import org.hibernate.annotations.Type;

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
    private String lastName;
    private ArrayList<Integer> profilePhoto;
}
