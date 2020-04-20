import java.util.*;
public class ChefVsMonk {
    public static void main(String ars[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long a=sc.nextLong();
            long b=sc.nextLong();
            long d=Math.max(a,b)-Math.min(a,b);
            a=gcd(Math.min(a,b),Math.max(a,b),1,1);
            System.out.println(Math.max(a,d));

        }
    }
    static long gcd(long a, long b, long x, long y)
    {
        // Base Case
        if (a == 0)
        {
            x = 0;
            y = 1;
            return b;
        }

        long x1=1, y1=1; // To store results of recursive call
        long gcd = gcd(b%a, a, x1, y1);

        // Update x and y using results of recursive
        // call
        x = y1 - (b/a) * x1;
        y = x1;

        return gcd;
    }
}
