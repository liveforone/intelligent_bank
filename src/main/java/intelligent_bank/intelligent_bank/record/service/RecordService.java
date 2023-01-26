package intelligent_bank.intelligent_bank.record.service;

import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.record.dto.RecordResponse;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import intelligent_bank.intelligent_bank.record.util.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;

    public Page<RecordResponse> getRecordsByMember(Member member, Pageable pageable) {
        return RecordMapper.entityToDtoPage(
                recordRepository.findRecordsByMember(member, pageable)
        );
    }
}
