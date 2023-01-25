package intelligent_bank.intelligent_bank.member.service;

import intelligent_bank.intelligent_bank.jwt.JwtTokenProvider;
import intelligent_bank.intelligent_bank.jwt.TokenInfo;
import intelligent_bank.intelligent_bank.member.dto.MemberRequest;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.model.Role;
import intelligent_bank.intelligent_bank.member.repository.MemberRepository;
import intelligent_bank.intelligent_bank.member.util.MemberMapper;
import intelligent_bank.intelligent_bank.member.util.MemberPassword;
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
    public void signup(MemberRequest memberRequest) {
        memberRequest.setPassword(
                MemberPassword.encodePassword(memberRequest.getPassword())
        );

        if (Objects.equals(memberRequest.getEmail(), "admin@intelligentBank.com")) {
            memberRequest.setAuth(Role.ADMIN);
        } else {
            memberRequest.setAuth(Role.MEMBER);
        }

        memberRepository.save(MemberMapper.dtoToEntity(memberRequest));
    }

    @Transactional
    public TokenInfo login(MemberRequest memberRequest) {
        String email = memberRequest.getEmail();
        String password = memberRequest.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        return jwtTokenProvider
                .generateToken(authentication);
    }

    @Transactional
    public void updateEmail(String oldEmail, String newEmail) {
        memberRepository.updateEmail(oldEmail, newEmail);
    }

    @Transactional
    public void updatePassword(Long id, String inputPassword) {
        String newPassword = MemberPassword.encodePassword(inputPassword);
        memberRepository.updatePassword(id, newPassword);
    }

    @Transactional
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }
}
