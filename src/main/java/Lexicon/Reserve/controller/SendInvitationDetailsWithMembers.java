package Lexicon.Reserve.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Lexicon.Reserve.entity.Invitations;
import Lexicon.Reserve.services.EmailServiceInformations;

import Lexicon.Reserve.services.Invitations_Services_Dao;

import Lexicon.Reserve.utilities.Informations;
import Lexicon.Reserve.utilities.Mail;

@Controller
@RequestMapping("/SendDetails")
public class SendInvitationDetailsWithMembers {

	@Autowired
	private Invitations_Services_Dao invitationService;

	@Autowired
	private EmailServiceInformations emailService;

	@GetMapping
	public ModelAndView sendInvitationDeyails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/SendDetails");
		return modelAndView;
	}

	@PostMapping
	public String SendInvitationDetails(@ModelAttribute("InformationsForm") @Valid Informations form,
			BindingResult result, HttpServletRequest request) {

		Invitations invitation = invitationService.findById_Invitation(form.getInvitation_id());

		if (result.hasErrors()) {
			return "error";
		}

		String email = form.getEmail();

		Mail mail = new Mail();
		mail.setFrom("no-reply@Reserve.com");
		mail.setTo(email);
		mail.setSubject("Invitation Details");
		Map<String, Object> model = new HashMap<>();
		model.put("MembersRejected", invitation.getListOfMembersRejected());
		model.put("MembersAccepted", invitation.getListOfMembersAccepted());
		model.put("MembersInvited", invitation.getListOfMembersInvited());
		model.put("invitations", invitation);
		model.put("invitationId", invitation.getId());
		model.put("accepted", invitation.getListOfMembersAccepted().size());
		model.put("rejected", invitation.getListOfMembersRejected().size());
		model.put("notReliedYet", invitation.getListOfMembersInvited().size()
				- (invitation.getListOfMembersAccepted().size() + invitation.getListOfMembersRejected().size()));
		model.put("signature", "https://Reserve.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/Invitation_full_informations/" + form.getInvitation_id());
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/SendDetails?success";

	}

}
