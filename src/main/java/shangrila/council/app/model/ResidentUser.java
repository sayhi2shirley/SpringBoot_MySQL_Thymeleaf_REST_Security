package shangrila.council.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* Resident_user is a Domain class or JPA entity */
@Entity
@Table(name = "resident_user", uniqueConstraints={@UniqueConstraint(columnNames = {"SNI" , "Email"})})
public class ResidentUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private int userID;

	@Column(name="FullName", nullable=false)
	private String fullName;

	@Column(name="DoB")
	private String dateOfBirth;

	@Column(name="Address", nullable=false)
	private String homeAddress;

	@Column(name="Email", unique=true, nullable=false)
	private String email;

	@Column(name="PasswordHash", nullable=false)
	private String password;

	@Column(name="SNI", unique=true, nullable=false)
	private String sniNumber;

	@Column(name="admin")
	private int admin;
	public ResidentUser() {  }

	public ResidentUser(String fullName, String dateOfBirth, String homeAddress,
			String email, String password, String sniNumber, int admin) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.email = email;
		this.password = password;
		this.sniNumber = sniNumber;
		this.admin = admin;

		System.out.println("Model: ResidentUser\n");
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSniNumber() {
		return sniNumber;
	}

	public void setSniNumber(String sniNumber) {
		this.sniNumber = sniNumber;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public boolean hasRole(int admin) {
		System.out.println("User: hasRole\n");


		if (this.getAdmin() == admin) {
			System.out.println("Model: Compare \n" + this.getEmail() + " " + 
					this.getAdmin() + " Admin " + 	admin);
			return true;
		}

		return false;
	}

}
