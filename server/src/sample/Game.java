package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int gameId;

    private ArrayList<Integer>players;
    private ArrayList<Integer>scores;

    private int keuze1;
    private int keuze2;
    private int beurt;
    private int[][] matrix;
    private int[] gegokt;
    private boolean initialized;
    private ArrayList<Boolean>update;

    public  Game(int player1, int player2){
        players=new ArrayList<>();
        scores=new ArrayList<>();
        update=new ArrayList<>();
        for(int i=0;i<2;i++) {
            update.add(false);
        }
        players.add(player1);
        players.add(player2);

        initialized=false;
    }
    public Game(int player1, int player2, int player3){
        players=new ArrayList<>();
        scores=new ArrayList<>();
        update=new ArrayList<>();

        for(int i=0;i<3;i++) {
            update.add(false);
        }

        players.add(player1);
        players.add(player2);
        players.add(player3);

        initialized=false;
    }
    public Game(int player1, int player2, int player3, int player4){
        players=new ArrayList<>();
        scores=new ArrayList<>();
        update=new ArrayList<>();

        for(int i=0;i<4;i++) {
            update.add(false);
        }

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        initialized=false;
    }
    public void setGameId(int id){
        gameId=id;
    }

    public void generateMatrix(){

        System.out.println("generating"+ " beurt: "+beurt);
        for (int i: players) {
            System.out.print(i+" ");
        }
        System.out.println();
        if(!initialized) {
            System.out.println("for loop");
            for(int i=0;i<players.size();i++){
                System.out.println("i :"+players.get(i));
            }
            System.out.println("foreach");
            for (int i:players) {
                System.out.println("player: "+i);
            }

            for(int i=0;i<players.size();i++){
                scores.add(0);
            }

            keuze1=-1;
            keuze2=-1;
            gegokt=new int[2];

            beurt=players.get(0);
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

    public int getFromMatrix(int i){
        System.out.println("value= "+i);
        i--;
        int row=i/4;
        int column=i%4;
        System.out.println("rij "+row+ "column: "+column);
        gegokt[0]=i;
        gegokt[1]=matrix[row][column];
        for(int j=0;j<update.size();j++){
            update.set(j,true);
        }
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
        int index=players.indexOf(beurt);
        if(keuze1==keuze2)scores.set(index,scores.get(index)+1);
        keuze1=-1;keuze2=-1;
        if(index==(players.size()-1)){
            beurt=players.get(0);
        }
        else{
            beurt=players.get(index+1);
        }

    }
    public int checkBeurt(){
        return beurt;

    }
    public int getIndex(int sessiontoken){
        return players.indexOf(sessiontoken);
    }
    public int[] getTegenspelerGok(int sessiontoken){
        int index=players.indexOf(sessiontoken);
        System.out.println("index: "+index);
        if(update.get(index)) {
            update.set(index,false);
            return gegokt;
        }
        else{
            return null;
        }
    }
    public boolean getResult(int sessiontoken){
        int index=players.indexOf(sessiontoken);
        boolean gewonnen=true;
        int hoogste=scores.get(index);
        for(int i=0;i<scores.size();i++){
            if(index!=i){
                if(scores.get(i)>hoogste)gewonnen=false;
            }
        }
        return gewonnen;
    }
    public int getScore(int sessiontoken){
       return scores.get(players.indexOf(sessiontoken));
    }

}
