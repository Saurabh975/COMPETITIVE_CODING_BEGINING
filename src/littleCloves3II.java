import java.util.*;
import java.io.*;
public class littleCloves3II {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        if(n<m) {
            n = n + m;
            m = n - m;
            n = n - m;
        }
        int ans;
        if(m==1)
        {
            int a[]={0,0,0,2,4,6};
            ans=a[(n-1)%6]+ 6*((n-1)/6);
        }
        else if(m==2)
        {
            if (n==2)ans=0;
            else if(n==3 || n==7)  ans=m*(n-1);
            else ans=m*n;
        }
        else {
            ans=n*m;
            ans-=ans&1;
        }
        System.out.println(ans);
    }
}
