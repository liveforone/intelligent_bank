package intelligent_bank.intelligent_bank.atml.service;

import intelligent_bank.intelligent_bank.atml.dto.AtmRequest;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.record.dto.RecordRequest;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import intelligent_bank.intelligent_bank.record.util.RecordMapper;
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
    public void depositAtm(AtmRequest atmRequest, BankBook requestBank) {
        long inputMoney = atmRequest.getInputMoney();
        String requestBankBookNum = requestBank.getBankBookNum();

        bankBookRepository.increaseBalance(
                requestBankBookNum,
                inputMoney
        );
        log.info("통장 번호 : " + requestBankBookNum + " ATM 입금 금액 : " + inputMoney);

        RecordRequest depositRequest = RecordRequest.builder()
                .title("[입금] ATM")
                .money(inputMoney)
                .bankBook(requestBank)
                .build();
        recordRepository.save(
                RecordMapper.dtoToEntity(depositRequest)
        );
    }
}
