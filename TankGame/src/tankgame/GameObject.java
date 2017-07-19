package tankgame;

import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected int x, y;
    protected int velX = 5;
    protected int velY = 5;
    protected BufferedImage Img;
    protected ObjectID objID;
    
    public GameObject(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.Img = img;
    }
    
    public abstract int getX();
    public abstract int getY();
    
    public abstract void setX(int x);
    public abstract void setY(int y);
    
    public abstract ObjectID getID();
}
