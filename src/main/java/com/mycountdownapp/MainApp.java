// --- com.mycountdownapp.MainApp.java ---
package com.mycountdownapp;

import com.mycountdownapp.gui.CountdownFrame;
import com.mycountdownapp.logic.CountdownLogic;
import com.mycountdownapp.sound.AlarmPlayer;

import javax.swing.SwingUtilities;

/**
 * Main application entry point for the Countdown Timer App.
 * Initializes the GUI, application logic, and sound player, then connects them.
 */
public class MainApp {
    public static void main(String[] args) {
        // Ensure that GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize the sound player
            AlarmPlayer alarmPlayer = new AlarmPlayer("alarm.wav");

            // Initialize the GUI frame
            CountdownFrame frame = new CountdownFrame();

            // Initialize the application logic, passing the frame and alarmPlayer
            CountdownLogic logic = new CountdownLogic(frame, alarmPlayer);

            // Set the logic for the frame (to handle button clicks, slider changes)
            frame.setCountdownLogic(logic);

            // Make the main application window visible
            frame.setVisible(true);
        });
    }
}
