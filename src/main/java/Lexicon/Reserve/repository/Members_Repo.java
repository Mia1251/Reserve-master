package Lexicon.Reserve.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Lexicon.Reserve.entity.Members;

@Repository
public interface Members_Repo extends CrudRepository<Members, Integer> {

	Members findByEmail(String email);
	Members findByFirstName(String name);
}
