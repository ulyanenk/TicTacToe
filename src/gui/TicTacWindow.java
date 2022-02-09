package gui;

import logics.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacWindow extends JFrame implements ActionListener {

    private CardLayout cardLayout = new CardLayout();
    private JPanel panelHolder = new JPanel(); // holds both panels: game panels and difficulty panel

    DifficultyChoice difficultyChoice;

    GamePanel gamePanel;

    //private int difficulty;

    public TicTacWindow() {

        ImageIcon boardPicture = new ImageIcon("gui/boardLogo.png");

        difficultyChoice = new DifficultyChoice(this); // "this" in closes defines the ActionListener
        gamePanel = new GamePanel(this);

        panelHolder.setLayout(cardLayout);
        panelHolder.add(difficultyChoice, "difficultyChoice"); // used in CardLayout
        panelHolder.add(gamePanel, "gamePanel");

        this.setSize(430, 650); // set x and y Dimensions (last 15 pixels aren't really visible)
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setResizable(false);

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

    void switchToGame(int difficulty) { // shows the game, after the difficulty gets chosen
        gamePanel.setDifficulty(difficulty);
        gamePanel.implementFirstTurnButton(); // implements the first turn button in dependency of the game mode
        cardLayout.show(panelHolder, "gamePanel");
    }

    void switchToDifficultyChoice () {
        cardLayout.show(panelHolder, "difficultyChoice");
        //Board.clearBoard();
        gamePanel.updateBoard(true);
        gamePanel.firstTurnButton.setVisible(true);
        gamePanel.firstTurnButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board.clearBoard(); // gives start value for all cells (empty)

        if (e.getSource() == difficultyChoice.easy) {
            switchToGame(1);

        } else if (e.getSource() == difficultyChoice.normal) {
            switchToGame(2);

        } else if (e.getSource() == difficultyChoice.hard) {
            switchToGame(3);

        } else if (e.getSource() == difficultyChoice.twoPlayers) {
            switchToGame(4);

        } else if (e.getSource() == gamePanel.returnToTheMenu) {
            switchToDifficultyChoice();
            gamePanel.playAgain.setEnabled(false);
            gamePanel.playAgain.setVisible(false);
        }
        gamePanel.implementTurnText();
    }
}
