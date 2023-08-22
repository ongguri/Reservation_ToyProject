package com.example.reservation_toyproject.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

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

    @Setter @Column(nullable = false) private String hospitalName;
    @Setter @Column(nullable = false) private String txList;
    @Setter @Column private String reservationStatus;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @OneToOne(mappedBy = "userReservation", cascade = CascadeType.ALL) private HospitalReception hospitalReception;

    protected UserReservation() {}

    private UserReservation(UserAccount userAccount, String hospitalName, String txList, String reservationStatus) {
        this.userAccount = userAccount;
        this.hospitalName = hospitalName;
        this.txList = txList;
        this.reservationStatus = reservationStatus;
    }

    public static UserReservation of(UserAccount userAccount, String hospitalName, String txList, String reservationStatus) {
        return new UserReservation(userAccount, hospitalName, txList, reservationStatus);
    }
}
