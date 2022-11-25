import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Bomb extends ObjectFall{
    ImageIcon bomb = new ImageIcon(getClass().getResource("bomb1.png"));
    // Image bomb1 = bomb.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
    Random rand = new Random();

    public Bomb(){
        x_bomb = (int)rand.nextInt(1000);
        y_bomb = 0;
    }

    public void draw(Graphics g){
        var height = 70;
        var width = 70;
        g.drawImage(bomb.getImage(), x_bomb, y_bomb, width,height,null);
        BombRect = new Rectangle(x_bomb,y_bomb,70,70);
        //g.drawRect(x_bomb, y_bomb, 70, 70);
    }
}
