package com.company;


import java.util.Scanner;

public class Main {


    static Scanner scan = new Scanner(System.in);

    // score counter; slot 0 computer, slot 1 user
    static int[] score = {0, 0};


    public static void main(String[] args) {

        // creates an object of all class that are needed
        AI ai = new AI();
        Input input = new Input();
        Logic logic = new Logic();

        System.out.println(" ");
        System.out.println("Welcome to the Tic Tac Toe");
        System.out.println(" ");

       // System.out.println("Would you like to turn first? [y/n]");

        // the first turn for the AI
       // PrintBoard.board[0][0] = CellValue.O;

        System.out.println("Enter the cell numbers without spaces; just letter and digit at any way");
        System.out.println(" ");
        System.out.println("For example: b3  or  1c  or  A2");
        System.out.println(" ");


        while (true) {

            // start value for all cells
            PrintBoard.clearBoard();

            // asks the user, who should turn first, and returns the quantity of turns, the games goes on
            int turnsQuantity = input.firstTurnChoice(scan);


            // prints out the board
            PrintBoard.printField();

            // starts the game cycle
            for (int t = 0; t < turnsQuantity; t++) {

                System.out.println(" ");
                System.out.println("Your turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                input.digitValueCheck();

                // prints out the board
                PrintBoard.printField();

                // checks the whether the user has won this turn
                if (logic.victory(CellValue.X, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Congratulations, you won!!!");

                    score[1]++;

                    break;
                }

                // if all cells are occupied, it's draw (happens if players makes turn first)
                /*
                if (ai.over(PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Draw");
                    System.out.println(" ");
                    System.out.println("Game over");
                    System.out.println(" ");

                    // System.exit(0);
                }
                 */

                // checks whether the AI already has a scenario to deal the situation
                // Scenarios.scenarioChoice(xValue, yValue);


                // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
                if (!logic.victoryCheck(CellValue.O, PrintBoard.board, CellValue.O)) {

                    // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
                    if (!logic.victoryCheck(CellValue.X, PrintBoard.board, CellValue.O)) {

                        // last: if after the analysis there is no move the AI could do, he just moves randomly
                        // logic.randomTurn(PrintBoard.board, CellValue.O);

                        // last: if after the analysis there is no move the AI could do, he uses his victory value for each cell algorithm
                        ai.selfPlay();
                    }
                }


                System.out.println(" ");
                System.out.println("The AI's turn:");

                // prints ot the board
                PrintBoard.printField();

                // checks whether the AI has won this turn
                if (logic.victory(CellValue.O, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("You lost!!!");

                    score[0]++;
                    break;
                }
            }

            System.out.println(" ");

            // if draw, prints draw
            if (ai.over(PrintBoard.board)) {

                System.out.println("Draw");
                System.out.println(" ");
            }

                System.out.println("Score:");
                System.out.println(" ");
                System.out.println("AI | You");
                System.out.println("   |");
                System.out.println(score[0] +  "  |  " + score[1]);
              System.out.println(" ");

            System.out.println("Would you like to play again? [y/n]");

            // if user doesn't want to play again, "if" breaks the game loop
            if (input.playAgain(scan)) {
             break;
            }

        }

        // after the games are finally over

        System.out.println("Final score:");
        System.out.println(" ");
        System.out.println("AI | You");
        System.out.println("   |");
        System.out.println(score[0] +  "  |  " + score[1]);
        System.out.println(" ");

        if (score[0] > score[1]) {

            System.out.println("You have lost the campaign!");

        } else if (score[0] < score[1]) {

            System.out.println("Congratulations, you have won the whole campaign!");
        }

        System.out.println(" ");
        System.out.println("Game over");
       // System.out.println(" ");
    }
}
