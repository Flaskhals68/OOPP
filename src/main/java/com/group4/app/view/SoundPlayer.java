package com.group4.app.view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);

            // Get an AudioInputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a Clip
            Clip clip = AudioSystem.getClip();

            // Open audioInputStream to the clip
            clip.open(audioInputStream);

            // Start playing the sound
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
