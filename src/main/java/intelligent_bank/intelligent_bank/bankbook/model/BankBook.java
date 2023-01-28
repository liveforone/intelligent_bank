package intelligent_bank.intelligent_bank.bankbook.model;

import intelligent_bank.intelligent_bank.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BankBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankBookNum;

    private long balance;

    @Enumerated(value = EnumType.STRING)
    private BankBookState bankBookState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdDate;

    @Builder
    public BankBook(Long id, String bankBookNum, long balance, BankBookState bankBookState, Member member) {
        this.id = id;
        this.bankBookNum = bankBookNum;
        this.balance = balance;
        this.bankBookState = bankBookState;
        this.member = member;
    }
}
