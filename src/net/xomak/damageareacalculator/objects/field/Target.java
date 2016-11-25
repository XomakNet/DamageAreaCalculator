package net.xomak.damageareacalculator.objects.field;

import net.xomak.damageareacalculator.objects.shapes.Shape;

public class Target extends FieldObject {
    public Target(final Shape geometricObject, final int id) {
        super(geometricObject, id);
    }

    @Override
    public boolean isTarget()  {return true; }
    public boolean isObstacle(){return false;}
    public boolean isLauncher(){return false;}
    @Override
    public String toString() {
        return "Target{id=" + id + '}';
    }
}
