package net.xomak.damageareacalculator;

import net.xomak.damageareacalculator.objects.shapes.Point;

public class PointsTuple {
    private Point topLeft;
    private Point bottomRight;

    public PointsTuple(final Point topLeft, final Point bottomRight) {

        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }


}
