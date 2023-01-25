package intelligent_bank.intelligent_bank.bankbook.repository;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.member.model.Member;

public interface BankBookRepositoryCustom {
    BankBook findOneByMember(Member member);

    BankBook findOneByBankBookNum(String bankBookNum);

    void suspendOneByMember(Member member);

    void increaseBalance(String bankBookNum, long inputMoney);

    void decreaseBalance(String bankBookNum, long inputMoney);
}
