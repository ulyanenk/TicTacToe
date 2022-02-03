package logics;

public class Board {

    public static CellValue[][] realBoard = new CellValue[3][3];

    public static void printField() {


        System.out.println(" ");
        System.out.println("      A   B   C");
        System.out.println(" ");

        System.out.println(" 1    " + realBoard[0][0].getTag() + " | " + realBoard[1][0].getTag() + " | " + realBoard[2][0].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 2    " + realBoard[0][1].getTag() + " | " + realBoard[1][1].getTag() + " | " + realBoard[2][1].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 3    " + realBoard[0][2].getTag() + " | " + realBoard[1][2].getTag() + " | " + realBoard[2][2].getTag());
        System.out.println("        |   |   ");

    }


    // to test the AI (should be never used in the end version)
    public static void printVirtualBoard(CellValue[][] whichBoard) {


        System.out.println(" ");
        System.out.println("      A   B   C");
        System.out.println(" ");

        System.out.println(" 1    " +  whichBoard[0][0].getTag() + " | " + whichBoard[1][0].getTag() + " | " + whichBoard[2][0].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 2    " + whichBoard[0][1].getTag() + " | " + whichBoard[1][1].getTag() + " | " + whichBoard[2][1].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 3    " + whichBoard[0][2].getTag() + " | " + whichBoard[1][2].getTag() + " | " + whichBoard[2][2].getTag());
        System.out.println("        |   |   ");

    }

    // start value for all cells or clear the board
    public static void clearBoard() {

        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                realBoard[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }

    }

    public static void clearBoard(CellValue[][] whichBoard) {

        // after one virtual game is over the virtual board gets "cleared" (every cell gets "N"(empty) value)
        for(int i = 0; i < 3; i++){
            for(int j = 2; j >= 0; j--) {

                whichBoard[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }
    }

}
