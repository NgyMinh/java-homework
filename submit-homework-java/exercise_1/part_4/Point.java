package part_4;

class Point {
	private float x = 0.0f;
	private float y = 0.0f;

	public Point() {
	}

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float[] getXY() {
		return new float[] { x, y };
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

class MovablePoint extends Point {
	private float xSpeed = 0.0f;
	private float ySpeed = 0.0f;

	public MovablePoint() {
		super();
	}

	public MovablePoint(float xSpeed, float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public MovablePoint(float x, float y, float xSpeed, float ySpeed) {
		super(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public float getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public void setSpeed(float xSpeed, float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public float[] getSpeed() {
		return new float[] { xSpeed, ySpeed };
	}

	@Override
	public String toString() {
		return super.toString() + ", speed=(" + xSpeed + "," + ySpeed + ")";
	}

	public MovablePoint move() {
		setXY(getX() + xSpeed, getY() + ySpeed);
		return this;
	}

	public static void main(String[] args) {
		Point point = new Point(1.5f, 2.5f);
		System.out.println("Point: " + point);

		MovablePoint movablePoint = new MovablePoint(1.5f, 2.5f, 0.5f, 0.5f);
		System.out.println("MovablePoint: " + movablePoint);
		movablePoint.move();
		System.out.println("After move: " + movablePoint);
	}
}
