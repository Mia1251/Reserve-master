package Lexicon.Reserve.utilities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor

public class MemberInvitations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "InvitationNumber")
	private String invitationId;

	@Column(name = "memberResponse")
	private String Accepted;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "date")
	private String date;

	@Column(name = "memberNotes")
	private String memberNotes;
	
	@Column(name = "member_email")
	private String member_email;
	
	


	public MemberInvitations(String invitationId, String accepted, String subject, String date, String memberNotes,
			String member_email) {
		super();
		this.invitationId = invitationId;
		Accepted = accepted;
		this.subject = subject;
		this.date = date;
		this.memberNotes = memberNotes;
		this.member_email = member_email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MemberInvitations() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccepted() {
		return Accepted;
	}

	public void setAccepted(String accepted) {
		Accepted = accepted;
	}

	public String getMemberNotes() {
		return memberNotes;
	}

	public void setMemberNotes(String memberNotes) {
		this.memberNotes = memberNotes;
	}

	public String getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Accepted == null) ? 0 : Accepted.hashCode());
		result = prime * result + id;
		result = prime * result + ((invitationId == null) ? 0 : invitationId.hashCode());
		result = prime * result + ((memberNotes == null) ? 0 : memberNotes.hashCode());
		result = prime * result + ((member_email == null) ? 0 : member_email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberInvitations other = (MemberInvitations) obj;
		if (Accepted == null) {
			if (other.Accepted != null)
				return false;
		} else if (!Accepted.equals(other.Accepted))
			return false;
		if (id != other.id)
			return false;
		if (invitationId == null) {
			if (other.invitationId != null)
				return false;
		} else if (!invitationId.equals(other.invitationId))
			return false;
		if (memberNotes == null) {
			if (other.memberNotes != null)
				return false;
		} else if (!memberNotes.equals(other.memberNotes))
			return false;
		if (member_email == null) {
			if (other.member_email != null)
				return false;
		} else if (!member_email.equals(other.member_email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberInvitations [id=");
		builder.append(id);
		builder.append(", invitationId=");
		builder.append(invitationId);
		builder.append(", Accepted=");
		builder.append(Accepted);
		builder.append(", memberNotes=");
		builder.append(memberNotes);
		builder.append(", member_email=");
		builder.append(member_email);
		builder.append("]");
		return builder.toString();
	}

	
	
}