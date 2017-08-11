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

public class Missile extends GameObject{
    private long timer = 0;
    private final int SPEED = 4;
    public static boolean launch = false;
    
    public Missile(ObjectID id, int x, int y, Image img) {
        super(id, x, y, img);
       
    }

    public boolean isLaunched() {
        return launch;
    }

    @Override
    public void update() {
        if (!collide()) {
            x += 5;
        } else {
            x += 0;
            TankWars.removeMissile(this);
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
        ArrayList<Explosion> explosions = TankWars.getExplosion();
        
        // Missile 1 to Walls (Concrete)
        ArrayList <Wall> cWalls = TankWars.getCWalls();
        for (int i = 0; i < cWalls.size(); i++) {
            if (checkBounds().intersects(cWalls.get(i).checkBounds())) {
                TankWars.addExplosion(new Explosion(ObjectID.EXPLOSION, cWalls.get(i).x - 25, cWalls.get(i).y, getExplosionIMG()));
                return true;
            }
        }
        
        // Missile 1 to Missile 2 Collision
        ArrayList<Missile_2> missiles = TankWars.getMissiles2();
        for (int i = 0; i < missiles.size(); i++) {
            if (checkBounds().intersects(missiles.get(i).checkBounds())) {
                TankWars.removeMissile(this);
                TankWars.addExplosion((new Explosion(ObjectID.EXPLOSION, x, y, getExplosionIMG())));
                return true;
            }
        }
        
        // Missile 1 to Tank 2 Collision
        Tank_2 tank = TankWars.getTank_2();
        if (checkBounds().intersects(tank.checkBounds())) {
            TankWars.addExplosion(new Explosion(ObjectID.EXPLOSION, tank.x - 25, tank.y, getExplosionIMG()));
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
        case KeyEvent.VK_SPACE:
            if (title.size() == 1) {
                TankWars.removeTitle();
            } else {
                launch = true;
                x = TankWars.getTank().x + 60;
                y = TankWars.getTank().y + 20;
                TankWars.addMissile(new Missile(ObjectID.MISSILE, x, y, this.Img));
                timer = System.currentTimeMillis();
            }
            break;
        }
        }
    }
}
