import console.GameMode;
import gui.TicTacWindow;

public class Main {


    public static void main(String[] args) {

        gui();
        //console();
    }

    private static void console(){

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

    private static void gui(){
        TicTacWindow window = new TicTacWindow();
    }
}