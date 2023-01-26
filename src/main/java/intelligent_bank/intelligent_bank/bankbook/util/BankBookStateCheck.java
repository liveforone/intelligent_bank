package intelligent_bank.intelligent_bank.bankbook.util;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.model.BankBookState;

public class BankBookStateCheck {

    public static boolean isSuspendBankBook(BankBook bankBook) {
        return bankBook.getBankBookState() == BankBookState.SUSPEND;
    }
}
