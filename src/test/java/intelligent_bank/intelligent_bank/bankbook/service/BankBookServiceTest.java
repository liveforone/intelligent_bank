package intelligent_bank.intelligent_bank.bankbook.service;

import intelligent_bank.intelligent_bank.bankbook.model.BankBookState;
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
class BankBookServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    BankBookService bankBookService;

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
    void suspendBankBookByMemberTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String bankBookNum = createBankBook(email, password);

        //when
        Member member = memberService.getMemberEntity(email);
        bankBookService.suspendBankBookByMember(member);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBankBookState())
                .isEqualTo(BankBookState.SUSPEND);
    }

    @Test
    @Transactional
    void increaseBalanceTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String bankBookNum = createBankBook(email, password);

        //when
        long inputMoney = 100000L;
        bankBookService.increaseBalance(bankBookNum, inputMoney);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBalance())
                .isEqualTo(inputMoney);
    }

    @Test
    @Transactional
    void decreaseBalanceTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        String bankBookNum = createBankBook(email, password);

        //when
        long balance = 100000L;
        bankBookService.increaseBalance(bankBookNum, balance);
        em.flush();
        em.clear();
        long inputMoney = 50000L;
        bankBookService.decreaseBalance(bankBookNum, inputMoney);
        em.flush();
        em.clear();

        //then
        long resultBalance = balance - inputMoney;
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBalance())
                .isEqualTo(resultBalance);
    }
}