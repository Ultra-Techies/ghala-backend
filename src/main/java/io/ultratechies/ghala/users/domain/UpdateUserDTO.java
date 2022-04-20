package io.ultratechies.ghala.users.domain;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

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
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] profilePhoto;
}
