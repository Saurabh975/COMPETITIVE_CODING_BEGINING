import java.io.*;
import java.util.*;
public class phoneNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        char ch[]=br.readLine().toCharArray();
        int c=0;
        for(char val:ch)if(val=='8')c++;
        System.out.println(c<=(n/11)?c:(n/11));
    }
}
