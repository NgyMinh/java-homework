package part_1;

public class Rectangle {
	private float length = 1.0f;
	private float width = 1.0f;

	public Rectangle() {
	}

	public Rectangle(float length, float width) {
		this.length = length;
		this.width = width;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public double getArea() {
		return length * width;
	}

	public double getPerimeter() {
		return 2 * (length + width);
	}

	public String toString() {
		return "Rectangle[length=" + length + ", width=" + width + "]";
	}

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle();
		System.out.println(r1);

		Rectangle r2 = new Rectangle(4.5f, 3.2f);
		System.out.println(r2);
		System.out.println("Area: " + r2.getArea());
		System.out.println("Perimeter: " + r2.getPerimeter());

		r2.setLength(5.0f);
		r2.setWidth(2.5f);
		System.out.println("Updated " + r2);
	}
}
