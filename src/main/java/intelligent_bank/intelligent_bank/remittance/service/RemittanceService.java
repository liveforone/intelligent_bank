package intelligent_bank.intelligent_bank.remittance.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.member.model.Member;
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
public class RemittanceService {

    private final BankBookRepository bankBookRepository;
    private final RecordRepository recordRepository;

    @Transactional
    public void remit(long inputMoney, BankBook receiverBank, Member sender) {
        BankBook senderBank = bankBookRepository.findOneByMember(sender);

        remitAndRecordForSender(senderBank, receiverBank, inputMoney);
        remitAndRecordForReceiver(senderBank, receiverBank, inputMoney);
    }

    private void remitAndRecordForSender(BankBook senderBank, BankBook receiverBank, long inputMoney) {
        String senderBankBankBookNum = senderBank.getBankBookNum();
        String receiverName = receiverBank.getMember().getRealName();

        bankBookRepository.decreaseBalance(
                senderBankBankBookNum,
                inputMoney
        );
        log.info("통장 번호 : " + senderBankBankBookNum + " 출금 금액 : " + inputMoney);

        RecordRequest senderRequest = RecordRequest.builder()
                .title(RecordStatus.WITHDRAW_REMIT.getValue() + receiverName)
                .money(-inputMoney)
                .recordState(RecordState.EXPENSE)
                .bankBook(senderBank)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        recordRepository.save(
                RecordMapper.dtoToEntity(senderRequest)
        );
    }

    private void remitAndRecordForReceiver(BankBook senderBank, BankBook receiverBank, long inputMoney) {
        String receiverBankBankBookNum = receiverBank.getBankBookNum();
        String senderName = senderBank.getMember().getRealName();

        bankBookRepository.increaseBalance(
                receiverBankBankBookNum,
                inputMoney
        );
        log.info("통장 번호 : " + receiverBankBankBookNum + " 입금 금액 : " + inputMoney);

        RecordRequest receiverRequest = RecordRequest.builder()
                .title(RecordStatus.DEPOSIT_REMIT.getValue() + senderName)
                .money(inputMoney)
                .recordState(RecordState.INCOME)
                .bankBook(receiverBank)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        recordRepository.save(
                RecordMapper.dtoToEntity(receiverRequest)
        );
    }
}
