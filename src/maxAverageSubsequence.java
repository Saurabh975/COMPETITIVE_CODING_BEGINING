import java.io.*;
import java.util.*;
public class maxAverageSubsequence {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0)
        {
            int max=0;
            int n=sc.nextInt();
            for(int i=0;i<n;i++)max=Integer.max(max,sc.nextInt());
            System.out.println(max);
        }
    }
}
