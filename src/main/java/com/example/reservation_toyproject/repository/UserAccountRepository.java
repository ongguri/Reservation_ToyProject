package com.example.reservation_toyproject.repository;

import com.example.reservation_toyproject.domain.UserAccount;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    List<UserAccount> findByEmail(String s);
}
