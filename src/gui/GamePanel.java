package gui;

import logics.CellValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {


    private static JButton button00 = new BoardButton(0, 0);
    private static JButton button01 = new BoardButton(0, 1);
    private static JButton button02 = new BoardButton(0, 2);
    private static JButton button10 = new BoardButton(1, 0);
    private static JButton button11 = new BoardButton(1, 1);
    private static JButton button12 = new BoardButton(1, 2);
    private static JButton button20 = new BoardButton(2, 0);
    private static JButton button21 = new BoardButton(2, 1);
    private static JButton button22 = new BoardButton(2, 2);

    JButton playAgain = new JButton("Play Again");
    //JLabel label00;

    List<BoardButton> buttonList = new ArrayList<>();

    GamePanel() {

        JLabel turnText = new JLabel(); // create a label
        JPanel boardPanel = new JPanel();
        //JPanel scorePanel = new ScorePanel();

        buttonList.add((BoardButton) button00);
        buttonList.add((BoardButton) button01);
        buttonList.add((BoardButton) button02);
        buttonList.add((BoardButton) button10);
        buttonList.add((BoardButton) button11);
        buttonList.add((BoardButton) button12);
        buttonList.add((BoardButton) button20);
        buttonList.add((BoardButton) button21);
        buttonList.add((BoardButton) button22);

        addActionListenerToButtons();
//        button00.addActionListener(this);
//        button10.addActionListener(this);
//        button20.addActionListener(this);
//        button01.addActionListener(this);
//        button02.addActionListener(this);
//        button11.addActionListener(this);
//        button12.addActionListener(this);
//        button21.addActionListener(this);
//        button22.addActionListener(this);

        playAgain.setBounds(90, 0, 220, 50);
        playAgain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        playAgain.setBackground(Color.WHITE);
        playAgain.setFont(new Font(null, Font.ROMAN_BASELINE, 40));
        playAgain.setEnabled(false);
        playAgain.setVisible(false);

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
        //this.add(scorePanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playAgain) {

            // cleanBoard();
            // synchronize();
        } else {

            makeTurn(CellValue.X);
            update();
        }
    }

     void update() {
        for (BoardButton n : buttonList) {
            n.synchronize();
        }
    }

    void addActionListenerToButtons() {
        for (BoardButton n : buttonList) {
            n.addActionListener(this);
        }
    }

    void makeTurn(CellValue player){
        for (BoardButton n : buttonList) {
            n.makeTurn(player);
        }
    }


    // ScoreTable (isn't finished yet)
    /*private class ScorePanel extends JPanel {

        private int score[] = {2, 1};

        JLabel scoreLabel = new JLabel();
        JLabel values = new JLabel();

        ScorePanel() {

            Font scoreFont = new Font("scoreFont", Font.ITALIC, 30);

            setBounds(300, 0 , 200, 100);

            scoreLabel.setSize(50, 30);
            scoreLabel.setText("O    X");
            scoreLabel.setFont(scoreFont);
            //scoreLabel.setVerticalAlignment(JLabel.TOP);
            //scoreLabel.setHorizontalAlignment(JLabel.CENTER);
            scoreLabel.setBackground(Color.GREEN);
            scoreLabel.setOpaque(false);

            values.setSize(50, 30);
            values.setText("3  |  2");
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
    }*/

}