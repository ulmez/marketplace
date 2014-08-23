package se.gozacke.author;

public class Author {
	private int authorId;
	private String firstName;
	private String surName;
	private String dob;
	
	public Author() {
		authorId = 0;
		firstName = "";
		surName = "";
		dob = "";
	}
	
	public Author(int authorId) {
		this.authorId = authorId;
		firstName = "";
		surName = "";
		dob = "";
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAuthorId() {
		return authorId;
	}
	
	@Override
	public String toString() {
		String authorInfo = "";
		
		authorInfo += "authorId: " + authorId + "\n";
		authorInfo += "firstName: " + firstName + "\n";
		authorInfo += "surName: " + surName + "\n";
		authorInfo += "dob: " + dob + "\n";
		
		return authorInfo;
	}
}