package net.xomak.damageareacalculator;

import net.xomak.damageareacalculator.objects.shapes.Point;
import net.xomak.damageareacalculator.objects.field.BattleField;
import net.xomak.damageareacalculator.objects.field.Launcher;
import net.xomak.damageareacalculator.objects.field.Obstacle;
import net.xomak.damageareacalculator.objects.field.Target;
import net.xomak.damageareacalculator.objects.shapes.Circle;
import net.xomak.damageareacalculator.objects.shapes.Rectangle;
import net.xomak.damageareacalculator.objects.shapes.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by regis on 24.11.2016.
 */
public class FileReader {
    private File file;
    private Scanner sc;
    private java.util.Map<String, Integer> ids = new HashMap<>();

    public FileReader(final String path) throws FileNotFoundException {
        file = new File(path);
        sc = new Scanner(file);
        ids.put("target", 0);
        ids.put("launcher", 0);
        ids.put("obstacle", 0);
    }

    private void addToMap(final BattleField battleField, final Shape geometricObject, final String type) {
        int id = ids.get(type) + 1;
        ids.put(type, id);
        switch(type) {
            case "target":
                battleField.addTarget(new Target(geometricObject, id));
                break;
            case "launcher":
                battleField.addLauncher(new Launcher(geometricObject, id));
                break;
            case "obstacle":
                battleField.addObstacle(new Obstacle(geometricObject, id));
                break;
        }
    }

    public void addToMap(final BattleField battleField) {
        while(sc.hasNext()) {
            Shape geometricObject;
            String type = sc.next();
            int x = sc.nextInt();
            int y = sc.nextInt();
            Point point = new Point(x, y);
            if(sc.hasNextInt()) {
                int width = sc.nextInt();
                if (sc.hasNextInt()) {
                    int height = sc.nextInt();
                    geometricObject = new Rectangle(point, width, height);
                } else {
                    geometricObject = new Circle(point, width);
                }
            }
            else {
                geometricObject = point;
            }
            addToMap(battleField, geometricObject, type);
        }
    }
}
