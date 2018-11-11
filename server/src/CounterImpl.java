import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class CounterImpl extends UnicastRemoteObject implements Counter{
    public int[][] gok;
    public int player;
    public CounterImpl () throws RemoteException{}

    HashMap hashMap=new HashMap();

    @Override
    public void SignIn(String a, String b) throws RemoteException{
        hashMap.put(a, b);
    }

    @Override
    public boolean LogIn(String a, String b) throws RemoteException {
        if(hashMap.get(a).equals(b)){
            return true;
        }
        return false;
    }

    @Override
    public String geefWW(String key){
        return hashMap.get(key).toString();
    }

    @Override
    public void sendAndereGok(int [][]gok, int i){
        player =i;
        this.gok=gok;
    }

    @Override
    public int[][] getAndereGok(int i){
        if(player!=i) {
            return gok;
        }
        else return null;
    }
}