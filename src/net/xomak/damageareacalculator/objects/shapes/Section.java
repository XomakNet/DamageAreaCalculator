package net.xomak.damageareacalculator.objects.shapes;

/**
 * Represents a section
 */
public class Section extends Shape {

    protected Point p1;
    protected Point p2;

    public Section(final Point p1, final Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public boolean hasIntersectionWith(final Shape shape) {
        return shape.hasIntersectionWith(this);
    }

    @Override
    public Point getTopLeft() {
        return new Point(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()));
    }

    @Override
    public Point getBottomRight() {
        return new Point(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()));
    }

    @Override
    public Point getCenter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Section{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    public Relation getRelationWith(final Section another) {
        if (!this.hasIntersectionWith(another)) {
            if (!this.isCollinearWith(another)) {
                return Relation.None;
            } else {
                return Relation.Parallelism;
            }
        } else {
            return Relation.Intersection;
        }
    }

    private boolean hasLinearIntersection(final double a1, final double a2, final double b1, final double b2) {
        double start1 = Math.min(a1, a2);
        double end1 = Math.max(a1, a2);
        double start2 = Math.min(b1, b2);
        double end2 = Math.max(b1, b2);
        return Math.max(start1, start2) <= Math.min(end1, end2);
    }

    private double pseudoScalar(final Point a, final Point b, final Point c) {
        return ((b.getX() - a.getX()) * (c.getY() - a.getY())) - ((b.getY() - a.getY()) * (c.getX() - a.getX()));
    }

    public boolean isCollinearWith(final Section another) {
        double x1 = this.p1.getX() - this.p2.getX();
        double y1 = this.p1.getY() - this.p2.getY();
        double x2 = another.p1.getX() - another.p2.getX();
        double y2 = another.p1.getY() - another.p2.getY();

        if (x1 != 0 && x2 != 0) {
            double n = x1 / x2;
            return n * y2 == y1;
        } else if (y1 != 0 && y2 != 0) {
            double n = y1 / y2;
            return n * x2 == x1;
        } else {
            return false;
        }
    }

    public void accept(final ShapeVisitor v) {
        v.visit(this);
    }

    public boolean hasIntersectionWith(final Section another) {
        return hasLinearIntersection(this.p1.getX(), this.p2.getX(), another.p1.getX(), another.p2.getX()) &&
                hasLinearIntersection(this.p1.getY(), this.p2.getY(), another.p1.getY(), another.p2.getY()) &&
                pseudoScalar(this.p1, this.p2, another.p1) * pseudoScalar(this.p1, this.p2, another.p2) <= 0
                && (pseudoScalar(another.p1, another.p2, this.p1) * pseudoScalar(another.p1, another.p2, this.p2) <= 0);
    }

    public boolean hasIntersectionWith(final Circle another) {
        return another.hasIntersectionWith(this);
    }

    @Override
    public boolean hasIntersectionWith(final Point shape) {
        throw new UnsupportedOperationException();
    }

    public boolean hasIntersectionWith(final Rectangle rect) {
        return rect.hasIntersectionWith(this);
    }

    public enum Relation {Intersection, Parallelism, None}

}
