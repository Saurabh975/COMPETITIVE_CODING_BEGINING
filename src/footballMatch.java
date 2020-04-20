import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class footballMatch {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            int q=Integer.parseInt(br.readLine());
            long ar[]=new long[2];
            String t1="",t2="";
            for(int i=0;i<q;i++)
        {
            String s=br.readLine();
            if(i==0)t1=s;
            if(t1.equals(s))ar[0]++;
            else {
                ar[1]++;t2=s;
            }
        }
        System.out.println(ar[0]==ar[1]? "Draw" : ar[0]>ar[1] ? t1 : t2);
        }
    }
}
