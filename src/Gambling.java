import java.io.*;
import java.util.*;
public class Gambling {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        PriorityQueue<Integer> a=new PriorityQueue<>(Comparator.reverseOrder()),
                b=new PriorityQueue<>(Comparator.reverseOrder());
        int n=sc.nextInt();
        for (int i = 0; i < n; i++) a.add(sc.nextInt());
        for (int i = 0; i < n; i++) b.add(sc.nextInt());
        long res=0;
        for (int i = 0; i < n+n; i++) {
            int x=a.isEmpty()?0:a.peek();
            int y=b.isEmpty()?0:b.peek();

            if((i&1)==1)
                if(x<y){res-=y;b.poll();}
                else a.poll();
            else
                if(x<y)b.poll();
                else {res+=x;a.poll();}
        }
        System.out.println(res);
    }
}
