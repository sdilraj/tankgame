package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Explosion_2 extends GameObject{
    Image img1;
    public Explosion_2(ObjectID id, int x, int y, Image img) {
        super(id, x, y, img);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(img1, 50, 50, null);
    }

    @Override
    public Rectangle checkBounds() {
        return null;
    }

    @Override
    public boolean collide() {
        return false;
    }

    
}