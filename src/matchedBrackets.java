import java.io.*;
import java.util.*;

public class matchedBrackets {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int nd,fp,lm,fpm;
        nd=fp=lm=fpm=0;
        int count=0,count1=1;
        for(int i=0;i<n;i++)
        {
            int x=sc.nextInt();
            if(x==1)count++;
            else count--;

            if(count>0)count1++;
            else count1=1;
            if(count > nd)
            {
                nd=count;
                fp=i+1;
            }

            if(count1 > lm) {
                lm = count1;
                fpm = (i - count1) + 3;
            }
        }
        System.out.println(nd+" "+fp+" "+lm+" "+fpm);
    }
}
