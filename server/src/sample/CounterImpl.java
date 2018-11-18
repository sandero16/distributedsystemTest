package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class CounterImpl extends UnicastRemoteObject implements Counter{
    public int[][] gok;
    public CounterImpl () throws RemoteException{}

    HashMap hashMap=new HashMap();
    ArrayList<Integer> sessiontoken=new ArrayList<>();
    ArrayList<Integer> waitingPlayers=new ArrayList<>();
    HashMap spelersessiontoken=new HashMap();
    HashMap<Integer, Boolean> spelergevonden=new HashMap();

    //ArrayList<Game>busyGames=new ArrayList<>();
    HashMap busyGame=new HashMap();

    @Override
    public void SignIn(String a, String b) throws RemoteException{
        System.out.println("username: "+a+ "ww: "+b);
        hashMap.put(a, b );
        spelersessiontoken.put(a, null);
        //writePlayers();
    }

    @Override
    public int LogIn(String a, String b) throws RemoteException {
        if(hashMap.get(a).equals(b)){
            //kijken of speler al een session token heeft
            if(spelersessiontoken.get(a)!=null){
                //TODO kijken of session token nog niet vervallen is

                System.out.println("token bestaat"+(int)spelersessiontoken.get(a));

                return (int)spelersessiontoken.get(a);
            }
            else{
                int token=sessiontoken.size();
                sessiontoken.add(token);
                spelersessiontoken.put(a,token);
                System.out.println("token"+token);
                return token;
            }
        }
        return -1;
    }

    @Override
    public String geefWW(String key){
        return hashMap.get(key).toString();
    }
    @Override
    public void changeBeurt(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        game.changeBeurt(sessiontoken);

    }
    public boolean checkBeurt(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        return game.checkBeurt(sessiontoken);
    }
    @Override
    public void sendAndereGok(int [][]gok, int i){
    //    player =i;
       // this.gok=gok;
    }

  /*  @Override
    public int[][] getAndereGok(int i){
      /*  if(player!=i) {
            return gok;
        }
        else return null;
    }*/

    @Override
    public void NewPlayer(int token){
       /* if(waitingGame.isEmpty()){
            waitingGame.add(token);

        }
        else{
            int token1=waitingGame.get(0);
            waitingGame.remove(0);
            Game tempGame=new Game(token1, token);
            busyGame.put(token1, tempGame);
            busyGame.put(token, tempGame);

        }*/
    }
    @Override
    public boolean zoekAnderespeler(int token){
        /*//betere manier om te checken of andere speler
        if(waitingGame.contains(token)){
            return false;
        }
        else{
            return true;
        }*/
            return true;
    }
    @Override
    public void testConnectie(){
        System.out.println("connectie is er");
    }
    @Override
    public boolean setGame(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        game.generateMatrix();
        System.out.println("matrix gegenereerd");
        if(game.getBeurt()==sessiontoken){
            System.out.println("juist");
            return true;
        }
        else {
            System.out.println("fout");
            return false;
        }
    }
    @Override
    public boolean vindtTegenspeler(int token){
        if(waitingPlayers.isEmpty()){
            waitingPlayers.add(token);
            spelergevonden.put(token, false);
            return false;
        }

        else if(waitingPlayers.get(0)==token){
            System.out.println("vindtegenspeler");
            return spelergevonden.get(token);
        }

        else{
            int player2=waitingPlayers.get(0);
            System.out.println("speler: "+token + " tegen speler: "+player2);
            Game tempgame=new Game(token,player2);
            spelergevonden.put(player2,true);
            busyGame.put(token, tempgame);
            busyGame.put(player2, tempgame);
            return true;
        }


    }

    @Override
    public int[]getAndereGok(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        return game.getTegenspelerGok();
    }
    public int getZet(int i, int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        int value=game.getFromMatrix(i);
        System.out.println("value");
        return value;
    }
    @Override
    public boolean getResult(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        return game.getResult(sessiontoken);
    }
    @Override
    public int getScore(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        return game.getScore(sessiontoken);
    }

    public void writePlayers(){
        for (Object name: hashMap.keySet()){

            String key =name.toString();
            String value = hashMap.get(key).toString();
            System.out.println(key + " " + value);


        }
    }
}