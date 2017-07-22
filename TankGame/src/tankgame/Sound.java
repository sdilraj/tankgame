/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    public static synchronized void playMusic() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip musicClip = AudioSystem.getClip();
                    AudioInputStream input = AudioSystem.getAudioInputStream(new File("Resources/Music.wav"));
                    musicClip.open(input);
                    musicClip.start();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
