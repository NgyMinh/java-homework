package part_6;

interface Movable2 {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
}

class MovablePoint2 implements Movable {
    int x, y;
    int xSpeed, ySpeed;

    public MovablePoint2(int x, int y, int xSpeed, int ySpeed) {
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
        return "(" + x + ", " + y + "), speed=(" + xSpeed + ", " + ySpeed + ")";
    }
}

class MovableCircle implements Movable {
    int radius;
    MovablePoint center;

    public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
        this.center = new MovablePoint(x, y, xSpeed, ySpeed);
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
        return "Center: " + center + ", Radius: " + radius;
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

        MovableCircle circle = new MovableCircle(5, 5, 2, 3, 10);
        System.out.println(circle);
        circle.moveUp();
        System.out.println("After moveUp: " + circle);
        circle.moveDown();
        System.out.println("After moveDown: " + circle);
        circle.moveLeft();
        System.out.println("After moveLeft: " + circle);
        circle.moveRight();
        System.out.println("After moveRight: " + circle);
    }
}

