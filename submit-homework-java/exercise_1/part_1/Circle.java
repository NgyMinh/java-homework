package part_1;

public class Circle {
	private double radius = 1.0;
	private String color = "red";

	public Circle() {
	}

	public Circle(double r) {
		this.radius = r;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double r) {
		this.radius = r;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String c) {
		this.color = c;
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	public static void main(String[] args) {
		Circle c1 = new Circle();
		System.out.println(
				"Circle 1 - Radius: " + c1.getRadius() + ", Color: " + c1.getColor() + ", Area: " + c1.getArea());

		Circle c2 = new Circle(5.5);
		System.out.println(
				"Circle 2 - Radius: " + c2.getRadius() + ", Color: " + c2.getColor() + ", Area: " + c2.getArea());

		c1.setRadius(3.0);
		c1.setColor("blue");
		System.out.println("Updated Circle 1 - Radius: " + c1.getRadius() + ", Color: " + c1.getColor() + ", Area: "
				+ c1.getArea());
	}
}
