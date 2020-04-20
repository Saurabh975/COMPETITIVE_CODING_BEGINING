import java.io.*;
import java.util.*;
public class socialCircles {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int l[]=new int[n];
        int r[]=new int[n];
        for(int i=0;i<n;i++){
            l[i]=sc.nextInt();r[i]=sc.nextInt();
        }
        long ans=n;
        Arrays.sort(l);Arrays.sort(r);

        for(int i=0;i<n;i++)ans+=Long.max(l[i],r[i]);
        System.out.println(ans);
    }
}

