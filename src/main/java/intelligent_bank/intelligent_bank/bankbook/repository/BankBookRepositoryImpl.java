package intelligent_bank.intelligent_bank.bankbook.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.model.QBankBook;
import intelligent_bank.intelligent_bank.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankBookRepositoryImpl implements BankBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BankBook findOneByMember(Member member) {
        QBankBook bankBook = QBankBook.bankBook;

        return queryFactory.selectFrom(bankBook)
                .join(bankBook.member).fetchJoin()
                .where(bankBook.member.eq(member))
                .fetchOne();
    }
}
