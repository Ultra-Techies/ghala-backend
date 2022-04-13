package io.ultratechies.ghala.users.domain;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdateUserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String assignedWarehouse;
    private String password;
}