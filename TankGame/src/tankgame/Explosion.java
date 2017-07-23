package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;

public class Explosion extends GameObject{
    private int timer = 0;
    private Timer time = new Timer();
    
    public Explosion(ObjectID id, int x, int y, Image img) {
        super(id, x, y, img);
        
    }

    @Override
    public void update() {
        TankWars.removeExplosion(this);
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(Img, x, y, null);
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
