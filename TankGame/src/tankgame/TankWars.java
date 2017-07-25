package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankWars extends JPanel implements Runnable, ActionListener {
    private long time = 0;
    private static Timer gameTimer;
    
    private static ArrayList <Missile> missilesArray = new ArrayList<>(); // Tank 1's Missiles
    private static ArrayList <Missile_2> missiles2Array = new ArrayList<>(); // Tank 2's Missiles
    private static Missile missile;
    private static Missile_2 missile_2;
    
    private static ArrayList <Explosion> explosions = new ArrayList<>();
    private static Explosion explosion;
    
    //private static ArrayList<Tank> player1 = new ArrayList<>();
    private static Tank tankP1;
    private static Tank_2 tankP2;
    
    private static ArrayList<Wall> cWalls = new ArrayList<>(); // Concrete walls
    private static ArrayList<Wall> dWalls = new ArrayList<>(); // Destroyable walls
    
    
    
    private int p1_x = 0, p1_y = 0; // Initial player 1 coordinates
    private int p2_x = 1015, p2_y = 655; // Initial player 2 coordinates
    
    private BufferedImage background;
        private BufferedImage bg;
    
    TankWars() {
        init();
        addKeyListener(new Movement(tankP1));
        addKeyListener(new Movement_2(tankP2));
        addKeyListener(new MissileMovement(missile));
        addKeyListener(new MissileMovement_2(missile_2));
        Sound.playMusic();
        gameTimer = new Timer(5, this);
        gameTimer.start();
    }
    
    @Override
    public void run() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
            g.drawImage(background, 0, 0, this);
            g.drawImage(background, 0, 240, this);
            g.drawImage(background, 0, 480, this);
            g.drawImage(background, 315, 0, this);
            g.drawImage(background, 315, 240, this);
            g.drawImage(background, 315, 480, this);
            g.drawImage(background, 630, 0, this);
            g.drawImage(background, 630, 240, this);
            g.drawImage(background, 630, 480, this);
            g.drawImage(background, 945, 0, this);
            g.drawImage(background, 945, 240, this);
            g.drawImage(background, 945, 480, this);
          
            
        for(int i = 0; i < dWalls.size(); i++) {
            dWalls.get(i).draw(g2D);
        }
        
        for(int i = 0; i < cWalls.size(); i++) {
            cWalls.get(i).draw(g2D);
        }
        
        for (int i = 0; i < missilesArray.size(); i++) {
            missilesArray.get(i).draw(g2D);
        }
        
        for (int i = 0; i < missiles2Array.size(); i++) {
            missiles2Array.get(i).draw(g2D);
        }
        
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).draw(g2D);
        }
        
        tankP1.draw(g2D);
        
        tankP2.draw(g2D);
        
    }
    
    public void init () {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        // Reading background img doesn't work with Image class for some reason...
        try {
            background = ImageIO.read(new File("Resources/Background.png"));
        } catch (IOException ex) {
            System.out.println("TankWars.Init");
        }

        ImageIcon addImg;   
        // Loading images for Tank 1 & 2
        addImg = new ImageIcon("Resources/Tank1.png");
        tankP1 = new Tank(ObjectID.TANK_1, p1_x, p1_y, addImg.getImage());
        addImg = new ImageIcon("Resources/Tank2.png");
        tankP2 = new Tank_2(ObjectID.TANK_2, p2_x, p2_y, addImg.getImage());
        
        // Loading images for Missile
        addImg = new ImageIcon("Resources/RocketR.png");
        missile = new Missile(ObjectID.MISSILE, 0, 0, addImg.getImage());
        addImg = new ImageIcon("Resources/RocketL.png");
        missile_2 = new Missile_2(ObjectID.MISSILE, 0, 0, addImg.getImage());
        Image Img;
        try {
            // Loading images for Explosion
            //addImg = new ImageIcon("Resources/Explosion_large.png");
            URL url = new URL("http://i.imgur.com/DD27OYN.gif");
            Img = Toolkit.getDefaultToolkit().createImage(url);
            explosion = new Explosion(ObjectID.EXPLOSION, 0, 0, Img);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TankWars.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Creating Concrete Walls
        addImg = new ImageIcon("Resources/Wall2.png");
        int y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 4; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 4; j++) {
                cWalls.add(new Wall(ObjectID.WALL, 425 + x, 240 + y, addImg.getImage(), false));
                x += 32;
            }
            y += 32;
        }
        
        // Creating Destroyable Walls
        //Random rand = new Random();
        ImageIcon ImgWallD;
        ImgWallD = new ImageIcon("Resources/Wall1.png");
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 150 + x, 75 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 150 + x, 75 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 720 + x, 75 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 720 + x, 75 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 150 + x, 250 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 150 + x, 250 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 720 + x, 250 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 720 + x, 250 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 150 + x, 425 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 150 + x, 425 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 720 + x, 425 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 720 + x, 425 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 440 + x, 10 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 440 + x, 10 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
        y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 3; i++) {
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 3; j++) {
                if((i == 1) && (j == 1))
                    cWalls.add(new Wall(ObjectID.WALL, 440 + x, 550 + y, addImg.getImage(), false));
                else
                    dWalls.add(new Wall(ObjectID.WALL, 440 + x, 550 + y, ImgWallD.getImage(), true));
                x += 32;
            }
            y += 32;
        }
    }
    
    public static Tank getTank() {
        return tankP1;
    }
    public static Tank_2 getTank_2() {
        return tankP2;
    }
    public static ArrayList<Wall> getCWalls() {
        return cWalls;
    }
    public static ArrayList<Wall> getDWalls() {
        return dWalls;
    }
    public static void removeDWall(Wall wall) {
        dWalls.remove(wall);
    }
    public static ArrayList<Missile> getMissiles() {
        return missilesArray;
    }
    public static void addMissile(Missile missile) {
        missilesArray.add(missile);
    }
    public static void removeMissile(Missile missile) {
        missilesArray.remove(missile);
    }
    public static ArrayList<Missile_2> getMissiles2() {
        return missiles2Array;
    }
    public static void addMissiles2(Missile_2 missile) {
        missiles2Array.add(missile);
    }
    public static void removeMissile2(Missile_2 missile) {
        missiles2Array.remove(missile);
    }
    public static Missile getMissile() {
        return missile;
    }   
    public static Missile_2 getMissile_2() {
        return missile_2;
    }
    public static ArrayList<Explosion> getExplosion() {
        return explosions;
    }
    public static void addExplosion(Explosion e) {
        explosions.add(e);
    }
    public static void removeExplosion(Explosion e) {
        explosions.remove(e);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        tankP1.update();
        tankP2.update();

        for (int i = 0; i < missilesArray.size(); i++) {
            missilesArray.get(i).update();
        }
        
        for (int i = 0; i < missiles2Array.size(); i++) {
            missiles2Array.get(i).update();
        }
        
        // Update the Destroyable Walls
        for(int i = 0; i < dWalls.size(); i++) {
            dWalls.get(i).update();
        }
        
        if (System.currentTimeMillis() - time > 1500) {
            for (int i = 0; i < explosions.size(); i++) {
                explosions.get(i).update();
            }
            time = System.currentTimeMillis();
        }
        
        repaint();
        
    }
    
    public static void stopGame() {
        gameTimer.stop();
    }
     
    public static void main(String args []) {
        Layout initGame = new Layout(1080, 720, "TankWars", new TankWars());
    }

}
