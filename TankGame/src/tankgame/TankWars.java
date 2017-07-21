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
    private static Missile missile_2;
    private boolean launch = false;
    private static Tank tankP1;
    private static Tank_2 tankP2;
    private ArrayList<Wall> cWalls = new ArrayList<>(); // Concrete walls
    private ArrayList<Wall> dWalls = new ArrayList<>(); // Destroyable walls
    private int p1_x = 0, p1_y = 0; // Initial player 1 coordinates
    private int p2_x = 1015, p2_y = 655; // Initial player 2 coordinates
    private BufferedImage background;
        
    TankWars() {
        init();
        addKeyListener(new Movement(tankP1));
        addKeyListener(new Movement_2(tankP2));
        addKeyListener(new MissileMovement(missile));
        addKeyListener(new MissileMovement(missile_2));
        
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
           
        tankP1.draw(g2D);
        tankP2.draw(g2D);
        if (missile.isLaunched())
                missile.draw(g2D);
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
        missile = new Missile (ObjectID.MISSILE, 0, 0, addImg.getImage());
        //bullet.add(new Missile(ObjectID.MISSILE, 0, 0, addImg.getImage()));
        addImg = new ImageIcon("Resources/RocketL.png");
        missile_2 = new Missile(ObjectID.MISSILE, 0, 0, addImg.getImage());
        //bullet.add(new Missile(ObjectID.MISSILE, 0, 0, addImg.getImage()));
    }
    
    public static Tank getTank() {
        return tankP1;
    }
    public static Tank_2 getTank_2() {
        return tankP2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if(!tankP1.collide())
            tankP1.update();
            
        //if(!tankP2.collide())
            tankP2.update();
        missile.update();

        repaint();
        
    }
     
    public static void main(String args []) {
        Layout initGame = new Layout(1080, 720, "TankWars", new TankWars());
    }

}
