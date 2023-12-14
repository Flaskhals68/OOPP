package com.group4.app.view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    private static Clip clip;
    private static Clip game_music;

    public static void playSound(String filePath, boolean looping) {
        try {
            File soundFile = new File(filePath);

            // Get an AudioInputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a Clip
            clip = AudioSystem.getClip();

            // Open audioInputStream to the clip
            clip.open(audioInputStream);

            // Start playing the sound
            if(looping){
                game_music = clip;
                game_music.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                clip.start();
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound() {
        if (game_music != null && game_music.isRunning()) {
            game_music.stop();
        }
    }
}
