package Lexicon.Reserve.controller;

import java.util.HashMap;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import Lexicon.Reserve.services.EmailServiceContactUs;
import Lexicon.Reserve.services.contactUsService_Dao;
import Lexicon.Reserve.utilities.ContactUs;
import Lexicon.Reserve.utilities.Mail;

@Controller
@RequestMapping("/feedback")
public class contactUsController {

	@Autowired
	private contactUsService_Dao contactService;

	@Autowired
	private EmailServiceContactUs emailService;


	public String SendFeedbackToAdmin(String name, String usermail, String text) {

		String email = "yayoubi@gmail.com";

		Mail mail = new Mail();
		mail.setFrom("no-reply@Reserve.com");
		mail.setTo(email);
		mail.setSubject("New User Feedback");
		Map<String, Object> model = new HashMap<>();
		model.put("signature", "https://Reserve.com");
		model.put("name", name);
		model.put("mail", usermail);
		model.put("text", text);
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/feedback?success";

	}

	@PostMapping
	public String ProcessUserFeedback(@ModelAttribute("ContactUs") @Valid ContactUs form, BindingResult result) {

		if (result.hasErrors()) {
			return "error";
		} else {

			String name = form.getName();
			String usermail = form.getMail();
			String text = form.getText();
			contactService.save_Contact(form);
			SendFeedbackToAdmin(name, usermail, text);
			System.out.println("yamen");
		}

		return "/feedbackmsg";
	}

}
