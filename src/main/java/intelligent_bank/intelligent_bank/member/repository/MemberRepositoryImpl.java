package intelligent_bank.intelligent_bank.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank.intelligent_bank.member.model.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public void updateEmail(String oldEmail, String newEmail) {
        QMember member = QMember.member;

        queryFactory.update(member)
                .set(member.email, newEmail)
                .where(member.email.eq(oldEmail))
                .execute();
    }

    public void updatePassword(Long id, String password) {
        QMember member = QMember.member;

        queryFactory.update(member)
                .set(member.password, password)
                .where(member.id.eq(id))
                .execute();
    }
}
