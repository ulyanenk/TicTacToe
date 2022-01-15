package useless;

import logics.CellValue;

import java.security.KeyPair;

public class GameCounter {

    //private int xValueAI;
    //private int yValueAI;

    //private int x;
    //private int y;

    static void gamesPerTurn() {

        int last = 1;
        int cellsLeft = 0;

        for (int i = 8; i > 0; i--) {

            cellsLeft = last * i;

            last = cellsLeft;
        }

        System.out.println("Options left: " + cellsLeft);

    }

    public void autoTurner(CellValue[][] whichBoard) {

        for (int x = 0; x < 3; x++) {
            for (int y = 2; y >= 0; y++) {



            }
        }


        //Pair<Integer, Integer> pair = new Pair<>(1, 2);

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

                return ("[" + xValueAI + " " + yValueAI + "]");
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

                value = (" [" + i + " " + j + "]: " + board[i][j] + " | ");

                value = value + value2;

                value2 = value;
            }
        }
        return value;
    }


}
