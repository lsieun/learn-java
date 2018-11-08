package lsieun.geometry;

public class Point {
    private static final String secret = "Javassist is Great!!!";
    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
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

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void jump(int value) {
        this.move(value);
    }

    protected void move(int dx) {
        this.x += dx;
        this.y += dx;
    }

    public void reset() {
        this.move();
    }

    private void move() {
        this.x = 0;
        this.y = 0;
    }

    public void getError() {
        System.out.println(this.x);
        System.out.println(this.y);
        int value = 1 / 0;
        System.out.println(value);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
