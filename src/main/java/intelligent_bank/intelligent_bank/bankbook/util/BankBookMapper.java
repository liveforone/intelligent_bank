package intelligent_bank.intelligent_bank.bankbook.util;

import intelligent_bank.intelligent_bank.bankbook.dto.BankBookRequest;
import intelligent_bank.intelligent_bank.bankbook.dto.BankBookResponse;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;

public class BankBookMapper {

    public static BankBook dtoToEntity(BankBookRequest bankBookRequest) {
        return BankBook.builder()
                .id(bankBookRequest.getId())
                .bankBookNum(bankBookRequest.getBankBookNum())
                .balance(bankBookRequest.getBalance())
                .bankBookState(bankBookRequest.getBankBookState())
                .member(bankBookRequest.getMember())
                .build();
    }

    public static BankBookResponse entityToDtoDetail(BankBook bankBook) {
        return BankBookResponse.builder()
                .id(bankBook.getId())
                .bankBookNum(bankBook.getBankBookNum())
                .balance(bankBook.getBalance())
                .bankBookState(bankBook.getBankBookState())
                .member(bankBook.getMember().getRealName())
                .createdDate(bankBook.getCreatedDate())
                .build();
    }
}
