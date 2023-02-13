package intelligent_bank.intelligent_bank.calculate.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.record.model.QRecord;
import intelligent_bank.intelligent_bank.record.model.RecordState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Month;

@Repository
@RequiredArgsConstructor
public class CalculateRepository {

    private final JPAQueryFactory queryFactory;

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
