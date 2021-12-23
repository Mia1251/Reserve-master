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
import Lexicon.Reserve.entity.Members;
import Lexicon.Reserve.services.EmailServiceSendMemberResponse;
import Lexicon.Reserve.services.Invitations_Services_Dao;
import Lexicon.Reserve.services.MemberInvitations_Dao;
import Lexicon.Reserve.services.Members_Services_Dao;
import Lexicon.Reserve.utilities.Mail;
import Lexicon.Reserve.utilities.MemberInvitations;
import Lexicon.Reserve.utilities.MemberResponse;

@Controller
@RequestMapping("/GetReply")
public class MemberResponseController {

	@Autowired
	private Members_Services_Dao memberService;

	@Autowired
	private Invitations_Services_Dao invitationService;

	@Autowired
	private MemberInvitations_Dao memberInvitationService;

	@Autowired
	private EmailServiceSendMemberResponse emailService;

	@GetMapping
	public ModelAndView GetReplyByMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/GetReply");
		return modelAndView;
	}

	public String SendMemberResponseToAdmin(String m_email, String response, String note) {

		String email = "yayoubi@gmail.com";

		Mail mail = new Mail();
		mail.setFrom("no-reply@Reserve.com");
		mail.setTo(email);
		mail.setSubject("New Member Response");
		Map<String, Object> model = new HashMap<>();
		model.put("signature", "https://Reserve.com");
		model.put("MemberEmail", m_email);
		model.put("MemberResponse", response);
		model.put("MemberNote", note);
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/GetReply?success";

	}

	@PostMapping
	public String ProcessMemberResponse(@ModelAttribute("MemberResponse") @Valid MemberResponse form,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "error";
		}

		Members member = memberService.findByEmail_Member(form.getMemberEmail());
		Invitations invitation = invitationService.findById_Invitation(form.getInvitationId());

		MemberInvitations memberInvitation = new MemberInvitations();
		String memberNote = form.getMemberNotes();
		String response = form.getAccepted();
		String id = form.getInvitationId();
		String email = form.getMemberEmail();
		String subject = invitation.getEventSubject();
		String date = invitation.getStartingDateTime();
		if (response.equalsIgnoreCase("true")) {

			if (invitation.getListOfMembersAccepted().contains(member)
					|| invitation.getListOfMembersRejected().contains(member)) {
				return "/GetInvalidReply";
			} else {
				invitation.getListOfMembersAccepted().add(member);
				memberInvitation.setAccepted("Yes");
				memberInvitation.setInvitationId(id);
				memberInvitation.setMemberNotes(memberNote);
				memberInvitation.setMember_email(email);
				memberInvitation.setSubject(subject);
				memberInvitation.setDate(date);
				memberInvitationService.save_MemberInvitations(memberInvitation);
				member.getInvitationDetails().add(memberInvitation);
				invitationService.save_Invitation(invitation);
				SendMemberResponseToAdmin(member.getEmail(), memberInvitation.getAccepted(),
						memberInvitation.getMemberNotes());
				
				
			}

		} else if (response.equalsIgnoreCase("false")) {

			if (invitation.getListOfMembersAccepted().contains(member)
					|| invitation.getListOfMembersRejected().contains(member)) {

				return "/GetInvalidReply";
			} else {
				invitation.getListOfMembersRejected().add(member);
				memberInvitation.setAccepted("No");
				memberInvitation.setInvitationId(id);
				memberInvitation.setMemberNotes(memberNote);
				memberInvitation.setMember_email(email);
				memberInvitation.setSubject(subject);
				memberInvitation.setDate(date);
				memberInvitationService.save_MemberInvitations(memberInvitation);
				member.getInvitationDetails().add(memberInvitation);
				memberService.save_Member(member);
				invitationService.save_Invitation(invitation);
				SendMemberResponseToAdmin(member.getEmail(), memberInvitation.getAccepted(),
						memberInvitation.getMemberNotes());
				memberInvitation.setMember_email(member.getEmail());
				
			}
		}
		return "/GetReply";
	}

}
