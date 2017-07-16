package tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankGame extends JPanel implements  KeyListener {;
    int x = 0, y = 0;
    public BufferedImage background;
    public BufferedImage tank1;
    public BufferedImage tank2;

    public TankGame() {
        try {
            background = ImageIO.read(new File("Resources/background.gif"));
            tank1 = ImageIO.read(new File("Resources/Tank1.png"));
            tank2 = ImageIO.read(new File("Resources/Tank2.png"));

        } catch (IOException e) {
            //
        }
        
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public static BufferedImage imageResize(BufferedImage img, int width, int height) {
        Image temp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImg.createGraphics();
        g2.drawImage(temp, 0, 0, null);
        g2.dispose();
        
        
        return newImg;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawImage(background, 0, 0, this);
        g.drawImage(background, 0, 230, this);
        g.drawImage(background, 0, 460, this);
        g.drawImage(background, 315, 0, this);
        g.drawImage(background, 315, 230, this);
        g.drawImage(background, 315, 460, this);
        g.drawImage(background, 630, 0, this);
        g.drawImage(background, 630, 230, this);
        g.drawImage(background, 630, 460, this);
        g.drawImage(background, 945, 0, this);
        g.drawImage(background, 945, 230, this);
        g.drawImage(background, 945, 460, this);
        //g.fillRect(x, y, 50, 50);
        g.drawImage(tank1, x, y, this);
        
        g.drawImage(tank2, x, y, this);
        
        //g.fillRect(x, y, 50, 50);
        /*BufferedImage Tank1 = null;
        try {
            Tank1 = ImageIO.read(new File("Resources/Tank1.gif"));
            g.drawImage(Tank1, 0, 0, null);
            g.dispose();
        } catch (IOException e) {}*/
        
        
        
        
    }


    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
        int code = e.getKeyCode();

        switch(code){
            case KeyEvent.VK_DOWN:
            y+=5;
            break;
        case  KeyEvent.VK_UP:
            y -= 5;
            break;
        case  KeyEvent.VK_LEFT:
            x -= 5;
            break;
        case KeyEvent.VK_RIGHT:
            x += 5;
            break;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }

    public static void main(String arge[]) {

        JFrame f = new JFrame();
        TankGame s = new TankGame();
        f.add(s);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1080, 720);
        f.setVisible(true);

    }

}