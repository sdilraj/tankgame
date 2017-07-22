package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankWars extends JPanel implements Runnable, ActionListener {
    Timer gameTimer;
    private static Missile missile;
    private static Missile_2 missile_2;
    private boolean launch = false;
    private static Tank tankP1;
    private static Tank_2 tankP2;
    private static ArrayList<Wall> cWalls = new ArrayList<>(); // Concrete walls
    private ArrayList<Wall> dWalls = new ArrayList<>(); // Destroyable walls
    private int p1_x = 0, p1_y = 0; // Initial player 1 coordinates
    private int p2_x = 1015, p2_y = 655; // Initial player 2 coordinates
    private BufferedImage background;
        
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
        
        
        for(int i = 0; i < cWalls.size(); i++) {
            cWalls.get(i).draw(g2D);
        }

        for(int i = 0; i < dWalls.size(); i++) {
            dWalls.get(i).draw(g2D);
        }
        
        tankP1.draw(g2D);
        tankP2.draw(g2D);
        
        if (missile.isLaunched())
        {
            missile.draw(g2D);
        }
            
        
        if (missile_2.isLaunched())
        {
           missile_2.draw(g2D);
        }
            
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
        addImg = new ImageIcon("Resources/Tank1.png");
        tankP1 = new Tank(ObjectID.TANK_1, p1_x, p1_y, addImg.getImage());
        addImg = new ImageIcon("Resources/Tank2.png");
        tankP2 = new Tank_2(ObjectID.TANK_2, p2_x, p2_y, addImg.getImage());
        addImg = new ImageIcon("Resources/RocketR.png");
        missile = new Missile(ObjectID.MISSILE, 0, 0, addImg.getImage());
        addImg = new ImageIcon("Resources/RocketL.png");
        missile_2 = new Missile_2(ObjectID.MISSILE, 0, 0, addImg.getImage());
        addImg = new ImageIcon("Resources/Wall2.png");
        int y = addImg.getImage().getHeight(null);
        for (int i = 0; i < 4; i++) {
            boolean destroyable = false;
            int x = addImg.getImage().getWidth(null);
            for (int j = 0; j < 4; j++) {
                cWalls.add(new Wall(ObjectID.WALL, 400 + x, 240 + y, addImg.getImage(), destroyable));
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
    public static ArrayList getCWalls() {
        return cWalls;
    }
    public static Missile getMissile() {
        return missile;
    }   
    public static Missile_2 getMissile_2() {
        return missile_2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tankP1.update();
        tankP2.update();
        missile.update();
        missile_2.update();

        repaint();
        
    }
     
    public static void main(String args []) {
        Layout initGame = new Layout(1080, 720, "TankWars", new TankWars());
    }

}
