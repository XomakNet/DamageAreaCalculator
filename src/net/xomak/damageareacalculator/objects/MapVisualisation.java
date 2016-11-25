package net.xomak.damageareacalculator.objects;

import net.xomak.damageareacalculator.objects.field.BattleField;
import net.xomak.damageareacalculator.objects.field.FieldObject;
import net.xomak.damageareacalculator.objects.field.FirePath;
import net.xomak.damageareacalculator.objects.field.Launcher;
import net.xomak.damageareacalculator.objects.shapes.Circle;
import net.xomak.damageareacalculator.objects.shapes.Rectangle;
import net.xomak.damageareacalculator.objects.shapes.Shape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MapVisualisation {
    BattleField curField;
    BufferedImage saveImg;
    Graphics2D graphics;
    private final static Color LAUNCHER_COLOR = new Color(0,0,255); //blue color
    private final static Color OBSTACLE_COLOR = new Color(0,0,0); //black color
    private final static Color TARGET_COLOR = new Color(237, 28, 36); // red color
    private final static Color LINE_COLOR = new Color(0, 200, 0); // green color

    /*
    @param curField Battlefield, which contains all information about objects in field, targets - all hit lines to targets,
    nameFile String - for changing name for png picture
     */
    public MapVisualisation(BattleField curField, Map<Launcher, Set<FirePath>> targets, String nameFile) {
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


        graphics = saveImg.createGraphics();
        //make map white
        graphics.setPaint ( new Color (255, 255, 255) );
        graphics.fillRect ( 0, 0, saveImg.getWidth(), saveImg.getHeight() );

        drawHitsLine(targets); //draw hit lines for each launchers


        for(FieldObject curObject : curField.getAllObjects()){
            if(curObject.isLauncher()){
                draw(curObject.getGeometricObject(), LAUNCHER_COLOR);
            }
            if(curObject.isObstacle()){
                draw(curObject.getGeometricObject(), OBSTACLE_COLOR);
            }
            if(curObject.isTarget()){
                draw(curObject.getGeometricObject(), TARGET_COLOR);
            }
        }

        try {
            ImageIO.write(saveImg, "png", new File(nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void drawHitsLine(Map<Launcher, Set<FirePath>> targets){
        graphics.setPaint (LINE_COLOR);
        for(Set<FirePath> curPaths : targets.values()){
            for(FirePath curLine : curPaths){
                graphics.draw(new Line2D.Float(curLine.getPath().getP1().getX(), curLine.getPath().getP1().getY()
                        , curLine.getPath().getP2().getX(), curLine.getPath().getP2().getY()));
            }
        }
    }

    private void draw(Shape curObject, Color color){ //draw all objects
        if(curObject instanceof Circle){
            graphics.setPaint (color);
            Circle circle = (Circle)curObject;
            graphics.draw(new Ellipse2D.Float(circle.getTopLeft().getX(),circle.getTopLeft().getY(),circle.getDiameter(), circle.getDiameter()));
        }
        else if (curObject instanceof Rectangle){
            graphics.setPaint (color);
            Rectangle rect = (Rectangle)curObject;
            graphics.draw(new Rectangle2D.Float(rect.getTopLeft().getX(),rect.getTopLeft().getY(),rect.getWidth(), rect.getHeight()));
        }
        else {
            saveImg.setRGB(curObject.getCenter().getX(), curObject.getCenter().getY(), color.getRGB());
        }
    }
}
