package intelligent_bank.intelligent_bank.member.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeEmailRequest {

    private String email;
    private String password;
}
