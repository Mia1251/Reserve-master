package Lexicon.Reserve.repository;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import Lexicon.Reserve.models.User;

public interface User_Service extends UserDetailsService {

	void updatePassword(String password, int userId);
	List<User> findall();

}
