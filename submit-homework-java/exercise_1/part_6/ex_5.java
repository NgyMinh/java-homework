package part_6;

interface GeometricObject {
	double getPerimeter();

	double getArea();
}

interface Resizable {
	void resize(int percent);
}

class Circle2 implements GeometricObject {
	protected double radius;

	public Circle2(double radius) {
		this.radius = radius;
	}

	@Override
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public String toString() {
		return "Circle[radius=" + radius + "]";
	}
}

class ResizableCircle extends Circle implements Resizable {
	public ResizableCircle(double radius) {
		super(radius);
	}

	@Override
	public void resize(int percent) {
		radius *= percent / 100.0;
	}

	@Override
	public String toString() {
		return "ResizableCircle[Circle[radius=" + radius + "]]";
	}

	public static void main(String[] args) {
		Circle circle = new Circle(10);
		System.out.println(circle);
		System.out.println("Perimeter: " + circle.getPerimeter());
		System.out.println("Area: " + circle.getArea());

		ResizableCircle resizableCircle = new ResizableCircle(10);
		System.out.println(resizableCircle);
		resizableCircle.resize(50);
		System.out.println("After resize: " + resizableCircle);
	}
}
