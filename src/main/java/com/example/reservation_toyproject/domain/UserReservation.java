package com.example.reservation_toyproject.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "hptName"),
    @Index(columnList = "txList")
}) // 추후 검색이 필요한 인덱스는 추가.
@Entity
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String hptName;
    @Setter private String txList;
    @Setter private String resStatus;

    @Setter private LocalDateTime resDate;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @OneToOne(mappedBy = "userReservation") private HptReception hptReception;

    protected UserReservation() {}

    private UserReservation(String hptName, String txList) {
        this.hptName = hptName;
        this.txList = txList;
    }

    public static UserReservation of(String hptName, String txList) {
        return new UserReservation(hptName, txList);
    }
}
