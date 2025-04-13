package part_4;
class Point2D {
	protected float x;
	protected float y;

	public Point2D() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Point2D(float x, float y) {
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
		return String.format("(%f, %f)", x, y);
	}
}

class Point3D extends Point2D {
	private float z;

	public Point3D() {
		super();
		this.z = 0.0f;
	}

	public Point3D(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public void setXYZ(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float[] getXYZ() {
		return new float[] { x, y, z };
	}

	@Override
	public String toString() {
		return String.format("(%f, %f, %f)", x, y, z);
	}

	public static void main(String[] args) {
		Point2D point2D = new Point2D(1.5f, 2.5f);
		System.out.println(point2D);

		Point3D point3D = new Point3D(3.0f, 4.0f, 5.0f);
		System.out.println(point3D);
	}
}
