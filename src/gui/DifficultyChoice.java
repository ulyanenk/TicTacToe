package gui;

import logics.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyChoice extends JPanel {

    private JLabel textLabel = new JLabel("Choose Difficulty: ");

    JButton easy = new DifficultyButton("EASY");
    JButton normal = new DifficultyButton("NORMAL");
    JButton hard = new DifficultyButton("HARD");
    JButton twoPlayers = new DifficultyButton("TWO PLAYERS");

    private JPanel buttonHolder = new JPanel(); // buttons stacked here

    ActionListener parent;

    DifficultyChoice(ActionListener parent) {

        easy.addActionListener(parent);
        normal.addActionListener(parent);
        hard.addActionListener(parent);
        twoPlayers.addActionListener(parent);
        twoPlayers.setEnabled(false); // isn't finished yet

        buttonHolder.setBackground(Color.WHITE);
        buttonHolder.setPreferredSize(new Dimension(100, 500));

        buttonHolder.setLayout(new GridLayout(4, 1, 1, 42)); // last parameter sets the gaps between the buttons
        buttonHolder.add(easy);
        buttonHolder.add(normal);
        buttonHolder.add(hard);
        buttonHolder.add(twoPlayers);

        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setFont(new Font("buttonFont", Font.ROMAN_BASELINE, 40));

        setBounds(0, 0, 430, 650);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.add(textLabel);
        this.add(buttonHolder, BorderLayout.SOUTH);
        //this.setVisible(false);


    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == this.easy) {
//            difficulty = 1;
//
//        } else if (e.getSource() == normal) {
//            difficulty = 2;
//
//        } else if (e.getSource() == hard) {
//            difficulty = 3;
//
//        } else if (e.getSource() == twoPlayers) {
//            difficulty = 4;
//        }
//
//        this.
//
//                Board.clearBoard(); // gives start value for all cells (empty)
//        parent.switchToGame(); //after the difficulty is chosen, the panel is no longer visible
//    }
}
