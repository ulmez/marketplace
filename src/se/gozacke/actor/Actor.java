package se.gozacke.actor;

public class Actor {
	private int actorId;
	private String firstName;
	private String surName;
	private String dob;
	
	public Actor() {
		actorId = 0;
		firstName = "";
		surName = "";
		dob = "";
	}
	
	public Actor(int actorId) {
		this.actorId = actorId;
		firstName = "";
		surName = "";
	}
	
	public int getActorId() {
		return actorId;
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
	
	@Override
	public String toString() {
		String actorInfo = "";
		
		actorInfo += "actorId: " + actorId + "\n";
		actorInfo += "firstName: " + firstName + "\n";
		actorInfo += "surName: " + surName + "\n";
		actorInfo += "dob: " + dob + "\n";
		
		return actorInfo;
	}
}