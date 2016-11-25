package net.xomak.damageareacalculator.objects.field;

import net.xomak.damageareacalculator.PointsTuple;
import net.xomak.damageareacalculator.objects.shapes.Point;
import net.xomak.damageareacalculator.objects.shapes.Section;
import net.xomak.damageareacalculator.objects.shapes.Shape;

import java.util.HashSet;
import java.util.Set;

public class Launcher extends FieldObject {

    public Launcher(final Shape geometricObject, final int id) {
        super(geometricObject, id);
    }

    @Override
    public boolean isTarget()  {return false;}
    public boolean isObstacle(){return false;}
    public boolean isLauncher(){return true; }

    /**
     * Returns potential damage area for this launcher, if it has given attack angle.
     * @param angle Angle, considered as angle from start position to the left or right outermost
     * @return PointsTuple, restricting damage area
     */
    public PointsTuple getDamageArea(final double angle) {
        Point point = (Point)this.geometricObject;
        int cathetusLength = (int)(point.getY() * Math.tan(Math.toDegrees(angle)));
        return new PointsTuple(new Point(point.getX() - cathetusLength, 0), new Point(point.getX() + cathetusLength,
                point.getY()));
    }

    /**
     * Returns set of Sections, starting from this launcher and going to all points, situated on cathetusis of triangle,
     * formed given angle
     * @param angle Angle, considered as angle from start position to the left or right outermost
     * @return Set of sections
     */
    public Set<Section> getSectionsByAngle(final double angle) {
        Point point = (Point)this.geometricObject;
        Set<Section> result = new HashSet<>();
        PointsTuple damageArea = getDamageArea(angle);
        int startX = damageArea.getTopLeft().getX() < 0 ? 0 : damageArea.getTopLeft().getX();
        int endX = damageArea.getBottomRight().getX();
        for(int currentX = startX; currentX < endX; currentX++) {
            result.add(new Section(point, new Point(currentX, 0)));
        }
        return result;
    }


    @Override
    public String toString() {
        return "Launcher{id=" + id + '}';
    }
}
