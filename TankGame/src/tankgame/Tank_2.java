package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank_2 extends GameObject{
    private final int SPEED = 2;
    private boolean launch = false;
    private int health = 100;
    private final int DAMGE = 25;
   // private ObjectID id;
     
    public Tank_2(ObjectID id, int x, int y, Image Img) {
        super(id, x, y, Img);
    }

    /*
    public int getHealth() {
        return health;
    }
    
    public boolean isDead() {
        return 0 == health;
    }
    
    public void dead() {
        this.Img = null;
    }
    
    public void respawn() {
        health = 100;
    }
*/
    @Override
    public void update() {
        if(!collide()) {
            x += velX;
            y += velY;
        }
        else {
            if (velX > 0)
                x -= 3;
            if (velX < 0)
                x += 3;
            if (velY > 0)
                y -= 3;
            if (velY < 0)
                y +=3;
        }
        
        
       /* //if (!collide()) {
            x += velX;
            y += velY;
        } else {
            x += 0;
            y +=0;
        }*/
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
        Tank_2 tank = TankWars.getTank_2();
        ArrayList <Wall> walls = TankWars.getCWalls();
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).checkBounds().intersects(tank.checkBounds())) {
                System.out.println("Tank collides wall");
                return true;
            }
        }
        return false;
       // return checkBounds().intersects(tank.checkBounds());
    }
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code){
        case KeyEvent.VK_S:
            velY = SPEED;
            break;
        case  KeyEvent.VK_W:
            velY = -SPEED;
            break;
        case  KeyEvent.VK_A:
            velX = -SPEED;
            break;
        case KeyEvent.VK_D:
            velX = SPEED;
        case KeyEvent.VK_Z:
            launch = true;
            break;
        }        
    }
    
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code){
        case KeyEvent.VK_S:
            velY = 0;
            break;
        case  KeyEvent.VK_W:
            velY = 0;
            break;
        case  KeyEvent.VK_A:
            velX = 0;
            break;
        case KeyEvent.VK_D:
            velX = 0;
            break;
        case KeyEvent.VK_Z:
            launch = false;
            break;
        }
    }
}