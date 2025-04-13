package part_6;

interface Movable {
	void moveUp();

	void moveDown();

	void moveLeft();

	void moveRight();
}

class MovablePoint implements Movable {
	int x, y;
	int xSpeed, ySpeed;

	public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public void moveUp() {
		y -= ySpeed;
	}

	@Override
	public void moveDown() {
		y += ySpeed;
	}

	@Override
	public void moveLeft() {
		x -= xSpeed;
	}

	@Override
	public void moveRight() {
		x += xSpeed;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ") speed=(" + xSpeed + ", " + ySpeed + ")";
	}

	public static void main(String[] args) {
		MovablePoint point = new MovablePoint(5, 5, 2, 3);
		System.out.println(point);
		point.moveUp();
		System.out.println("After moveUp: " + point);
		point.moveDown();
		System.out.println("After moveDown: " + point);
		point.moveLeft();
		System.out.println("After moveLeft: " + point);
		point.moveRight();
		System.out.println("After moveRight: " + point);
	}
}
