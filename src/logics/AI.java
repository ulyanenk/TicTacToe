package logics;

import java.util.HashSet;
import java.util.Set;

public class AI {

    // creates new virtual board
    private CellValue[][] virtualBoard = new CellValue[Board.realBoard.length][Board.realBoard.length];

    // array that counts victories
    private int[][] victoryCounter = new int[Board.realBoard.length][Board.realBoard.length];
    //private int[][] FreeChoiceCounter = new int[PrintBoard.board.length][PrintBoard.board.length]; // counts how many times "free choice" occurs

    // a set that saves all played games
    private Set<String> gamesRepository = new HashSet<>();

    public Set<String> getGamesRepository() {
        return gamesRepository;
    }

    public String selfPlay(int difficulty) {

        AnalyticAI analyticAI = new AnalyticAI();

        // first loop, repeats until every game for every turn is completed
        // second and third loops are listing each cell of the board
        for (int gamesPerCell = 0; gamesPerCell < 150; gamesPerCell++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    int importedTurnNumber = 1;

                    // copies the real board for the AI to "think" about the next turn
                    for (int x = 0; x < 3; x++) {
                        for (int y = 2; y >= 0; y--) {

                            virtualBoard[x][y] = Board.realBoard[x][y];

                            // counts how many turns has been already made
                            if (Board.realBoard[x][y] == CellValue.X || Board.realBoard[x][y] == CellValue.O) {
                                importedTurnNumber++;
                            }
                        }
                    }

                    //checks whether the cell is already occupied or not, if not the loop starts again
                    if (!Board.realBoard[i][j].equals(CellValue.N)) {

                        // the occupied cells are getting too high negative value in order to get never used
                        victoryCounter[i][j] = -100000;

                        // System.out.println("got on occupied"); // for me to check [commented]
                        continue;
                    }

                    // starts the turn loop in dependence of difficulty
                    if (difficulty == 2) {

                        //[normal] without set
                        selfTurnNormal(i, j);

                    } else if (difficulty == 3) {

                            //[hard] saves all played games in the set
                            gamesRepository.add(selfTurnHard(i, j, importedTurnNumber));
                    }
                }
            }
        }


        String madeTurn = "";
        if (difficulty != 3) {
            madeTurn = realTurn(); // makes a turn in the real board in the cell with the highest victory rating
        }
        else {
            //madeTurn =
            analyticAI.realTurn(gamesRepository); // makes turn according to analysis
        }

        // after all virtual games for one real turn are over, the repository gets cleared
        gamesRepository.clear();

        // after all virtual games for one real turn are over the victory counter gets "cleared" (every slot gets 0 value)
        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                victoryCounter[i][j] = 0;

                // for me to check [commented]
                //System.out.println("victoryCounter " + i + " " + j + " = " + victoryCounter[i][j]);
            }
        }
        return madeTurn;
    }

    // checks whether all cells are occupied
     public boolean over(CellValue[][] whichBoard) {

        for(int i= 0; i < 3; i++) {
            for(int j = 2; j >= 0; j--) {

                // if any board cell is free, this cell gets value 1 in a counter
                if(whichBoard[i][j].equals(CellValue.N)) {

                    // for me to test [commented]
                   // System.out.println("the board still contains free slots");

                    return false;
                }
                // for me to test [commented]
                // System.out.println(board[i][j].getTag());
            }
        }
         // for me to test [commented]
         // System.out.println("no more free slots left");

        return true;
    }

    // the method makes turns until one virtual game is finished
    private String selfTurnHard(int x, int y, int realBoardTurnAmount) {

        //GameCounter gameCounter = new GameCounter();

        Logic logic = new Logic();

        //String xTurnCell;
        //String yTurnCell;

        virtualBoard[x][y] = CellValue.O; //makes the first turn

        int turnNumber = realBoardTurnAmount; // counts turns

        String oneGame = (turnNumber + "[" + x + ":" + y + "]T "); // saves a game into a String
        //Shortages: J: Free choice of the "real" AI; H: Prevent enemy's victory (rAI); V: Victory; L: Lost; P: Prevent turn (enemy AI); F: Free choice (eAI); T: Cell try (rAI);
        // O: One cell remaining (rAI); X: One cell remaining (eAI);

        // for me to test the virtual games [commented]
        //PrintBoard.printVirtualBoard(virtualBoard);

        // one virtual game; for cycle is used in order not to get in the endless loop
        for (int i = 0; i < 200; i++) {

            // one turn start

            // the "X" computer turn (the virtual player AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there

            String currentTurn = logic.victoryNow(CellValue.X, virtualBoard, CellValue.X); // saves the cell coordinate the tur was made in into String
            char turnType = 'L';

            if (currentTurn == null) {

                // second: the AI checks, whether the enemy is able to finish game this turn, if yes, the AI turns there
                currentTurn = logic.victoryNow(CellValue.O, virtualBoard, CellValue.X);
                turnType = 'P';

                if (currentTurn == null) {

                    //third: AI checks, whether it is only one cell remaining, if yes, it turns there
                    currentTurn = logic.moveInLastCellRemaining(CellValue.X);
                    turnType = 'X';

                    if (currentTurn == null) {

                        // last: if after the analysis there is no move the AI could do, he just moves randomly
                        currentTurn = logic.randomTurn(virtualBoard, CellValue.X);
                        turnType = 'F';
                        //xTurnCell = gameCounter.randomTurn(virtualBoard, CellValue.X);
                    }
                }
            }

            turnNumber++; // counts which turn is now, in order sort the turns chronologically later

            oneGame += (turnNumber + currentTurn + turnType + " "); // adds all turn info to the game String

            currentTurn = null; // "clears" the currentTurn

            // for me to test the virtual games [commented]
            //PrintBoard.printVirtualBoard(virtualBoard);

            // checks whether the AI has won the game or whether it's draw
            if (logic.victory(CellValue.X, virtualBoard)) {

                //checks whether there the set already contains this virtual game, if yes: the loops restarts
                if (!gamesRepository.contains(arrayToString(virtualBoard))) {

                    victoryCounter[x][y]--;

                    //lastGame = arrayToString(virtualBoard);
                    break;

                } else {

                    // for me to test the set [commented]
                    //System.out.println("twice");

                    Board.clearBoard(virtualBoard);
                    turnNumber = 0;
                    continue;
                }

            } else if (over(virtualBoard)) {

                if (!gamesRepository.contains(arrayToString(virtualBoard))) {

                    //lastGame = arrayToString(virtualBoard);
                    break;

                } else {

                    // for me to test the set [commented]
                    //System.out.println("twice");

                    Board.clearBoard(virtualBoard);
                    turnNumber = 0;
                    continue;
                }
            }


            // the "O" computer turn (the virtual computer AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there

            currentTurn = logic.victoryNow(CellValue.O, virtualBoard, CellValue.O);
            turnType = 'V';

            if (currentTurn == null) {

                // second: the AI checks, whether the enemy is able to finish game this turn, if yes, the AI turns there

                currentTurn = logic.victoryNow(CellValue.X, virtualBoard, CellValue.O);
                turnType = 'H';

                if (currentTurn == null) {

                    //third: AI checks, whether it is only one cell remaining, if yes, it turns there
                    currentTurn = logic.moveInLastCellRemaining(CellValue.O);
                    turnType = 'O';

                    if (currentTurn == null) {

                        // last: if after the analysis there is no move the AI could do, he just moves randomly
                        currentTurn = logic.randomTurn(virtualBoard, CellValue.O);
                        turnType = 'J';
                        //yTurnCell = gameCounter.randomTurn(virtualBoard, CellValue.O);
                    }
                }
            }
            turnNumber++;

            oneGame += (turnNumber + currentTurn + turnType + " "); // adds all turn info to the game String

            currentTurn = null; // "clears" the currentTurn

            //PrintBoard.printVirtualBoard(virtualBoard); // for me to test the virtual games [commented]

            // checks user's victory; same principe as by the AI check
            if (logic.victory(CellValue.O, virtualBoard)) {

                if (!gamesRepository.contains(arrayToString(virtualBoard))) {

                    victoryCounter[x][y]++;
                    //lastGame = arrayToString(virtualBoard);
                    break;

                } else {
                    //System.out.println("twice"); // for me to test the set [commented]

                    Board.clearBoard(virtualBoard);
                    turnNumber = 0;
                }

            } else if (over(virtualBoard)) {
                if (!gamesRepository.contains(arrayToString(virtualBoard))) {

                    //lastGame = arrayToString(virtualBoard);
                    break;

                } else {

                    //System.out.println("twice"); // for me to test the set [commented]

                    Board.clearBoard(virtualBoard);
                    turnNumber = 0;
                }
            }

            // one turn over
        }

        //String lastGame = arrayToString(virtualBoard); // saves current game

        //for (String playedGame : gamesRepository){ System.out.println(playedGame); } // for me to test the set [commented]

        // after one virtual game is over the virtual board gets "cleared" (every cell gets "N"(empty) value)
        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                //System.out.println(i + " " + j + ": the current victory counter: " + victoryCounter[i][j]); // for me to test [commented]

                virtualBoard[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }
        return /*lastGame*/ oneGame;
    }

    private String realTurn () {

        //finds the cell with the highest victory value
        int bestCell = victoryCounter[0][2];

        for (int xVictoryCounter = 0; xVictoryCounter < 3; xVictoryCounter++) {
            for (int yVictoryCounter = 2; yVictoryCounter >= 0; yVictoryCounter--) {

                if (victoryCounter[xVictoryCounter][yVictoryCounter] > bestCell) {
                    bestCell = victoryCounter[xVictoryCounter][yVictoryCounter];

                    // for me to test [commented]
                    // System.out.println("bestCell got value");
                }
            }
        }

        // for me to test [commented]
        // System.out.println("The highest victory value for one cell is: " + bestCell);

        // makes turn in the real board in the cell with the highest victory rating
        for (int xBestOption = 0; xBestOption < 3; xBestOption++) {
            for (int yBestOption = 2; yBestOption >= 0; yBestOption--) {

                if (victoryCounter[xBestOption][yBestOption] == bestCell) {
                    Board.realBoard[xBestOption][yBestOption] = CellValue.O;

                    // for me to test [commented]
                    // System.out.println("it works");

                    return ("[" + xBestOption + " " + yBestOption + "]");
                }
            }
        }
        return null; // actually should never shoot, but an error occurs for not having default return statement
    }

   /* public void selfPlayHard() {

        // first loop, repeats until every game for every turn is completed
        // second and third loops are listing each cell of the board
        for (int gamesPerCell = 0; gamesPerCell < 2000; gamesPerCell++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    // copies the real board for the AI to "think" about the next turn
                    for (int x = 0; x < 3; x++) {
                        for (int y = 2; y >= 0; y--) {

                            virtualBoard[x][y] = PrintBoard.board[x][y];
                        }
                    }

                    //checks whether the cell is already occupied or not, if not the loop starts again
                    if (!PrintBoard.board[i][j].equals(CellValue.N)) {

                        // the occupied cells are getting too high negative value in order to get never used
                        victoryCounter[i][j] = -100000;

                        // for me to check [commented]
                        // System.out.println("got on occupied");

                        continue;
                    }

                    // starts the turn loop
                    selfTurnHard(i, j);

                }
            }
        }

        // makes a turn in the real board in the cell with the highest victory rating
        realTurn();

        // after all virtual games for one real turn are over the victory counter gets "cleared" (every slot gets 0 value)
        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                victoryCounter[i][j] = 0;

                // for me to check [commented]
                // System.out.println("victoryCounter " + i + " " + j + " = " + victoryCounter[i][j]);
            }
        }
    }*/

    private void selfTurnNormal(int x, int y) {

        Logic logic = new Logic();

        //makes the first turn
        virtualBoard[x][y] = CellValue.O;

        // for me to test the virtual games [commented]
        //PrintBoard.printVirtualBoard(virtualBoard);

        // one virtual game
        while (true) {

            // one turn start

            // the "X" computer turn (the virtual player AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
            //arguments: (forWhom, whichBoard, whoPlays)
            if (logic.victoryNow(CellValue.X, virtualBoard, CellValue.X) == null) {

                // second: the AI checks, whether the enemy is able to finish game this turn, if yes, the AI turns there
                //arguments: (forWhom, whichBoard, whoPlays)
                if (logic.victoryNow(CellValue.O, virtualBoard, CellValue.X) == null) {

                    // last: if after the analysis there is no move the AI could do, he just moves randomly
                    logic.randomTurn(virtualBoard, CellValue.X);
                }
            }

            // for me to test the virtual games [commented]
            //PrintBoard.printVirtualBoard(virtualBoard);

            if (logic.victory(CellValue.X, virtualBoard)) {

                    victoryCounter[x][y]--;
                    break;

            } else if (over(virtualBoard)) {
                break;
            }


            // the "O" computer turn (the virtual computer AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
            //arguments: (forWhom, whichBoard, whoPlays)
            if (logic.victoryNow(CellValue.O, virtualBoard, CellValue.O) == null) {

                // second: the AI checks, whether the enemy is able to finish game this turn, if yes, the AI turns there
                //arguments: (forWhom, whichBoard, whoPlays)
                if (logic.victoryNow(CellValue.X, virtualBoard, CellValue.O) == null) {

                    // last: if after the analysis there is no move the AI could do, he just moves randomly
                    logic.randomTurn(virtualBoard, CellValue.O);
                }
            }

            // for me to test the virtual games [commented]
            //PrintBoard.printVirtualBoard(virtualBoard);

            if (logic.victory(CellValue.O, virtualBoard)) {

                victoryCounter[x][y]++;
                break;

            } else if (over(virtualBoard)) {
                break;
            }

            // one turn over
        }


        // after one virtual game is over the virtual board gets "cleared" (every cell gets "N"(empty) value)
        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                // for me to test [commented]
                //System.out.println(i + " " + j + ": the current victory counter: " + victoryCounter[i][j]);

                virtualBoard[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }
    }

    // converts a CellValue array into String; it's used to place the array into collection
    private String arrayToString(CellValue[][] board) {

        String value2 = "";
        String value = "";

        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                //System.out.print(i + " " + j + ": " + virtualBoard[i][j] + " | ");

                value = ("[" + i + " " + j + "]: " + board[i][j] + " | ");

                value = value + value2;

                value2 = value;
            }
        }
        return value;
    }


}