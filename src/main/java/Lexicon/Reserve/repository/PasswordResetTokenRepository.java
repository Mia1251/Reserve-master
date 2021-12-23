package Lexicon.Reserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Lexicon.Reserve.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

}
