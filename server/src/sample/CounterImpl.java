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


    ArrayList<Integer> waitingPlayers2=new ArrayList<>();
    ArrayList<Integer> waitingPlayers3=new ArrayList<>();
    ArrayList<Integer> waitingPlayers4=new ArrayList<>();
    ArrayList<Integer> occupiedPlayers=new ArrayList<>();

    HashMap spelersessiontoken=new HashMap();
    HashMap busyGame=new HashMap();

    @Override
    public void SignIn(String a, String b) throws RemoteException{
        System.out.println("username: "+a+ "ww: "+b);
        hashMap.put(a, b );
        spelersessiontoken.put(a, null);
    }

    @Override
    public int LogIn(String a, String b) throws RemoteException {
        if(hashMap.get(a).equals(b)){
            if(spelersessiontoken.get(a)!=null){
                return (int)spelersessiontoken.get(a);
            }
            else{
                int token=sessiontoken.size();
                sessiontoken.add(token);
                spelersessiontoken.put(a,token);
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
        return (sessiontoken==game.checkBeurt());
    }


    @Override
    public void testConnectie(){
        System.out.println("connectie is er");
    }
    @Override
    public boolean setGame(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        game.generateMatrix();
        if(game.checkBeurt()==sessiontoken){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void addToGame(int token, int aantalspelers){
        System.out.println("1token: "+token);
        if(aantalspelers==2) {

            if (!waitingPlayers2.contains(token)) {
                waitingPlayers2.add(token);
            }
            if (waitingPlayers2.size() == 2&&waitingPlayers2.get(1)==token) {
                System.out.println("3token: "+token);
                int speler1 = waitingPlayers2.get(0);
                int speler2 = waitingPlayers2.get(1);
                waitingPlayers2.clear();
                Game game = new Game(speler1, speler2);
                busyGame.put(speler1, game);
                busyGame.put(speler2, game);
                occupiedPlayers.add(speler1);
                occupiedPlayers.add(speler2);
            }

        }
        else if(aantalspelers==3) {
            if (!waitingPlayers3.contains(token)) {
                waitingPlayers3.add(token);
            }
            if (waitingPlayers3.size() == 3) {
                int speler1 = waitingPlayers3.get(0);
                int speler2 = waitingPlayers3.get(1);
                int speler3 = waitingPlayers3.get(2);
                waitingPlayers3.clear();
                System.out.println(speler1+" "+speler2+" "+ speler3+" ");
                Game game = new Game(speler1, speler2 ,speler3);

                busyGame.put(speler1, game);
                busyGame.put(speler2, game);
                busyGame.put(speler3, game);
                occupiedPlayers.add(speler1);
                occupiedPlayers.add(speler2);
                occupiedPlayers.add(speler3);

            }

        }
        else{
            if (!waitingPlayers4.contains(token)) {
                waitingPlayers4.add(token);
            }
            if (waitingPlayers4.size() == 4) {
                int speler1 = waitingPlayers4.get(0);
                int speler2 = waitingPlayers4.get(1);
                int speler3 = waitingPlayers4.get(2);
                int speler4 = waitingPlayers4.get(3);
                waitingPlayers4.clear();
                Game game = new Game(speler1, speler2 ,speler3, speler4);

                busyGame.put(speler1, game);
                busyGame.put(speler2, game);
                busyGame.put(speler3, game);
                busyGame.put(speler4, game);

                occupiedPlayers.add(speler1);
                occupiedPlayers.add(speler2);
                occupiedPlayers.add(speler3);

            }

        }
    }

    @Override
    public boolean vindtTegenspeler(int token){

       return occupiedPlayers.contains(token);
    }

    @Override
    public int[]getAndereGok(int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        int[]gok=game.getTegenspelerGok(sessiontoken);
        return gok;
    }
    public int getZet(int i, int sessiontoken){
        Game game=(Game)busyGame.get(sessiontoken);
        int value=game.getFromMatrix(i);
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
}