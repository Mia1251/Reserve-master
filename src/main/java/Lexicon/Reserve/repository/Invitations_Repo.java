package Lexicon.Reserve.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Lexicon.Reserve.entity.Invitations;

@Repository
public interface Invitations_Repo extends CrudRepository<Invitations, String> {

}
