package part_2;

class Customer {
	private int id;
	private String name;
	private int discount;

	public Customer(int id, String name, int discount) {
		this.id = id;
		this.name = name;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return name + "(" + id + ")(" + discount + "%)";
	}
}

class Invoice {
	private int id;
	private Customer customer;
	private double amount;

	public Invoice(int id, Customer customer, double amount) {
		this.id = id;
		this.customer = customer;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customer.getId();
	}

	public String getCustomerName() {
		return customer.getName();
	}

	public int getCustomerDiscount() {
		return customer.getDiscount();
	}

	public double getAmountAfterDiscount() {
		return amount - (amount * customer.getDiscount() / 100);
	}

	@Override
	public String toString() {
		return "Invoice[id=" + id + ",customer=" + customer.toString() + ",amount=" + amount + "]";
	}
}

public class Main_ex2 {
	public static void main(String[] args) {
		Customer customer = new Customer(1, "John Doe", 10);
		Invoice invoice = new Invoice(101, customer, 500.0);

		System.out.println(customer);
		System.out.println(invoice);

		System.out.println("Customer ID: " + invoice.getCustomerId());
		System.out.println("Customer Name: " + invoice.getCustomerName());
		System.out.println("Customer Discount: " + invoice.getCustomerDiscount() + "%");
		System.out.println("Amount after discount: " + invoice.getAmountAfterDiscount());
	}
}
