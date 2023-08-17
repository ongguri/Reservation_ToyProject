package com.example.reservation_toyproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class HospitalReception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false) private UserAccount userAccount;

    @Setter
    @OneToOne(optional = false) private UserReservation userReservation;

    protected HospitalReception() {}

    private HospitalReception(UserAccount userAccount, UserReservation userReservation) {
        this.userAccount = userAccount;
        this.userReservation = userReservation;
    }

    public static HospitalReception of(UserAccount userAccount, UserReservation userReservation) {
        return new HospitalReception(userAccount, userReservation);
    }
}
