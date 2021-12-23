package Lexicon.Reserve.services;


import Lexicon.Reserve.utilities.ContactUs;

public interface contactUsService_Dao {

	ContactUs findById_contact (int id);
	
	ContactUs save_Contact(ContactUs Contact);
}
