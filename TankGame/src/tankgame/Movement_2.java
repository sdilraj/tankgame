package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement_2 extends KeyAdapter {
    Tank_2 pTank;
    
    public Movement_2(Tank_2 tank) {
        pTank = tank;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
            pTank.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
            pTank.keyReleased(e);
    }
}
