package intelligent_bank.intelligent_bank.calculate.util;

import intelligent_bank.intelligent_bank.calculate.dto.CalculateResponse;

public class CalculateMapper {

    public static CalculateResponse dtoBuilder(Long sumExpense, Long sumIncome) {
        return CalculateResponse.builder()
                .expense((sumExpense == null) ? 0 : sumExpense)
                .income((sumIncome == null) ? 0 : sumIncome)
                .build();
    }
}
