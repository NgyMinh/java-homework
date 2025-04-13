package part_1;

public class Circle_2 {
	private double radius = 1.0;

	public Circle_2() {
	}

	public Circle_2(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	public double getCircumference() {
		return 2 * Math.PI * radius;
	}

	public String toString() {
		return "Circle[radius=" + radius + "]";
	}

// Lớp TestCircle
	public static void main(String[] args) {
		Circle_2 c1 = new Circle_2();
		System.out.println(c1);

		Circle_2 c2 = new Circle_2(5.5);
		System.out.println(c2);
		System.out.println("Area: " + c2.getArea());
		System.out.println("Circumference: " + c2.getCircumference());

		c2.setRadius(3.0);
		System.out.println("Updated " + c2);
	}
}
