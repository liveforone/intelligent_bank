package intelligent_bank.intelligent_bank.remittance.controller;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.remittance.dto.RemittanceRequest;
import intelligent_bank.intelligent_bank.remittance.service.RemittanceService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class RemittanceController {

    private final BankBookService bankBookService;
    private final RemittanceService remittanceService;
    private final MemberService memberService;

    @PostMapping("/remit")
    public ResponseEntity<?> remit(
            @RequestBody RemittanceRequest remittanceRequest,
            Principal principal
    ) {
        String requestBankBookNum = remittanceRequest.getBankBookNum();
        BankBook requestBank = bankBookService.getBankBookByBankBookNum(requestBankBookNum);

        if (CommonUtils.isNull(requestBank)) {
            return ResponseEntity.ok("존재하지 않는 통장 번호입니다.");
        }

        Member sender = memberService.getMemberEntity(principal.getName());
        remittanceService.remit(remittanceRequest, requestBank, sender);

        return ResponseEntity.ok("송금 완료되었습니다.");
    }
}
