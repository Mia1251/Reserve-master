package Lexicon.Reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Lexicon.Reserve.models.PasswordResetToken;
import Lexicon.Reserve.models.User;
import Lexicon.Reserve.repository.PasswordResetTokenRepository;
import Lexicon.Reserve.services.EmailService;
import Lexicon.Reserve.services.UserService;
import Lexicon.Reserve.utilities.Mail;
import Lexicon.Reserve.utilities.PasswordForgotDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private EmailService emailService;

	@GetMapping
	public ModelAndView forgetPassword() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forgot-Password");
		return modelAndView;
	}

	@ModelAttribute("forgotPasswordForm")
	public PasswordForgotDto forgotPasswordDto() {
		return new PasswordForgotDto();
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "forgot-password";
		}

		User user = userService.findUserByEmail(form.getEmail());
		if (user == null) {
			result.rejectValue("email", null, "We could not find an account for that e-mail address.");
			return "forgot-password";
		}

		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);

		Mail mail = new Mail();
		mail.setFrom("no-reply@Reserve.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Password reset request");

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "https://Reserve.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/forgot-password?success";

	}

}
