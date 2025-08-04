package com.mycountdownapp.logic;

import com.mycountdownapp.gui.CountdownFrame;
import com.mycountdownapp.sound.AlarmPlayer;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 // manages the core logic of the countdown timer application
 // this includes handling the timer, updating the countdown, and
 // coordinating with the GUI and sound components
public class CountdownLogic {
    private Timer timer;
    private int remainingSeconds;
    private boolean isTimerRunning = false;

    private CountdownFrame frame; // reference to the GUI frame
    private AlarmPlayer alarmPlayer; // reference to the alarm player

    public CountdownLogic(CountdownFrame frame, AlarmPlayer alarmPlayer) {
        this.frame = frame;
        this.alarmPlayer = alarmPlayer;
        this.timer = new Timer(1000, new TimerActionListener());
    }


     // starts, pauses, or resumes the timer based on its current state
     //  hours Initial hours for the countdown
     // minutes Initial minutes for the countdown
     // seconds Initial seconds for the countdown

    public void startOrPauseTimer(int hours, int minutes, int seconds) {
        if (!isTimerRunning) { // if timer is not running (Start or Resume)
            if (remainingSeconds == 0) { // if starting a new countdown
                remainingSeconds = (hours * 3600) + (minutes * 60) + seconds;
                if (remainingSeconds <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please enter a positive number for the countdown.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // stop any currently playing alarm before starting a new countdown
            if (alarmPlayer != null && alarmPlayer.isRunning()) {
                alarmPlayer.stop();
            }
            timer.start();
            isTimerRunning = true;
            frame.setStartButtonText("Pause");
        } else { // if timer is running (Pause)
            timer.stop();
            isTimerRunning = false;
            frame.setStartButtonText("Resume");
        }
        updateCountdownDisplay(); // update display immediately after state change
    }

    // stops the timer and any active alarm.

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
        if (alarmPlayer != null && alarmPlayer.isRunning()) {
            alarmPlayer.stop();
        }
        isTimerRunning = false;
        frame.setStartButtonText("Start");
    }

    // resets the timer, countdown display, input fields, and any active alarm

    public void resetTimer() {
        stopTimer(); // stop timer and alarm first
        remainingSeconds = 0;
        updateCountdownDisplay();
        frame.resetInputFields();
        frame.setStartButtonText("Start");
    }

    // sets the alarm volume using the AlarmPlayer
    // the value from the volume slider (0-100)

    public void setAlarmVolume(int sliderValue) {
        if (alarmPlayer != null) {
            alarmPlayer.setVolume(sliderValue);
            // if volume is set to 0 and alarm is running stop it
            if (sliderValue == 0 && alarmPlayer.isRunning()) {
                alarmPlayer.stop();
            }
        }
    }

    // Inner class to handle timer events (every second)
    private class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (remainingSeconds > 0) {
                remainingSeconds--;
                updateCountdownDisplay();
            } else {
                timer.stop();
                isTimerRunning = false;
                frame.setStartButtonText("Start");
                frame.updateCountdownDisplay("Finished!");

                // only start alarm if volume is not 0
                // we assume alarmPlayer's setVolume method handles mute/unmute
                if (alarmPlayer != null && frame.getVolumeSliderValue() > 0) { // access slider value from frame
                    alarmPlayer.start();
                }
            }
        }
    }


     // Formats and updates the countdown display on the GUI
    private void updateCountdownDisplay() {
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        frame.updateCountdownDisplay(timeFormatted);
    }
}