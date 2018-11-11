import java.util.*;

public class Main {
    public static void main(String[] args){
        Random random=new Random();


        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            solution.add(i);
            solution.add(i);
        }
        Collections.shuffle(solution);
        for (int i: solution) {
            System.out.print(i+ " ");
        }
        int[][] matrix=new int[4][4];
        System.out.println();
        System.out.println();
        for(int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                matrix[i][j]=solution.get(i*4+j);
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        Scanner scanner=new Scanner(System.in);
        boolean game=true;
        System.out.println();
        ArrayList geraden=new ArrayList<>();
        while(game){
            for(int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    if(geraden.contains(i*4+j)){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }

            System.out.println("eerste guess");
            System.out.println("rij: ");
            int rij=scanner.nextInt();


            System.out.println("kolom");
            int kolom=scanner.nextInt();

            for(int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    if(geraden.contains(i*4+j)){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else if(i==rij&&j==kolom){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
            System.out.println("tweede guess");
            System.out.println("rij: ");
            int rij2=scanner.nextInt();
            System.out.println("kolom");
            int kolom2=scanner.nextInt();

            for(int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    if(geraden.contains(i*4+j)){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else if(i==rij&&j==kolom){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else if(i==rij2&&j==kolom2){
                        System.out.print(matrix[i][j]+" ");
                    }
                    else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
            System.out.println();

            if(matrix[rij][kolom]==matrix[rij2][kolom2]){
                geraden.add(rij*4+kolom);
                geraden.add(rij2*4+kolom2);
            }

            if(geraden.size()==16)game=false;
        }


    }
}

