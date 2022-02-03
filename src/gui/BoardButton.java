package gui;

import logics.Board;
import logics.CellValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardButton extends JButton /*implements ActionListener*/ {

    private final int x;
    private final int y;

    Font boardFont = new Font("BoardFont", Font.ROMAN_BASELINE, 110);

    public BoardButton(int x, int y) {
        this.x = x;
        this.y = y;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        //this.addActionListener(this);
        this.setFont(boardFont);
    }

    /*@Override
    public void actionPerformed(ActionEvent e) { // after the button pushed

        //this.setEnabled(false);
        //this.setIcon(MyFrame.cross);
        //this.setText("X");
        makeTurn(CellValue.X);
        //Board.printField();
        //GamePanel.update();
    }*/

    // temporary turn, so long it's not connected with the main project
    /*public static void tempTurn() {

        int rand = (int) (Math.random() * 8);

        switch (rand) {

            case 0:
                makeAnyTurn(GamePanel.button00);
                break;
            case 1:
                makeAnyTurn(GamePanel.button01);
                break;
            case 2:
                makeAnyTurn(GamePanel.button02);
                break;
            case 3:
                makeAnyTurn(GamePanel.button10);
                break;
            case 4:
                makeAnyTurn(GamePanel.button11);
                break;
            case 5:
                makeAnyTurn(GamePanel.button12);
                break;
            case 6:
                makeAnyTurn(GamePanel.button20);
                break;
            case 7:
                makeAnyTurn(GamePanel.button21);
                break;
            case 8:
                makeAnyTurn(GamePanel.button22);

        }
    }

    private static void makeAnyTurn(JButton button) {

            button.setEnabled(false);
            //this.setIcon(MyFrame.cross);
            button.setText("O");
            button.setFont(new Font("BoardFont", Font.ROMAN_BASELINE, 110));

    }*/

    public void makeTurn(CellValue player) {
        Board.realBoard[x][y] = player;
    }

    public void synchronize() {

        switch (Board.realBoard[x][y]) {
            case X -> {
                this.setEnabled(false);
                this.setText("X");
                //this.setFont(boardFont);
            }
            case O -> {
                this.setEnabled(false);
                this.setText("O");
                //this.setFont(boardFont);
            }
//            case N -> {
//                this.setEnabled(true);
//                this.setText("");
//            }
        }
    }

    public void disableButton() {
        this.setEnabled(false);
    }
}
