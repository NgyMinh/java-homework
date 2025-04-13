package part_2;

class MyPoint {
	private int x;
	private int y;

	public MyPoint(int x, int y) {
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

	public double distance(MyPoint another) {
		int xDiff = this.x - another.x;
		int yDiff = this.y - another.y;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

class MyLine {
	private MyPoint begin;
	private MyPoint end;

	public MyLine(int x1, int y1, int x2, int y2) {
		this.begin = new MyPoint(x1, y1);
		this.end = new MyPoint(x2, y2);
	}

	public MyLine(MyPoint begin, MyPoint end) {
		this.begin = begin;
		this.end = end;
	}

	public MyPoint getBegin() {
		return begin;
	}

	public void setBegin(MyPoint begin) {
		this.begin = begin;
	}

	public MyPoint getEnd() {
		return end;
	}

	public void setEnd(MyPoint end) {
		this.end = end;
	}

	public int getBeginX() {
		return begin.getX();
	}

	public void setBeginX(int x) {
		begin.setX(x);
	}

	public int getBeginY() {
		return begin.getY();
	}

	public void setBeginY(int y) {
		begin.setY(y);
	}

	public int getEndX() {
		return end.getX();
	}

	public void setEndX(int x) {
		end.setX(x);
	}

	public int getEndY() {
		return end.getY();
	}

	public void setEndY(int y) {
		end.setY(y);
	}

	public int[] getBeginXY() {
		return begin.getXY();
	}

	public void setBeginXY(int x, int y) {
		begin.setXY(x, y);
	}

	public int[] getEndXY() {
		return end.getXY();
	}

	public void setEndXY(int x, int y) {
		end.setXY(x, y);
	}

	public double getLength() {
		return begin.distance(end);
	}

	public double getGradient() {
		int xDiff = end.getX() - begin.getX();
		int yDiff = end.getY() - begin.getY();
		return Math.atan2(yDiff, xDiff);
	}

	@Override
	public String toString() {
		return "MyLine[begin=" + begin + ",end=" + end + "]";
	}
}

public class Main_ex4 {
	public static void main(String[] args) {
		MyPoint p1 = new MyPoint(1, 2);
		MyPoint p2 = new MyPoint(4, 6);

		MyLine line = new MyLine(p1, p2);

		System.out.println(line);
		System.out.println("Length: " + line.getLength());
		System.out.println("Gradient: " + line.getGradient());
	}
}
