package Lexicon.Reserve.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Lexicon.Reserve.repository.contact_Repo;
import Lexicon.Reserve.utilities.ContactUs;

@Service
@Transactional
public class contactUs_services_impl implements contactUsService_Dao{
	
	private contact_Repo contact;
	
	
	@Autowired
	public contactUs_services_impl(contact_Repo contact) {
		super();
		this.contact = contact;
	}

	@Override
	public ContactUs findById_contact(int id) {
		
		return contact.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public ContactUs save_Contact(ContactUs Contact) {
		
		return contact.save(Contact);
	}

}
