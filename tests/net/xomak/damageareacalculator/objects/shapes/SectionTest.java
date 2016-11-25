package net.xomak.damageareacalculator.objects.shapes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SectionTest extends TestCase {

    public void testHasIntersectionWithSection_sectionWhenItHas() throws Exception {
        Section section = new Section(new Point(0,0), new Point(2,2));
        Section another = new Section(new Point(0,2), new Point(2,0));
        Assert.assertEquals(true, section.hasIntersectionWith(another));
    }

    public void testHasIntersectionWithSection_sectionWhenItHasNot() throws Exception {
        Section section = new Section(new Point(0,0), new Point(2,2));
        Section another = new Section(new Point(0,2), new Point(4,4));
        Assert.assertEquals(false, section.hasIntersectionWith(another));
    }

    public void testHasIntersectionWith_sectionWhenTheyCoincide() throws Exception {
        Section section = new Section(new Point(0,0), new Point(0,5));
        Section another = new Section(new Point(0,2), new Point(0,4));
        Assert.assertEquals(true, section.hasIntersectionWith(another));
    }

    public void testHasIntersectionWith_sectionWhenTheyCollinear() throws Exception {
        Section section = new Section(new Point(0,0), new Point(0,5));
        Section another = new Section(new Point(0,6), new Point(0,9));
        Assert.assertEquals(false, section.hasIntersectionWith(another));
    }
}