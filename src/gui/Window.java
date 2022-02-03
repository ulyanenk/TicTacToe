package gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static CardLayout cardLayout = new CardLayout();
    private static JPanel panelHolder = new JPanel(); // holds both panels: game panels and difficulty panel

    DifficultyChoice difficultyChoice = new DifficultyChoice();

    public Window() {

        ImageIcon boardPicture = new ImageIcon("gui/boardLogo.png");
        JPanel gamePanel = new GamePanel();

        panelHolder.setLayout(cardLayout);
        panelHolder.add(difficultyChoice, "difficultyChoice"); // used in CardLayout
        panelHolder.add(gamePanel, "gamePanel");

        this.setSize(430, 650); // set x and y Dimensions (last 15 pixels aren't really visible)
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setResizable(true);

        //this.setLayout(cardLayout);

        this.setIconImage(boardPicture.getImage()); // changes the icon
        this.getContentPane().setBackground(Color.WHITE);

        //this.add(difficultyChoice, "difficultyChoice"); // used in CardLayout
        //this.add(gamePanel, "gamePanel");
        this.add(panelHolder);
        //this.pack(); // automatically passes the size of the frame to the size of the label

        cardLayout.show(panelHolder, "difficultyChoice"); // show difficultyChoice first

        this.setVisible(true);

    }

    static void switchToGame(){ // shows the game, after the difficulty gets chosen
        cardLayout.show(panelHolder, "gamePanel");
    }
}
