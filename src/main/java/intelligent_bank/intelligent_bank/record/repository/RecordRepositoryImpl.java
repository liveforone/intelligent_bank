package intelligent_bank.intelligent_bank.record.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.model.QBankBook;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.model.QRecord;
import intelligent_bank.intelligent_bank.record.model.Record;
import intelligent_bank.intelligent_bank.record.model.RecordState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Record> findRecordsByMember(Member member, Pageable pageable) {
        QRecord record = QRecord.record;
        QBankBook bankBook = QBankBook.bankBook;

        List<Record> content = queryFactory.selectFrom(record)
                .join(record.bankBook, bankBook)
                .where(bankBook.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(record.id.desc())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

    public Long calculateThisMonthExpense(BankBook bankBook, int nowYear, Month nowMonth) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.EXPENSE),
                        record.createdYear.eq(nowYear),
                        record.createdMonth.eq(nowMonth)
                )
                .fetchOne();
    }

    public Long calculateThisMonthIncome(BankBook bankBook, int nowYear, Month nowMonth) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.INCOME),
                        record.createdYear.eq(nowYear),
                        record.createdMonth.eq(nowMonth)
                )
                .fetchOne();
    }

    public Long calculateThisYearExpense(BankBook bankBook, int nowYear) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.EXPENSE),
                        record.createdYear.eq(nowYear)
                )
                .fetchOne();
    }

    public Long calculateThisYearIncome(BankBook bankBook, int nowYear) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.INCOME),
                        record.createdYear.eq(nowYear)
                )
                .fetchOne();
    }

    public Long calculateTotalExpense(BankBook bankBook) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.EXPENSE)
                )
                .fetchOne();
    }

    public Long calculateTotalIncome(BankBook bankBook) {
        QRecord record = QRecord.record;

        return queryFactory.select(record.money.sum())
                .from(record)
                .join(record.bankBook)
                .where(
                        record.bankBook.eq(bankBook),
                        record.recordState.eq(RecordState.INCOME)
                )
                .fetchOne();
    }
}
