package com.company;

public class Scenarios {



    static void scenarioChoice(int x, int y) {

        switch (x) {

            case 0:

                switch (y){

                    case 1:
                        //blizhniaja storona
                        break;

                    case 2:
                        // blizhnij ugol
                        break;
                }
                break;

            case 1:

                switch (y){

                    case 0:
                        //blizhniaja storona
                        break;
                    case 1:
                        // centre
                        break;
                    case 2:
                        // dalniaja storona

                        break;
                }
                break;

            case 2:

                switch (y){

                    case 0:
                        //blizhnij ugol

                        break;

                    case 1:
                        // dalniaja storona

                        break;

                    case 2:
                        //dalnij ugol

                        break;
                }


        }

        /*
        if(x == 2 && y == 0) {

        }

      */
    }

    private static boolean nearFlankT1(int x, int y){

        if(x == 1 && y == 0) {

            PrintBoard.board[0][2] = CellValue.O;
            return true;

        } else if(x == 0 && y == 1) {

            PrintBoard.board[2][0] = CellValue.O;
            return true;

        } else {

            PrintBoard.board[1][1] = CellValue.O;
            return false;
        }


    }

}
 /* case PrintBoard.board[2][0]:
        case PrintBoard.board[0][2]:

        // blizhnij ugol
        break;

        case PrintBoard.board[2][2]:

        // dalnij ugol
        break;

        case PrintBoard.board[1][1]:

        // centre
        break;

        case PrintBoard.board[0][1]:
        case PrintBoard.board[1][0]:

        // blizhniaja storona
        break;

        case PrintBoard.board[2][1]:
        case PrintBoard.board[1][2]:

// dalniaja storona

  */