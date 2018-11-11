import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private void doTest(){
        try {
// fire to localhost port 1099
            System.out.println(System.getProperty("java.security.policy"));
            Registry myRegistry = LocateRegistry.getRegistry("localhost", 1099);

// search for CounterService
            Counter impl = (Counter) myRegistry.lookup("Login");
            System.out.println(System.getProperty("java.security.policy"));
// call server's method
            impl.SignIn("Sandero","aze");
            Scanner scanner=new Scanner(System.in);
            System.out.println("geef wachtwoord");
            String ww=scanner.nextLine();
            System.out.println("wachtwoord: "+ ww);
            if(impl.LogIn("Sandero",ww)){
                System.out.println("juist");
            }
            else{
                System.out.println("fout"+ " het juiste was: "+ impl.geefWW("Sandero"));
            }

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            boolean end=true;
            while(end){
                while(impl.getAndereGok(1)==null){
                }
                int [][] gok=impl.getAndereGok(1);
                System.out.println(gok [0][0]);
                boolean input=true;
                while(input){
                    String s=stdIn.readLine();
                    if(s!=null){
                        int i=Integer.parseInt(s);
                        int[][] mijnGok=new int[1][1];
                        mijnGok[0][0]=i;
                        input=false;
                        impl.sendAndereGok(mijnGok, 1);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.doTest();
    }
}