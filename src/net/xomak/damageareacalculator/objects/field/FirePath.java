package net.xomak.damageareacalculator.objects.field;

import net.xomak.damageareacalculator.objects.shapes.Section;

public class FirePath {
    private Target target;
    private Section path;

    public FirePath(final Target target, final Section path) {

        this.target = target;
        this.path = path;
    }

    public Target getTarget() {
        return target;
    }

    public Section getPath() {
        return path;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof FirePath)) return false;

        FirePath firePath = (FirePath) o;

        return target.equals(firePath.target);

    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    @Override
    public String toString() {
        return "FirePath{" +
                "target=" + target +
                ", path=" + path +
                '}';
    }
}
