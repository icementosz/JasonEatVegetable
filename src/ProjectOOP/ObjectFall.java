import java.awt.*;

public abstract class ObjectFall {
    protected int x_vegetable,y_vegetable;
    protected int x_bomb,y_bomb;
    public Rectangle VegetableRect = new Rectangle(x_vegetable,y_vegetable,70,70);
    public Rectangle BombRect = new Rectangle(x_bomb,y_bomb,70,70);

    public void setx_vegetable(int x){
        this.x_vegetable = x;
    }

    public void sety_vegetable(int y){
        this.y_vegetable = y;
    }
    
    public int getx_vegetable(){
        return x_vegetable;
    }

    public int gety_vegetable(){
        return y_vegetable;
    }

    public void setx_bomb(int x){
        this.x_bomb = x;
    }

    public void sety_bomb(int y){
        this.y_bomb = y;
    }
    
    public int getx_bomb(){
        return x_bomb; 
    }

    public int gety_bomb(){
        return y_bomb;
    }

    public abstract void draw(Graphics g);
}
