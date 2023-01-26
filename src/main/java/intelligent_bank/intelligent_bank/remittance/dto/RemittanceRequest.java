package intelligent_bank.intelligent_bank.remittance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RemittanceRequest {

    private long inputMoney;
    private String bankBookNum;
    private String password;
}
