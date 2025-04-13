package part_5;
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setXY(int x, int y) { 
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point: (" + x + "," + y + ")";
    }
}

class Line {
    private Point begin; 
    private Point end;   

    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Line(int beginX, int beginY, int endX, int endY) {
        this.begin = new Point(beginX, beginY);
        this.end = new Point(endX, endY);
    }

    public Point getBegin() { return begin; }
    public Point getEnd() { return end; }

    public void setBegin(Point begin) { this.begin = begin; }
    public void setEnd(Point end) { this.end = end; }

    public int getBeginX() { return begin.getX(); }
    public int getBeginY() { return begin.getY(); }
    public int getEndX() { return end.getX(); }
    public int getEndY() { return end.getY(); }

    public void setBeginX(int x) { begin.setX(x); }
    public void setBeginY(int y) { begin.setY(y); }
    public void setBeginXY(int x, int y) { begin.setXY(x, y); }

    public void setEndX(int x) { end.setX(x); }
    public void setEndY(int y) { end.setY(y); }
    public void setEndXY(int x, int y) { end.setXY(x, y); }

    public double getLength() {
        int xDiff = end.getX() - begin.getX();
        int yDiff = end.getY() - begin.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public double getGradient() {
        int xDiff = end.getX() - begin.getX();
        int yDiff = end.getY() - begin.getY();
        return Math.atan2(yDiff, xDiff);
    }

    @Override
    public String toString() {
        return "Line[begin=" + begin + ", end=" + end + ", length=" + getLength() + "]";
    }
}

class TestPoint {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        System.out.println(p1);
        p1.setXY(100, 10);
        System.out.println("Sau khi thay đổi tọa độ: " + p1);
    }
}

class TestLine {
    public static void main(String[] args) {
        Line l1 = new Line(0, 0, 3, 4);
        System.out.println(l1);

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        Line l2 = new Line(p1, p2);
        System.out.println(l2);
    }
}
