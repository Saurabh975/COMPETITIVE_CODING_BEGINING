import java.util.*;

public class digonalWalkingV2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0)
        {
            long x=Math.abs(sc.nextLong());
            long y=Math.abs(sc.nextLong());
            long k=sc.nextLong();
            if(x>k || y >k)System.out.println(-1);
            else
            {
                long ans=k;
                if((k&1) != (x&1))ans--;
                if((k&1) != (y&1))ans--;
                System.out.println(ans);
            }
        }
    }
}
