package Lexicon.Reserve.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Lexicon.Reserve.entity.Members;
import Lexicon.Reserve.repository.Members_Repo;

@Service("members_Services")
@Transactional
public class Members_Services_Impl implements Members_Services_Dao {

	private Members_Repo member_Repo;

	@Autowired
	public Members_Services_Impl(Members_Repo member_Repo) {
		super();
		this.member_Repo = member_Repo;
	}

	@Override
	public Members findById_Member(int id) {

		return member_Repo.findById(id).orElseThrow(IllegalArgumentException::new);
	}
	
	@Override
	public Members findByName_Member(String firstName) {
		
		return member_Repo.findByFirstName(firstName);
	}

	@Override
	public Members save_Member(Members member) {

		return member_Repo.save(member);
	}

	@Override
	public boolean remove_Member(int id) {
		member_Repo.deleteById(id);
		return member_Repo.existsById(id);
	}

	@Override
	public List<Members> findAll() {

		return (List<Members>) member_Repo.findAll();
	}

	@Override
	public void saveAll(List<Members> members) {
		for (Members member : members) {

			member_Repo.save(member);
		}

	}

	@Override
	public Members update(String memberEmail, Members updated) throws IllegalArgumentException {
		Members original = findByEmail_Member(memberEmail);
		original.setFirstName(updated.getFirstName());
		original.setLastName(updated.getLastName());
		original.setEmail(updated.getEmail());
		original.setMobilePhone(updated.getMobilePhone());
		original.setTitle(updated.getTitle());
		original.setTitle(updated.getTitle());
		original.setGroup(updated.getGroup());
		original.setStyrelsen(updated.getStyrelsen());
		return member_Repo.save(original);
	}

	@Override
	public Members findByEmail_Member(String email) {
		return member_Repo.findByEmail(email);
	}



}
