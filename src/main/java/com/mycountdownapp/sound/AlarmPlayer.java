package com.mycountdownapp.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


 // This class manages the alarm functionality by playing sound files
 // It has the ability to control the volume level

public class AlarmPlayer {

    private Clip clip;
    private FloatControl gainControl;

    public AlarmPlayer(String soundFilePath) {
        try {
            // search for the resource file inside the JAR
            URL soundUrl = getClass().getResource("/" + soundFilePath);
            AudioInputStream audioInputStream;

            if (soundUrl == null) {
                // if not found in JAR, try to find it on the file system
                File soundFile = new File(soundFilePath).getAbsoluteFile();
                if (!soundFile.exists()) {
                    throw new IOException("Alarm sound file not found: " + soundFilePath);
                }
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } else {
                // read the file from within the JAR using URL
                audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            }

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // get the FloatControl to manage volume
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                System.err.println("Volume control is not supported.");
            }

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.err.println("Failed to load the alarm sound file: " + soundFilePath);
            ex.printStackTrace();
            clip = null; // set clip to null on error
        }
    }

    // Starts the alarm and plays it in a continuous loop
    public void start() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // stops the alarm
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0); // Reset position after stopping
        }
    }

    // checks if the alarm is currently running
    // @return True if the alarm is running, false otherwise
    public boolean isRunning() {
        return clip != null && clip.isRunning();
    }

    // sets the volume level based on a slider value (0-100)
    public void setVolume(int sliderValue) {
        if (gainControl != null) {
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();

            // Convert slider value (0-100) to the volume range
            float newVolume = min + (max - min) * (sliderValue / 100.0f);

            // Set the new volume, ensuring it's within the valid range
            gainControl.setValue(newVolume);
        }
    }
}
