package Lexicon.Reserve.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "invitations")
public class Invitations {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "uuid", unique = true)
	private String id;
	@Column(name = "descriptions")
	@NotEmpty(message = "*Please provide your descriptions")
	private String descriptions;
	@Column(name = "clothes_Type")
	@NotEmpty(message = "*Please provide clothes Type")
	private String clothesType;
	@Column(name = "event_Subject")
	@NotEmpty(message = "*Please provide event Subject")
	private String eventSubject;
	@Column(name = "food_Menu")
	@NotEmpty(message = "*Please provide food Menu")
	private String foodMenu;
	@Column(name = "starting_Date_Time")
	private String startingDateTime;
	@Column(name = "ending_Date_Time")
	private String endingDateTime;
	@Column(name = "location")
	@NotEmpty(message = "*Please provide location")
	private String location;
	@Column(name = "Notes")
	@NotEmpty(message = "*Please provide Notes")
	private String Notes;
	@ManyToMany
	@Column(name = "ListOfMembersAccepted")
	private List<Members> ListOfMembersAccepted;
	@ManyToMany
	@Column(name = "ListOfMembersRejected")
	private List<Members> ListOfMembersRejected;
	@ManyToMany
	@Column(name = "ListOfMembersInvited")
	private List<Members> ListOfMembersInvited;
	@Column(name = "active")
	private String active;
	@Column(name = "sent")
	private String sent;

	public Invitations() {
	}



	public Invitations(@NotEmpty(message = "*Please provide your descriptions") String descriptions,
			@NotEmpty(message = "*Please provide clothes Type") String clothesType,
			@NotEmpty(message = "*Please provide event Subject") String eventSubject,
			@NotEmpty(message = "*Please provide food Menu") String foodMenu, String startingDateTime,
			String endingDateTime, @NotEmpty(message = "*Please provide location") String location,
			@NotEmpty(message = "*Please provide Notes") String notes) {
		super();
		this.descriptions = descriptions;
		this.clothesType = clothesType;
		this.eventSubject = eventSubject;
		this.foodMenu = foodMenu;
		this.startingDateTime = startingDateTime;
		this.endingDateTime = endingDateTime;
		this.location = location;
		Notes = notes;
		
	}



	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getClothesType() {
		return clothesType;
	}

	public void setClothesType(String clothesType) {
		this.clothesType = clothesType;
	}

	public String getEventSubject() {
		return eventSubject;
	}

	public void setEventSubject(String eventSubject) {
		this.eventSubject = eventSubject;
	}

	public String getFoodMenu() {
		return foodMenu;
	}

	public void setFoodMenu(String foodMenu) {
		this.foodMenu = foodMenu;
	}

	public String getStartingDateTime() {
		return startingDateTime;
	}

	public void setStartingDateTime(String startingDateTime) {
		this.startingDateTime = startingDateTime;
	}

	public String getEndingDateTime() {
		return endingDateTime;
	}

	public void setEndingDateTime(String endingDateTime) {
		
		this.endingDateTime = endingDateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public List<Members> getListOfMembersAccepted() {
		return ListOfMembersAccepted;
	}

	public void setListOfMembersAccepted(List<Members> listOfMembersAccepted) {
		ListOfMembersAccepted = listOfMembersAccepted;
	}

	public List<Members> getListOfMembersRejected() {
		return ListOfMembersRejected;
	}

	public void setListOfMembersRejected(List<Members> listOfMembersRejected) {
		ListOfMembersRejected = listOfMembersRejected;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public List<Members> getListOfMembersInvited() {
		return ListOfMembersInvited;
	}



	public void setListOfMembersInvited(List<Members> listOfMembersInvited) {
		ListOfMembersInvited = listOfMembersInvited;
	}



	public String getActive() {
		return active;
	}



	public void setActive(String active) {
		this.active = active;
	}



	public String getSent() {
		return sent;
	}



	public void setSent(String sent) {
		this.sent = sent;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ListOfMembersAccepted == null) ? 0 : ListOfMembersAccepted.hashCode());
		result = prime * result + ((ListOfMembersInvited == null) ? 0 : ListOfMembersInvited.hashCode());
		result = prime * result + ((ListOfMembersRejected == null) ? 0 : ListOfMembersRejected.hashCode());
		result = prime * result + ((Notes == null) ? 0 : Notes.hashCode());
		result = prime * result + ((clothesType == null) ? 0 : clothesType.hashCode());
		result = prime * result + ((descriptions == null) ? 0 : descriptions.hashCode());
		result = prime * result + ((endingDateTime == null) ? 0 : endingDateTime.hashCode());
		result = prime * result + ((eventSubject == null) ? 0 : eventSubject.hashCode());
		result = prime * result + ((foodMenu == null) ? 0 : foodMenu.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((startingDateTime == null) ? 0 : startingDateTime.hashCode());
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
		Invitations other = (Invitations) obj;
		if (ListOfMembersAccepted == null) {
			if (other.ListOfMembersAccepted != null)
				return false;
		} else if (!ListOfMembersAccepted.equals(other.ListOfMembersAccepted))
			return false;
		if (ListOfMembersInvited == null) {
			if (other.ListOfMembersInvited != null)
				return false;
		} else if (!ListOfMembersInvited.equals(other.ListOfMembersInvited))
			return false;
		if (ListOfMembersRejected == null) {
			if (other.ListOfMembersRejected != null)
				return false;
		} else if (!ListOfMembersRejected.equals(other.ListOfMembersRejected))
			return false;
		if (Notes == null) {
			if (other.Notes != null)
				return false;
		} else if (!Notes.equals(other.Notes))
			return false;
		if (clothesType == null) {
			if (other.clothesType != null)
				return false;
		} else if (!clothesType.equals(other.clothesType))
			return false;
		if (descriptions == null) {
			if (other.descriptions != null)
				return false;
		} else if (!descriptions.equals(other.descriptions))
			return false;
		if (endingDateTime == null) {
			if (other.endingDateTime != null)
				return false;
		} else if (!endingDateTime.equals(other.endingDateTime))
			return false;
		if (eventSubject == null) {
			if (other.eventSubject != null)
				return false;
		} else if (!eventSubject.equals(other.eventSubject))
			return false;
		if (foodMenu == null) {
			if (other.foodMenu != null)
				return false;
		} else if (!foodMenu.equals(other.foodMenu))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (startingDateTime == null) {
			if (other.startingDateTime != null)
				return false;
		} else if (!startingDateTime.equals(other.startingDateTime))
			return false;
		return true;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invitations [id=");
		builder.append(id);
		builder.append(", descriptions=");
		builder.append(descriptions);
		builder.append(", clothesType=");
		builder.append(clothesType);
		builder.append(", eventSubject=");
		builder.append(eventSubject);
		builder.append(", foodMenu=");
		builder.append(foodMenu);
		builder.append(", startingDateTime=");
		builder.append(startingDateTime);
		builder.append(", endingDateTime=");
		builder.append(endingDateTime);
		builder.append(", location=");
		builder.append(location);
		builder.append(", Notes=");
		builder.append(Notes);
		builder.append(", ListOfMembersAccepted=");
		builder.append(ListOfMembersAccepted);
		builder.append(", ListOfMembersRejected=");
		builder.append(ListOfMembersRejected);
		builder.append(", ListOfMembersInvited=");
		builder.append(ListOfMembersInvited);
		builder.append("]");
		return builder.toString();
	}

}
