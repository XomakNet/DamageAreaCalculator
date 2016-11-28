package net.xomak.damageareacalculator.objects.field;


import net.xomak.damageareacalculator.PointsTuple;
import net.xomak.damageareacalculator.objects.shapes.Point;
import net.xomak.damageareacalculator.objects.shapes.Section;
import net.xomak.damageareacalculator.objects.shapes.Shape;

import java.util.*;
import java.util.stream.Collectors;


public class BattleField {
    private double angle = 30;

    private Set<Target> targets = new HashSet<>();
    private Set<Launcher> launchers = new HashSet<>();
    private Set<Obstacle> obstacles = new HashSet<>();

    private static boolean inRange(final int left, final int right, final int value) {
        return value >= left && value <= right;
    }

    public Set<Target> getTargets() {
        return targets;
    }

    public Set<Launcher> getLaunchers() {
        return launchers;
    }

    public Set<Obstacle> getObstacles() {
        return obstacles;
    }

    public Set<FieldObject> getAllObjects() {
        Set<FieldObject> obstaclesAndTargets = new HashSet<>();
        obstaclesAndTargets.addAll(targets);
        obstaclesAndTargets.addAll(obstacles);
        obstaclesAndTargets.addAll(launchers);
        return obstaclesAndTargets;
    }

    public boolean addTarget(final Target target) {
        return targets.add(target);
    }

    public boolean addObstacle(final Obstacle obstacle) {
        return obstacles.add(obstacle);
    }

    public boolean addLauncher(final Launcher launcher) {
        return launchers.add(launcher);
    }

    /**
     * Checks, whether object is in damage area.
     *
     * @param damageArea Damage area, represented by two points
     * @param object     Object
     * @return
     */
    public boolean isInDamageArea(final PointsTuple damageArea, final Shape object) {
        int leftX = damageArea.getTopLeft().getX();
        int rightX = damageArea.getBottomRight().getX();
        int topY = damageArea.getTopLeft().getY();
        int bottomY = damageArea.getBottomRight().getY();


        boolean byX = inRange(leftX, rightX, object.getTopLeft().getX()) ||
                inRange(leftX, rightX, object.getBottomRight().getX());
        boolean byY = inRange(topY, bottomY, object.getBottomRight().getY()) ||
                inRange(topY, bottomY, object.getTopLeft().getY());
        return byX && byY;
    }

    /**
     * Returns map, containing Launchers and relevant available targets
     *
     * @return
     */
    public Map<Launcher, Set<FirePath>> getAllAchievableTarget() {
        Map<Launcher, Set<FirePath>> result = new HashMap<>();
        for (Launcher launcher : launchers) {
            result.put(launcher, getTargetsAchievableFrom(launcher));
        }
        return result;
    }

    /**
     * Returns set of targets, achievable from given launcher
     *
     * @param launcher Launcher
     * @return Set of fire paths
     */
    public Set<FirePath> getTargetsAchievableFrom(final Launcher launcher) {
        Set<FirePath> resultTargets = new HashSet<>();
        PointsTuple damageArea = launcher.getDamageArea(angle);

        Set<Target> potentialTargets = targets.stream()
                .filter(target -> isInDamageArea(damageArea, target.getGeometricObject())).collect(Collectors.toSet());

        if (potentialTargets.size() > 0) {
            Set<Obstacle> potentialObstacles = obstacles.stream()
                    .filter(obstacle -> isInDamageArea(damageArea, obstacle.getGeometricObject()))
                    .collect(Collectors.toSet());

            Set<FieldObject> obstaclesAndTargets = new HashSet<>();
            obstaclesAndTargets.addAll(potentialObstacles);
            obstaclesAndTargets.addAll(potentialTargets);

            Set<Section> sectionsFromLauncher = launcher.getSectionsByAngle(angle);
            for (Section section : sectionsFromLauncher) {
                Optional<FieldObject> nearestObject = obstaclesAndTargets.stream()
                        .filter(fieldObject -> fieldObject.getGeometricObject().hasIntersectionWith(section))
                        .min(new ByDistanceToPointComparator(launcher.getGeometricObject().getCenter()));
                if (nearestObject.isPresent() && nearestObject.get().isTarget()) {
                    resultTargets.add(new FirePath((Target) nearestObject.get(), section));
                }
            }
        }
        return resultTargets;
    }
}

class ByDistanceToPointComparator implements Comparator<FieldObject> {
    private Point pointFrom;

    public ByDistanceToPointComparator(final Point pointFrom) {
        this.pointFrom = pointFrom;
    }

    @Override
    public int compare(final FieldObject o1, final FieldObject o2) {

        return pointFrom.getDistanceTo(o1.getGeometricObject().getCenter())
                .compareTo(pointFrom.getDistanceTo(o2.getGeometricObject().getCenter()));
    }
}