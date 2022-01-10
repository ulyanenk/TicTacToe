package com.company;

import java.util.Scanner;


public class GameMode {



    Scanner scan = new Scanner(System.in);

    // array that counts each victory of both players
    private final int[] score = {0, 0};

    // makes visible for usage in main
    public int[] getScore() {
        return score;
    }

    // a constructor
   /* public GameMode(Scanner scan) {
        this.scan = scan;
    }*/


    private AI ai = new AI();
    private Input input = new Input();
    private Logic logic = new Logic();


    public void basicRules() {

        System.out.println(" ");
        System.out.println("Enter the cell numbers without spaces; just letter and digit at any way");
        System.out.println(" ");
        System.out.println("For example: b3  or  1c  or  A2");
        System.out.println(" ");
    }

    // user chooses difficulty; the return value is used by the game method
    private int chooseDifficulty() {

        System.out.println("Choose difficulty:");
        System.out.println("easy (1)");
        System.out.println("normal (2)");
        System.out.println("hard (3) (isn't written yet)");
        //System.out.println("   or");
        System.out.println("two players (4)");

        while (true) {

            String difficultyChoice = scan.nextLine();

            switch (difficultyChoice) {

                case "easy":
                case "1":
                    return 1;

                case "normal":
                case "2":
                    return 2;

                //isn't written yet
                case "hard":
                case "3":
                    System.out.println("Hard difficulty isn't written yet unfortunately :(");
                    System.out.println("Choose another one:");
                    //return 3;
                    continue;

                case "two":
                case "two players":
                case "4":
                case "pvp":
                case "PVP":
                    return 4;

                default:
                    System.out.println("'" + difficultyChoice + "' isn't valid difficulty level, type correctly");
            }
        }
    }

    // converts the option into variable for usage in game method
    public final int difficulty = chooseDifficulty();


    // the whole game process
    public void againstAI() {

        //one cycle - one game
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
                input.digitValueCheck(CellValue.X);

                // prints out the board
                PrintBoard.printField();

                // checks the whether the user has won this turn or if all cells are occupied, it's draw
                if (logic.victory(CellValue.X, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Congratulations, you won!!!");

                    score[1]++;

                    break;

                } else if (ai.over(PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Draw");
                    break;
                }


                // checks whether the AI already has a scenario to deal the situation
                // Scenarios.scenarioChoice(xValue, yValue);


                // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
                if (!logic.victoryCheck(CellValue.O, PrintBoard.board, CellValue.O)) {

                    // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
                    if (!logic.victoryCheck(CellValue.X, PrintBoard.board, CellValue.O)) {

                        // last: if after the analysis there is no move the AI could do, he uses an algorithm in dependence of the difficulty level
                        switch (difficulty) {

                            case 1:
                                // [easy] AI just moves randomly
                                logic.randomTurn(PrintBoard.board, CellValue.O);
                                break;

                            case 2:
                                // [normal] AI uses his victory value for each cell algorithm
                                ai.selfPlay();
                                break;

                            // isn't written yet
                            //case 3:
                            // [hard]
                        }
                    }
                }


                System.out.println(" ");
                System.out.println("The AI's turn:");

                // prints ot the board
                PrintBoard.printField();

                // checks whether the AI has won this turn or if all cells are occupied, it's draw
                if (logic.victory(CellValue.O, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("You lost!!!");

                    score[0]++;
                    break;

                } else if (ai.over(PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Draw");
                    break;
                }
            }


            // if draw, prints draw
            /*if (ai.over(PrintBoard.board)) {

                System.out.println(" ");
                System.out.println("Draw");
            }*/

            System.out.println(" ");
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

        else {

            System.out.println("Draw");
        }

        System.out.println(" ");
        System.out.println("Game over");
        // System.out.println(" ");
    }

    public void twoPlayers() {

        // these variables are needed to define the first player and count score correctly
        CellValue firstPlayer;
        CellValue secondPlayer;

        int firstPlayerScore;
        int secondPlayerScore;

        //char firstPlayerChar;
        //char secondPlayerChar;

        //one cycle - one game
        while (true) {

            // start value for all cells
            PrintBoard.clearBoard();

            // asks the user, who should turn first; true: X turns first; false: O turns first
            if (logic.firstTurnPlayer(scan)) {

                firstPlayer = CellValue.X;
                secondPlayer = CellValue.O;

                firstPlayerScore = 1;
                secondPlayerScore = 0;

               // firstPlayerChar = 'X';
               // secondPlayerChar = 'O';

            } else {

                firstPlayer = CellValue.O;
                secondPlayer = CellValue.X;

                firstPlayerScore = 0;
                secondPlayerScore = 1;

               // firstPlayerChar = 'O';
               // secondPlayerChar = 'X';
            }


            // prints out the board
            PrintBoard.printField();

            // starts the game cycle
            for (int t = 0; t < 5; t++) {

                System.out.println(" ");
                System.out.println(firstPlayer.getTag() + "'s turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                input.digitValueCheck(firstPlayer);

                // prints out the board
                PrintBoard.printField();

                // checks the whether the user has won this turn
                if (logic.victory(firstPlayer, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println(firstPlayer.getTag() + " player wins");

                    score[firstPlayerScore]++;

                    break;
                }

                // if all cells are occupied, it's draw
                if (ai.over(PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Draw");
                    break;
                }


                System.out.println(" ");
                System.out.println(secondPlayer.getTag() + "'s turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                input.digitValueCheck(secondPlayer);

                // prints out the board
                PrintBoard.printField();

                // checks the whether the user has won this turn
                if (logic.victory(secondPlayer, PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println(secondPlayer.getTag() + " player wins");

                    score[secondPlayerScore]++;

                    break;
                }

                // if all cells are occupied, it's draw
                if (ai.over(PrintBoard.board)) {

                    System.out.println(" ");
                    System.out.println("Draw");
                    break;
                }

            }


            System.out.println(" ");
            System.out.println("Score:");
            System.out.println(" ");
            System.out.println("O | X");
            System.out.println("  |");
            System.out.println(score[0] +  " | " + score[1]);
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
        System.out.println("O | X");
        System.out.println("  |");
        System.out.println(score[0] +  " | " + score[1]);
        System.out.println(" ");

        if (score[0] > score[1]) {

            System.out.println("O player plays better than X player");

        } else if (score[0] < score[1]) {

            System.out.println("X player plays better than O player");
        }

        else {

            System.out.println("Draw, none of you is better than another one");
        }

        System.out.println(" ");
        System.out.println("Game over");
        // System.out.println(" ");


        }


}
