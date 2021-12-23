
package Lexicon.Reserve.controller;

import java.util.HashMap;
import java.util.List;
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

import Lexicon.Reserve.entity.Members;
import Lexicon.Reserve.services.EmailServiceReminder;
import Lexicon.Reserve.services.Members_Services_Dao;
import Lexicon.Reserve.utilities.Mail;
import Lexicon.Reserve.utilities.SelectedItem;

@Controller
@RequestMapping("/reminderSent")
public class SendReminder {

	@Autowired
	private EmailServiceReminder reminder_emailService;

	@Autowired
	private Members_Services_Dao memberService;

	@GetMapping
	public ModelAndView sendInvitationDeyails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/reminderSent");
		return modelAndView;
	}

	@PostMapping
	public String processInvitationEmailForm(@ModelAttribute("SelectedItem") @Valid SelectedItem form,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "error";
		}

		List<String> emails = form.getEmail();

		for (String email : emails) {

			Members member = memberService.findByEmail_Member(email);

			if (member == null) {
				result.rejectValue("email", null, "We could not find an member for that e-mail address.");
				return "/error";
			} else {

				Mail mail = new Mail();
				mail.setFrom("no-reply@Reserve.com");
				mail.setTo(member.getEmail());
				mail.setSubject("Invitation Reminder");
				Map<String, Object> model = new HashMap<>();
				model.put("member", member);
				model.put("SelectedItem", form);
				model.put("Email", member.getEmail());
				model.put("InvitationId", form.getInvitationId());
				model.put("signature", "https://Reserve.com");
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
				model.put("resetUrl", url + "/YouHaveBeenInvited/" + form.getInvitationId() + "/" + member.getEmail());
				mail.setModel(model);
				reminder_emailService.sendEmail(mail);


			}
		}

		return "redirect:/reminderSent?success";
	}
}
