package intelligent_bank.intelligent_bank.calculate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateResponse {

    private Long expense;
    private Long income;
}
