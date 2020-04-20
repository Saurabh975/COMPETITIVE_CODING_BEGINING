import java.io.*;
import java.util.*;
public class Equality {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        char ch[]=sc.next().toCharArray();
        sc.close();
        int fre[]=new int[26];
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            fre[ch[i]-'A']++;
        }
        for(int i=0;i<k;i++)min=min<=fre[i]?min:fre[i];
        System.out.println(min*k);
    }
}
