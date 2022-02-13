package gui;

import logics.AI;
import logics.Board;
import logics.CellValue;
import logics.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    private BoardButton button00 = new BoardButton(0, 0);
    private BoardButton button01 = new BoardButton(0, 1);
    private BoardButton button02 = new BoardButton(0, 2);
    private BoardButton button10 = new BoardButton(1, 0);
    private BoardButton button11 = new BoardButton(1, 1);
    private BoardButton button12 = new BoardButton(1, 2);
    private BoardButton button20 = new BoardButton(2, 0);
    private BoardButton button21 = new BoardButton(2, 1);
    private BoardButton button22 = new BoardButton(2, 2);

    JButton playAgain; //= new JButton(/*"Play Again"*/);
    //JLabel label00;
    List<BoardButton> buttonList = new ArrayList<>();
    JLabel turnText = new JLabel();
    RunGame runGame = new RunGame();
    ScorePanel scorePanel;
    JButton firstTurnButton;
    JButton returnToTheMenu;

    ImageIcon arrow = new ImageIcon("gui/leftArrow.png");

    private int difficulty;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    private final int[] score = {0, 0}; // counts each victory of both players
    private int turnCounter = 1; // defines whose turn is it; if is the integer is odd X moves, if even O moves

    //ActionListener parent;

    GamePanel(ActionListener parent) {

        JPanel boardPanel = new JPanel();
        scorePanel = new ScorePanel();
        firstTurnButton = new JButton(/*"AI moves first"*/);
        returnToTheMenu = new JButton();
        playAgain = new JButton("Play Again");

        returnToTheMenu.setBounds(10, 0, 60, 60);
        returnToTheMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        returnToTheMenu.setBackground(Color.WHITE);
       // returnToTheMenu.setFont(new Font(null, Font.BOLD, 30));
        returnToTheMenu.setIcon(arrow);
        returnToTheMenu.setEnabled(true);
        returnToTheMenu.setVisible(true);
        returnToTheMenu.addActionListener(parent);

        firstTurnButton.setBounds(90, 0, 220, 50);
        firstTurnButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        firstTurnButton.setBackground(Color.WHITE);
        firstTurnButton.setFont(new Font(null, Font.ROMAN_BASELINE, 30));
        firstTurnButton.setEnabled(true);
        firstTurnButton.setVisible(true);
        firstTurnButton.addActionListener(this);
        //implementFirstTurnButton();

        buttonList.add(button00);
        buttonList.add(button01);
        buttonList.add(button02);
        buttonList.add(button10);
        buttonList.add(button11);
        buttonList.add(button12);
        buttonList.add(button20);
        buttonList.add(button21);
        buttonList.add(button22);
        addActionListenerToButtons();

        playAgain.setBounds(90, 0, 220, 50);
        playAgain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        playAgain.setBackground(Color.WHITE);
        playAgain.setFont(new Font(null, Font.ROMAN_BASELINE, 40));
        playAgain.setEnabled(false);
        playAgain.setVisible(false);
        playAgain.addActionListener(this);

        turnText.setText("Your turn: "); // set text of the label
        turnText.setForeground(Color.BLUE); // set colour of the text
        turnText.setFont(new Font("My Font", Font.ROMAN_BASELINE, 50)); // set font of the text
        turnText.setBackground(Color.WHITE);
//        label.setOpaque(true); // display background color

        turnText.setVerticalAlignment(JLabel.CENTER); // set the image coordinates
        turnText.setHorizontalAlignment(JLabel.CENTER);
        turnText.setBounds(0, 0, 400, 200); // set coordinates and size of the label

        boardPanel.setBounds(7, 200, 400, 400);
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.add(button00);
        boardPanel.add(button10);
        boardPanel.add(button20);
        boardPanel.add(button01);
        boardPanel.add(button11);
        boardPanel.add(button21);
        boardPanel.add(button02);
        boardPanel.add(button12);
        boardPanel.add(button22);
        boardPanel.setVisible(true);

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(turnText);
        this.add(boardPanel);
        this.add(playAgain);
        this.add(scorePanel);
        this.add(firstTurnButton);
        this.add(returnToTheMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playAgain) {

             Board.clearBoard();
             updateBoard(true);
             //turnText.setText("Your Turn");

             playAgain.setEnabled(false);
             playAgain.setVisible(false);

            firstTurnButton.setEnabled(true);
            firstTurnButton.setVisible(true);

            implementTurnText();

        } else if (e.getSource() == firstTurnButton) {

            if (difficulty == 4) { // if two players mode is on, it adds 1 to a counter
                turnCounter++;
                implementFirstTurnButton();
                implementTurnText();

            } else {
                firstTurnButton.setEnabled(false);
                firstTurnButton.setVisible(false);
                Logic logic = new Logic();
                logic.firstTurnCornerRandom();
                updateBoard(true);
            }

        } //else if (e.getSource() == returnToTheMenu) {
        //}
        else { // for the bord buttons

            if (difficulty != 4) { // if playing against AI
                makeTurn(CellValue.X, e); // makes turn in the button, that was pushed

                if (!checkVictory(CellValue.X)) {
                    runGame.AiTurn(difficulty);
                    checkVictory(CellValue.O);
                    //System.out.println("ai's turn made");
                    //Board.printField();
                }
                updateBoard(false);

            } else { // if playing against another human
               twoPlayersMode(e);

               CellValue player; // updates the turn text label
               if (isEven(turnCounter)) {
                   player = CellValue.X;
               } else {
                   player = CellValue.O;
               }

               if (!checkVictory(player)) {
                   implementTurnText();
               }
                //update(false);
            }
            if (firstTurnButton.isEnabled()) {
                firstTurnButton.setEnabled(false); // turns off this button if, it wasn't chosen
                firstTurnButton.setVisible(false);
                //System.out.println("AI not first turn");
            }
        }
//        implementTurnText();
    }

     void updateBoard(boolean emptyCells) {
        for (BoardButton n : buttonList) {
            n.synchronize(emptyCells);
        }
    }

    private void addActionListenerToButtons() {
        for (BoardButton n : buttonList) {
            n.addActionListener(this);
        }
    }

    private void disableAllButtons() {
        for (BoardButton n : buttonList) {
            n.setEnabled(false);
        }

    }

    private void makeTurn(CellValue player, ActionEvent e) {

        for (BoardButton n : buttonList) {
            if (n == e.getSource()){

                n.makeTurn(player);
                n.setEnabled(false);

                if (player == CellValue.O) {
                    n.setText("O");
                } else if (player == CellValue.X) {
                    n.setText("X");
                }

                //System.out.println("turn made"); // [commented]
                //Board.printField();
            }
        }
    }

    private void twoPlayersMode(ActionEvent e) {
        //aiTurnFirstButton.setEnabled(false);
        //aiTurnFirstButton.setVisible(false);

        turnCounter++;
        CellValue player;

        if (isEven(turnCounter)) {
            player = CellValue.X;
        } else {
            player = CellValue.O;
        }

        makeTurn(player, e);
//        if (checkVictory(player)) {
//            turnCounter = 1;
//        }

    }

    private boolean isEven(int num) { // checks the parity of an integer
        return num % 2 == 0;
    }

    void implementFirstTurnButton() {
        String buttonText;

        if (difficulty == 4) { // if twoPlayers mode
            if (!isEven(turnCounter)) {
                buttonText = "O moves first";

            } else {
                buttonText = "X moves first";
            }

        } else { // if playing against AI
            buttonText = "AI moves first";
        }
        firstTurnButton.setText(buttonText);
    }

    void implementTurnText() {
        String text;
        if (difficulty == 4) {
            if (!isEven(turnCounter)) {
                text = "X's turn";

            } else {
                text = "O's turn";
            }
        } else {
            text = "Your turn";
        }
        turnText.setText(text);
    }

   /* private String setGameOverSign(int gameResult) {

        switch (gameResult) {

            case 1: return "You Won";
            case 2: return "You Lost";
            case 3: return "Draw";
            case 4: return "X player wins";
            case 5: return "O player wins";
            default: return null; // shall never shoot
        }
    }*/

    private boolean checkVictory(CellValue forWhom){ // checks the whether any party has won this turn

        Logic logic = new Logic();
        AI ai = new AI();

        String xWins;
        String oWins;

        if (difficulty == 4) { // set victory text in dependency of game mode
            xWins = "X won";
            oWins = "O won";

        } else {
            xWins = "You won";
            oWins = "You lost";
        }

            // checks the whether the user has won this turn or if all cells are occupied, it's draw
            if (logic.victory(forWhom, Board.realBoard)) {

                if (forWhom == CellValue.X) {
                    turnText.setText(xWins);
                    score[1]++;

                } else if (forWhom == CellValue.O) {
                    turnText.setText(oWins);
                    score[0]++;
                }
                disableAllButtons();
                playAgain.setEnabled(true);
                playAgain.setVisible(true);
                scorePanel.actualize();
                return true;

            } else if (ai.over(Board.realBoard)) {
                turnText.setText("Draw");
                disableAllButtons();
                playAgain.setEnabled(true);
                playAgain.setVisible(true);
                return true;
            }
            return false;
    }

    void returnToMenu() {


    }


    private class ScorePanel extends JPanel {

        //private int score[] = {2, 1};

        JLabel scoreLabel = new JLabel();
        JLabel values = new JLabel();

        ScorePanel() {

            Font scoreFont = new Font("scoreFont", Font.ITALIC, 30);

            setBounds(330, 0, 200, 100);

            scoreLabel.setSize(50, 10);
            scoreLabel.setText("O  X");
            scoreLabel.setFont(scoreFont);
            //scoreLabel.setVerticalAlignment(JLabel.TOP);
            //scoreLabel.setHorizontalAlignment(JLabel.CENTER);
            scoreLabel.setBackground(Color.GREEN);
            scoreLabel.setOpaque(false);

            values.setSize(50, 20);
            values.setText(score[0] + " : " + score[1]);
            values.setFont(scoreFont);
            values.setBackground(Color.RED);
            values.setOpaque(false);
            //values.setVerticalAlignment(JLabel.BOTTOM);
            //values.setHorizontalAlignment(JLabel.CENTER);

            this.setLayout(new GridLayout(2, 1, 0, 0));
            this.add(scoreLabel);
            this.add(values);
            this.setBackground(Color.WHITE);

        }

        void actualize() {
            values.setText(score[0] + " : " + score[1]);

            if (score[1] >= 10 && score[0] >= 10) {
                scoreLabel.setText(" O    X");

            } else if (score[0] >= 10) {
                scoreLabel.setText(" O   X");

            } else if (score[1] >= 10) {
                scoreLabel.setText("O   X");
            }
        }
    }

}