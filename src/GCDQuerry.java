import java.io.*;
import java.util.*;
public class GCDQuerry {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int q=Integer.parseInt(st.nextToken());
            st=new StringTokenizer(br.readLine());
            int ar[]=new int[n+1];
            for(int i=1;i<=n;i++)ar[i]=Integer.parseInt(st.nextToken());
            int prefix[]=new int[n+1];
            int suffix[]=new int[n+1];
            prefix[1]=ar[1];
            suffix[n]=ar[n];
            for(int i=2;i<=n;i++)prefix[i]=gcd(prefix[i-1],ar[i]);
            for(int i=n-1;i>=0;i--)suffix[i]=gcd(suffix[i+1],ar[i]);

            while(q-->0)
            {
                st=new StringTokenizer(br.readLine());
                int l=Integer.parseInt(st.nextToken());
                int r=Integer.parseInt(st.nextToken());
                System.out.println(l==1 ? suffix[r+1] : r==n ? prefix[l-1] : gcd(prefix[l-1],suffix[r+1]));
            }
        }
    }
    static int gcd(int a,int b)
    {
        if(b==0)return a;
        return gcd(b,a%b);
    }
}
