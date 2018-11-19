package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int gameId;
    private int sessionToken1;
    private int sessionToken2;
    private int score1;
    private int score2;

    private int keuze1;
    private int keuze2;
    private int beurt;
    private int[][] matrix;
    private int[] gegokt;
    private boolean initialized;
    boolean update=false;

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

        System.out.println("generating"+ " beurt: "+beurt);
        System.out.println("speler 1: "+sessionToken1);
        System.out.println("speler 2: "+sessionToken2);
        if(!initialized) {
            score1=0;
            score2=0;
            keuze1=-1;
            keuze2=-1;
            gegokt=new int[2];
            beurt=(int)(Math.random()*2)+1;
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
    public int getBeurt(){
        if(beurt==1){
            System.out.println("token: "+sessionToken1);
            return sessionToken1;
        }
        else{
            System.out.println("token: "+sessionToken2);
            return sessionToken2;
        }
    }
    public int getFromMatrix(int i){
        System.out.println("value= "+i);
        i--;
        int row=i/4;
        int column=i%4;
        System.out.println("rij "+row+ "column: "+column);
        gegokt[0]=i;
        gegokt[1]=matrix[row][column];
        update=true;
        if(keuze1==-1){
            keuze1=matrix[row][column];
        }
        else{
            keuze2=matrix[row][column];
        }
        return matrix[row][column];
    }
    public void changeBeurt(int sessiontoken){
        //beveiliging
        System.out.println("score1: "+score1+" score2: "+score2);
        System.out.println("keuze1: "+keuze1+  " Keuze2: "+keuze2);
        System.out.println("sessiontoken meegegeven: "+sessiontoken+" beurt");
        if(sessiontoken==sessionToken1&&beurt==1){
            System.out.println("1 score aant het geven");
            if(keuze1==keuze2)score1++;
            keuze1=-1;keuze2=-1;
            beurt=2;
        }
        else if(sessiontoken==sessionToken2&&beurt==2){
            System.out.println("2 score aan het geven");
            if(keuze1==keuze2)score2++;
            keuze1=-1;keuze2=-1;
            beurt=1;
        }
        System.out.println("score1: "+score1+" score2: "+score2);

    }
    public boolean checkBeurt(int sessiontoken){
        if(beurt==1) {
            return (sessiontoken == sessionToken1);
        }
        else{
            return (sessiontoken==sessionToken2);
        }

    }
    public int[] getTegenspelerGok(){
        if(update) {
            update=false;
            return gegokt;
        }
        else{
            return null;
        }
    }
    public boolean getResult(int sessiontoken){
        if(sessiontoken==sessionToken1){
            return score1>score2;
        }
        else{
            return score2>score1;
        }
    }
    public int getScore(int sessiontoken){
        if(sessiontoken==sessionToken1){
            return score2;
        }
        else{
            return score1;
        }
    }

}
