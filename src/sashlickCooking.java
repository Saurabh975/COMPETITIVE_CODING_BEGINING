import java.io.*;
import java.util.*;

public class sashlickCooking {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int m=2*k+1;
        int no_of_turns=(int)Math.ceil((double)n/(double)m);
        System.out.println(no_of_turns);
        for(int i=n%m==0?k+1:n%m>k?k+1:1;i<=n;i+=m)System.out.print(i+" ");
        System.out.println();
    }
}
