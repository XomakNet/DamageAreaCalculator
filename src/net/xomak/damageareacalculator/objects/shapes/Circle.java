package net.xomak.damageareacalculator.objects.shapes;

import static java.lang.Math.pow;

public class Circle extends Shape {
    private Point center;
    private int radius;

    public Circle(final Point center, final int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getDiameter() {
        return 2 * radius;
    }

    public void accept(final ShapeVisitor v) {
        v.visit(this);
    }

    public boolean hasIntersectionWith(final Section section) {
        Point relatFirst, relatSecond;
        //we need relative coords for section, where (0;0) is center of cycle
        relatFirst = new Point(section.getP1().getX() - center.getX(), section.getP1().getY() - center.getY());
        relatSecond = new Point(section.getP2().getX() - center.getX(), section.getP2().getY() - center.getY());

        double a = pow(relatSecond.getX() - relatFirst.getX(), 2) + pow(relatSecond.getY() - relatFirst.getY(), 2);
        double b = 2 * (relatFirst.getX() * (relatSecond.getX() - relatFirst.getX())
                + relatFirst.getY() * (relatSecond.getY() - relatFirst.getY()));
        double c = pow(relatFirst.getX(), 2) + pow(relatFirst.getY(), 2) - radius * radius;

        if (-b < 0)
            return (c < 0);
        if (-b < (2 * a))
            return (4 * a * c - b * b <= 0); // <= for find tangent intersection

        return (a + b + c < 0);
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
