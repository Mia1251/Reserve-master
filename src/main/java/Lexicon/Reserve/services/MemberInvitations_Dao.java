  
package Lexicon.Reserve.services;

import java.util.List;

import Lexicon.Reserve.utilities.MemberInvitations;


public interface MemberInvitations_Dao {

	 
	MemberInvitations findById_MemberInvitations(int id);

	MemberInvitations save_MemberInvitations(MemberInvitations memberinvitation);

	boolean remove_MemberInvitations(int id);

	List<MemberInvitations> findAll();
}