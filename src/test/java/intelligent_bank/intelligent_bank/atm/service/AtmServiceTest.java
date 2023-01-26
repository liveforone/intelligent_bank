package intelligent_bank.intelligent_bank.atm.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.member.dto.MemberRequest;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AtmServiceTest {

    @Autowired
    AtmService atmService;

    @Autowired
    BankBookService bankBookService;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    String createBankBook(String email, String password) {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setEmail(email);
        memberRequest.setPassword(password);
        memberRequest.setRealName("test_member");
        memberService.signup(memberRequest);
        em.flush();
        em.clear();

        Member member = memberService.getMemberEntity(email);
        return bankBookService.saveBankBook(member);
    }

    @Test
    @Transactional
    void depositAtmTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String requestBankBookNum = createBankBook(email, password);
        em.flush();
        em.clear();

        //when
        long inputMoney = 10000L;
        BankBook requestBank = bankBookService.getBankBookByBankBookNum(requestBankBookNum);
        atmService.depositAtm(inputMoney, requestBank);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(requestBankBookNum).getBalance())
                .isEqualTo(inputMoney);
    }

    @Test
    @Transactional
    void withdrawAtmTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String requestBankBookNum = createBankBook(email, password);
        em.flush();
        em.clear();

        //when
        long inputMoney = 10000L;
        BankBook requestBank = bankBookService.getBankBookByBankBookNum(requestBankBookNum);
        atmService.withdrawAtm(inputMoney, requestBank);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(requestBankBookNum).getBalance())
                .isEqualTo(-inputMoney);
    }
}