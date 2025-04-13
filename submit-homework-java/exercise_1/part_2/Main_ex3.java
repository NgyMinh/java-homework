package part_2;

import part_2.Customer;  

class Account {
    private int id;
    private Customer customer;
    private double balance = 0.0;

    public Account(int id, Customer customer, double balance) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
    }

    public Account(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public Account deposit(double amount) {
        balance += amount;
        return this;
    }

    public Account withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Amount withdrawn exceeds the current balance!");
        }
        return this;
    }

    @Override
    public String toString() {
        return customer.toString() + " balance=$" + String.format("%.2f", balance);
    }
}

public class Main_ex3 {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "John Doe", 'm'); // Đối tượng Customer từ package part_2.models
        Account account1 = new Account(101, customer1, 500.0);

        System.out.println(account1);

        account1.deposit(200.0);
        System.out.println("After deposit: " + account1);

        account1.withdraw(100.0);
        System.out.println("After withdrawal: " + account1);

        account1.withdraw(700.0);
    }
}
