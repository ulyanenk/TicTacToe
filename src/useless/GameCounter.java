package useless;

public class GameCounter {

    static void gamesPerTurn() {

        int last = 1;
        int cellsLeft = 0;

        for (int i = 8; i > 0; i--) {

            cellsLeft = last * i;

            last = cellsLeft;
        }

        System.out.println("Options left: " + cellsLeft);

    }
}
