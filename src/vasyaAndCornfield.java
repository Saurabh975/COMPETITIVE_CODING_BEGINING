import java.io.*;
import java.util.*;

public class vasyaAndCornfield {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        int  n=sc.nextInt();
        int d=sc.nextInt();
        int t=sc.nextInt();

        while(t-->0)
        {
            int x=sc.nextInt();
            int y=sc.nextInt();

            System.out.println(((x+y-d<0)||(x+y-2*n+d>0)||(y-x+d<0)||(y-x-d>0))?"NO":"YES");
        }
    }
}
