package intelligent_bank.intelligent_bank.member.util;

import intelligent_bank.intelligent_bank.member.dto.MemberSignupRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberResponse;
import intelligent_bank.intelligent_bank.member.model.Member;

public class MemberMapper {

    public static Member dtoToEntity(MemberSignupRequest memberSignupRequest) {
        return Member.builder()
                .id(memberSignupRequest.getId())
                .email(memberSignupRequest.getEmail())
                .password(memberSignupRequest.getPassword())
                .realName(memberSignupRequest.getRealName())
                .auth(memberSignupRequest.getAuth())
                .build();
    }

    public static MemberResponse dtoBuilder(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .realName(member.getRealName())
                .auth(member.getAuth())
                .build();
    }
}
