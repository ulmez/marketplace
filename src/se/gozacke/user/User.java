package se.gozacke.user;

public class User {
	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String surName;
	private String streetAddress;
	private String postCode;
	private String town;
	private String telephone;
	
	public User() {
		
	}
	
	public User(int userId) {
		this.userId = userId;
		email = null;
		password = null;
		firstName = null;
		surName = null;
		streetAddress = null;
		postCode = null;
		town = null;
		telephone = null;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public String toString() {
		String userInfo = "";
		
		userInfo += "userId: " + userId + "\n";
		userInfo += "email: " + email + "\n";
		userInfo += "password: " + password + "\n";
		userInfo += "firstName: " + firstName + "\n";
		userInfo += "surName: " + surName + "\n";
		userInfo += "streetAddress: " + streetAddress + "\n";
		userInfo += "postCode: " + postCode + "\n";
		userInfo += "town: " + town + "\n";
		userInfo += "telephone: " + telephone + "\n";
		
		return userInfo;
	}
}