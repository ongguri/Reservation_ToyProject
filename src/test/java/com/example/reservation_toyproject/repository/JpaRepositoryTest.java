package com.example.reservation_toyproject.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.reservation_toyproject.config.JpaConfig;
import com.example.reservation_toyproject.domain.UserAccount;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("JPA연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

    private final UserAccountRepository userAccountRepository;
    private final UserReservationRepository userReservationRepository;
    private final HospitalReceptionRepository hospitalReceptionRepository;

    public JpaRepositoryTest(
        @Autowired UserAccountRepository userAccountRepository,
        @Autowired UserReservationRepository userReservationRepository,
        @Autowired HospitalReceptionRepository hospitalReceptionRepository
    ) {
        this.userAccountRepository = userAccountRepository;
        this.userReservationRepository = userReservationRepository;
        this.hospitalReceptionRepository = hospitalReceptionRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        // Then
        assertThat(userAccounts)
            .isNotNull()
            .hasSize(25);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        //Given
        long previousCount = userAccountRepository.count();

        // When
        UserAccount savedArticle = userAccountRepository.save(
            UserAccount.of("rkd@naver.com", "2984", "sunyong",
                "010-1234-3333", "123.456.78.9", "male"));

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        //Given
        UserAccount userAccount = userAccountRepository.findByEmail("kiwanczyk0@upenn.edu");

        String updatedName = "kangSunYong";
        userAccount.setName(updatedName);

        // When
        UserAccount savedUserAccount = userAccountRepository.saveAndFlush(userAccount);

        // Then
        assertThat(savedUserAccount).hasFieldOrPropertyWithValue("name", updatedName);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        //Given
        UserAccount userAccount = userAccountRepository.findByEmail("kiwanczyk0@upenn.edu");

        long previousUserAccountCount = userAccountRepository.count();
        long previousUserReservationCount = userReservationRepository.count();
        int deletedReservationSize = userAccount.getUserReservations().size();

        // When
        userAccountRepository.delete(userAccount);

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousUserAccountCount - 1);
        assertThat(userReservationRepository.count()).isEqualTo(previousUserReservationCount - deletedReservationSize);
    }
}
