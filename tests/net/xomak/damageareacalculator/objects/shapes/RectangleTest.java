package net.xomak.damageareacalculator.objects.shapes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RectangleTest extends TestCase {

    public void testHasIntersectionWith_RectangleWhenItHas() throws Exception {
        Rectangle rectangle = new Rectangle(new Point(1,1), 5, 3);
        Section section = new Section(new Point(7, 1), new Point(5,5));
        Assert.assertEquals(true, rectangle.hasIntersectionWith(section));
    }

    public void testHasIntersectionWith_RectangleWhenItHasNot() throws Exception {
        Rectangle rectangle = new Rectangle(new Point(1,1), 5, 3);
        Section section = new Section(new Point(7, 1), new Point(7,5));
        Assert.assertEquals(false, rectangle.hasIntersectionWith(section));
    }

}