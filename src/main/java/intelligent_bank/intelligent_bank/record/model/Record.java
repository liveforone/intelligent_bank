package intelligent_bank.intelligent_bank.record.model;

import intelligent_bank.intelligent_bank.bankbook.model.BankBook;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private long money;

    @Enumerated(value = EnumType.STRING)
    private RecordState recordState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_book_id")
    private BankBook bankBook;

    @Column(updatable = false)
    private int createdYear;

    @Column(updatable = false)
    private Month createdMonth;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Builder
    public Record(Long id, String title, long money, RecordState recordState, BankBook bankBook, int createdYear, Month createdMonth) {
        this.id = id;
        this.title = title;
        this.money = money;
        this.recordState = recordState;
        this.bankBook = bankBook;
        this.createdYear = createdYear;
        this.createdMonth = createdMonth;
    }
}
