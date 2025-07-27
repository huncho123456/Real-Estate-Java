package com.Satisfyre.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Satisfyre.app.entity.VerificationTokenEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface verificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {

     Optional<VerificationTokenEntity> findByToken(String token);


}
