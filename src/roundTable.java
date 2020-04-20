import java.io.*;
import java.util.*;

public class roundTable{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long dp[][]=new long[n][2];// 0 stores if current cost is taken and 1 indicates if not taken
        int ar[]=new int[n];
        for(int i=0;i<n;i++)ar[i]=sc.nextInt();
        dp[0][0]=ar[0];dp[0][1]=ar[n-1];
        if(ar[n-1]<ar[0])n=n-1;

        for(int i=1;i<n;i++)
        {
            dp[i][0]=ar[i]+((dp[i-1][0]<=dp[i-1][1])?dp[i-1][0]:dp[i-1][1]);
            dp[i][1]=dp[i-1][0];
        }
        System.out.println((dp[n-1][0]<=dp[n-1][1])?dp[n-1][0]:dp[n-1][1]);
    }
}
