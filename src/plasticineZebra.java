import java.io.*;
import java.util.*;

public class plasticineZebra {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        s=s+s;
        int ans=0;
        for(int i=0;i<s.length();i++)
        {
            int t=1;
            while(i+1<s.length() && s.charAt(i) != s.charAt(i+1)){i++;t++;}
            ans=Integer.max(ans,t);
        }
        System.out.println(Integer.min(ans,s.length()/2));
    }
}
