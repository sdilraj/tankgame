import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Missile_2 extends GameObject{
    private long timer = 0;
    private final int SPEED = 4;
    public static boolean launch = false;

    
    public Missile_2(ObjectID id, int x, int y, Image img) {
        super(id, x, y, img);
    }

    public boolean isLaunched() {
        return launch;
    }

    @Override
    public void update() {
        if (!collide()) {
            x -= 5;
        } else {
            x += 0;
            TankWars.removeMissile2(this);
        }
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
        // Missile 2 to Walls Collision (Concrete)
        ArrayList <Wall> cWalls = TankWars.getCWalls();       
        for (int i = 0; i < cWalls.size(); i++) {
            if (checkBounds().intersects(cWalls.get(i).checkBounds())) {
                TankWars.removeMissile2(this);
                TankWars.addExplosion(new Explosion(ObjectID.EXPLOSION, cWalls.get(i).x + 10, cWalls.get(i).y, getExplosionIMG()));
                return true;
            }
        }
        
        // Missile 2 to Missile 1 Collision
        ArrayList<Missile> missiles = TankWars.getMissiles();
        for (int i = 0; i < missiles.size(); i++) {
            if (checkBounds().intersects(missiles.get(i).checkBounds())) {
                TankWars.addExplosion((new Explosion(ObjectID.EXPLOSION, x, y, getExplosionIMG())));
                return true;
            }
        }
        
        // Missile 2 to Tank 1 Collision
        Tank tank = TankWars.getTank();
        if (checkBounds().intersects(tank.checkBounds())) {
            TankWars.addExplosion(new Explosion(ObjectID.EXPLOSION, tank.x + 25, tank.y, getExplosionIMG()));
            Sound.playExplosion();
            return true;
        }
        
        return false;
    }
    
    public Image getExplosionIMG() {
        Image Img;
        try {
            // Loading images for Explosion
            URL url = new URL("http://i.imgur.com/BS0tKaB.gif");
            Img = Toolkit.getDefaultToolkit().createImage(url);
            return Img;
        } catch (MalformedURLException ex) {
            Logger.getLogger(TankWars.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void keyPressed(KeyEvent e) {
        ArrayList title = TankWars.getTitle();
        if (System.currentTimeMillis() - timer > 1500) {
        int code = e.getKeyCode();
        switch(code){
        case KeyEvent.VK_Z:
            if (title.isEmpty()) {
                launch = true;
                x = TankWars.getTank_2().x - 30;
                y = TankWars.getTank_2().y + 20;
                TankWars.addMissiles2(new Missile_2(ObjectID.MISSILE, x, y, this.Img));
                timer = System.currentTimeMillis();
            }
            break;
        }
        }
    }
}

    
        
        
        
 