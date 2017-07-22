
package tankgame;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static tankgame.Missile.launch;


public class Missile_2 extends GameObject{
    private long timer = 0;
    private final int SPEED = 4;
    private int tankXPos = TankWars.getTank_2().x;
    public static boolean launch = false;
    private Tank_2 tk;
    private Wall w;
    
    public Missile_2(ObjectID id, int x, int y, Image img) {
        super(id, x, y, img);
    }

     public void shoot() {
        int i = 0;
        
        while(i != -20)
        {
            this.x -= 10;
            i--;
            
        }
         
    }        
    
    public void getStats() {
    }
    
    public boolean isHit() {
        return false;
    }
    
    public void reload() {
        
    }
    
    public boolean isLaunched() {
        return launch;
    }

    @Override
    public void update() {
        if (!collide())
            x += velX;
        else
            x += 0;
    }

    @Override
    public void draw(Graphics2D g2D) {
        // Since x based on x coordinates of tank, doesn't work really well when moving and shooting
        g2D.drawImage(Img, x, y, null);
    }

    @Override
    public Rectangle checkBounds() {
        return new Rectangle(x, y, Img.getWidth(null), Img.getHeight(null));
    }

    @Override
    public boolean collide() {
        
        ArrayList <Wall> walls = TankWars.getCWalls();
        
        for (int i = 0; i < walls.size(); i++) {
            if (checkBounds().intersects(walls.get(i).checkBounds())) {
                return true;
            }
        }
        
        return false;
    }
    
    public void keyPressed(KeyEvent e) {
        //if (System.currentTimeMillis() - timer > 1500) {
        int code = e.getKeyCode();
        switch(code){
        case KeyEvent.VK_Z:
            launch = true;
            x = TankWars.getTank_2().x - 60;
            y = TankWars.getTank_2().y + 20;
            velX = SPEED;
            timer = System.currentTimeMillis();
            break;
        }
        //}
    }
}

    
        
        
        
 