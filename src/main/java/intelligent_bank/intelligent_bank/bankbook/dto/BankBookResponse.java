package intelligent_bank.intelligent_bank.bankbook.dto;

import intelligent_bank.intelligent_bank.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBookResponse {

    private Long id;
    private String bankBookNum;
    private long balance;
    private String member;
    private LocalDate createdDate;
}
