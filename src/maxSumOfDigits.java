import java.io.*;
import java.util.*;
public class maxSumOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long x=0;
        int sum=0;
        while(x<=n) {
            x=x*10+9;
        }
        x=(x-9)/10;
        sum = sumdigit(x) + sumdigit(n - x);
        System.out.println(sum);
    }
    static int sumdigit(long x)
    {
        int sum=0;
        while(x>0)
        {
            sum+=x%10;
            x/=10;
        }
        return sum;
    }
}
