package part_5;

class Circle {
	private double radius;
	private String color;

	public Circle() {
		this.radius = 1.0;
		this.color = "red";
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
		return "Circle[radius=" + radius + ", color=" + color + "]";
	}
}

class Cylinder {
	private Circle base; 
	private double height;

	public Cylinder() {
		base = new Circle(); 
		height = 1.0;
	}

	public Cylinder(double radius, String color, double height) {
		base = new Circle(radius, color);
		this.height = height;
	}

	public double getRadius() {
		return base.getRadius();
	}

	public void setRadius(double radius) {
		base.setRadius(radius);
	}

	public String getColor() {
		return base.getColor();
	}

	public void setColor(String color) {
		base.setColor(color);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getVolume() {
		return base.getArea() * height;
	}

	@Override
	public String toString() {
		return "Cylinder[base=" + base + ", height=" + height + ", volume=" + getVolume() + "]";
	}
}

class TestCylinder {
	public static void main(String[] args) {
		Cylinder c1 = new Cylinder();
		System.out.println(c1);

		Cylinder c2 = new Cylinder(3.0, "blue", 5.0);
		System.out.println(c2);
	}
}
