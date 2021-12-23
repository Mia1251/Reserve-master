package Lexicon.Reserve.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Lexicon.Reserve.utilities.MemberInvitations;

@Repository
public interface MemberInvitation_Repo extends CrudRepository<MemberInvitations,Integer>{

}
