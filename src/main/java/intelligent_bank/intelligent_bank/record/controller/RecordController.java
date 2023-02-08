package intelligent_bank.intelligent_bank.record.controller;

import intelligent_bank.intelligent_bank.aop.stopwatch.LogExecutionTime;
import intelligent_bank.intelligent_bank.member.model.Member;
import intelligent_bank.intelligent_bank.member.service.MemberService;
import intelligent_bank.intelligent_bank.record.dto.RecordResponse;
import intelligent_bank.intelligent_bank.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;
    private final MemberService memberService;

    @GetMapping("/my-record")
    @LogExecutionTime
    public ResponseEntity<?> getMyRecord(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Principal principal
    ) {
        Member member = memberService.getMemberEntity(principal.getName());
        Page<RecordResponse> records = recordService.getRecordsByMember(member, pageable);

        return ResponseEntity.ok(records);
    }
}
