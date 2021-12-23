package Lexicon.Reserve.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import com.opencsv.bean.CsvBindByName;
import Lexicon.Reserve.utilities.MemberInvitations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "members")
public class Members {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	@CsvBindByName
	private String firstName;
	@Column(name = "last_name")
	@CsvBindByName
	private String lastName;
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@CsvBindByName
	private String email;
	@Column(name = "mobile_phone")
	@CsvBindByName
	private String mobilePhone;
	@Column(name = "address")
	@CsvBindByName
	private String address;
	@OneToMany
	@Column(name = "invitations_List")
	private List<MemberInvitations> invitationDetails;
	@Column(name = "title")
	private String title;
	@Column(name="g")
	private String group;
	@Column (name = "styrelsen")
	private String styrelsen;


	public Members(String firstName, String lastName, @Email(message = "*Please provide a valid Email") String email,
			String mobilePhone, String address, String title, String group, String styrelsen) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.title = title;
		this.group = group;
		this.styrelsen = styrelsen;
	}


	public Members() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<MemberInvitations> getInvitationDetails() {
		return invitationDetails;
	}


	public void setInvitationDetails(List<MemberInvitations> invitationDetails) {
		this.invitationDetails = invitationDetails;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	public String getStyrelsen() {
		return styrelsen;
	}


	public void setStyrelsen(String styrelsen) {
		this.styrelsen = styrelsen;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + id;
		result = prime * result + ((invitationDetails == null) ? 0 : invitationDetails.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((styrelsen == null) ? 0 : styrelsen.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Members other = (Members) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id != other.id)
			return false;
		if (invitationDetails == null) {
			if (other.invitationDetails != null)
				return false;
		} else if (!invitationDetails.equals(other.invitationDetails))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (styrelsen == null) {
			if (other.styrelsen != null)
				return false;
		} else if (!styrelsen.equals(other.styrelsen))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Members [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobilePhone=");
		builder.append(mobilePhone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", invitationDetails=");
		builder.append(invitationDetails);
		builder.append(", title=");
		builder.append(title);
		builder.append(", group=");
		builder.append(group);
		builder.append(", styrelsen=");
		builder.append(styrelsen);
		builder.append("]");
		return builder.toString();
	}

	
	

}
