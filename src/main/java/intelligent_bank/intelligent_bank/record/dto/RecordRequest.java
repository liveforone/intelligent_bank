package intelligent_bank.intelligent_bank.record.dto;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.record.model.RecordState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequest {

    private Long id;
    private String title;
    private long money;
    private RecordState recordState;
    private BankBook bankBook;
    private int createdYear;
    private Month createdMonth;
}
