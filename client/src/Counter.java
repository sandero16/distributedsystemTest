import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Counter extends Remote {
    void SignIn(String a, String b) throws RemoteException;
    boolean LogIn(String a, String b) throws RemoteException;
    String geefWW(String a) throws  RemoteException;
    int[][] getAndereGok(int i) throws  RemoteException;
    void sendAndereGok( int [][] gok, int i) throws RemoteException;
}