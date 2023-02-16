package intelligent_bank.intelligent_bank.calculate.controller;

import intelligent_bank.intelligent_bank.aop.stopwatch.LogExecutionTime;
import intelligent_bank.intelligent_bank.calculate.dto.CalculateResponse;
import intelligent_bank.intelligent_bank.calculate.service.CalculateService;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.Month;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalculateController {

    private final CalculateService calculateService;
    private final MemberService memberService;

    @GetMapping("/my-calculate/month")
    @LogExecutionTime
    public ResponseEntity<?> calculateThisMonth(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateThisMonth = calculateService.calculateThisMonth(member);
        return ResponseEntity.ok(calculateThisMonth);
    }

    @GetMapping("/my-calculate/year")
    @LogExecutionTime
    public ResponseEntity<?> calculateThisYear(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateThisYear = calculateService.calculateThisYear(member);
        return ResponseEntity.ok(calculateThisYear);
    }

    @GetMapping("/my-calculate/total")
    @LogExecutionTime
    public ResponseEntity<?> calculateTotal(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        CalculateResponse calculateTotal = calculateService.calculateTotal(member);
        return ResponseEntity.ok(calculateTotal);
    }

    @PostMapping("/calculate/interest")
    public ResponseEntity<?> calculateInterest(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);

        if (!CommonUtils.createNowMonth().equals(Month.DECEMBER)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("이자 신청은 12월에만 가능합니다.");
        }

        calculateService.addInterest(member);
        log.info("이자 지금 완료");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("이자 지급이 완료되었습니다.");
    }
}
