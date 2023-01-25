package intelligent_bank.intelligent_bank.bankbook.repository;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankBookRepository extends JpaRepository<BankBook, Long>, BankBookRepositoryCustom {
}
