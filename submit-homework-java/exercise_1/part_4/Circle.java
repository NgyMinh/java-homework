package part_4;

class Circle {
	private double radius = 1.0;
	private String color = "red";

	public Circle() {
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, String color) {
		this.radius = radius;
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public String toString() {
		return String.format("Circle[radius=%.2f,color=%s]", radius, color);
	}
}

class Cylinder extends Circle {
	private double height = 1.0;

	public Cylinder() {
		super();
	}

	public Cylinder(double radius) {
		super(radius);
	}

	public Cylinder(double radius, double height) {
		super(radius);
		this.height = height;
	}

	public Cylinder(double radius, double height, String color) {
		super(radius, color);
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getVolume() {
		return getArea() * height;
	}

	@Override
	public String toString() {
		return String.format("Cylinder[%s,height=%.2f]", super.toString(), height);
	}

	public static void main(String[] args) {
		Circle circle = new Circle(5.0, "blue");
		System.out.println(circle);
		System.out.println("Area: " + circle.getArea());

		Cylinder cylinder = new Cylinder(3.0, 7.0, "green");
		System.out.println(cylinder);
		System.out.println("Volume: " + cylinder.getVolume());
	}
}
