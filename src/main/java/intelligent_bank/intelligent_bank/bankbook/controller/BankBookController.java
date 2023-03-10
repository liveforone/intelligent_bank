package intelligent_bank.intelligent_bank.bankbook.controller;

import intelligent_bank.intelligent_bank.aop.stopwatch.LogExecutionTime;
import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.bankbook.util.BankBookMapper;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankBookController {

    private final BankBookService bankBookService;
    private final MemberService memberService;

    @GetMapping("/my-bank")
    public ResponseEntity<?> myBank(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);
        BankBook bankBook = bankBookService.getBankBookByMember(member);

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("통장이 없습니다. \n통장을 개설하여주세요");
        }

        return ResponseEntity.ok(BankBookMapper.entityToDtoDetail(bankBook));
    }

    @GetMapping("/bank/post")
    public ResponseEntity<?> postBankBookPage() {
        return ResponseEntity.ok("통장 개설 페이지입니다.");
    }

    @PostMapping("/bank/post")
    @LogExecutionTime
    public ResponseEntity<?> postBankBook(
            Principal principal,
            HttpServletRequest request
    ) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);
        bankBookService.saveBankBook(member);
        log.info("통장 개설 완료");

        String url = "/my-bank";
        return CommonUtils.makeResponseEntityForRedirect(url, request);
    }

    @PatchMapping("/bank/suspend")
    public ResponseEntity<?> suspendBankBook(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);
        bankBookService.suspendBankBookByMember(member);
        log.info("통장 정지 성공");

        return ResponseEntity.ok("통장이 성공적으로 정지 되었습니다.");
    }
}
