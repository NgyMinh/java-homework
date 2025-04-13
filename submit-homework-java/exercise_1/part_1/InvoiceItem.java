package part_1;

public class InvoiceItem {
	private String id;
	private String desc;
	private int qty;
	private double unitPrice;

	public InvoiceItem(String id, String desc, int qty, double unitPrice) {
		this.id = id;
		this.desc = desc;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotal() {
		return unitPrice * qty;
	}

	public String toString() {
		return "InvoiceItem[id=" + id + ", desc=" + desc + ", qty=" + qty + ", unitPrice=" + unitPrice + "]";
	}

	public static void main(String[] args) {
		InvoiceItem item1 = new InvoiceItem("A101", "Laptop", 2, 750.50);
		System.out.println(item1);

		System.out.println("Total price: $" + item1.getTotal());

		item1.setQty(3);
		item1.setUnitPrice(720.75);
		System.out.println("Updated " + item1);
		System.out.println("Updated total price: $" + item1.getTotal());
	}
}
