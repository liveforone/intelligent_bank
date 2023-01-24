package intelligent_bank.intelligent_bank.member.dto;

import intelligent_bank.intelligent_bank.member.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequest {

    private Long id;
    private String email;
    private String password;
    private String realName;
    private Role auth;
}
