package net.xomak.damageareacalculator;

import net.xomak.damageareacalculator.objects.shapes.Point;

public class PointsTuple {
    private Point topLeft;

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public PointsTuple(final Point topLeft, final Point bottomRight) {

        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    private Point bottomRight;


}
