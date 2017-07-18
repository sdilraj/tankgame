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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TankWars extends JPanel implements Runnable, KeyListener {
    private Tank tankP1;
    private Tank tankP2;
    Wall [] dWalls = new Wall[50]; // Destroyable walls
    Wall [] cWalls = new Wall[50]; // Concrete walls
    private int p1_x = 0, p1_y = 0; // Player 1 starting coordinates
    private int p2_x = 1015, p2_y = 655; // Player 2 starting coordinates
    private boolean mapComplete = false;
    private BufferedImage background;
    private BufferedImage tank1, tank2;
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
            break;
        case  KeyEvent.VK_UP:
            tankP1.moveUp();
            break;
        case  KeyEvent.VK_LEFT:
            tankP1.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            tankP1.moveRight();
            break;
        case KeyEvent.VK_W:
            tankP2.moveUp();
            break;
        case KeyEvent.VK_A:
            tankP2.moveLeft();
            break;
        case KeyEvent.VK_S:
            tankP2.moveDown();
            break;
        case KeyEvent.VK_D:
            tankP2.moveRight();
            break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String args []) {
        Layout initGame = new Layout(1080, 720, "TankWars", new TankWars());
    }
}
