package intelligent_bank.intelligent_bank.bankbook.controller;

import intelligent_bank.intelligent_bank.bankbook.dto.BankBookResponse;
import intelligent_bank.intelligent_bank.bankbook.service.BankBookService;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
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

    private final BankBookService bankBookService;
    private final MemberService memberService;

    @GetMapping("/my-bank")
    public ResponseEntity<?> myBank(Principal principal) {
        String email = principal.getName();
        Member member = memberService.getMemberEntity(email);
        BankBookResponse bankBook = bankBookService.getBankBookByMember(member);

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity.ok("통장이 없습니다. \n통장을 개설하여주세요");
        }

        return ResponseEntity.ok(bankBook);
    }
}
