package net.xomak.damageareacalculator.objects;

import net.xomak.damageareacalculator.objects.field.BattleField;
import net.xomak.damageareacalculator.objects.field.FieldObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class MapVisualisation {
    BattleField curField;
    BufferedImage saveImg;
    private final static int Launcher_color = new Color(0,0,255).getRGB(); //blue color
    private final static int Obstacle_color = new Color(0,0,0).getRGB(); //black color
    private final static int Target_color = new Color(237, 28, 36).getRGB(); // red color

    public MapVisualisation(BattleField curField, String nameFile) {
        this.curField = curField;
        int width = 0, height = 0;

        //prepare map (find max x and y values)
        for (Iterator<FieldObject> it = curField.getAllObjects().iterator(); it.hasNext(); ) {
            FieldObject curObject = it.next();
            if (width < curObject.getGeometricObject().getBottomRight().getX())
                width = curObject.getGeometricObject().getBottomRight().getX();

            if (height < curObject.getGeometricObject().getBottomRight().getY())
                height = curObject.getGeometricObject().getBottomRight().getY();
        }
        saveImg = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);

        //make map white
        Graphics2D graphics = saveImg.createGraphics();

        graphics.setPaint ( new Color (255, 255, 255) );
        graphics.fillRect ( 0, 0, saveImg.getWidth(), saveImg.getHeight() );


        for(FieldObject curObject : curField.getAllObjects()){
            if(curObject.isLauncher()){
                saveImg.setRGB(curObject.getGeometricObject().getCenter().getX(),curObject.getGeometricObject().getCenter().getY(),Launcher_color);
            }
            if(curObject.isObstacle()){
                saveImg.setRGB(curObject.getGeometricObject().getCenter().getX(),curObject.getGeometricObject().getCenter().getY(),Obstacle_color);
            }
            if(curObject.isTarget()){
                saveImg.setRGB(curObject.getGeometricObject().getCenter().getX(),curObject.getGeometricObject().getCenter().getY(),Target_color);
            }
        }

        try {
            ImageIO.write(saveImg, "png", new File(nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
