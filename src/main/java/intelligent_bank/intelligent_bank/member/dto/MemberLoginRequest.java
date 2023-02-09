package intelligent_bank.intelligent_bank.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginRequest {

    @Email(message = "이메일 형식을 지켜 작성해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 반드시 입력하세요.")
    private String password;
}
