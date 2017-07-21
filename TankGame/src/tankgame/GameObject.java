package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
    protected int x, y; 
    protected int velX = 0;
    protected int velY = 0;
    protected Image Img;
    protected ObjectID id;
    
    public GameObject(ObjectID id,int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.Img = img;
        this.id = id;
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
         
    public abstract void update();
    
    public abstract void draw(Graphics2D g2D);
    
    public abstract Rectangle checkBounds();
    
    public abstract boolean collide();
   
}
