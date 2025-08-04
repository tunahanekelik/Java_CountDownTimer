Countdown Timer App
Project Overview
This application allows users to set a countdown in hours, minutes, and seconds. It features an alarm sound that plays when the countdown is complete, along with adjustable volume control. The alarm sound is set to Tyler, the Creator's song "Ring Ring Ring." Developed using Java and Swing, it boasts a clean and modular code structure.

Features
Customizable Countdown: Set the timer for specific hours, minutes, and seconds.

Adjustable Alarm Volume: Control the alarm sound level with a dedicated slider.

Unique Alarm Sound: Features "Ring Ring Ring" by Tyler, the Creator as the alarm.

Timer Controls: Intuitive "Start/Pause," "Stop," and "Reset" buttons.

User-Friendly Interface: A simple and clean graphical interface for ease of use.

Technologies Used
Java: The core programming language.

Java Swing: For building the graphical user interface.

Getting Started
To run this application, ensure you have Java Development Kit (JDK) 8 or a newer version installed on your system.

Running from JAR
Download the JAR: Obtain the CountdownTimerApp.jar file from the releases page (you will create this later) or by building the project yourself.

Ensure Alarm Sound: Make sure the alarm.wav file is present in the same directory as the .jar file, or embedded within the JAR (as configured in the project).

Open Command Prompt/Terminal: Navigate to the directory where you saved the .jar file using your command prompt or terminal.

Execute: Run the application using the command:

java -jar CountdownTimerApp.jar

Running from Source (IDE)
Clone the Repository:

git clone https://github.com/tunahanekelik/Java_CountDownTimer.git

Open in IntelliJ IDEA: Open the cloned project in IntelliJ IDEA (or your preferred Java IDE).

Build Project: Ensure the project is properly built (Build > Rebuild Project).

Run MainApp: Locate com.mycountdownapp.MainApp.java and run its main method.

Project Structure
The project is organized into a modular structure following OOP principles:

com.mycountdownapp.MainApp: The application's entry point.

com.mycountdownapp.gui.CountdownFrame: Manages the graphical user interface components.

com.mycountdownapp.logic.CountdownLogic: Contains the core timer logic and coordinates between GUI and sound.

com.mycountdownapp.sound.AlarmPlayer: Handles playing and controlling the alarm sound.

Contributing
Contributions are welcome! If you find a bug or have suggestions for new features, please open an issue or submit a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for more details.
