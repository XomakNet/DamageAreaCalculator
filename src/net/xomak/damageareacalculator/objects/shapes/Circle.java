package net.xomak.damageareacalculator.objects.shapes;

/**
 * Created by regis on 24.11.2016.
 */
public class Circle extends Shape {
    private Point center;
    private int radius;

    public Circle(final Point center, final int radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean hasIntersectionWith(final Section section) {
        // TODO: Write this method
        return false;
        // throw new UnsupportedOperationException("This method is not implemented yet");
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
        return new Point(this.center.getX() - radius, this.center.getY() - radius);
    }

    @Override
    public Point getBottomRight() {
        return new Point(this.center.getX() + radius, this.center.getY() + radius);
    }

    @Override
    public Point getCenter() {
        return center;
    }
}
