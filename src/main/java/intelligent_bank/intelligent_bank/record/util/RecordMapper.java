package intelligent_bank.intelligent_bank.record.util;

import intelligent_bank.intelligent_bank.record.dto.RecordRequest;
import intelligent_bank.intelligent_bank.record.dto.RecordResponse;
import intelligent_bank.intelligent_bank.record.model.Record;
import org.springframework.data.domain.Page;

public class RecordMapper {

    public static Record dtoToEntity(RecordRequest recordRequest) {
        return Record.builder()
                .id(recordRequest.getId())
                .title(recordRequest.getTitle())
                .money(recordRequest.getMoney())
                .recordState(recordRequest.getRecordState())
                .bankBook(recordRequest.getBankBook())
                .createdYear(recordRequest.getCreatedYear())
                .createdMonth(recordRequest.getCreatedMonth())
                .build();
    }

    private static RecordResponse dtoBuilder(Record record) {
        return RecordResponse.builder()
                .id(record.getId())
                .title(record.getTitle())
                .money(record.getMoney())
                .recordState(record.getRecordState())
                .createdDate(record.getCreatedDate())
                .build();
    }

    public static Page<RecordResponse> entityToDtoPage(Page<Record> records) {
        return records.map(RecordMapper::dtoBuilder);
    }
}
