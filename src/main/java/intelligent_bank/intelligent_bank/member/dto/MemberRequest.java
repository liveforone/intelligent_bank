package intelligent_bank.intelligent_bank.member.dto;

import intelligent_bank.intelligent_bank.member.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequest {

    private Long id;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String realName;
    private Role auth;
}
