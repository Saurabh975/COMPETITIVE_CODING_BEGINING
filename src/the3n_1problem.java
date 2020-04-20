import java.io.*;
import java.util.*;
public class the3n_1problem {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int ar[] = new int[10001];
        for (int i = 1; i < 10001; i++) {
            ar[i] = cycle_length(i);
        }

        //StringTokenizer s=new StringTokenizer(sc.nextLine());
        try {
            while (true) {
                int i = sc.nextInt();//Integer.parseInt(s.nextToken());
                int j = sc.nextInt();//Integer.parseInt(s.nextToken());
                int max = 0;
                for (int k = i; k <= j; k++)
                    max = Integer.max(max, ar[k]);
                System.out.println(i + " " + j + " " + max);

                //s=new StringTokenizer(sc.nextLine());
            }
        }
        catch (Exception e){System.exit(0);}
    }
    static  int cycle_length(final int n)
    {
        if(n==1)return 1;
        else if((n&1)==0)return 1+cycle_length(n>>1);
        else return 1+ cycle_length(3*n +1);
    }
}
