package com.univcert.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email")String email);

    @Query(value = "select u from User u where u.teamName  = :teamName or u.email = :email")
    Optional<User> findByEmailOrTeamName(@Param("email")String email, @Param("teamName")String teamName);

    @Query("select u from User u left join fetch u.certList where u.API_KEY = :API_KEY")
    Optional<User> findByEmailFetchCertList(@Param("API_KEY")String API_KEY);


}
