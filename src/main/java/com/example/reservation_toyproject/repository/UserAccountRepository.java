package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.UserAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    UserAccount findByEmail(String s);
}
