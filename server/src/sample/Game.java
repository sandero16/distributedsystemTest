package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int gameId;
    private int sessionToken1;
    private int sessionToken2;
    private int beurt;
    private int[][] matrix;
    private boolean initialized;

    public  Game(int player1, int player2){
        sessionToken1=player1;
        sessionToken2=player2;
        initialized=false;
    }
    public void setGameId(int id){
        gameId=id;
    }
    public void setPlayer2(int token){
        sessionToken2=token;
    }
    public int getPlayer1token(){
        return sessionToken1;
    }
    public void generateMatrix(){
        if(!initialized) {
            initialized=true;
            List<Integer> solution = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                solution.add(i);
                solution.add(i);
            }
            Collections.shuffle(solution);
            for (int i : solution) {
                System.out.print(i + " ");
            }
            matrix = new int[4][4];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    matrix[i][j] = solution.get(i * 4 + j);
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

    }

}
