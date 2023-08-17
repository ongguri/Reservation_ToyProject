package com.example.reservation_toyproject.domain;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "hospitalName"),
    @Index(columnList = "txList")
}) // 추후 검색이 필요한 인덱스는 추가.
@Entity
public class UserReservation extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String hospitalName;
    @Setter private String txList;
    @Setter private String reservationStatus;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @OneToOne(mappedBy = "userReservation", cascade = CascadeType.ALL) private HospitalReception hospitalReception;

    protected UserReservation() {}

    private UserReservation(UserAccount userAccount, String hospitalName, String txList) {
        this.userAccount = userAccount;
        this.hospitalName = hospitalName;
        this.txList = txList;
    }

    public static UserReservation of(UserAccount userAccount, String hospitalName, String txList) {
        return new UserReservation(userAccount, hospitalName, txList);
    }
}
