package Lexicon.Reserve.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Lexicon.Reserve.repository.MemberInvitation_Repo;
import Lexicon.Reserve.utilities.MemberInvitations;

@Service("MemberInvitations_Services")
@Transactional
public class MemberInvitations_Service_Impl implements MemberInvitations_Dao{

	
	private MemberInvitation_Repo memberInvitation_repo ; 
	
	
	@Autowired
	public MemberInvitations_Service_Impl(MemberInvitation_Repo memberInvitation_repo) {
		super();
		this.memberInvitation_repo = memberInvitation_repo;
	}

	@Override
	public MemberInvitations findById_MemberInvitations(int id) {
		return memberInvitation_repo.findById(id).orElseThrow(IllegalArgumentException::new);
		
	}

	@Override
	public MemberInvitations save_MemberInvitations(MemberInvitations memberinvitation) {
		return memberInvitation_repo.save(memberinvitation);
		
	}

	@Override
	public boolean remove_MemberInvitations(int id) {
		memberInvitation_repo.deleteById(id);
		return memberInvitation_repo.existsById(id);
	}

	@Override
	public List<MemberInvitations> findAll() {
		
		return (List<MemberInvitations>) memberInvitation_repo.findAll();
	}

}