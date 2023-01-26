package intelligent_bank.intelligent_bank.record.repository;

import intelligent_bank.intelligent_bank.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {
}
