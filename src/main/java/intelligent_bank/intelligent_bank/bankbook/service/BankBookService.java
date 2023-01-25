package intelligent_bank.intelligent_bank.bankbook.service;

import intelligent_bank.intelligent_bank.bankbook.dto.BankBookRequest;
import intelligent_bank.intelligent_bank.bankbook.dto.BankBookResponse;
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

    public BankBookResponse getBankBookByMember(Member member) {
        return BankBookMapper.entityToDtoDetail(
                bankBookRepository.findOneByMember(member)
        );
    }

    @Transactional
    public void saveBankBook(BankBookRequest bankBookRequest, Member member) {
        String bankBookNum = RandomStringUtils.randomNumeric(13);
        bankBookRequest.setBankBookNum(bankBookNum);
        bankBookRequest.setMember(member);
        bankBookRequest.setBankBookState(BankBookState.USE);

        bankBookRepository.save(
                BankBookMapper.dtoToEntity(bankBookRequest)
        );
    }
}
