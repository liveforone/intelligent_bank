package intelligent_bank.intelligent_bank.bankbook.dto;

import intelligent_bank.intelligent_bank.bankbook.model.BankBookState;
import intelligent_bank.intelligent_bank.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBookRequest {

    private Long id;
    private String bankBookNum;
    private long balance;
    private BankBookState bankBookState;
    private Member member;
}
