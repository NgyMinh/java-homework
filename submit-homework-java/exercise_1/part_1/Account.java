package part_1;

public class Account {

	private String id;
	private String name;
	private int balance = 0;

	// Constructor 1: Chỉ có id và name, balance mặc định là 0
	public Account(String id, String name) {
		this.id = id;
		this.name = name;
	}

	// Constructor 2: Khởi tạo với id, name và balance
	public Account(String id, String name, int balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	// Nạp tiền vào tài khoản
	public int credit(int amount) {
		balance += amount;
		return balance;
	}

	// Rút tiền nếu số dư đủ, ngược lại in ra thông báo lỗi
	public int debit(int amount) {
		if (amount <= balance) {
			balance -= amount;
		} else {
			System.out.println("Amount exceeded balance");
		}
		return balance;
	}

	// Chuyển tiền sang tài khoản khác
	public int transferTo(Account anotherAccount, int amount) {
		if (amount <= balance) {
			this.balance -= amount;
			anotherAccount.balance += amount;
		} else {
			System.out.println("Amount exceeded balance");
		}
		return this.balance;
	}

	// Hiển thị thông tin tài khoản
	public String toString() {
		return "Account[id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

	public static void main(String[] args) {
		Account acc1 = new Account("A1001", "John Doe", 500);
		Account acc2 = new Account("A1002", "Jane Smith", 300);

		System.out.println(acc1);
		System.out.println(acc2);

		acc1.credit(200);
		System.out.println("After crediting 200: " + acc1);

		acc1.debit(100);
		System.out.println("After debiting 100: " + acc1);

		acc1.transferTo(acc2, 400);
		System.out.println("After transferring 400 to acc2:");
		System.out.println(acc1);
		System.out.println(acc2);
	}
}
