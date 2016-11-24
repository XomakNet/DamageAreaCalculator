package net.xomak.damageareacalculator.objects.shapes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CircleTest extends TestCase {

    public void testHasIntersectionWith_sectionWhenItHas() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(0, 2), new Point(6, 2));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItHasNot() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(8, 2), new Point(6, 2));
        Assert.assertEquals(false, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItIsTangent() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(1, 1), new Point(1, 7));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

}