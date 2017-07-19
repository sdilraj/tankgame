package tankgame;

import java.awt.image.BufferedImage;

public class Tank extends GameObject{
    protected BufferedImage copyImg;
    protected int ZERO = 0;
    protected int health = 100;
    
    
    
    public Tank(int x, int y, BufferedImage img) {
        super(x, y, img);
        copyImg = img;
    }
    
    public void moveDirection() {
        
    }
    
    public void moveUp() {
        this.y -= this.velY;
    }
    
    public void moveDown() {
        this.y += this.velY;
    }
    
    public void moveLeft() {
        this.x -= this.velX;
    }
    
    public void moveRight() {
        this.x += this.velX;
    }
    
    public int getHealth() {
        return health;
    }
    
    public boolean isDead() {
        return ZERO == health;
    }
    
    public void dead() {
        this.Img = null;
    }
    
    public void respawn() {
        health = 100;
        this.Img = copyImg;
    }
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public ObjectID getID() {
        return this.objID;
    }
    
    
}
