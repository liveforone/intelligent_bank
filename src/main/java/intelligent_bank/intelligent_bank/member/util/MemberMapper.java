package intelligent_bank.intelligent_bank.member.util;

import intelligent_bank.intelligent_bank.member.dto.MemberRequest;
import intelligent_bank.intelligent_bank.member.dto.MemberResponse;
import intelligent_bank.intelligent_bank.member.model.Member;

public class MemberMapper {

    public static Member dtoToEntity(MemberRequest memberRequest) {
        return Member.builder()
                .id(memberRequest.getId())
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .realName(memberRequest.getRealName())
                .auth(memberRequest.getAuth())
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
