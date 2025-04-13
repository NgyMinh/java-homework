package models;

public class Class {
	private int classID;
	private String description;
	private int numberOfCredits;

	public Class() {
	}

	public Class(int classID, String description, int numberOfCredits) {
		this.classID = classID;
		this.description = description;
		this.numberOfCredits = numberOfCredits;
	}

	
	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
}