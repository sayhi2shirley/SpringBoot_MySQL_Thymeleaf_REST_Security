package shangrila.council.app.web.dto;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class ResidentUserRegDTO {

	/* A DTO(Data Transfer Object) is a server-side value object which 
	 * stores data using the presentation layer representation.
	 * We separate DTOs into those received by the server ( Request ),
	 * and those returned by the server ( Response ). They are
	 * automatically serialised/deserialised by Spring 
	 * This is used to transfer the data from server to client or client
	 *  to server. Bulk amount information is also passed using DTO object. 
	 */
	
	private String email;

	private String fullName;	
	private Date dateOfBirth;
	
	private String homeAddress;
	
	private String password;
	private String sniNumber;
	private int admin;	
	
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public ResidentUserRegDTO() {}
	
	public ResidentUserRegDTO(String email, String fullName, 
			Date dateOfBirth, String homeAddress, 
			String password, String sniNumber, int admin) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.password = password;
		this.sniNumber = sniNumber;
		this.admin = admin;
		System.out.println("DTO: ResidentUserRegDTO\n");
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
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

}
