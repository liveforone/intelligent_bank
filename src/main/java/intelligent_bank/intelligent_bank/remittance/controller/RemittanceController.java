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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

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
            @RequestBody @Valid RemittanceRequest remittanceRequest,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity.ok(errorMessage);
        }

        BankBook requestBank = bankBookService.getBankBookByBankBookNum(
                remittanceRequest.getBankBookNum()
        );

        if (CommonUtils.isNull(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 송금이 불가능합니다.");
        }

        Member sender = memberService.getMemberEntity(principal.getName());
        String inputPassword = remittanceRequest.getPassword();
        if (MemberPasswordValidator.isNotMatchingPassword(inputPassword, sender.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        long inputMoney = remittanceRequest.getInputMoney();
        remittanceService.remit(inputMoney, requestBank, sender);
        log.info("송금 완료");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("송금 완료되었습니다.");
    }
}
