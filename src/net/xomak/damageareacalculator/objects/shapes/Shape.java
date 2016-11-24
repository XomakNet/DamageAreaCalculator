package net.xomak.damageareacalculator.objects.shapes;

/**
 * Created by regis on 24.11.2016.
 */
public abstract class Shape {
    public abstract boolean hasIntersectionWith(final Shape shape);
    public abstract boolean hasIntersectionWith(final Circle shape);
    public abstract boolean hasIntersectionWith(final Point shape);
    public boolean hasIntersectionWith(final Section shape) {
        throw new UnsupportedOperationException("General hasIntersectionWith was called.");
    };
    public abstract Point getTopLeft();
    public abstract Point getBottomRight();
    public abstract Point getCenter();
}
