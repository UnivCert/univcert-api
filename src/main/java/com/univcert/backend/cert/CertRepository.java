package com.univcert.backend.cert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CertRepository extends JpaRepository<Cert, Long> {

    @Query("select c from Cert c where c.email = :mail")
    Optional<Cert> findCertByUnivEmail(String mail);
}
