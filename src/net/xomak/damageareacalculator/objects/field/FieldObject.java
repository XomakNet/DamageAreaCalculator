package net.xomak.damageareacalculator.objects.field;

import net.xomak.damageareacalculator.objects.shapes.Shape;

/**
 * Represents object on the field
 */
public abstract class FieldObject {

    public Shape getGeometricObject() {
        return geometricObject;
    }

    protected Shape geometricObject;
    protected int id;

    public FieldObject(final Shape geometricObject, final int id) {
        this.geometricObject = geometricObject;
        this.id = id;
    }

    public abstract boolean isTarget();
    public abstract boolean isObstacle();
    public abstract boolean isLauncher();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (this.getClass().equals(o.getClass()))
        {
            FieldObject that = (FieldObject) o;

            return id == that.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id * this.getClass().hashCode();
    }

}
