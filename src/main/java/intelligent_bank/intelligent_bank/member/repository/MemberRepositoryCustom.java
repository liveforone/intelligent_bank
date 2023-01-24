package intelligent_bank.intelligent_bank.member.repository;

import intelligent_bank.intelligent_bank.member.model.Member;

public interface MemberRepositoryCustom {

    Member findByEmail(String email);

    void updateEmail(String oldEmail, String newEmail);

    void updatePassword(Long id, String password);
}
