package main;

import javax.swing.*;

public class Main {

    // "View" in clean architecture
    // set up the screen for the User
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BlueBoyAdventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();  // BEFORE starting the game thread! #7
        gamePanel.startGameThread();
    }
}
