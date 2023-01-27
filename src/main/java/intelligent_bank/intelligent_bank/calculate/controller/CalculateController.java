package intelligent_bank.intelligent_bank.calculate.controller;

import intelligent_bank.intelligent_bank.calculate.dto.CalculateResponse;
import intelligent_bank.intelligent_bank.calculate.service.CalculateService;
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
public class CalculateController {

    private final CalculateService calculateService;
    private final MemberService memberService;

    @GetMapping("/my-calculate/month")
    public ResponseEntity<?> calculateThisMonth(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateThisMonth = calculateService.calculateThisMonth(member);
        return ResponseEntity.ok(calculateThisMonth);
    }

    @GetMapping("/my-calculate/year")
    public ResponseEntity<?> calculateThisYear(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateThisYear = calculateService.calculateThisYear(member);
        return ResponseEntity.ok(calculateThisYear);
    }

    @GetMapping("/my-calculate/total")
    public ResponseEntity<?> calculateTotal(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateTotal = calculateService.calculateTotal(member);
        return ResponseEntity.ok(calculateTotal);
    }
}
