package intelligent_bank.intelligent_bank.atm.controller;

import intelligent_bank.intelligent_bank.aop.stopwatch.LogExecutionTime;
import intelligent_bank.intelligent_bank.atm.dto.AtmRequest;
import intelligent_bank.intelligent_bank.atm.service.AtmService;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.bankbook.util.BankBookStateCheck;
import intelligent_bank.intelligent_bank.member.validator.MemberPasswordValidator;
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

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AtmController {

    private final BankBookService bankBookService;
    private final AtmService atmService;

    @PostMapping("/atm/deposit")
    @LogExecutionTime
    public ResponseEntity<?> depositAtm(
            @RequestBody @Valid AtmRequest atmRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity.ok(errorMessage);
        }

        BankBook requestBank = bankBookService.getBankBookByBankBookNum(
                atmRequest.getBankBookNum()
        );

        if (CommonUtils.isNull(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 입금이 불가능합니다.");
        }

        String originalPassword = requestBank.getMember().getPassword();
        String inputPassword = atmRequest.getPassword();
        if (MemberPasswordValidator.isNotMatchingPassword(inputPassword, originalPassword)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        long inputMoney = atmRequest.getInputMoney();
        atmService.depositAtm(inputMoney, requestBank);
        log.info("ATM 입금 성공");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 입금에 성공하셨습니다");
    }

    @PostMapping("/atm/withdraw")
    @LogExecutionTime
    public ResponseEntity<?> withdrawAtm(
            @RequestBody @Valid AtmRequest atmRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity.ok(errorMessage);
        }

        BankBook requestBank = bankBookService.getBankBookByBankBookNum(
                atmRequest.getBankBookNum()
        );

        if (CommonUtils.isNull(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(requestBank)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 출금이 불가능합니다.");
        }

        String originalPassword = requestBank.getMember().getPassword();
        String inputPassword = atmRequest.getPassword();
        if (MemberPasswordValidator.isNotMatchingPassword(inputPassword, originalPassword)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        long inputMoney = atmRequest.getInputMoney();
        atmService.withdrawAtm(inputMoney, requestBank);
        log.info("ATM 출금 성공");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 출금에 성공하셨습니다");
    }
}
