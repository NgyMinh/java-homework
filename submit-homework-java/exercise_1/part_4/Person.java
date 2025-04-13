package part_4;

class Person {
	private String name;
	private String address;

	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("Person[name=%s,address=%s]", name, address);
	}
}

class Student extends Person {
	private String program;
	private int year;
	private double fee;

	public Student(String name, String address, String program, int year, double fee) {
		super(name, address);
		this.program = program;
		this.year = year;
		this.fee = fee;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return String.format("Student[%s,program=%s,year=%d,fee=%.2f]", super.toString(), program, year, fee);
	}
}

class Staff extends Person {
	private String school;
	private double pay;

	public Staff(String name, String address, String school, double pay) {
		super(name, address);
		this.school = school;
		this.pay = pay;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return String.format("Staff[%s,school=%s,pay=%.2f]", super.toString(), school, pay);
	}

	public static void main(String[] args) {
		Person person = new Person("Alice", "123 Main St");
		System.out.println(person);

		Student student = new Student("Bob", "456 University Ave", "Computer Science", 2, 5000.0);
		System.out.println(student);

		Staff staff = new Staff("Charlie", "789 High School Rd", "XYZ School", 4500.0);
		System.out.println(staff);
	}
}
