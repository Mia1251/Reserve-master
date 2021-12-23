package Lexicon.Reserve.utilities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Table(name = "invitationEmails")
public class SelectedItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name = "Emails")
	private List<String> email;
	@NotEmpty
	private String InvitationId;


	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public String getInvitationId() {
		return InvitationId;
	}

	public void setInvitationId(String invitationId) {
		InvitationId = invitationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
