package intelligent_bank.intelligent_bank.member.dto;

import intelligent_bank.intelligent_bank.member.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSignupRequest {

    private Long id;

    @Email(message = "이메일 형식을 지켜 작성해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 반드시 입력하세요.")
    private String password;

    @NotBlank(message = "실명은 반드시 기입해야하며, 잘못된 실명기입은 소비자가 책임집니다.")
    private String realName;
    private Role auth;
}
