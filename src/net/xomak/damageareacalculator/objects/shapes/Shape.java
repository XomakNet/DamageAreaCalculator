package net.xomak.damageareacalculator.objects.shapes;

public abstract class Shape {
    public abstract boolean hasIntersectionWith(final Shape shape);

    public abstract boolean hasIntersectionWith(final Circle shape);

    public abstract boolean hasIntersectionWith(final Point shape);

    public boolean hasIntersectionWith(final Section shape) {
        throw new UnsupportedOperationException("General hasIntersectionWith was called.");
    }

    public abstract Point getTopLeft();

    public abstract Point getBottomRight();

    public abstract Point getCenter();

    public void accept(final ShapeVisitor v) {
        v.visit(this);
    }
}
