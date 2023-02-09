package intelligent_bank.intelligent_bank.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeEmailRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
