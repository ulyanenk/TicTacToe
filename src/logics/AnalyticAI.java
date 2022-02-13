package logics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnalyticAI {

    private int[][] victoryBoard = new int[3][3];

    //private final String exampleForAll = ("4[2:2]T 5[1:2]F 6[0:1]J 7[1:0]F 8[2:0]H 9[0:2]P ");

    // deep analysis of each made turn; a blueprint to make it; never used;
    /*public void gameAnalysis() {

        String[] turnAsString = exampleForAll.split(" ");
        //String[] splitter2 = turnAsString[0].split(":");
        //String remover1 = splitter2[0].;

        char[] turnToChar = turnAsString[0].toCharArray();

        int turnNumber = Character.getNumericValue(turnToChar[0]);
        int xValue = Character.getNumericValue(turnToChar[2]);
        int yValue = Character.getNumericValue(turnToChar[4]);
        char turnType = turnToChar[6];

        System.out.println(xValue);
        System.out.println(yValue);
        System.out.println(turnType);

        // print the turnAsString array out
        for (int i = 0; i < 8; i++) {

            try {
                //System.out.println(turnToChar[i]);

            } catch (ArrayIndexOutOfBoundsException ex1) {
                //System.out.println(i + ": no such index found");
            }
        }
    }*/

    private int fAppearance(String game) { // counts how many times 'F' appears

        int countF = 0;

        char[] splittedGame = game.toCharArray();

        for (int i = 0; i < splittedGame.length; i++) {

            if (splittedGame[i] == 'F') {
                countF++;
            }
        }
        return countF;
    }

    private Map<String, Integer> analyticTurn(Set<String> games) { // creates a map, that contains the whole game as a key; and F appearance as a value
        //Set<String> games = new HashSet();
        Map<String, Integer> map = new HashMap<>();

        for (String n : games) {

            //if (n.contains("V")) { // V stands for victory, that means that the new map shall contain only victorious combinations
                map.put(n, fAppearance(n)); //the game is a key; F appearance is a value
            //}

        }
        return map;
    }

    private Set<String> findOutBestTurnOption(Set<String> games) {

        Map<String, Integer> map = analyticTurn(games);

        Set<String> bestOptions = new HashSet<>(); // creates a new Set, where the best options should be placed

        int num1 = 10;
        for (Integer value : map.values()) { // finds out the lowest value of the map
            if (value < num1) {
                num1 = value;
            }
        }

        for (String key : map.keySet()) { // places the best options in the right set
            if (map.get(key) == num1) {
                bestOptions.add(key);
            }
        }
        return bestOptions;
    }

    private void getSetCoordinates(String game) { // gets the coordinates from the game string and sets them in the victory board

        char[] gameElements = game.toCharArray();

        int turnNumber;
        int xValue = Character.getNumericValue(gameElements[2]);
        int yValue = Character.getNumericValue(gameElements[4]);

        victoryBoard[xValue][yValue]++;
    }

    public void realTurn(Set<String> games) { // makes the real turn

        Set<String> set = findOutBestTurnOption(games);

        for (String n : set) { // fills the victory board
            System.out.println(n); // [commented]
            getSetCoordinates(n);
        }
        System.out.println("\nnew turn"); // [commented]

        int bestValue = 0;

        for (int x = 0; x < 3; x++) { // find out cell with the highest victory value
            for (int y = 2; y >= 0; y--) {

                if (victoryBoard[x][y] > bestValue) {
                    bestValue = victoryBoard[x][y];
                }
            }
        }

        for (int x = 0; x < 3; x++) { // makes turn in the cell with the highest value
            for (int y = 2; y >= 0; y--) {

                if (victoryBoard[x][y] == bestValue) {
                    Board.realBoard[x][y] = CellValue.O;
                    return;
                }
            }
        }
    }
}
