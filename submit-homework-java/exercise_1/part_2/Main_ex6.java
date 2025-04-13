package part_2;

class MyPoint4 {
	private int x, y;

	public MyPoint4(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getXY() {
		return new int[] { x, y };
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double distance(MyPoint4 another) {
		int dx = another.x - this.x;
		int dy = another.y - this.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

class MyTriangle {
	private MyPoint4 v1, v2, v3;

	public MyTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.v1 = new MyPoint4(x1, y1);
		this.v2 = new MyPoint4(x2, y2);
		this.v3 = new MyPoint4(x3, y3);
	}

	public MyTriangle(MyPoint4 v1, MyPoint4 v2, MyPoint4 v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	@Override
	public String toString() {
		return "MyTriangle[v1=" + v1 + ", v2=" + v2 + ", v3=" + v3 + "]";
	}

	public double getPerimeter() {
		double side1 = v1.distance(v2);
		double side2 = v2.distance(v3);
		double side3 = v3.distance(v1);
		return side1 + side2 + side3;
	}

	public String getType() {
		double side1 = v1.distance(v2);
		double side2 = v2.distance(v3);
		double side3 = v3.distance(v1);

		if (side1 == side2 && side2 == side3) {
			return "Equilateral";
		} else if (side1 == side2 || side2 == side3 || side3 == side1) {
			return "Isosceles";
		} else {
			return "Scalene";
		}
	}
}

public class Main_ex6 {
	public static void main(String[] args) {
		// Tạo 3 điểm
		MyPoint4 p1 = new MyPoint4(0, 0);
		MyPoint4 p2 = new MyPoint4(4, 0);
		MyPoint4 p3 = new MyPoint4(2, 3);

		MyTriangle triangle1 = new MyTriangle(p1, p2, p3);

		System.out.println(triangle1);
		System.out.println("Perimeter: " + triangle1.getPerimeter());
		System.out.println("Type: " + triangle1.getType());

		MyTriangle triangle2 = new MyTriangle(0, 0, 3, 0, 1, (int) Math.sqrt(3));
		System.out.println("\n" + triangle2);
		System.out.println("Perimeter: " + triangle2.getPerimeter());
		System.out.println("Type: " + triangle2.getType());
	}
}
