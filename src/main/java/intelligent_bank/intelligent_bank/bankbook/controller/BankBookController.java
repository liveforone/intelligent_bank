package intelligent_bank.intelligent_bank.bankbook.controller;

import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankBookController {

    private final MemberService memberService;

    @GetMapping("/my-bank")
    public ResponseEntity<?> myBank(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);
        //통장 찾기
        //통장 없다면 통장 만들라고 말하기
        //통장이 있다면 통장 리턴 + 멤버이름까지
    }
}
