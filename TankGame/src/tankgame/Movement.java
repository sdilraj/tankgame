package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement extends KeyAdapter {    
    Tank pTank;
    
    public Movement(Tank tank) {
        pTank = tank;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //int code = e.getKeyCode();
            pTank.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        //int code = e.getKeyCode();
            pTank.keyReleased(e);
    }
}
