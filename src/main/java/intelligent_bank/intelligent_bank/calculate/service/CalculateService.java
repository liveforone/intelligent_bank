package intelligent_bank.intelligent_bank.calculate.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.calculate.dto.CalculateResponse;
import intelligent_bank.intelligent_bank.calculate.util.CalculateMapper;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CalculateService {

    private final BankBookRepository bankBookRepository;
    private final RecordRepository recordRepository;

    public CalculateResponse calculateThisMonth(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();
        Month nowMonth = CommonUtils.createNowMonth();

        Long sumExpense = recordRepository.calculateThisMonthExpense(
                bankBook,
                nowYear,
                nowMonth
        );
        Long sumIncome = recordRepository.calculateThisMonthIncome(
                bankBook,
                nowYear,
                nowMonth
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateThisYear(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = recordRepository.calculateThisYearExpense(
                bankBook,
                nowYear
        );
        Long sumIncome = recordRepository.calculateThisYearIncome(
                bankBook,
                nowYear
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateTotal(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);

        Long sumExpense = recordRepository.calculateTotalExpense(bankBook);
        Long sumIncome = recordRepository.calculateTotalIncome(bankBook);

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    @Transactional
    public void addInterest(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = recordRepository.calculateThisYearExpense(
                bankBook,
                nowYear
        );
        Long sumIncome = recordRepository.calculateThisYearIncome(
                bankBook,
                nowYear
        );

        String bankBookNum = bankBook.getBankBookNum();
        long profit =  sumIncome - sumExpense;

        if (profit > 0) {
            long interest = (long) (profit * 0.1);
            bankBookRepository.increaseBalance(bankBookNum, interest);
        }
    }
}
