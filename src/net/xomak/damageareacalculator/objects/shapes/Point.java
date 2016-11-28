package net.xomak.damageareacalculator.objects.shapes;

/**
 * Represents a point
 */
public class Point extends Shape {
    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void accept(final ShapeVisitor v) {
        v.visit(this);
    }

    public Double getDistanceTo(final Point another) {
        return Math.sqrt(Math.pow(this.getX() - another.getX(), 2) + Math.pow(this.getY() - another.getY(), 2));
    }

    @Override
    public boolean hasIntersectionWith(final Shape shape) {
        return shape.hasIntersectionWith(this);
    }

    @Override
    public boolean hasIntersectionWith(final Circle shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasIntersectionWith(final Point shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Point getTopLeft() {
        return this;
    }

    @Override
    public Point getBottomRight() {
        return this;
    }

    @Override
    public Point getCenter() {
        return this;
    }
}
