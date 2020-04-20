import java.io.*;
import java.util.*;
public class combinatoricsMonicasObsession {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double n = Long.parseLong(br.readLine());
        long c = 0;
        while (n > 1) {
            c++;
            n = (int) Math.ceil(n / 2);
        }
        System.out.println(c);
    }
}