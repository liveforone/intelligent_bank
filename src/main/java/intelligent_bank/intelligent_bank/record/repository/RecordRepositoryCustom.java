package intelligent_bank.intelligent_bank.record.repository;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface RecordRepositoryCustom {
    Page<Record> findRecordsByMember(Member member, Pageable pageable);

    Long calculateThisMonthExpense(BankBook bankBook, int nowYear, Month nowMonth);

    Long calculateThisMonthIncome(BankBook bankBook, int nowYear, Month nowMonth);

    Long calculateThisYearExpense(BankBook bankBook, int nowYear);

    Long calculateThisYearIncome(BankBook bankBook, int nowYear);

    Long calculateTotalExpense(BankBook bankBook);

    Long calculateTotalIncome(BankBook bankBook);
}
