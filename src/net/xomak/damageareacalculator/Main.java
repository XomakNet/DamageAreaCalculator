package net.xomak.damageareacalculator;

import net.xomak.damageareacalculator.objects.MapVisualisation;
import net.xomak.damageareacalculator.objects.field.BattleField;
import net.xomak.damageareacalculator.objects.field.FirePath;
import net.xomak.damageareacalculator.objects.field.Launcher;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


        BattleField battleField = new BattleField();
        Map<Launcher, Set<FirePath>> targets;
        try {
            FileReader reader = new FileReader("input.txt");
            reader.addToMap(battleField);
            targets = battleField.getAllAchievableTarget();
            System.out.println(targets);
            MapVisualisation map = new MapVisualisation(battleField, "map.png");
            System.out.println("map.png saved in project directory");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
