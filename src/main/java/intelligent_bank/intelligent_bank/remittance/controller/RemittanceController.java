package intelligent_bank.intelligent_bank.remittance.controller;

import intelligent_bank.intelligent_bank.aop.stopwatch.LogExecutionTime;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.bankbook.util.BankBookStateCheck;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.member.validator.MemberPasswordValidator;
import intelligent_bank.intelligent_bank.remittance.dto.RemittanceRequest;
import intelligent_bank.intelligent_bank.remittance.service.RemittanceService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RemittanceController {

    private final BankBookService bankBookService;
    private final RemittanceService remittanceService;
    private final MemberService memberService;

    @PostMapping("/remit")
    @LogExecutionTime
    public ResponseEntity<?> remit(
            @RequestBody RemittanceRequest remittanceRequest,
            Principal principal
    ) {
        String requestBankBookNum = remittanceRequest.getBankBookNum();
        BankBook requestBank = bankBookService.getBankBookByBankBookNum(requestBankBookNum);

        if (CommonUtils.isNull(requestBank)) {
            return ResponseEntity.ok("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(requestBank)) {
            return ResponseEntity.ok("정지된 통장입니다.\n정지된 통장으로는 송금이 불가능합니다.");
        }

        Member sender = memberService.getMemberEntity(principal.getName());
        String inputPassword = remittanceRequest.getPassword();
        if (MemberPasswordValidator.isNotMatchingPassword(inputPassword, sender.getPassword())) {
            return ResponseEntity.ok("비밀번호가 일치하지 않습니다.");
        }

        long inputMoney = remittanceRequest.getInputMoney();
        remittanceService.remit(inputMoney, requestBank, sender);
        log.info("송금 완료");

        return ResponseEntity.ok("송금 완료되었습니다.");
    }
}
