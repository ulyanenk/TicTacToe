package console;

import logics.Board;
import logics.AI;
import logics.CellValue;
import logics.Logic;

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
    private Input input = new Input(scan);
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
        System.out.println("hard (3)");
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

                case "hard":
                case "3":
                    //System.out.println("Hard difficulty isn't written yet unfortunately :(");
                    //System.out.println("Choose another one:");
                    return 3;

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

        String oneGame = ""; // saves one played game

        //one cycle - one game
        while (true) {

            int turnNumber = 0;

            // start value for all cells
            Board.clearBoard();

            // asks the user, who should turn first, and returns the quantity of turns, the games goes on
            int turnsQuantity = input.firstTurnChoice(scan);

            // prints out the board
            Board.printField();

            // starts the game cycle
            while (true)/*for (int t = 0; t < turnsQuantity; t++)*/ {

                System.out.println(" ");
                System.out.println("Your turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                String currentTurn = input.digitValueCheck(CellValue.X); //saves the turn coordinates into String
                char turnType = 'U';

                turnNumber++;                                           // counts which turn is now, in order sort the turns chronologically later
                oneGame += (turnNumber + currentTurn + turnType + " "); // adds all turn info to the game String
                currentTurn = null;                                     // "clears" the currentTurn

                // prints out the board
                Board.printField();

                // checks the whether the user has won this turn or if all cells are occupied, it's draw
                if (logic.victory(CellValue.X, Board.realBoard)) {

                    System.out.println("\nCongratulations, you won!!!");

                    score[1]++;
                    break;

                } else if (ai.over(Board.realBoard)) {

                    System.out.println("\nDraw");
                    break;
                }

                // checks whether the AI already has a scenario to deal the situation
                // Scenarios.scenarioChoice(xValue, yValue);


                // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
                currentTurn = logic.victoryNow(CellValue.O, Board.realBoard, CellValue.O);
                turnType = 'V';

                if (currentTurn == null) {

                    // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
                    currentTurn = logic.victoryNow(CellValue.X, Board.realBoard, CellValue.O);
                    turnType = 'P';

                    if (currentTurn == null) {

                        // last: if after the analysis there is no move the AI could do, he uses an algorithm in dependence of the difficulty level
                        turnType = 'F';

                        switch (difficulty) {

                            case 1:
                                // [easy] AI just moves randomly
                                currentTurn = logic.randomTurn(Board.realBoard, CellValue.O);
                                break;

                            case 2:
                            case 3:
                                // [normal] or [hard] AI uses his victory value for each cell algorithm
                                currentTurn = ai.selfPlay(difficulty);
                                break;
                        }
                    }
                }

                turnNumber++; // counts which turn is now, in order sort the turns chronologically later
                oneGame += (turnNumber + currentTurn + turnType + " "); // adds all turn info to the game String
                currentTurn = null; // "clears" the currentTurn

                System.out.println("\nThe AI's turn:");

                // prints ot the board
                Board.printField();

                // checks whether the AI has won this turn or if all cells are occupied, it's draw
                if (logic.victory(CellValue.O, Board.realBoard)) {

                    System.out.println("\nYou lost!!!");

                    score[0]++;
                    break;

                } else if (ai.over(Board.realBoard)) {

                    System.out.println("\nDraw");
                    break;
                }
            }

            System.out.println("\nScore:\n");
            System.out.println("AI | You");
            System.out.println("   |");
            System.out.println(score[0] +  "  |  " + score[1]);

            System.out.println("\nWould you like to play again? [y/n]");

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

        } else {

            System.out.println("Draw");
        }

        System.out.println("\nGame over");
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
            Board.clearBoard();

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
            Board.printField();

            // starts the game cycle
            for (int t = 0; t < 5; t++) {

                System.out.println(" ");
                System.out.println(firstPlayer.getTag() + "'s turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                input.digitValueCheck(firstPlayer);

                // prints out the board
                Board.printField();

                // checks the whether the user has won this turn
                if (logic.victory(firstPlayer, Board.realBoard)) {

                    System.out.println(" ");
                    System.out.println(firstPlayer.getTag() + " player wins");

                    score[firstPlayerScore]++;

                    break;
                }

                // if all cells are occupied, it's draw
                if (ai.over(Board.realBoard)) {

                    System.out.println("\nDraw");
                    break;
                }


                System.out.println(" ");
                System.out.println(secondPlayer.getTag() + "'s turn: ");

                // user has to type right cell value; the cycle repeats itself until the user types in the right value
                input.digitValueCheck(secondPlayer);

                // prints out the board
                Board.printField();

                // checks the whether the user has won this turn
                if (logic.victory(secondPlayer, Board.realBoard)) {

                    System.out.println(" ");
                    System.out.println(secondPlayer.getTag() + " player wins");

                    score[secondPlayerScore]++;

                    break;
                }

                // if all cells are occupied, it's draw
                if (ai.over(Board.realBoard)) {

                    System.out.println("\nDraw");
                    break;
                }

            }

            System.out.println("\nScore:\n");
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
        System.out.println("\nGame over");
        }


}
