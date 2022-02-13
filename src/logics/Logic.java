package logics;

import java.util.Scanner;

public class Logic {


    // static int[] fieldValue = new int[10];

    /*
   fieldValue[1] = 3;
   fieldValue[2] = 2;
   fieldValue[3] = 3;
   fieldValue[4] = 2;
   fieldValue[5] = 4;
   fieldValue[6] = 2;
   fieldValue[7] = 3;
   fieldValue[8] = 2;
   fieldValue[9] = 3;
                              */

    // the algorithm checks whether any player has won this turn
    public boolean victory(CellValue player, CellValue[][] whichBoard) {

        for (int i = 0; i < 3; i++) {

            if(whichBoard[0][i] == player && whichBoard[1][i] == player && whichBoard[2][i] == player) {

               // victoryText(player);

                return true;

            } else if (whichBoard[i][0] == player && whichBoard[i][1] == player && whichBoard[i][2] == player) {

               // victoryText(player);

                return true;
            }
        }

        if(whichBoard[0][0] == player && whichBoard[1][1] == player && whichBoard[2][2] == player) {

           // victoryText(player);

           return true;

        } else if(whichBoard[2][0] == player && whichBoard[1][1] == player && whichBoard[0][2] == player) {

           // victoryText(player);

            return true;
        }

        return false;
    }

 /*   static boolean over() {

        for(int i= 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                if(PrintBoard.board[i][j].equals(CellValue.O) || PrintBoard.board[i][j].equals(CellValue.X)) {

                    System.out.println(" ");
                    System.out.println("Game over");
                    System.out.println(" ");

                    System.exit(0);
                    return true;
                }
                // System.out.println(board[i][j].getTag());
            }
        }
        return false;
    }
  */

    // the method is used to check whether one of the players has finished the game this turn, if yes says it and closes the program
     public boolean victoryText(CellValue winner) {

        if (victory(winner, Board.realBoard)) {

            System.out.println(" ");

            switch (winner) {

                case X:
                    System.out.println("Congratulations, you won!!!");
                    return true;

                case O:
                    System.out.println("You lost!!!");
                    return true;

            }
            System.out.println(" ");

            System.exit(0);
        }
        return false;
    }

    /*
    static void sector() {


        CellValue[] x0 = new CellValue[2];
        CellValue[] x1 = new CellValue[2];
        CellValue[] x2 = new CellValue[2];
        CellValue[] y0 = new CellValue[2];
        CellValue[] y1 = new CellValue[2];
        CellValue[] y2 = new CellValue[2];
        CellValue[] dNeg = new CellValue[2];
        CellValue[] dPos = new CellValue[2];

        x0[0] = CellValue.X;
        x0[1] = CellValue.X;
        x0[2] = CellValue.N;


        int res = 0;
        int last = 0;
        int checkX = 0;


        for(int i = 0; i > 3; i++) {

            if (x0[i].equals(CellValue.X)) {

               checkX++;

            } else if(x0[i].equals(CellValue.O)) {

                checkX--;
            }
        }

        if(checkX == 2) {

            for(int i = 0; i > 3; i++) {

                if (x0[i].equals(CellValue.N)) {

                    x0[i] = CellValue.O;
                }
            }
        }
    }
     */


    // the method checks the victory possibility this turn in dependence of the faction
    // if forWhom and player have the same value, the AI checks the possible victory for himself
    // if forWhom and player have the different values, the AI checks the possible victory for his opponent
    public String victoryNow(CellValue forWhom, CellValue[][] whichBoard, CellValue player) {

        // creates new virtual board
        CellValue[][] virtualBoard = new CellValue[Board.realBoard.length][Board.realBoard.length];

        int xValueAI;
        int yValueAI;

        // the cycle makes AI make all possible turns to find out the possibility to win this turn
        for (xValueAI = 0; xValueAI <= 2; xValueAI++) {
            for (yValueAI = 2; yValueAI >= 0; yValueAI--) {

                // copies the real board for the AI to "think" about the next turn
                for (int x = 0; x < 3; x++) {
                    for (int y = 2; y >= 0; y--) {

                        virtualBoard[x][y] = whichBoard[x][y];
                    }
                }


            // the cycle checks whether the cell is already occupied
            while (true) {

                    // for me to see how many times it takes to get the right turn [commented]
                   //  System.out.println(xValueAI + " " + yValueAI);

                    //checks whether the cell is already occupied or not
                    if (whichBoard[xValueAI][yValueAI].equals(CellValue.N)) {

                        virtualBoard[xValueAI][yValueAI] = forWhom;

                        // for me to test [commented]
                        // System.out.println("The cell is free");

                        break;
                    }
                    break;
            }

            // checks the possibility of victory this turn
            if (victory(forWhom, virtualBoard)) {

                whichBoard[xValueAI][yValueAI] = player;

                // for me to test this function [commented]
                // System.out.println("The victoryChecks has worked");
                return ("[" + xValueAI + ":" + yValueAI + "]");
            }
            //for me to test the whole function [commented]
           // else {
            //    System.out.println("The victoryChecks hasn't shot"); }

            }
        }
       return null;
    }

    public String randomTurn(CellValue[][] whichBoard, CellValue player) {

        while (true) {

            int xValueAI = (int) (Math.random() * 3);
            int yValueAI = (int) (Math.random() * 3);

            // for me to see how many times it takes to get the right turn [commented]
            // System.out.println(xValueAI + " " + yValueAI);

            //checks whether the cell is already occupied or not
            if (whichBoard[xValueAI][yValueAI].equals(CellValue.N)) {

                whichBoard[xValueAI][yValueAI] = player;
                return ("[" + xValueAI + ":" + yValueAI + "]");
            }
        }
    }


   /* private void constantValueBoard() {

        int[][] valueBoard = new int[3][3];

        valueBoard[0][0] = 3;
        valueBoard[1][0] = 2;
        valueBoard[2][0] = 3;
        valueBoard[0][1] = 2;
        valueBoard[1][1] = 3;
        valueBoard[2][1] = 2;
        valueBoard[0][2] = 3;
        valueBoard[1][2] = 2;
        valueBoard[2][2] = 3;


        int max = valueBoard[0][0];


        int rand1 = (int) (Math.random() * 4);
        int rand2 = (int) (Math.random() * 3);

        switch (rand1) {

            case 0:


            case 1:
            case 2:
            case 3:


        }

    }*/

    public boolean firstTurnPlayer(Scanner scan) {

        System.out.println("Who should start first? [x/o]");

        while (true) {

            String response = scan.nextLine();

            switch (response) {

                case "X":
                case "x":
                    return true;

                case "O":
                case "o":
                case "0":
                    return false;

                default:
                    System.out.println("Enter x or o");
            }
        }
    }

    public void firstTurnCornerRandom() {

        // AI makes first in one of the corners
        int thisTurn = (int) (Math.random() * 4) + 1;

        switch (thisTurn) {

            case 1:
                Board.realBoard[0][0] = CellValue.O;
                break;

            case 2:
                Board.realBoard[2][0] = CellValue.O;
                break;

            case 3:
                Board.realBoard[0][2] = CellValue.O;
                break;

            case 4:
                Board.realBoard[2][2] = CellValue.O;
        }
    }

    public String moveInLastCellRemaining(CellValue player) {
        int freeCellAmount = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 2; y >= 0; y--) {
                if (Board.realBoard[x][y] == CellValue.N) {
                    freeCellAmount++;
                }
            }
        }

        if (freeCellAmount == 1) {
            for (int x = 0; x < 3; x++) {
                for (int y = 2; y >= 0; y--) {
                    if (Board.realBoard[x][y] == CellValue.N) {
                        Board.realBoard[x][y] = player;

                        return ("[" + x + ":" + y + "]");
                    }
                }
            }
        }
        return null;
    }

}
