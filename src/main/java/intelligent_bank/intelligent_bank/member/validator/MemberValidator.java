package intelligent_bank.intelligent_bank.member.validator;

import intelligent_bank.intelligent_bank.member.dto.MemberLoginRequest;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberService memberService;

    public boolean isDuplicateEmail(String email) {
        Member member = memberService.getMemberEntity(email);

        return !CommonUtils.isNull(member);
    }

    public boolean isNotRightMemberInfo(MemberLoginRequest memberLoginRequest) {
        Member foundMember = memberService.getMemberEntity(memberLoginRequest.getEmail());

        return CommonUtils.isNull(foundMember)
                || MemberPasswordValidator.isNotMatchingPassword(
                memberLoginRequest.getPassword(),
                foundMember.getPassword());
    }
}
