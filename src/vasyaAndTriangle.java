import java.io.*;
import java.util.*;
public class vasyaAndTriangle {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt();
        long m=sc.nextInt();
        long k=sc.nextInt();

        if(((n*m)<<1)%k!=0)System.out.println("NO");
        else
        {
            System.out.println("YES\n0 0");
            long gcd=gcd(2*n,k);
            if(gcd==1)
                System.out.println(n+" 0\n0 "+(2*m/k));
            else
                System.out.println((n*2/gcd)+" 0\n0 "+(gcd*m/k));
        }
    }
    static long gcd(long a,long b)
    {
        return b==0?a:gcd(b,a%b);
    }
}
