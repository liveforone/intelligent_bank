package intelligent_bank.intelligent_bank.bankbook.repository;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.member.model.Member;

public interface BankBookRepositoryCustom {
    BankBook findOneByMember(Member member);

    void suspendOneByMember(Member member);
}
