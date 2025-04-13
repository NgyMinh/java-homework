package part_2;

import part_2.MyPoint;

class MyCircle {
	private MyPoint center;
	private int radius;

	public MyCircle() {
		this.center = new MyPoint(0, 0);
		this.radius = 1;
	}

	public MyCircle(int x, int y, int radius) {
		this.center = new MyPoint(x, y);
		this.radius = radius;
	}

	public MyCircle(MyPoint center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public MyPoint getCenter() {
		return center;
	}

	public void setCenter(MyPoint center) {
		this.center = center;
	}

	public int getCenterX() {
		return center.getX();
	}

	public void setCenterX(int x) {
		center.setX(x);
	}

	public int getCenterY() {
		return center.getY();
	}

	public void setCenterY(int y) {
		center.setY(y);
	}

	public int[] getCenterXY() {
		return center.getXY();
	}

	public void setCenterXY(int x, int y) {
		center.setXY(x, y);

	}

	public String getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCircumference() {
		// TODO Auto-generated method stub
		return null;
	}
}
public class Main_ex5 {
    public static void main(String[] args) {
        // Tạo một đối tượng MyCircle với tọa độ tâm (3,4) và bán kính 5
        MyCircle circle = new MyCircle(3, 4, 5);
        
        System.out.println("Circle: " + circle);
        System.out.println("Center X: " + circle.getCenterX());
        System.out.println("Center Y: " + circle.getCenterY());
        System.out.println("Radius: " + circle.getRadius());
        System.out.println("Area: " + circle.getArea());
        System.out.println("Circumference: " + circle.getCircumference());

        // Thay đổi tọa độ tâm
        circle.setCenterXY(10, 10);
        System.out.println("Updated Circle: " + circle);
    }
}





