package intelligent_bank.intelligent_bank.member.service;

import intelligent_bank.intelligent_bank.jwt.JwtTokenProvider;
import intelligent_bank.intelligent_bank.jwt.TokenInfo;
import intelligent_bank.intelligent_bank.member.dto.ChangeEmailRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberLoginRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberSignupRequest;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.model.Role;
import intelligent_bank.intelligent_bank.member.repository.MemberRepository;
import intelligent_bank.intelligent_bank.member.util.MemberMapper;
import intelligent_bank.intelligent_bank.member.validator.MemberPasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public Member getMemberEntity(String email) {
        return memberRepository.findByEmail(email);
    }

    /*
     * 모든 유저 반환
     * when : 권한이 어드민인 유저가 호출할때
     */
    public List<Member> getAllMemberForAdmin() {
        return memberRepository.findAll();
    }

    @Transactional
    public void signup(MemberSignupRequest memberSignupRequest) {
        memberSignupRequest.setPassword(
                MemberPasswordValidator.encodePassword(memberSignupRequest.getPassword())
        );

        if (Objects.equals(memberSignupRequest.getEmail(), "admin@intelligentBank.com")) {
            memberSignupRequest.setAuth(Role.ADMIN);
        } else {
            memberSignupRequest.setAuth(Role.MEMBER);
        }

        memberRepository.save(MemberMapper.dtoToEntity(memberSignupRequest));
    }

    @Transactional
    public TokenInfo login(MemberLoginRequest memberLoginRequest) {
        String email = memberLoginRequest.getEmail();
        String password = memberLoginRequest.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        return jwtTokenProvider
                .generateToken(authentication);
    }

    @Transactional
    public void updateEmail(String email, ChangeEmailRequest changeEmailRequest) {
        String newEmail = changeEmailRequest.getEmail();
        memberRepository.updateEmail(email, newEmail);
    }

    @Transactional
    public void updatePassword(Long id, String inputPassword) {
        String newPassword = MemberPasswordValidator.encodePassword(inputPassword);
        memberRepository.updatePassword(id, newPassword);
    }

    @Transactional
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }
}
