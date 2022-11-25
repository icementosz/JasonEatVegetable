import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Vegetable extends ObjectFall{
    ImageIcon vegetable = new ImageIcon(getClass().getResource("vegetable1.png"));
    // Image vegetable1 = vegetable.getScaledInstance(70, 70, Image.SCALE_DEFAULT); //Resize
    Random rand = new Random();
    
    public Vegetable(){
        x_vegetable = (int)rand.nextInt(950);
        y_vegetable = 0;
    }

    public Vegetable(int x,int y){
        x_vegetable = (int)rand.nextInt(950);
        y_vegetable = (int)rand.nextInt(300);
    }
    public void draw(Graphics g){
        var width = 70;
        var height = 70;
        g.drawImage(vegetable.getImage(), x_vegetable, y_vegetable, width,height,null);
        VegetableRect = new Rectangle(x_vegetable,y_vegetable,70,70);
        //g.drawRect(x_vegetable, y_vegetable, 70, 70);
    }
}
