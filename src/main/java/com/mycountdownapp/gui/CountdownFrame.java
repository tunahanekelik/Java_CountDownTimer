package com.mycountdownapp.gui;

import com.mycountdownapp.logic.CountdownLogic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


 // Represents the main graphical user interface (GUI) for the Countdown Timer application
 // It is responsible for creating and arranging all visual components and
 // delegating user interactions to the CountdownLogic
public class CountdownFrame extends JFrame {
    private JTextField hourInput, minuteInput, secondInput;
    private JLabel countdownLabel;
    private JButton startButton, stopButton, resetButton;
    private JSlider volumeSlider;

    // Reference to the application logic to delegate events
    private CountdownLogic countdownLogic;

    public CountdownFrame() {
        super("Countdown Timer"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        // Main panel with BorderLayout for overall layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        add(mainPanel); // Add mainPanel to the JFrame

        // Input panel for hour, minute, second fields
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        hourInput = new JTextField("00", 2);
        minuteInput = new JTextField("00", 2);
        secondInput = new JTextField("00", 2);

        inputPanel.add(new JLabel("Hour:"));
        inputPanel.add(hourInput);
        inputPanel.add(new JLabel("Minute:"));
        inputPanel.add(minuteInput);
        inputPanel.add(new JLabel("Second:"));
        inputPanel.add(secondInput);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Countdown display label
        countdownLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 48));
        mainPanel.add(countdownLabel, BorderLayout.CENTER);

        // Panel for control buttons (Start, Stop, Reset)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(resetButton);

        // Panel for volume slider
        JPanel volumePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // Default volume at 50%
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        volumePanel.add(new JLabel("Volume:"));
        volumePanel.add(volumeSlider);

        // Combine buttons and volume slider into a single control panel at the bottom
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 rows, 1 column
        controlPanel.add(buttonsPanel);
        controlPanel.add(volumePanel);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Pack the frame to fit components and make it visible
        pack();
    }


     // Sets the CountdownLogic instance to handle user interactions
     // This method is called by MainApp to wire up the GUI with the logic
     // logic The CountdownLogic instance
    public void setCountdownLogic(CountdownLogic logic) {
        this.countdownLogic = logic;
        addListeners(); // Add listeners after logic is set
    }


     // Adds action listeners to buttons and change listener to the slider
     // These listeners delegate actions to the CountdownLogic
    private void addListeners() {
        startButton.addActionListener(e -> {
            try {
                int hours = Integer.parseInt(hourInput.getText());
                int minutes = Integer.parseInt(minuteInput.getText());
                int seconds = Integer.parseInt(secondInput.getText());
                countdownLogic.startOrPauseTimer(hours, minutes, seconds);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for time.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        stopButton.addActionListener(e -> countdownLogic.stopTimer());
        resetButton.addActionListener(e -> countdownLogic.resetTimer());

        volumeSlider.addChangeListener(e -> countdownLogic.setAlarmVolume(volumeSlider.getValue()));
    }


     // Updates the countdown display label. This method is called by CountdownLogic
     // timeFormatted The formatted time string (HH:MM:SS)
    public void updateCountdownDisplay(String timeFormatted) {
        countdownLabel.setText(timeFormatted);
    }


     // Resets the input fields to default "00". This method is called by CountdownLogic.

    public void resetInputFields() {
        hourInput.setText("00");
        minuteInput.setText("00");
        secondInput.setText("00");
    }


     // Sets the text of the start button (e.g., "Start", "Pause", "Resume").
     // This method is called by CountdownLogic.
     // The new text for the start button.

    public void setStartButtonText(String text) {
        startButton.setText(text);
    }

     // returns the current value of the volume slider.
     // this is used by CountdownLogic to check the alarm volume before playing.
     // The current value of the volume slider (0-100).

    public int getVolumeSliderValue() {
        return volumeSlider.getValue();
    }
}