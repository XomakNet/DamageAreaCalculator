package net.xomak.damageareacalculator.objects.shapes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CircleTest extends TestCase {

    public void testHasIntersectionWith_sectionWhenItHas() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(0, 2), new Point(6, 2));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItHasTwo() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(0, 1), new Point(6, 2));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItHasNot() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(8, 2), new Point(6, 2));
        Assert.assertEquals(false, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItIsTangentVertical() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(1, 1), new Point(1, 7));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_sectionWhenItIsTangentHorizontal() throws Exception {
        Circle circle = new Circle(new Point(3, 3), 2);
        Section section = new Section(new Point(1, 1), new Point(1, 7));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionOneCircleInDataSet() throws Exception {
        Circle circle = new Circle(new Point(44, 52), 1);
        Section section = new Section(new Point(30, 90), new Point(64, 0));
        Assert.assertEquals(true, circle.hasIntersectionWith(section));
    }

    public void testHasIntersectionLastCircleInDataSet() throws Exception {
        Circle circle = new Circle(new Point(124, 40), 1);
        Section section = new Section(new Point(123, 89), new Point(127, 3));
        Assert.assertEquals(false, circle.hasIntersectionWith(section));
    }
}