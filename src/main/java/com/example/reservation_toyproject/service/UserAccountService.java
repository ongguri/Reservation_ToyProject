package com.example.reservation_toyproject.service;


import com.example.reservation_toyproject.domain.UserAccount;
import com.example.reservation_toyproject.dto.UserAccountDto;
import com.example.reservation_toyproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public void saveUserAccount(UserAccountDto dto) {
        userAccountRepository.save(dto.toEntity());
    }

    public boolean authenticateUser(UserAccountDto dto) {
        UserAccount user = userAccountRepository.findByEmail(dto.getEmail());

        if(user != null && user.getPassword().equals(dto.getPassword())) {
            return true;
        }

        return false;
    }
}
