package tankgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tank extends GameObject{
    private final int SPEED = 2;
    private final int COLLISION = 1;
    private int hitCount;
    
    private int left;
    private int right;
    private int up;
    private int down;
    
    private Image winner;
    private Image health100;
    private Image health75;
    private Image health50;
    private Image health25;
    
    /*****************************/
    private int screenUp = -5;
    private int screenDown = 664;
    private int screenLeft = -5;
    private int screenRight = 1015;
    /****************************/
    
    
    public Tank(ObjectID id, int x, int y, Image Img) {
        super(id, x, y, Img);
        getHealthImgs();
    }

    @Override
    public void update() {
        //System.out.println("Tank 1: " + x + ", " + y);
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
            if((x + x) > right)
                x += COLLISION;
            if ((y + y) > down)
                y += COLLISION;
            if (y < up)
                y -= COLLISION;
            if (x < left)
                x -= COLLISION;
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        if (hitCount == 0) 
            g2D.drawImage(health100, 10, 700, null);
        if (hitCount == 1)
            g2D.drawImage(health75, 10, 700, null);
        if (hitCount == 2)
            g2D.drawImage(health50, 10, 700, null);
        if (hitCount == 3)
            g2D.drawImage(health25, 10, 700, null);
        if(hitCount == 4) {
            g2D.drawImage(winner, 0, 0, null);
            TankWars.stopGame();
        }
        
        g2D.drawString("Player 1", 12, 710);
        g2D.drawImage(Img, x, y, null);
    }
    
    public int getHit() {
        return hitCount;
    }
    
    @Override
    public Rectangle checkBounds() {
        return new Rectangle(x, y, Img.getWidth(null), Img.getHeight(null));
    }
    
    @Override
    public boolean collide() {
        // Tank to Tank Collision
        Tank_2 tank2 = TankWars.getTank_2();
        if(checkBounds().intersects(tank2.checkBounds())) {
            left = tank2.x;
            right = tank2.x + tank2.x;
            up = tank2.y;
            down = tank2.y + tank2.y;
            return true;
        }
        
        // Tank to Missile Collision
        ArrayList <Missile_2> missile = TankWars.getMissiles2();
        for (int i = 0; i < missile.size(); i ++) {
            if (checkBounds().intersects(missile.get(i).checkBounds())) {
                hitCount++;
            }       
        }
        
        // Tank to Wall Collision (Undestroyable)
        ArrayList <Wall> cWalls = TankWars.getCWalls();
        for (int i = 0; i < cWalls.size(); i++) {
            if (checkBounds().intersects(cWalls.get(i).checkBounds())) {
                left = cWalls.get(i).x;
                right = cWalls.get(i).x + cWalls.get(i).x;
                up = cWalls.get(i).y;
                down = cWalls.get(i).y + cWalls.get(i).y;
                return true;
            }
        }
        
        // Tank to Wall Collision (Destroyable)
        ArrayList <Wall> dWalls = TankWars.getDWalls();
        for (int i = 0; i < dWalls.size(); i++) {
            if(checkBounds().intersects(dWalls.get(i).checkBounds())) {
                left = dWalls.get(i).x;
                right = dWalls.get(i).x + dWalls.get(i).x;
                up = dWalls.get(i).y;
                down = dWalls.get(i).y + dWalls.get(i).y;
                return true;
            }
        }
        
        return false;
    }
    
    private void getHealthImgs() {
        ImageIcon Img;
        
        Img = new ImageIcon("Resources/health.png");
        health100 = Img.getImage();
        Img = new ImageIcon("Resources/health1.png");
        health75 = Img.getImage();
        Img = new ImageIcon("Resources/health2.png");
        health50 = Img.getImage();
        Img = new ImageIcon("Resources/health3.png");
        health25 = Img.getImage();
        Img = new ImageIcon("Resources/Player2Win.png");
        winner = Img.getImage();
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
        }
    }
}
