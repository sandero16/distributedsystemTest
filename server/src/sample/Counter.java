package sample;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Counter extends Remote {
    void SignIn(String a, String b) throws RemoteException;
    int LogIn(String a, String b) throws RemoteException;
    String geefWW(String a) throws  RemoteException;
    int[] getAndereGok(int i) throws  RemoteException;
    void testConnectie() throws  RemoteException;
    boolean vindtTegenspeler(int token) throws RemoteException;
    void addToGame(int token, int aantalspelers) throws RemoteException;
    boolean setGame(int sessiontoken) throws RemoteException;
    int getZet(int i, int sessiontoken) throws RemoteException;
    void changeBeurt(int sessiontoken) throws RemoteException;
    boolean checkBeurt(int sessiontoken) throws RemoteException;
    boolean getResult(int sessiontoken) throws  RemoteException;
    int getScore(int sessiontoken) throws RemoteException;
}
