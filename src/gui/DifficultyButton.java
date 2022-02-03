package gui;

import javax.swing.*;
import java.awt.*;

public class DifficultyButton extends JButton {

    public DifficultyButton(String text) {
        super(text); // inherited, insert text parameter

        Font buttonFont = new Font("buttonFont", Font.ROMAN_BASELINE, 35);

        setFont(buttonFont);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
}
