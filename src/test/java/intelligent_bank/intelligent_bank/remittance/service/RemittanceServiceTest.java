package intelligent_bank.intelligent_bank.remittance.service;

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
class RemittanceServiceTest {

    @Autowired
    RemittanceService remittanceService;

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
    void remitTest() {
        //given
        String senderEmail = "aa1111@gmail.com";
        String senderPassword = "1111";
        createBankBook(senderEmail, senderPassword);

        String receiverEmail = "aa1234@gmail.com";
        String receiverPassword = "1111";
        String receiverBankBookNum = createBankBook(receiverEmail, receiverPassword);
        em.flush();
        em.clear();

        //when
        long inputMoney = 10000L;
        BankBook receiverBank = bankBookService.getBankBookByBankBookNum(receiverBankBookNum);
        Member sender = memberService.getMemberEntity(senderEmail);
        remittanceService.remit(inputMoney, receiverBank, sender);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(receiverBankBookNum).getBalance())
                .isEqualTo(inputMoney);
    }
}