package intelligent_bank.intelligent_bank.remittance.controller;

import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.remittance.dto.RemittanceRequest;
import intelligent_bank.intelligent_bank.remittance.service.RemittanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class RemittanceController {

    private final RemittanceService remittanceService;
    private final MemberService memberService;

    @PostMapping("/remit")
    public ResponseEntity<?> remit(
            @RequestBody RemittanceRequest remittanceRequest,
            Principal principal
    ) {
        Member sender = memberService.getMemberEntity(principal.getName());
        remittanceService.remit(remittanceRequest, sender);

        return ResponseEntity.ok("송금 완료되었습니다.");
    }
}
