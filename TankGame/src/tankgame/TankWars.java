package tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
    import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TankWars extends JPanel implements Runnable, KeyListener {
    private Tank tankP1;
    private Tank tankP2;
    private Missile bull1;
    private Missile bull2;
    Wall [] dWalls = new Wall[50]; // Destroyable walls
    Wall [] cWalls = new Wall[50]; // Concrete walls
    private int p1_x = 0, p1_y = 0; // Player 1 starting coordinates
    private int p2_x = 1015, p2_y = 655; // Player 2 starting coordinates
    private boolean mapComplete = false;
    private BufferedImage background;
    private BufferedImage tank1, tank2, bul1, bul2;
    
    //Image icon;
    
    TankWars() {
        init();
    }
    
    @Override
    public void run() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        //if (mapComplete == false) {
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
            mapComplete = true;
        //}
        
        g.drawImage(tankP1.Img, tankP1.x, tankP1.y, this);
        g.drawImage(tankP2.Img, tankP2.x, tankP2.y, this);
        g.drawImage(bull1.Img, bull1.x, bull1.y, this);
        g.drawImage(bull2.Img, bull2.x, bull2.y, this);
        
    }
    
    public void init () {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        try {
            background = ImageIO.read(new File("Resources/Background.png"));
            tank1 = ImageIO.read(new File("Resources/Tank1.png"));
            tankP1 = new Tank(p1_x, p1_y, tank1);
            tank2 = ImageIO.read(new File("Resources/tank2.png"));
            tankP2 = new Tank(p2_x, p2_y, tank2);
    
            bul1 = ImageIO.read(new File("Resources/Rocket.png"));
            bull1 = new Missile(p1_x, p1_y, bul1);
            bul2 = ImageIO.read(new File("Resources/Rocket.png"));
            bull2 = new Missile(p2_x, p2_y, bul2);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
        int code = e.getKeyCode();

        switch(code){
            case KeyEvent.VK_DOWN:
            tankP1.moveDown();
            bull1.moveDown();
            break;
        case  KeyEvent.VK_UP:
            tankP1.moveUp();
            bull1.moveUp();
            break;
        case  KeyEvent.VK_LEFT:
            tankP1.moveLeft();
            bull1.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            tankP1.moveRight();
            bull1.moveRight();
            break;
        case KeyEvent.VK_W:
            tankP2.moveUp();
            bull2.moveUp();
            break;
        case KeyEvent.VK_A:
            tankP2.moveLeft();
            bull2.moveLeft();
            break;
        case KeyEvent.VK_S:
            tankP2.moveDown();
            bull2.moveDown();
            break;
        case KeyEvent.VK_D:
            tankP2.moveRight();
            bull2.moveRight();
            break;
        case KeyEvent.VK_L:
            bull2.shoot();
            break;
        case KeyEvent.VK_Q:
            bull1.shoot();
            break;        
        }
        repaint();
    }
    
    
    // rough below
    
   /* ArrayList bullets = TankWars.getBullets();
    for (int w = 0; w < bullets.size(); w++) {
    Bullet m = Bullets) bullets.get(w);
    g2d.drawImage(m.getImage(),m.getX(), m.getY(), null);
}
*/
    
    
    
    //static ArrayList bullets;
    //bullets = new ArrayList();
    
    //public static ArrayList getBullets() {
      //  return bullets;
    //}
    
    
    
//    public void fire() {
      //  Bullet z = new Bullet(0,0);
     //   bullets.add(z);
  //  }
    
    
    //above rough

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String args []) {
        Layout initGame = new Layout(1080, 720, "TankWars", new TankWars());
    }
}
