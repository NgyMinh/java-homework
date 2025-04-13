package models;

public class Learn {
	private int learnID;
	private int studentID;
	private int classID;

	public Learn() {
	}

	public Learn(int learnID, int studentID, int classID) {
		this.learnID = learnID;
		this.studentID = studentID;
		this.classID = classID;
	}

	
	public int getLearnID() {
		return learnID;
	}

	public void setLearnID(int learnID) {
		this.learnID = learnID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
}