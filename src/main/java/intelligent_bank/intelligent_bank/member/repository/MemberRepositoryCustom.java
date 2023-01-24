package intelligent_bank.intelligent_bank.member.repository;

public interface MemberRepositoryCustom {

    void updateEmail(String oldEmail, String newEmail);

    void updatePassword(Long id, String password);
}
