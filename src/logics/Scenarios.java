package logics;

import java.util.HashMap;
import java.util.Map;

public class Scenarios {

    //Map<Integer, Integer> scenarioFirst = new HashMap<>();
    Map<String, CellSpecification> coordinatesToPosition = new HashMap<>();

    public void coordinatesMapInitialize() {

        coordinatesToPosition.put("0:0", CellSpecification.CORNER);
        coordinatesToPosition.put("0:1", CellSpecification.FLANK);
        coordinatesToPosition.put("0:2", CellSpecification.CORNER);
        coordinatesToPosition.put("1:0", CellSpecification.FLANK);
        coordinatesToPosition.put("1:1", CellSpecification.CENTER);
        coordinatesToPosition.put("1:2", CellSpecification.FLANK);
        coordinatesToPosition.put("2:0", CellSpecification.CORNER);
        coordinatesToPosition.put("2:1", CellSpecification.FLANK);
        coordinatesToPosition.put("2:2", CellSpecification.CORNER);

    }

    public boolean checkScenario(int difficulty) {

        if (difficulty != 3) {return false;} // doesn't work for easy difficulty

        int cellsOccupiedByX = 0;
        int cellsOccupiedByO = 0;
        String rightCoordinates = "";

        for (int x = 0; x < 3; x++) { // checks whether there is only one turn made
            for (int y = 2; y >= 0; y--) {
                if (Board.realBoard[x][y] == CellValue.X) {
                    cellsOccupiedByX++;
                    rightCoordinates = x + ":" + y;
                }
                else if (Board.realBoard[x][y] == CellValue.O) {
                    cellsOccupiedByO++;
                    rightCoordinates = x + ":" + y;
                }
//                if (Board.realBoard[x][y] == CellValue.X || Board.realBoard[x][y] == CellValue.O) {
//                    cellsOccupied++;
//                }
            }
        }

        if (cellsOccupiedByX == 1 && cellsOccupiedByO == 0) {

            coordinatesMapInitialize();

            return turnInDependencyOfPositionIfUsersTurnFirts(coordinatesToPosition.get(rightCoordinates));

        } else if (cellsOccupiedByX == 0 && cellsOccupiedByO == 1) {

            //coordinatesMapInitialize();
            //return true;
        }
        return false;
    }

    private boolean turnInDependencyOfPositionIfUsersTurnFirts(CellSpecification firstTurn) {

        switch (firstTurn) {

            case CORNER: Board.realBoard[1][1] = CellValue.O; return true;
            case CENTER:
            case FLANK:
        }
        return false;
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