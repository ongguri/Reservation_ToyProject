package com.example.reservation_toyproject.dto.request;


import com.example.reservation_toyproject.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserAccountRequest {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String gender;

    protected UserAccountRequest() {
    }

    private UserAccountRequest(String email, String password, String name, String phoneNumber,
        String birthday, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
    }

    public static UserAccountRequest of(String email, String password, String name, String phoneNumber,
        String birthday, String gender) {
        return new UserAccountRequest(email, password, name, phoneNumber, birthday, gender);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
            email,
            password,
            name,
            phoneNumber,
            birthday,
            gender
        );
    }
}
