package Lexicon.Reserve.utilities;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;




public class MemberResponse {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MemberResponseId;

	@NotEmpty
	private String  memberEmail;
	
	@NotEmpty
	private String invitationId;
	
	
	private String accepted;
	
	
	private String memberNotes;

	public int getMemberResponseId() {
		return MemberResponseId;
	}

	public void setMemberResponseId(int memberResponseId) {
		MemberResponseId = memberResponseId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}


	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getMemberNotes() {
		return memberNotes;
	}

	public void setMemberNotes(String memberNotes) {
		this.memberNotes = memberNotes;
	}
	
	
}
