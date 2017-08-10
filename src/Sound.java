import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
* Citing: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java?noredirect=1&lq=1
*   Thanks to pek.
*
*/

public class Sound {
    public static synchronized void playMusic() {
        try {
            Clip musicClip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(new File("Resources/Music.wav"));
            musicClip.open(input);
            musicClip.start();
            musicClip.loop(10);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static synchronized void playExplosion() {
        try {
            Clip musicClip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(new File("Resources/Explosion_small.wav"));
            musicClip.open(input);
            musicClip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
