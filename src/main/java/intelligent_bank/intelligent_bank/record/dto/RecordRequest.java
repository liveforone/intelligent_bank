package intelligent_bank.intelligent_bank.record.dto;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequest {

    private Long id;
    private String title;
    private long money;
    private BankBook bankBook;
}
