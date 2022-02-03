package console;

//import Main;
import logics.CellValue;
import logics.Logic;
import logics.Board;

import java.util.Scanner;

public class Input {


    // are in the class because the method that uses them isn't able to return a String and an int at the same time
    private int digitConvert;

    // gets start value in order to program don't crush in case user puts something wrong
    private String letterConvert = "d";

    // is in the class in order to be able to print out the user's input in case of error caused by user's wrong input
    private String eingabe;

    private Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    private void chronologyAssignment(Scanner valueScanner) {

        // user types the cell number
       // eingabe = valueScanner.nextLine();

        // checks whether the user's input is empty or longer than two values, if it is, user has to type again
        while (true) {
            // user types the cell number
           eingabe = valueScanner.nextLine();

            if ((eingabe != null) && (eingabe.length() == 2)) {

                break;
            }
            System.out.println("Type a cell number");
        }


        // splits the input into readable for machine variables
        String[] splitter = eingabe.split("", -1);

        // for me to check the function [commented]
        // System.out.println(splitter[0]);
        // System.out.println(splitter[1]);


        // the cycle finds out which part of the value is a digit and which is a letter
        for (int i = 0; i < 2; i++) {

                try {

                    digitConvert = Integer.parseInt(splitter[i]);

                    switch (i) {

                        case 0:
                            letterConvert = splitter[1];

                            // for me to test [commented]
                            // System.out.println("case 0 works");
                            break;

                        case 1:
                            letterConvert = splitter[0];

                            // for me to test [commented]
                            // System.out.println("case 1 works");
                            break;

                    }


                }   catch (NumberFormatException ex3) {

                    // for me to test [commented]
                    // System.out.println("second");
                } catch (ArrayIndexOutOfBoundsException ex4) {

                    //for me to test [commented]; actually this should remain
                    System.out.println("Type a value!");

                }

        }
    }


    // reads the x value (letter)
    private int letterValueCheck(String splited) {

        switch (splited) {

            case "a":
            case "A":
                return 0;

            case "b":
            case "B":
                return 1;

            case "c":
            case "C":
                return 2;

                // returns in case the user types something wrong, in order to get "Array oversize" exception in yValueCheck method
            default:
                return 3;
        }
    }


    // reads the y value (digit) and checks the whether the x value is suitable
    public String digitValueCheck(CellValue whoPlays) {

        while (true) {

            // finds out where is the x and y value
            chronologyAssignment(scanner);

            // checks the value
            try {

                    // x gets value after the first input, letter
                    int xValue = letterValueCheck(letterConvert);

                    // y gets value after the second input, digit
                    int yValue = digitConvert - 1;

                    //checks whether the cell is already occupied or not
                    if (Board.realBoard[xValue][yValue].equals(CellValue.N)) {

                        // inserts the user's values in the board array
                       Board.realBoard[xValue][yValue] = whoPlays;
                        return ("[" + xValue + " " + yValue + "]");

                    } else {
                        System.out.println("This cell is already occupied");
                    }

                // catches the wrong user's input
            }  catch (ArrayIndexOutOfBoundsException ex2) {

                System.out.println(eingabe + " is not a valid cell value");
            }

            // PrintBoard.board[xValue][yValue] = CellValue.X;

        }
    }

    // asks who shall turn first
    public int firstTurnChoice(Scanner firstTurnChoiceInputScan) {

        Logic logic = new Logic();

        System.out.println("Would you like to turn first? [y/n]");

        while (true) {

            String firstTurnChoiceInput = firstTurnChoiceInputScan.nextLine();

            switch (firstTurnChoiceInput) {

                case "n":
                case "N":
                case "No":
                case "no":

                    logic.firstTurnCornerRandom();

                    System.out.println("The AI turns first:");
                    System.out.println(" ");
                    return 4;

                case "y":
                case "Y":
                case "Yes":
                case "yes":
                    return 5;
            }

            System.out.println("Type y or n:");
        }
    }

    // asks whether the user wants to play again
    public boolean playAgain(Scanner answer) {

        while (true) {

            String answerString = answer.nextLine();

            switch (answerString) {

                case "y":
                case "Y":
                case "Yes":
                case "yes":
                    return false;

                case "n":
                case "N":
                case "No":
                case "no":
                    return true;
            }

            System.out.println("Type y or n:");
        }
    }


}
