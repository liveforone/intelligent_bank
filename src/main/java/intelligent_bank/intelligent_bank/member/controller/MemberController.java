package intelligent_bank.intelligent_bank.member.controller;

import intelligent_bank.intelligent_bank.jwt.TokenInfo;
import intelligent_bank.intelligent_bank.member.dto.ChangeEmailRequest;
import intelligent_bank.intelligent_bank.member.dto.ChangePasswordRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberResponse;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.model.Role;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.member.util.MemberEmail;
import intelligent_bank.intelligent_bank.member.util.MemberMapper;
import intelligent_bank.intelligent_bank.member.util.MemberPassword;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("home");
    }

    @GetMapping("/member/signup")
    public ResponseEntity<?> signupPage() {
        return ResponseEntity.ok("가입할 이메일과 비밀번호 그리고 실명을 입력해주세요");
    }

    @PostMapping("/member/signup")
    public ResponseEntity<?> signup(@RequestBody MemberRequest memberRequest) {
        Member requestMember = memberService.getMemberEntity(memberRequest.getEmail());
        if (MemberEmail.isDuplicateEmail(requestMember)) {
            log.info("이메일이 중복됨.");
            return ResponseEntity.ok("중복되는 이메일이 있어 회원가입이 불가능합니다.");
        }

        memberService.signup(memberRequest);
        log.info("회원 가입 성공");

        return ResponseEntity.ok("반갑습니다. 회원가입에 성공하셨습니다.");
    }

    @GetMapping("/member/login")
    public ResponseEntity<?> loginPage() {
        return ResponseEntity.ok("이메일과 비밀번호를 입력하세요.");
    }

    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody MemberRequest memberRequest) {
        String requestEmail = memberRequest.getEmail();
        Member foundMember = memberService.getMemberEntity(requestEmail);

        if (CommonUtils.isNull(foundMember)) {
            log.info("잘못된 이메일.");
            return ResponseEntity.ok("회원 조회가 되지않아 로그인이 불가능합니다.");
        }

        String inputPw = memberRequest.getPassword();
        String originalPw = foundMember.getPassword();
        if (MemberPassword.isNotMatchingPassword(inputPw, originalPw)) {
            log.info("비밀번호가 일치하지 않음.");
            return ResponseEntity.ok("비밀번호가 다릅니다. 다시 시도하세요.");
        }

        TokenInfo tokenInfo = memberService.login(memberRequest);
        log.info("로그인 성공");

        return ResponseEntity.ok(tokenInfo);
    }

    @GetMapping("/member/my-page")
    public ResponseEntity<MemberResponse> myPage(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        return ResponseEntity.ok(MemberMapper.dtoBuilder(member));
    }

    @PatchMapping("/member/change-email")
    public ResponseEntity<?> changeEmail(
            @RequestBody ChangeEmailRequest changeEmailRequest,
            Principal principal
    ) {
        String requestEmail = changeEmailRequest.getEmail();
        Member fountMember = memberService.getMemberEntity(requestEmail);

        if (MemberEmail.isDuplicateEmail(fountMember)) {
            log.info("이메일이 중복됨.");
            return ResponseEntity
                    .ok("해당 이메일이 이미 존재합니다. 다시 입력해주세요");
        }

        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        String inputPw = changeEmailRequest.getPassword();
        String originalPw = member.getPassword();
        if (MemberPassword.isNotMatchingPassword(inputPw, originalPw)) {
            log.info("비밀번호 일치하지 않음.");
            return ResponseEntity.ok("비밀번호가 다릅니다. 다시 입력해주세요.");
        }

        memberService.updateEmail(email, requestEmail);
        log.info("이메일 변경 성공");

        return ResponseEntity.ok("이메일이 변경되었습니다.");
    }

    @PatchMapping("/member/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest,
            Principal principal
    ) {
        String email = principal.getName();
        Member foundMember = memberService.getMemberEntity(email);

        String inputPw = changePasswordRequest.getOldPassword();
        String originalPw = foundMember.getPassword();
        if (MemberPassword.isNotMatchingPassword(inputPw, originalPw)) {
            log.info("비밀번호 일치하지 않음.");
            return ResponseEntity.ok("비밀번호가 다릅니다. 다시 입력해주세요.");
        }

        Long memberId = foundMember.getId();
        String requestPw = changePasswordRequest.getNewPassword();
        memberService.updatePassword(memberId, requestPw);
        log.info("비밀번호 변경 성공");

        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }
    @DeleteMapping("/member/withdraw")
    public ResponseEntity<?> withdraw(
            @RequestBody String password,
            Principal principal
    ) {
        String email = principal.getName();
        Member foundMember = memberService.getMemberEntity(email);

        String originalPw = foundMember.getPassword();
        if (MemberPassword.isNotMatchingPassword(password, originalPw)) {
            log.info("비밀번호 일치하지 않음.");
            return ResponseEntity.ok("비밀번호가 다릅니다. 다시 입력해주세요.");
        }

        Long memberId = foundMember.getId();
        log.info("회원 : " + memberId + " 탈퇴 성공");
        memberService.deleteUser(memberId);

        return ResponseEntity.ok("그동안 서비스를 이용해주셔서 감사합니다.");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> adminPage(Principal principal) {
        String email = principal.getName();
        Member foundMember = memberService.getMemberEntity(email);

        if (!foundMember.getAuth().equals(Role.ADMIN)) {
            log.info("어드민 페이지 접속에 실패했습니다.");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Member> allMembers = memberService.getAllMemberForAdmin();
        log.info("어드민이 어드민 페이지에 접속했습니다.");

        return ResponseEntity.ok(allMembers);
    }

    @GetMapping("/member/prohibition")
    public ResponseEntity<?> prohibition() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("접근 권한이 없습니다.");
    }
}