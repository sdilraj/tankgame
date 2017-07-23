package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Wall extends GameObject {
    private boolean destroyable;
    
    public Wall(ObjectID id, int x, int y, Image img, boolean destroy) {
        super(id, x, y, img);
        this.destroyable = destroy;
    }
    
    public void respawn() {
        
    }

    @Override
    public void update() {
        if (collide()) {
            TankWars.removeDWall(this);
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(Img, x, y, null);
    }

    @Override
    public Rectangle checkBounds() {
        return new Rectangle(x, y, Img.getWidth(null), Img.getHeight(null));
    }

    @Override
    public boolean collide() {
        // Missile 1 to Wall Collision (destroyable)
        ArrayList <Missile> missiles = TankWars.getMissiles();
        for (int i = 0; i < missiles.size(); i++) {
            if (checkBounds().intersects(missiles.get(i).checkBounds())) {
                TankWars.removeMissile(missiles.get(i));
                return true;
            }
        }
        
        // Missile 2 to Wall Collision (destroyable)
        ArrayList <Missile_2> missiles2 = TankWars.getMissiles2();
        for (int i = 0; i < missiles2.size(); i++) {
            if (checkBounds().intersects(missiles2.get(i).checkBounds())) {
                TankWars.removeMissile2(missiles2.get(i));
                return true;
            }
        }
        
        return false;
    }
    
}
