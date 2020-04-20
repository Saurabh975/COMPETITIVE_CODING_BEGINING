import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class Mystry {
    public static void main(String args[]) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(System.out));
//        ts = Calendar.getInstance();
//        ts.setTime(new Date());
        String num = input.readLine();
        while (num != null) {
            long n = Long.parseLong(num);
            int c = 0;
            while (n > 0) {
                n &= n - 1;
                c++;
            }
            pw.write(c+"\n");
            num = input.readLine();
        }
        pw.close();
//        te = Calendar.getInstance();
//        te.setTime(new Date());
//        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);

    }
}
