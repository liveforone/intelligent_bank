package intelligent_bank.intelligent_bank.bankbook.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankBookRepositoryImpl implements BankBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
