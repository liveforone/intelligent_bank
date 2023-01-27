package intelligent_bank.intelligent_bank.record.dto;

import intelligent_bank.intelligent_bank.record.model.RecordState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {

    private Long id;
    private String title;
    private long money;
    private RecordState recordState;
    private LocalDateTime createdDate;
}
