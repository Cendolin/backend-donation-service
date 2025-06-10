package id.hanifu.cendolin_donation_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Username cannot blank")
    private String username;

    @NotBlank(message = "Email cannot blank")
    @Email(message = "Email should valid")
    private String email;

    @NotBlank(message = "UserID cannot blank")
    private String userId;

    @NotBlank(message = "CreatedAt cannot blank")
    private String createdAt;

    @NotBlank(message = "UpdatedAt cannot blank")
    private String updatedAt;
}
