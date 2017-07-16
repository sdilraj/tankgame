/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author doraemon
 */
public class Layout {
    int width, height;
    String title;
    
    public Layout(int width, int height, String gameTitle, TankWars game) {
        game.setPreferredSize(new Dimension(width, height));
        
        JFrame gameInterface = new JFrame(gameTitle);
    
        gameInterface.add(game);
        gameInterface.pack();
        gameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameInterface.setVisible(true);
        gameInterface.setResizable(false);
               
    }
}
