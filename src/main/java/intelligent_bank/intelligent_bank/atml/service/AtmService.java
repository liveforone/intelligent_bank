package intelligent_bank.intelligent_bank.atml.service;

import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import intelligent_bank.intelligent_bank.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmService {

    private final BankBookRepository bankBookRepository;
    private final RecordRepository recordRepository;
}
