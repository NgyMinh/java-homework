package part_6;
interface Movable3 {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
}

// Class MovablePoint
class MovablePoint3 implements Movable2 {
    protected int x, y, xSpeed, ySpeed;

    public MovablePoint3(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void moveUp() {
        y += ySpeed;
    }

    @Override
    public void moveDown() {
        y -= ySpeed;
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
        return "(" + x + "," + y + "), speed=(" + xSpeed + "," + ySpeed + ")";
    }
}

// Class MovableCircle
class MovableCircle3 implements Movable3 {
    private int radius;
    private MovablePoint3 center;

    public MovableCircle3(int x, int y, int xSpeed, int ySpeed, int radius) {
        this.center = new MovablePoint3(x, y, xSpeed, ySpeed);
        this.radius = radius;
    }

    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void moveLeft() {
        center.moveLeft();
    }

    @Override
    public void moveRight() {
        center.moveRight();
    }

    @Override
    public String toString() {
        return "(" + center.x + "," + center.y + "), speed=(" + center.xSpeed + "," + center.ySpeed + "), radius=" + radius;
    }

    public static void main(String[] args) {
        MovablePoint3 point = new MovablePoint3(0, 0, 2, 3);
        System.out.println("Initial Point: " + point);
        point.moveUp();
        point.moveRight();
        System.out.println("After Moving: " + point);

        MovableCircle circle = new MovableCircle(5, 5, 1, 1, 10);
        System.out.println("Initial Circle: " + circle);
        circle.moveDown();
        circle.moveLeft();
        System.out.println("After Moving: " + circle);
    }
}
