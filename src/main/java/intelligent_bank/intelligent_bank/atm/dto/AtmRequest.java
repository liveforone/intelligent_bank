package intelligent_bank.intelligent_bank.atm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AtmRequest {

    @NotNull(message = "금액을 입력하세요.")
    private long inputMoney;

    @Size(min = 13, max = 13, message = "계좌번호는 13자리입니다. 정확히 입력해주세요.")
    private String bankBookNum;

    @NotBlank(message = "비밀번호를 정확히 입력해주세요.")
    private String password;
}
