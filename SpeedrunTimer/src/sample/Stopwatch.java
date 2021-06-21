package sample;
// Class for the actual timer
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Stopwatch {

    // Consists of two buttons and a label
    private JFrame frame = new JFrame();
    private JButton startButton = new JButton("START"); // Start/stop button (alternative: press p on keyboard)
    private JButton resetButton = new JButton("RESET"); // Reset button (alternative: press o on keyboard)
    private JLabel timeLabel = new JLabel(); // Used to display timer to users
    private int elapsedTime = 0;
    private int seconds =0;
    private int minutes =0;
    private int hours =0;
    private boolean started = false; // Used to determine whether or not the timer is running
    private String secondString = String.format("%02d", seconds); // String format shows 00
    private String minuteString = String.format("%02d", minutes);
    private String hourString = String.format("%02d", hours);
    private Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime=elapsedTime+1000; // elapsedTime is in milliseconds
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60; // Modulo so that it cannot exceed 60
            seconds = (elapsedTime/1000) % 60;
            secondString = String.format("%02d", seconds);
            minuteString = String.format("%02d", minutes);
            hourString = String.format("%02d", hours);
            timeLabel.setText(hourString+":"+minuteString+":"+secondString); // Formats timeLabel to hours:minutes:seconds

        }

    });

    // Action variables to get the buttons to do the actions
    private Action startStopAct = new AbstractAction(){ // Action variables are used to give the buttons and keyboard presses functionality
        @Override
        public void actionPerformed(ActionEvent e){
            if(started==false) { // Starts the timer if the timer is not running
                started=true;
                startButton.setText("STOP");
                start();
            }
            else { // Stops the timer if the timer is moving
                started=false;
                startButton.setText("START");
                stop();
            }
        }
    };



    private Action resetAct = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            started=false;
            startButton.setText("START");
            reset();

        }
    };

    // Variables for setting up keybinds
    private InputMap im = ((JPanel)frame.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    private ActionMap am = ((JPanel)frame.getContentPane()).getActionMap();

    public Stopwatch(){


        timeLabel.setText(hourString+":"+minuteString+":"+secondString);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Verdana",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(startStopAct); // Binding the startStopAct Action to the startButton

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Verdana",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(resetAct); // Binding the resetAct Action to the resetButton

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);

        // Setting up the keybinds in the constructor
        // P and O are used for keybinds because they are rarely used in games
        im.put(KeyStroke.getKeyStroke("P"),"StartStopTimer");
        im.put(KeyStroke.getKeyStroke("O"),"ResetTimer");
        am.put("StartStopTimer",startStopAct);
        am.put("ResetTimer",resetAct);
    }







    private void start() {
        timer.start(); // Activates the timer object
    }

    private void stop() {
        timer.stop(); // Deactivates the timer object
    }

    //Resets the timer
    private void reset() {
        timer.stop();
        elapsedTime=0;
        seconds =0;
        minutes=0;
        hours=0;
        secondString = String.format("%02d", seconds);
        minuteString = String.format("%02d", minutes);
        hourString = String.format("%02d", hours);
        timeLabel.setText(hourString+":"+minuteString+":"+secondString);
    }

    // Used to get the time when a segment is completed
    public String getTimeLabelText(){
        return timeLabel.getText();
    }

}
