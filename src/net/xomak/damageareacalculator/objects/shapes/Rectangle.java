package net.xomak.damageareacalculator.objects.shapes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by regis on 24.11.2016.
 */
public class Rectangle extends Shape {
    private Point topLeft;
    private int width;
    private int height;

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public Rectangle(final Point topLeft, final int width, final int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public Set<Section> getBounds() {
        Set<Section> toReturn = new HashSet<>();
        Point a = topLeft;
        Point b = new Point(topLeft.getX() + width, topLeft.getY());
        Point c = new Point(topLeft.getX() + width, topLeft.getY() + height);
        Point d = new Point(topLeft.getX(), topLeft.getY() + height);
        toReturn.add(new Section(a, b));
        toReturn.add(new Section(b, c));
        toReturn.add(new Section(c, d));
        toReturn.add(new Section(d, a));
        return toReturn;
    }

    public boolean hasIntersectionWith(final Section another) {
        Set<Section> bounds = this.getBounds();
        for (Section bound : bounds) {
            if (another.hasIntersectionWith(bound)) {
                return true;
            }
        }
        return false;
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
        return topLeft;
    }

    @Override
    public Point getBottomRight() {
        return new Point(topLeft.getX() + width, topLeft.getY() + height);
    }

    @Override
    public Point getCenter() {
        return new Point(topLeft.getX() + width / 2, topLeft.getY() + height / 2);
    }
}
