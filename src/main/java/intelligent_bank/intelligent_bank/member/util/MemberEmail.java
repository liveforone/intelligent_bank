package intelligent_bank.intelligent_bank.member.util;

import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.utility.CommonUtils;

public class MemberEmail {
    public static boolean isDuplicateEmail(Member member) {
        return !CommonUtils.isNull(member);
    }
}
