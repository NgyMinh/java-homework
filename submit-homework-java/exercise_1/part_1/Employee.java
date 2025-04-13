package part_1;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private int salary;

	public Employee(int id, String firstName, String lastName, int salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAnnualSalary() {
		return salary * 12;
	}

	public int raiseSalary(int percent) {
		salary += salary * percent / 100;
		return salary;
	}

	public String toString() {
		return "Employee[id=" + id + ", name=" + getName() + ", salary=" + salary + "]";
	}

	public static void main(String[] args) {
		Employee emp1 = new Employee(1, "John", "Doe", 5000);
		System.out.println(emp1);

		System.out.println("Annual Salary: " + emp1.getAnnualSalary());

		emp1.raiseSalary(10);
		System.out.println("After 10% raise: " + emp1);

		emp1.setSalary(7000);
		System.out.println("Updated salary: " + emp1);
	}
}
