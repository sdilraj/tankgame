package tankgame;

import java.awt.image.BufferedImage;

public class Wall extends GameObject {
    
    
    public Wall(int x, int y, BufferedImage img) {
        super(x, y, img);
    }
    
    public void respawn() {
        
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
