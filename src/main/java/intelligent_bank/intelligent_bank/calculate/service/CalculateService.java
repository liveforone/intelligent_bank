package intelligent_bank.intelligent_bank.calculate.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.calculate.dto.CalculateResponse;
import intelligent_bank.intelligent_bank.calculate.repository.CalculateRepository;
import intelligent_bank.intelligent_bank.calculate.util.CalculateMapper;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.dto.RecordRequest;
import intelligent_bank.intelligent_bank.record.model.RecordState;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import intelligent_bank.intelligent_bank.record.util.RecordMapper;
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
    private final CalculateRepository calculateRepository;

    public CalculateResponse calculateThisMonth(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();
        Month nowMonth = CommonUtils.createNowMonth();

        Long sumExpense = calculateRepository.calculateThisMonthExpense(
                bankBook,
                nowYear,
                nowMonth
        );
        Long sumIncome = calculateRepository.calculateThisMonthIncome(
                bankBook,
                nowYear,
                nowMonth
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateThisYear(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = calculateRepository.calculateThisYearExpense(
                bankBook,
                nowYear
        );
        Long sumIncome = calculateRepository.calculateThisYearIncome(
                bankBook,
                nowYear
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateTotal(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);

        Long sumExpense = calculateRepository.calculateTotalExpense(bankBook);
        Long sumIncome = calculateRepository.calculateTotalIncome(bankBook);

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    @Transactional
    public void addInterest(Member member) {
        BankBook bankBook = bankBookRepository.findOneByMember(member);
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = calculateRepository.calculateThisYearExpense(
                bankBook,
                nowYear
        );
        Long sumIncome = calculateRepository.calculateThisYearIncome(
                bankBook,
                nowYear
        );

        String bankBookNum = bankBook.getBankBookNum();
        long profit =  sumIncome + sumExpense;

        if (profit > 0) {
            long interest = (long) (profit * 0.01);
            bankBookRepository.increaseBalance(bankBookNum, interest);
            log.info("이자 정산 완료");

            RecordRequest depositRequest = RecordRequest.builder()
                    .title("[입금] 연 이자 지급")
                    .money(interest)
                    .recordState(RecordState.INCOME)
                    .bankBook(bankBook)
                    .createdYear(CommonUtils.createNowYear())
                    .createdMonth(CommonUtils.createNowMonth())
                    .build();
            recordRepository.save(
                    RecordMapper.dtoToEntity(depositRequest)
            );
        }
    }
}
