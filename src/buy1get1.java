import java.io.*;
import java.util.*;
public  class buy1get1
{
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            char ch[]=br.readLine().trim().toCharArray();
            int arl[]=new int[26];int aru[]=new int[26];
            for(int i=0;i<ch.length;i++)
            {
                if(ch[i]>='A' && ch[i]<='Z')aru[ch[i]-'A']++;
                else if(ch[i]>='a' && ch[i]<='z')arl[ch[i]-'a']++;
            }
            int cost=0;
            for(int i=0;i<26;i++)
                cost+=(arl[i]+1)/2 + (aru[i]+1)/2;
            System.out.println(cost);
        }
    }
}