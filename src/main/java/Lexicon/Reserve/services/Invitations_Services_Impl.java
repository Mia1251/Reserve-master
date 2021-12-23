package Lexicon.Reserve.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Lexicon.Reserve.entity.Invitations;
import Lexicon.Reserve.repository.Invitations_Repo;

@Service
@Transactional
public class Invitations_Services_Impl implements Invitations_Services_Dao {

	private Invitations_Repo invitation_Repo;

	@Autowired
	public Invitations_Services_Impl(Invitations_Repo invitation_Repo) {
		super();
		this.invitation_Repo = invitation_Repo;
	}

	@Override
	public Invitations findById_Invitation(String id) {
		return invitation_Repo.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public Invitations save_Invitation(Invitations invitation) {

		return invitation_Repo.save(invitation);
	}

	@Override
	public boolean remove_Invitation(String id) {
		invitation_Repo.deleteById(id);
		return invitation_Repo.existsById(id);
	}

	@Override
	public List<Invitations> findAll_Invitations() {

		return (List<Invitations>) invitation_Repo.findAll();
	}

	@Override
	public Invitations update(String invitationId, Invitations updated) throws IllegalArgumentException {
		Invitations original = findById_Invitation(invitationId);
		original.setClothesType(updated.getClothesType());
		original.setDescriptions(updated.getDescriptions());
		original.setEventSubject(updated.getEventSubject());
		original.setFoodMenu(updated.getFoodMenu());
		original.setLocation(updated.getLocation());
		original.setStartingDateTime(updated.getStartingDateTime());
		;
		original.setEndingDateTime(updated.getEndingDateTime());
		;
		original.setNotes(updated.getNotes());
		original.setStartingDateTime(updated.getStartingDateTime());
		return invitation_Repo.save(original);
	}
}
