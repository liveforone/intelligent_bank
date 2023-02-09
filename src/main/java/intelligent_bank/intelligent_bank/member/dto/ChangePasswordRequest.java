package intelligent_bank.intelligent_bank.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangePasswordRequest {

    @NotBlank(message = "현재 비밀번호를 반드시 입력하세요.")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호를 반드시 입력하세요.")
    private String newPassword;
}
