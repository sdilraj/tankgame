package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MissileMovement_2 extends KeyAdapter {
    Missile_2 missile;
    
    public MissileMovement_2(Missile_2 missile) {
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
