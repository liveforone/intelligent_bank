package intelligent_bank.intelligent_bank.bankbook.service;

import intelligent_bank.intelligent_bank.bankbook.repository.BankBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankBookService {

    private final BankBookRepository bankBookRepository;
}
