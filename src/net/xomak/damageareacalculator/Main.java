package net.xomak.damageareacalculator;

import net.xomak.damageareacalculator.objects.field.BattleField;
import net.xomak.damageareacalculator.objects.field.Launcher;
import net.xomak.damageareacalculator.objects.field.Target;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


        BattleField battleField = new BattleField();
        Map<Launcher, Set<Target>> targets;
        try {
            FileReader reader = new FileReader("input.txt");
            reader.addToMap(battleField);
            targets = battleField.getAllAchievableTarget();
            System.out.println(targets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
