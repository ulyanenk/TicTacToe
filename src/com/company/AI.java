package com.company;

public class AI {

    // creates new virtual board
    private CellValue[][] virtualBoard = new CellValue[PrintBoard.board.length][PrintBoard.board.length];

    // array that counts victories
    private int[][] victoryCounter = new int[PrintBoard.board.length][PrintBoard.board.length];

    public void selfPlay() {

        // first loop, repeats until every game for every turn is completed
        // second and third loops are listing each cell of the board
        for (int gamesPerCell = 0; gamesPerCell < 50; gamesPerCell++) {
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
                    selfTurn(i, j);

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
    private void selfTurn(int x, int y) {

        Logic logic = new Logic();

        while (true) {

            // one turn start

            // the "O" computer turn (the virtual computer AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
            //arguments: (forWhom, whichBoard, whoPlays)
            if (!logic.victoryCheck(CellValue.O, virtualBoard, CellValue.O)) {

                // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
                //arguments: (forWhom, whichBoard, whoPlays)
                if (!logic.victoryCheck(CellValue.X, virtualBoard, CellValue.O)) {

                    // last: if after the analysis there is no move the AI could do, he just moves randomly
                    logic.randomTurn(virtualBoard, CellValue.O);
                }
            }

            // for me to test the virtual games [commented]
            // PrintBoard.printVirtualBoard(virtualBoard);

            if (logic.victory(CellValue.O, virtualBoard)) {

                victoryCounter[x][y]++;

                // for me to test the virtual games [commented]
                // System.out.println("victory");

                break;

            } else if (over(virtualBoard)) {

                break;
            }

            // the "X" computer turn (the virtual player AI)
            // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
            //arguments: (forWhom, whichBoard, whoPlays)
            if (!logic.victoryCheck(CellValue.X, virtualBoard, CellValue.X)) {

                // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
                //arguments: (forWhom, whichBoard, whoPlays)
                if (!logic.victoryCheck(CellValue.O, virtualBoard, CellValue.X)) {

                    // last: if after the analysis there is no move the AI could do, he just moves randomly
                    logic.randomTurn(virtualBoard, CellValue.X);
                }
            }

            // for me to test the virtual games [commented]
            // PrintBoard.printVirtualBoard(virtualBoard);

            if (logic.victory(CellValue.X, virtualBoard)) {

                victoryCounter[x][y]--;

                // for me to test the virtual games [commented]
                // System.out.println("defeat");

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
                // System.out.println(i + " " + j + ": the current victory counter: " + victoryCounter[i][j]);

                virtualBoard[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }
    }

    private void realTurn () {

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

                    PrintBoard.board[xBestOption][yBestOption] = CellValue.O;

                    // for me to test [commented]
                    // System.out.println("it works");

                    return;
                }
            }
        }
    }
}
