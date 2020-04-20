import java.io.*;
import java.util.*;
public class vasyaAndGoldenTicket {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char ch[] = br.readLine().toCharArray();
        int sum[] = new int[n];
        sum[0] = ch[0] - '0';
        for (int i = 1; i < n; i++) sum[i] = sum[i - 1] + ch[i] - '0';
        if (sum[n - 1] == 0) {
            System.out.println("YES");
            System.exit(0);
        } else {
            for (int i : sum)
            if (possiblity(ch, n, i))
                {
                    System.out.println("YES");
                    System.exit(0);
                }
                System.out.println("NO");
            }

        }
    static boolean possiblity(char ch[],int n,int s)
    {
        int c=0;int temp=0;
        for(int i=0;i<n;++i) {
            temp+=(ch[i]-'0');
            if(temp==s) {
                ++c;
                temp = 0;
            }
        }
        return temp>0?false:c>=2;

    }
}
