package intelligent_bank.intelligent_bank.record.repository;

import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordRepositoryCustom {
    Page<Record> findRecordsByMember(Member member, Pageable pageable);
}
