package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank extends GameObject{
    private boolean launch = false;
    private final int SPEED = 2;
    private int health = 100;
    private final int DAMGE = 25;
    private int hitCount;
    /*****************************/
    private int screenUp = -5;
    private int screenDown = 664;
    private int screenLeft = -5;
    private int screenRight = 1015;
    /****************************/
    
    
    public Tank(ObjectID id, int x, int y, Image Img) {
        super(id, x, y, Img);
    }

    @Override
    public void update() {
        System.out.println("Tank 1: " + x + ", " + y);
        if(!collide()) {
            if (y > screenDown) {          // Game Frame Boundaries
                y += -1;                   //
            } else if (y < screenUp) {     //
                y += 1;                    //
            } else if (x > screenRight) {
                x += -1;
            } else if ( x < screenLeft) {
                x += 1;
            } else {
                x += velX;
                y += velY;
            }
        } else {
                if (velX > 0)
                    x -= 2;
                else if (velX < 0)
                    x += 2;
                else if (velY > 0)
                    y -= 2;
                else if (velY < 0)
                    y += 2;
            
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
        // Tank to Tank collision
        Tank_2 tank2 = TankWars.getTank_2();
        if(checkBounds().intersects(tank2.checkBounds())) {
            System.out.println("Tank 1: Collides Tank 2");
            return true;
        }
        
        // Tank to Missile collision
        ArrayList <Missile_2> missile = TankWars.getMissiles2();
        for (int i = 0; i < missile.size(); i ++) {
            if (checkBounds().intersects(missile.get(i).checkBounds())) {
                hitCount++;
                System.out.println(hitCount);
            }       
        }
        
        // Tank to Wall collision
        Tank tank = TankWars.getTank();
        ArrayList <Wall> walls = TankWars.getCWalls();
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).checkBounds().intersects(tank.checkBounds())) {
                System.out.println("Tank 1: Collides Wall");
                return true;
            }
        }
        
        return false;
    }
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code){
        case KeyEvent.VK_DOWN:
            velY = SPEED;
            break;
        case  KeyEvent.VK_UP:
            velY = -SPEED;
            break;
        case  KeyEvent.VK_LEFT:
            velX = -SPEED;
            break;
        case KeyEvent.VK_RIGHT:
            velX = SPEED;
            break;
        case KeyEvent.VK_C:
            launch = true;
            break;
        }        
    }
    
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code){
        case KeyEvent.VK_DOWN:
            velY = 0;
            break;
        case  KeyEvent.VK_UP:
            velY = 0;
            break;
        case  KeyEvent.VK_LEFT:
            velX = 0;
            break;
        case KeyEvent.VK_RIGHT:
            velX = 0;
            break;
        case KeyEvent.VK_C:
            launch = false;
            break;
        }
    }
}
