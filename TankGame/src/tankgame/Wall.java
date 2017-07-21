package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Wall extends GameObject {
    private boolean destroyable;
    
    public Wall(ObjectID id, int x, int y, Image img, boolean destoryable) {
        super(id, x, y, img);
        this.destroyable = destroyable;
    }
    
    public void respawn() {
        
    }

    @Override
    public void update() {
        
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
        Tank tank = TankWars.getTank();
        if (checkBounds().intersects(tank.checkBounds())) {
            return true;
        }
        
        return false;
    }
    
}
