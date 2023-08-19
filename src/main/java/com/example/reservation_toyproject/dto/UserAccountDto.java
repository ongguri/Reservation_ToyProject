package com.example.reservation_toyproject.dto;


import com.example.reservation_toyproject.domain.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class UserAccountDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    protected UserAccountDto() {
    }

    private UserAccountDto(String email, String password, String name, String phoneNumber, String birthday, String gender,
                           LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserAccountDto of(String email, String password, String name, String phoneNumber, String birthday, String gender,
                             LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserAccountDto(email, password, name, phoneNumber, birthday, gender, createdAt, modifiedAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                email,
                password,
                name,
                phoneNumber,
                birthday,
                gender
        );
    }
}
