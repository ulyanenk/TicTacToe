package com.company;

public class PrintBoard {

    public static CellValue[][] board = new CellValue[3][3];

    public static void printField() {


        System.out.println(" ");
        System.out.println("      A   B   C");
        System.out.println(" ");

        System.out.println(" 1    " + board[0][0].getTag() + " | " + board[1][0].getTag() + " | " + board[2][0].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 2    " + board[0][1].getTag() + " | " + board[1][1].getTag() + " | " + board[2][1].getTag());
        System.out.println("     ___|___|___");
        System.out.println(" 3    " + board[0][2].getTag() + " | " + board[1][2].getTag() + " | " + board[2][2].getTag());
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

                board[i][j] = CellValue.N;

                // System.out.println(board[i][j].getTag());
            }
        }

    }

}
