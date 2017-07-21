package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MissileMovement extends KeyAdapter {
    Missile missile;
    
    public MissileMovement(Missile missile) {
        this.missile = missile;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        missile.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
