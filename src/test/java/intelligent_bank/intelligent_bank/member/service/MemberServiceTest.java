package intelligent_bank.intelligent_bank.member.service;

import intelligent_bank.intelligent_bank.member.dto.MemberSignupRequest;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.model.Role;
import intelligent_bank.intelligent_bank.member.validator.MemberPasswordValidator;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    void createMember(String email, String password) {
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName("test_member");
        memberService.signup(memberSignupRequest);
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void adminSignupTest() {
        //given
        String email = "admin@intelligentBank.com";
        String password = "1111";
        String realName = "test_admin";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);

        //when
        memberService.signup(memberSignupRequest);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(memberService.getMemberEntity(email).getAuth())
                .isEqualTo(Role.ADMIN);
    }

    @Test
    @Transactional
    void updatePasswordTest() {
        //given
        String email = "aa1111@gmail.com";
        String password = "1111";
        createMember(email, password);

        //when
        String newPassword = "1234";
        Member member = memberService.getMemberEntity(email);
        Long memberId = member.getId();
        memberService.updatePassword(memberId, newPassword);
        em.flush();
        em.clear();

        //then
        String memberPw = memberService.getMemberEntity(email).getPassword();
        boolean notMatchingPassword = MemberPasswordValidator.isNotMatchingPassword(newPassword, memberPw);
        Assertions
                .assertThat(notMatchingPassword)
                .isFalse();
    }
}