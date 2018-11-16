package sample;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Counter extends Remote {
    void SignIn(String a, String b) throws RemoteException;
    int LogIn(String a, String b) throws RemoteException;
    String geefWW(String a) throws  RemoteException;
   // int[][] getAndereGok(int i) throws  RemoteException;
    void sendAndereGok( int [][] gok, int i) throws RemoteException;
    void NewPlayer(int token) throws RemoteException;
    boolean zoekAnderespeler(int sessiontoken) throws RemoteException;
    void testConnectie() throws  RemoteException;
    boolean vindtTegenspeler(int token) throws RemoteException;
    void setGame(int sessiontoken) throws RemoteException;
}
