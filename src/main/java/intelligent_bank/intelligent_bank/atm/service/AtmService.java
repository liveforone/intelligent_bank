package intelligent_bank.intelligent_bank.atm.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.record.dto.RecordRequest;
import intelligent_bank.intelligent_bank.record.model.RecordState;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import intelligent_bank.intelligent_bank.record.util.RecordMapper;
import intelligent_bank.intelligent_bank.record.util.RecordStatus;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmService {

    private final BankBookRepository bankBookRepository;
    private final RecordRepository recordRepository;

    @Transactional
    public void depositAtm(long inputMoney, BankBook requestBank) {
        String requestBankBookNum = requestBank.getBankBookNum();

        bankBookRepository.increaseBalance(
                requestBankBookNum,
                inputMoney
        );
        log.info("통장 번호 : " + requestBankBookNum + " ATM 입금 금액 : " + inputMoney);

        RecordRequest depositRequest = RecordRequest.builder()
                .title(RecordStatus.DEPOSIT_ATM.getValue())
                .money(inputMoney)
                .recordState(RecordState.INCOME)
                .bankBook(requestBank)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        recordRepository.save(
                RecordMapper.dtoToEntity(depositRequest)
        );
    }

    @Transactional
    public void withdrawAtm(long inputMoney, BankBook requestBank) {
        String requestBankBookNum = requestBank.getBankBookNum();

        bankBookRepository.decreaseBalance(
                requestBankBookNum,
                inputMoney
        );
        log.info("통장 번호 : " + requestBankBookNum + " ATM 출금 금액 : " + inputMoney);

        RecordRequest withdrawRequest = RecordRequest.builder()
                .title(RecordStatus.WITHDRAW_ATM.getValue())
                .money(-inputMoney)
                .recordState(RecordState.EXPENSE)
                .bankBook(requestBank)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        recordRepository.save(
                RecordMapper.dtoToEntity(withdrawRequest)
        );
    }
}
