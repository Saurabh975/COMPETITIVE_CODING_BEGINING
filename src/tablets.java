import java.io.*;
import java.util.*;
public class tablets {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        int n=sc.nextInt();int ar[]=new int[n];int dp[]=new int[n];
        for(int i=0;i<n;i++)ar[i]=sc.nextInt();

        for(int i=1;i<n;i++)if(ar[i]>ar[i-1])dp[i]=dp[i-1]+1;
        for(int i=n-2;i>=0;i--)if(ar[i]>ar[i+1] && dp[i]<=dp[i+1])dp[i]=dp[i+1]+1;

        int ans=n;
        for(int i:dp)ans+=i;
        System.out.println(ans);
    }
}
