package net.xomak.damageareacalculator.objects.field;

import net.xomak.damageareacalculator.objects.shapes.Shape;

public class Obstacle extends FieldObject {

    public Obstacle(final Shape geometricObject, final int id) {
        super(geometricObject, id);
    }

    @Override
    public boolean isTarget() {
        return false;
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean isLauncher() {
        return false;
    }

    @Override
    public String toString() {
        return "Obstacle{id=" + id + '}';
    }
}
