package useless;

import logics.Board;
import logics.CellValue;

import java.util.HashMap;
import java.util.Map;

public class Scenarios {

    Map<Integer, Integer> scenarioFirst = new HashMap<>();
    Map<Integer, CellSpecification> scenarioSecond = new HashMap<>();

    public void mapsValue() {

        scenarioSecond.put(00, CellSpecification.CORNER);
        scenarioSecond.put(01, CellSpecification.FLANK);
        scenarioSecond.put(02, CellSpecification.CORNER);
        scenarioSecond.put(10, CellSpecification.FLANK);
        scenarioSecond.put(11, CellSpecification.CENTER);
        scenarioSecond.put(12, CellSpecification.FLANK);
        scenarioSecond.put(20, CellSpecification.CORNER);
        scenarioSecond.put(21, CellSpecification.FLANK);
        scenarioSecond.put(22, CellSpecification.CORNER);

    }


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

    private boolean nearFlankT1(int x, int y){

        if(x == 1 && y == 0) {

            Board.realBoard[0][2] = CellValue.O;
            return true;

        } else if(x == 0 && y == 1) {

            Board.realBoard[2][0] = CellValue.O;
            return true;

        } else {

            Board.realBoard[1][1] = CellValue.O;
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