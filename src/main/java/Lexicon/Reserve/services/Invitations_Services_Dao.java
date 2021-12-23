package Lexicon.Reserve.services;

import java.util.List;

import Lexicon.Reserve.entity.Invitations;

public interface Invitations_Services_Dao {

	Invitations findById_Invitation(String id);

	Invitations save_Invitation(Invitations invitation);

	boolean remove_Invitation(String id);

	List<Invitations> findAll_Invitations();

	Invitations update(String invitationId, Invitations updated) throws IllegalArgumentException;

}
