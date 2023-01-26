package intelligent_bank.intelligent_bank.atml.controller;

import intelligent_bank.intelligent_bank.atml.service.AtmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AtmController {

    private final AtmService atmService;
}
