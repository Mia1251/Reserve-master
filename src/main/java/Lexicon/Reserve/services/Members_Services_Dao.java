package Lexicon.Reserve.services;

import java.util.List;

import Lexicon.Reserve.entity.Members;

public interface Members_Services_Dao {

	Members findById_Member(int id);

	Members save_Member(Members member);

	boolean remove_Member(int id);

	List<Members> findAll();

	void saveAll(List<Members> members);

	Members update(String memberEmail, Members updated) throws IllegalArgumentException;

	Members findByEmail_Member(String email);
	
	Members findByName_Member(String name);
}
