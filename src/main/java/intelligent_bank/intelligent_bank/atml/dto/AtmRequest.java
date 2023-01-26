package intelligent_bank.intelligent_bank.atml.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtmRequest {

    private long inputMoney;
    private String bankBookNum;
    private String password;
}
