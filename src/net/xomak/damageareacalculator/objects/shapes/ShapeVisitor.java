package net.xomak.damageareacalculator.objects.shapes;

public interface ShapeVisitor {
    void visit(final Rectangle rectangle);

    void visit(final Circle circle);

    void visit(final Section section);

    void visit(final Point point);

    void visit(final Shape shape);
}
