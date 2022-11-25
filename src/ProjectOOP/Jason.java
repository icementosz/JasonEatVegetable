import java.awt.*;
import javax.swing.*;

public class Jason{
    ImageIcon jason  = new ImageIcon(getClass().getResource("jason.png"));
    // Image jason1 = jason.getScaledInstance(200, 200, Image.SCALE_DEFAULT); //Resize
    private int x_jason=450,y_jason=550;

    public void setx_jason(int x){
        this.x_jason = x;
    }

    public void sety_jason(int y){
        this.y_jason = y;
    }
    
    public int getx_jason(){
        return x_jason;
    }

    public int gety_jason(){
        return y_jason;
    }

    public void draw(Graphics g){
        var width = 200;
        var height = 200;
        g.drawImage(jason.getImage(), x_jason, y_jason,width,height,null);
        // g.drawRect(x_jason+50, y_jason, 90, 100);
    }

}
