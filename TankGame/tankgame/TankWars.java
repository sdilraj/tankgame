package tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class TankWars extends JPanel implements Runnable, KeyListener {
    private int p1_x = 0, p1_y = 0; // Player 1 coordinates
    private int p2_x = 1015, p2_y = 655; // Player 2 coordinates
    
    private BufferedImage background;
    private BufferedImage tank1, tank2;
    
    TankWars() {
        init();
    }
    
    @Override
    public void run() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
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
        
        g.drawImage(tank1, p1_x, p1_y, this);
        g.drawImage(tank2, p2_x, p2_y, this);
    }
    
    public void init () {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        try {
            background = ImageIO.read(new File("Resources/Background.png"));
            tank1 = ImageIO.read(new File("Resources/Tank1.png"));
            tank2 = ImageIO.read(new File("Resources/tank2.png"));
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
            p1_y += 5;
            break;
        case  KeyEvent.VK_UP:
            p1_y -= 5;
            break;
        case  KeyEvent.VK_LEFT:
            p1_x -= 5;
            break;
        case KeyEvent.VK_RIGHT:
            p1_x += 5;
            break;
        case KeyEvent.VK_W:
            p2_y -= 5;
            break;
        case KeyEvent.VK_A:
            p2_x -= 5;
            break;
        case KeyEvent.VK_S:
            p2_y += 5;
            break;
        case KeyEvent.VK_D:
            p2_x += 5;
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
