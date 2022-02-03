import console.GameMode;
import gui.Window;
import logics.Board;

import java.awt.*;

public class Main {


     //public static Scanner scan = new Scanner(System.in);

    // score counter; slot 0 computer, slot 1 user
   // static int[] score = {0, 0};


    public static void main(String[] args) {

        //Board.clearBoard();

        Window window = new Window();

        System.out.println(" ");
        System.out.println("Welcome to the Tic Tac Toe");
        System.out.println(" ");

        GameMode gameMode = new GameMode();

        //int chosenDifficulty = gameMode.chooseDifficulty();

        // the player chooses the game mod
        if (gameMode.difficulty == 4) {

            gameMode.basicRules();
            gameMode.twoPlayers();

        } else {

            gameMode.basicRules();
            gameMode.againstAI();
        }

    }
}