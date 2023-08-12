package com.example.reservation_toyproject.domain;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "email"),
    @Index(columnList = "phoneNumber")
}) // 추후 검색이 필요한 인덱스는 추가.
@Entity
public class UserAccount {

    @Id
    @Column(length = 50)
    private String email;

    @Setter @Column(nullable = false) private String password;

    @Setter @Column(length = 20) private String name;
    @Setter @Column(length = 20) private String phoneNumber;
    @Setter @Column(length = 15) private String birthday;
    @Setter private String gender;

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private final Set<UserReservation> userReservations = new LinkedHashSet<>();

    protected UserAccount() { }  // 코드 밖에서 new 로 생성하지 못하게끔 막음.

    private UserAccount(String email, String password, String name, String phoneNumber, String birthday, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
    } // userAccount 클래스와 관련있는 정보만 오픈하는 방식으로 생성자를 통해 만들 수 있게끔 유도.

    public static UserAccount of(String email, String password, String name, String phoneNumber, String birthday, String gender) {
        return new UserAccount(email, password, name, phoneNumber, birthday, gender);
    }  // new 키워드를 쓰지 않고 좀 더 사용이 편하게 팩토리 메서드를 통해서 제공하게끔.
    // UserAccount 를 생성하고자 할 때는 매개변수에 있는 값들이 필요하다고 가이드.
}
