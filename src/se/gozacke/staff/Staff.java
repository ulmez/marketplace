package se.gozacke.staff;

public class Staff {
	private final int staffId;
	private int departmentId;
	private int salary;
	private String firstName;
	private String surName;
	private String dateOfBirth;
	private String streetAddress;
	private String town;
	private String postCode;
	private String mobile;
	private String email;
	
	public Staff(int staffId) {
		this.staffId = staffId;
		departmentId = 0;
		salary = 0;
		firstName = null;
		surName = null;
		dateOfBirth = null;
		streetAddress = null;
		town = null;
		postCode = null;
		mobile = null;
		email = null;
	}
	
	public int getStaffId() {
		return staffId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		String staffInfo = "";
		
		staffInfo += "staffId: " + staffId + "\n";
		staffInfo += "salary: " + salary + "\n";
		staffInfo += "firstName: " + firstName + "\n";
		staffInfo += "surName: " + surName + "\n";
		staffInfo += "dateOfBirth: " + dateOfBirth + "\n";
		staffInfo += "streetAddress: " + streetAddress + "\n";
		staffInfo += "town: " + town + "\n";
		staffInfo += "postCode: " + postCode + "\n";
		staffInfo += "mobile: " + mobile + "\n";
		staffInfo += "email: " + email + "\n";
		
		return staffInfo;
	}
}