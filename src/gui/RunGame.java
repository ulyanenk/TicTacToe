package gui;

import logics.AI;
import logics.Board;
import logics.CellValue;
import logics.Logic;

public class RunGame {

    //Window window = new Window();
    Logic logic = new Logic();
    AI ai = new AI();

    // array that counts each victory of both players
    //private final int[] score = {0, 0};

    /*public void againstAI(){

       // int difficulty = window.difficultyChoice.getDifficulty();

        // checks the whether the user has won this turn or if all cells are occupied, it's draw
        if (logic.victory(CellValue.X, Board.realBoard)) {

            System.out.println("\nCongratulations, you won!!!");
            score[1]++;

        } else if (ai.over(Board.realBoard)) {

            System.out.println("\nDraw");
        }

        // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
        if (logic.victoryNow(CellValue.O, Board.realBoard, CellValue.O) == null) {

            // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
            if (logic.victoryNow(CellValue.X, Board.realBoard, CellValue.O) == null) {

                // last: if after the analysis there is no move the AI could do, he uses an algorithm in dependence of the difficulty level
                switch (difficulty) {

                    case 1:
                        // [easy] AI just moves randomly
                        logic.randomTurn(Board.realBoard, CellValue.O);
                        break;

                    case 2:
                    case 3:
                        // [normal] or [hard] AI uses his "victory value for each cell" algorithm
                        ai.selfPlay(difficulty);
                }
            }
        }

        // checks whether the AI has won this turn or if all cells are occupied, it's draw
        if (logic.victory(CellValue.O, Board.realBoard)) {

            System.out.println("\nYou lost!!!");
            score[0]++;

        } else if (ai.over(Board.realBoard)) {

            System.out.println("\nDraw");
        }

    }*/

    public void AiTurn(int difficulty){

        // first: the AI checks, whether it's able to finish game this turn, if yes, the AI turns there
        if (logic.victoryNow(CellValue.O, Board.realBoard, CellValue.O) == null) {

            // second: the AI checks, whether user is able to finish game this turn, if yes, the AI turns there
            if (logic.victoryNow(CellValue.X, Board.realBoard, CellValue.O) == null) {

                // last: if after the analysis there is no move the AI could do, he uses an algorithm in dependence of the difficulty level
                switch (difficulty) {
                    case 1 ->
                            // [easy] AI just moves randomly
                            logic.randomTurn(Board.realBoard, CellValue.O);
                    case 2, 3 ->
                            // [normal] or [hard] AI uses his "victory value for each cell" algorithm
                            ai.selfPlay(difficulty);
                }
            }
        }

    }


}
