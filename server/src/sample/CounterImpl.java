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
    public void setGame(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        game.generateMatrix();
        System.out.println("matrix gegenereerd");
    }
    @Override
    public boolean vindtTegenspeler(int token){
        System.out.println();
        System.out.println();
        System.out.println("connectie bestaat");
        if(waitingPlayers.isEmpty()){
            waitingPlayers.add(token);
            return false;
        }
        else{
            int player2=waitingPlayers.get(0);
            Game tempgame=new Game(token,player2);
            busyGame.put(token, tempgame);
            busyGame.put(player2, tempgame);
            return true;
        }

    }
    public void writePlayers(){
        for (Object name: hashMap.keySet()){

            String key =name.toString();
            String value = hashMap.get(key).toString();
            System.out.println(key + " " + value);


        }
    }
}