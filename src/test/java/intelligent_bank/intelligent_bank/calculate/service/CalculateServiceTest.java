package intelligent_bank.intelligent_bank.calculate.service;

import intelligent_bank.intelligent_bank.atm.service.AtmService;
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
class CalculateServiceTest {

    @Autowired
    CalculateService calculateService;

    @Autowired
    BankBookService bankBookService;

    @Autowired
    MemberService memberService;

    @Autowired
    AtmService atmService;

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
    void addInterestTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String bankBookNum = createBankBook(email, password);
        em.flush();
        em.clear();

        long depositMoney = 50000L;
        long withdrawMoney = 20000L;
        BankBook bankBook = bankBookService.getBankBookByBankBookNum(bankBookNum);
        atmService.depositAtm(depositMoney, bankBook);
        atmService.depositAtm(depositMoney, bankBook);
        atmService.depositAtm(depositMoney, bankBook);
        atmService.depositAtm(depositMoney, bankBook);
        atmService.withdrawAtm(withdrawMoney, bankBook);
        em.flush();
        em.clear();

        //when
        Member member = memberService.getMemberEntity(email);
        calculateService.addInterest(member);
        em.flush();
        em.clear();

        //then
        long resultBalance = 181800L;
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBalance())
                .isEqualTo(resultBalance);
    }
}