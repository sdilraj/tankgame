package tankgame;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Layout {
    
    public Layout(int width, int height, String gameTitle, TankWars tank) {
        tank.setPreferredSize(new Dimension(width, height));

        JFrame gameInterface = new JFrame(gameTitle);
        gameInterface.add(tank);
        gameInterface.pack();
        gameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameInterface.setVisible(true);
        gameInterface.setResizable(false);
               
    }
}
