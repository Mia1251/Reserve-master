package Lexicon.Reserve.controller;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Lexicon.Reserve.entity.Invitations;
import Lexicon.Reserve.entity.Members;
import Lexicon.Reserve.models.User;
import Lexicon.Reserve.repository.User_Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import Lexicon.Reserve.services.Invitations_Services_Dao;
import Lexicon.Reserve.services.Members_Services_Dao;
import Lexicon.Reserve.services.UserService;
import Lexicon.Reserve.utilities.ContactUs;
import Lexicon.Reserve.utilities.Informations;
import Lexicon.Reserve.utilities.MemberInvitations;
import Lexicon.Reserve.utilities.MemberResponse;
import Lexicon.Reserve.utilities.SelectedItem;


@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private Members_Services_Dao members_Services;
	@Autowired
	private Invitations_Services_Dao invitation_Service;

	@Autowired
	private User_Service Service;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView main(@Valid ContactUs contactUs, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/Home");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/creat_invitation" }, method = RequestMethod.GET)
	public ModelAndView creat_invitation() {
		ModelAndView modelAndView = new ModelAndView();
		Invitations invitation = new Invitations();
		modelAndView.addObject("invitation", invitation);
		modelAndView.setViewName("admin/creat_invitation");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin/creat_member" }, method = RequestMethod.GET)
	public ModelAndView creat_member() {
		ModelAndView modelAndView = new ModelAndView();
		Members member = new Members();
		modelAndView.addObject("member", member);
		modelAndView.setViewName("admin/creat_member");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/find_member", method = RequestMethod.GET)
	public ModelAndView find_member() {
		ModelAndView modelAndView = new ModelAndView();
		Members member = new Members();
		modelAndView.addObject("member", member);

		modelAndView.setViewName("admin/find_member");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/creat_member", method = RequestMethod.POST)
	public ModelAndView createNewMember(@Valid Members member, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Members memberExists = members_Services.findByEmail_Member(member.getEmail());

		if (memberExists != null) {
			modelAndView.addObject("errorMessage", "There is already a member added with the email provided");
			modelAndView.addObject("member", new Members());
			modelAndView.setViewName("admin/creat_member");

		} else if (memberExists == null) {
			members_Services.save_Member(member);
			modelAndView.addObject("successMessage", "member has been added successfully");
			modelAndView.addObject("member", new Members());
			modelAndView.setViewName("admin/creat_member");
			
			
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/creat_invitation", method = RequestMethod.POST)
	public ModelAndView createInvitation(@Valid Invitations invitation, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/creat_invitation");
		} else {
			invitation_Service.save_Invitation(invitation);
			invitation.setActive("Yes");
			invitation.setSent("No");
			modelAndView.addObject("successMessage", "invitation has been added successfully");
			modelAndView.addObject("invitation", new Invitations());
			modelAndView.setViewName("admin/creat_invitation");
			invitation_Service.save_Invitation(invitation);
		}
		return modelAndView;
	}

	@RequestMapping(value = { "admin/find_member/{email}" }, method = RequestMethod.GET, produces = "application/json")
	public String member_By_email(@RequestParam("email") String email, Model theModel) {

		Members member = members_Services.findByEmail_Member(email);

		if (members_Services.findByEmail_Member(email) != null) {

			theModel.addAttribute("Member", member);

			List<MemberInvitations> InvitationDetails = member.getInvitationDetails();

			theModel.addAttribute("Invitations", InvitationDetails);

			return "admin/member_result";

		} else {

			return "admin/emailNotFoundMember";
		}

	}
	
	
	@RequestMapping(value = { "admin/find_member/{firstName}" }, method = RequestMethod.GET, produces = "application/json")
	public String member_By_name(@RequestParam("firstName") String firstName, Model theModel) {

		Members member = members_Services.findByName_Member(firstName);
		System.out.println(member);

		if (members_Services.findByName_Member(firstName) != null) {

			theModel.addAttribute("Member", member);

			List<MemberInvitations> InvitationDetails = member.getInvitationDetails();

			theModel.addAttribute("Invitations", InvitationDetails);

			return "admin/member_result";

		} else {

			return "admin/emailNotFoundMember";
		}

	}

	@RequestMapping(value = {
			"/admin/find_invitation/result" }, method = RequestMethod.GET, produces = "application/json")
	public String get_invitations(Model theModel) throws ParseException {

		List<Invitations> invitations = invitation_Service.findAll_Invitations();
		for (Invitations i : invitations) {
			DateTime dt = new DateTime(i.getStartingDateTime());
            
			if (dt.isAfterNow()) {
				i.setActive("Yes");
				invitation_Service.save_Invitation(i);
				Service.findall();
			} else {
				i.setActive("No");
				invitation_Service.save_Invitation(i);
			}
		}

		List<Invitations> all_invitations = invitation_Service.findAll_Invitations();
		List<Invitations> active = new ArrayList<>();
		List<Invitations> disactive = new ArrayList<>();
		for (Invitations inv : all_invitations) {
			if (inv.getActive().equalsIgnoreCase("Yes")) {
				active.add(inv);
			} else if (inv.getActive().equalsIgnoreCase("No")) {
				disactive.add(inv);
			}
		}

		if (invitations.isEmpty()) {

			return "admin/noInvitationsFound";

		} else {
			theModel.addAttribute("invitations", active);
			theModel.addAttribute("old_invitations", disactive);

			return "admin/invitaions_result";
		}

	}

	@RequestMapping(value = { "/admin/upload" }, method = RequestMethod.GET)
	public ModelAndView upload() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/upload");
		return modelAndView;
	}

	@RequestMapping(value = {
			"/admin/find_invitation/update" }, method = RequestMethod.GET, produces = "application/json")
	public String get_invitations_toUpdate(Model theModel) {

		if (invitation_Service.findAll_Invitations().isEmpty()) {

			return "admin/noInvitationsFound";

		} else {
			theModel.addAttribute("invitations", invitation_Service.findAll_Invitations());

			return "admin/update_invitation_Form";
		}

	}

	@RequestMapping(value = { "/admin/update_member" }, method = RequestMethod.GET)
	public ModelAndView update_member() {
		ModelAndView modelAndView = new ModelAndView();
		Members member = new Members();
		modelAndView.addObject("member", member);
		modelAndView.setViewName("admin/update_member");
		return modelAndView;
	}

	@RequestMapping(value = {
			"/admin/update_member/{email}" }, method = RequestMethod.GET, produces = "application/json")
	public String member_By_email_toUpdate(@RequestParam("email") String email, Model theModel) {
		if (members_Services.findByEmail_Member(email) != null) {
			theModel.addAttribute("Member", members_Services.findByEmail_Member(email));
			return "admin/updateMemberForm";
		} else {

			return "admin/emailNotFoundMember";
		}

	}

	@GetMapping("/Member/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Members member = members_Services.findById_Member(id);
		model.addAttribute("member", member);
		return "admin/update-member";
	}

	@PostMapping("/Member/update/{id}")
	public String updateMember(@PathVariable("id") int id, @Valid Members member, BindingResult result, Model model) {
		if (result.hasErrors()) {
			member.setId(id);
			return "admin/update-member";
		}

		members_Services.save_Member(member);
		return "admin/member_updated";
	}

	@GetMapping("/Member/delete/{id}")
	public String deleteMember(@PathVariable("id") int id, Model model) {
		Members member = members_Services.findById_Member(id);
		members_Services.remove_Member(member.getId());
		return "admin/member_deleted";
	}

	@GetMapping("/invitations/edit/{id}")
	public String showUpdateForm_invitation(@PathVariable("id") String id, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		model.addAttribute("invitations", invitation);
		return "admin/update-invitation";
	}

	@PostMapping("/invitations/update/{id}")
	public String updateInvitation(@PathVariable("id") String id, @Valid Invitations invitation, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			invitation.setId(id);
			return "admin/update-invitation";
		}

		invitation_Service.save_Invitation(invitation);
		return "admin/invitation_updated";
	}
	
	@GetMapping("/invitations/createfromUpdate/{id}")
	public String showcreateForm_invitation(@PathVariable("id") String id, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		model.addAttribute("invitations", invitation);
		return "admin/createFromOldInvitation";
	}
	

	@GetMapping("/invitations/delete/{id}")
	public String deleteInvitation(@PathVariable("id") String id, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		invitation_Service.remove_Invitation(invitation.getId());
		return "admin/invitation_deleted";
	}

	@RequestMapping(value = {
			"/admin/find_invitation/resultToSend" }, method = RequestMethod.GET, produces = "application/json")
	public String get_invitations_ToSend(Model theModel) {

		List<Invitations> invitations = invitation_Service.findAll_Invitations();
		for (Invitations i : invitations) {
			DateTime dt = new DateTime(i.getStartingDateTime());

			if (dt.isAfterNow()) {
				i.setActive("Yes");
				invitation_Service.save_Invitation(i);
				Service.findall();
			} else {
				i.setActive("No");
				invitation_Service.save_Invitation(i);
			}
		}

		for (Invitations invit : invitations) {

			if (!invit.getListOfMembersInvited().isEmpty()) {
				invit.setSent("Yes");
				invitation_Service.save_Invitation(invit);
			} else {
				invit.setSent("No");
			}
		}

			List<Invitations> all_invitations = invitation_Service.findAll_Invitations();
			List<Invitations> active = new ArrayList<>();
			List<Invitations> disactive = new ArrayList<>();
			List<Invitations> sent = new ArrayList<>();
			List<Invitations> un_sent = new ArrayList<>();
			for (Invitations inv : all_invitations) {
				if (inv.getActive().equalsIgnoreCase("Yes")) {
					active.add(inv);
				} else if (inv.getActive().equalsIgnoreCase("No")) {
					disactive.add(inv);
				}else if (inv.getSent().equalsIgnoreCase("Yes")) {
					sent.add(inv);
				}else if (inv.getSent().equalsIgnoreCase("No"))
					un_sent.add(inv);
			}

			if (invitations.isEmpty()) {

				return "admin/noInvitationsFound";

			} else {
				theModel.addAttribute("invitations", active);
				theModel.addAttribute("old_invitations", disactive);
				
			}
			return "admin/invitation_toSend";
		}



	@GetMapping("/invitations/send/{id}")
	public String showInvitationLetter(@PathVariable("id") String id, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		model.addAttribute("Member", members_Services.findAll());
		model.addAttribute("invitations", invitation);
		model.addAttribute("SelectedItem", new SelectedItem());
		return "admin/invitationsLetterToSend";
	}

	@GetMapping("/YouHaveBeenInvited/{id}/{email}")
	public String showInvitationLetterByMember(@PathVariable("id") String id, @PathVariable("email") String email,
			Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		List<Invitations>all = invitation_Service.findAll_Invitations();
		List<Invitations>active= new ArrayList<Invitations>();
		
		for (Invitations i : all) {
			
			if (i.getActive().equalsIgnoreCase("Yes")) {
				 active.add(i);
			
			}
				model.addAttribute("invitation", active);
				
			}	
		MemberResponse member_response = new MemberResponse();
		model.addAttribute("Member", members_Services.findByEmail_Member(email));
		model.addAttribute("invitations", invitation);
		model.addAttribute("SelectedItem", new SelectedItem());
		model.addAttribute("MemberResponse", member_response);
		
		return "/YouHaveBeenInvited";
	}

	@RequestMapping(value = { "/invitation_by_id/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public String Show_invitation(@PathVariable("id") String invitationId, Model theModel) {

		Invitations invitation = invitation_Service.findById_Invitation(invitationId);
		if (invitation.equals(null)) {

			return "admin/noInvitationsFound";

		} else {
			theModel.addAttribute("invitations", invitation);

			DateTime dt = new DateTime(invitation.getStartingDateTime());
			System.out.println(dt);
			if (dt.isAfterNow()) {
				invitation.setActive("Yes");
				invitation_Service.save_Invitation(invitation);
			} else {
				invitation.setActive("No");
				invitation_Service.save_Invitation(invitation);
			}
		}

		return "admin/invitation_by_Id";
	}

	@GetMapping("/member_list_accepted/{id}")
	public String member_list_accepted(@PathVariable("id") String invitationId, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(invitationId);
		List<Members> accepted = invitation.getListOfMembersAccepted();
		if (invitation.getListOfMembersAccepted().isEmpty()) {

			return "admin/noMembersFound";

		} else {

			model.addAttribute("Members", accepted);
			model.addAttribute("invitations", invitation.getId());

		}

		return "admin/list_of_member_accepted";
	}

	@GetMapping("/member_list_rejected/{id}")
	public String member_list_rejected(@PathVariable("id") String invitationId, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(invitationId);
		List<Members> rejected = invitation.getListOfMembersRejected();

		if (invitation.getListOfMembersRejected().isEmpty()) {

			return "admin/noMembersFound";
		} else {

			model.addAttribute("Members", rejected);
			model.addAttribute("invitations", invitation.getId());

		}

		return "admin/list_of_member_rejected";
	}

	@GetMapping("/member_list_invited/{id}")
	public String member_list_ivited(@PathVariable("id") String invitationId, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(invitationId);

		List<Members> invited = invitation.getListOfMembersInvited();
		List<Members> accepted = invitation.getListOfMembersAccepted();
		List<Members> rejected = invitation.getListOfMembersRejected();

		if (invitation.getListOfMembersInvited().isEmpty()) {
			return "admin/noMembersInvited";

		} else {
			model.addAttribute("Members", invited);
			model.addAttribute("invitations", invitation.getId());
			model.addAttribute("accepted", accepted.size());
			model.addAttribute("rejected", rejected.size());
			model.addAttribute("notReliedYet", invited.size() - (accepted.size() + rejected.size()));

		}

		return "admin/list_of_member_invited";
	}

	@GetMapping("/Invitation_full_informations/{id}")
	public String showInformations(@PathVariable("id") String id, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(id);
		List<Members> invited = invitation.getListOfMembersInvited();
		List<Members> rejected = invitation.getListOfMembersRejected();
		List<Members> accepted = invitation.getListOfMembersAccepted();
		List<MemberInvitations> updateList = new ArrayList<MemberInvitations>();
		List<Members> reject_accept = new ArrayList<Members>();
		reject_accept.addAll(rejected);
		reject_accept.addAll(accepted);
		if (invitation.getListOfMembersInvited().isEmpty()) {
			return "admin/noMembersInvited";
		} else {

			model.addAttribute("MembersRejected", rejected);
			model.addAttribute("MembersAccepted", accepted);
			model.addAttribute("MembersInvited", invited);
			model.addAttribute("invitations", invitation);
			model.addAttribute("invitationId", invitation.getId());
			model.addAttribute("accepted", accepted.size());
			model.addAttribute("rejected", rejected.size());
			model.addAttribute("notReliedYet", invited.size() - (accepted.size() + rejected.size()));
			model.addAttribute("InformationsForm", new Informations());

			for (Members member : invited) {

				if (reject_accept.contains(member)) {

					List<MemberInvitations> response = member.getInvitationDetails();

					for (MemberInvitations mi : response) {

						if (mi.getInvitationId().equals(id)) {
							updateList.add(mi);
							System.out.println(updateList);
							model.addAttribute("response", updateList);

						}
					}
				}

			}

		}
		return "/Invitation_full_informations";
	}

	@GetMapping("/SendInvitationDetails/{id}")
	public String SendInvitationForm(@PathVariable("id") String invitationId, Model model) {
		Invitations invitation = invitation_Service.findById_Invitation(invitationId);
		model.addAttribute("invitations", invitation.getId());
		model.addAttribute("InformationsForm", new Informations());
		return "admin/SendInvitationDetails";
	}

	@GetMapping("/SendReminder/{id}")
	public String SendReminder(@PathVariable("id") String invitationId, Model model) {

		Invitations invitation = invitation_Service.findById_Invitation(invitationId);
		model.addAttribute("invitations", invitation.getId());

		List<Members> invited = invitation.getListOfMembersInvited();
		List<Members> accepted = invitation.getListOfMembersAccepted();
		List<Members> reject = invitation.getListOfMembersRejected();

		List<Members> replied = new ArrayList<Members>();

		replied.addAll(reject);
		replied.addAll(accepted);

		for (Members member : replied) {

			if (invited.contains(member)) {
				invited.remove(member);

				if (invited.isEmpty()) {

					return "/all_members_replied_already";

				} else {

					model.addAttribute("Member", invited);
					model.addAttribute("SelectedItem", new SelectedItem());
				}

			}
		}

		return "admin/SendReminder";
	}

	@RequestMapping(value = { "/sign" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sign");
		return modelAndView;
	}

	@RequestMapping(value = { "/feedback" }, method = RequestMethod.GET)
	public ModelAndView feedback(@Valid ContactUs contactUs, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ContactUs", contactUs);
		modelAndView.setViewName("feedback");
		return modelAndView;
	}

	@RequestMapping(value = { "/feedbackmsg" }, method = RequestMethod.GET)
	public ModelAndView feedbackmsg(@Valid ContactUs contactUs, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("feedbackmsg");
		return modelAndView;
	}
}
