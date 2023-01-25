package intelligent_bank.intelligent_bank.bankbook.service;

import intelligent_bank.intelligent_bank.bankbook.dto.BankBookRequest;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.model.BankBookState;
import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.bankbook.util.BankBookMapper;
import intelligent_bank.intelligent_bank.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankBookService {

    private final BankBookRepository bankBookRepository;

    public BankBook getBankBookByMember(Member member) {
        return bankBookRepository.findOneByMember(member);
    }

    public BankBook getBankBookByBankBookNum(String bankBookNum) {
        return bankBookRepository.findOneByBankBookNum(bankBookNum);
    }

    @Transactional
    public String saveBankBook(Member member) {
        String bankBookNum = RandomStringUtils.randomNumeric(13);

        BankBookRequest bankBookRequest = BankBookRequest.builder()
                .bankBookNum(bankBookNum)
                .bankBookState(BankBookState.USE)
                .member(member)
                .build();

        return bankBookRepository.save(
                BankBookMapper.dtoToEntity(bankBookRequest)
        ).getBankBookNum();
    }

    @Transactional
    public void suspendBankBookByMember(Member member) {
        bankBookRepository.suspendOneByMember(member);
    }

    @Transactional
    public void increaseBalance(String bankBookNum, long inputMoney) {
        bankBookRepository.increaseBalance(bankBookNum, inputMoney);
    }

    @Transactional
    public void decreaseBalance(String bankBookNum, long inputMoney) {
        bankBookRepository.decreaseBalance(bankBookNum, inputMoney);
    }
}
