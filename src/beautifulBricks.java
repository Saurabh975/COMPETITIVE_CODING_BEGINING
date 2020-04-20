
// Author @ BlackRise :) //

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class beautifulBricks {
    static Calendar ts, te;                                                  //For time calculation
    static long mod9 = (long) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);


    public static void main(String[] args) {                                 //threading has been used to increase the stack size.

        new Thread(null, null, "BlackRise", 1 << 25)  //the last parameter is stack size which is desired,
        {
            public void run() {

                try {
                    Blackrise();
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }

    static void Blackrise() {                                                //The name Blackrise is my pen name... you can change the name according to your wish

        tsc();                                                               //calculates the starting time of execution

        int t = ni();
        while (t-- > 0) {
            long n = ni();
            long k = ni();
            if (k > n) {
                pl(0);
                continue;
            }
            if (n == k) {
                pl(2);
                continue;
            }
            long ans = 0;
            for (int i = 1; i <= Math.min(n + 1, k); i++)
                ans = (ans % mod9 + (mulmod(combination(k - 1, i - 1), mulmod(combination(n - k + 1, i), power(2, i, mod9))))) % mod9;
            pl(ans);
            //pl(combination(1000000000,1000));
        }

        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
    }

    static long combination(long n, long k) {
        if (k == 0)
            return 1;
        long pro = 1;
        for (long i = n; i > n - k; i--)
            pro = ((pro % mod9) * (i % mod9)) % mod9;

        long pro2 = 1;
        // pl();pl();

        for (long i = 2; i <= k; i++)
            pro2 = ((pro2 % mod9) * (i % mod9)) % mod9;

        //pl();pl();

        return ((pro % mod9) * (mulinv(pro2, mod9))) % mod9;
    }


    static long mulinv(long a, long m) {
        return power(a, m - 2, m);
    }

    static long power(long x, long y, long m) {
        if (y == 0)
            return 1;

        long p = power(x, y / 2, m) % m;
        p = (p * p) % m;

        if (y % 2 == 0)
            return p;
        else
            return (x * p) % m;
    }

    static long mulmod(long a, long b) {
        long res = 0; // Initialize result
        a = a % mod9;
        while (b > 0) {
            // If b is odd, add 'a' to result
            if (b % 2 == 1)
                res = (res + a) % mod9;

            // Multiply 'a' with 2
            a = (a * 2) % mod9;

            // Divide b by 2
            b /= 2;
        }

        // Return result
        return res % mod9;
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static class InputReader {                                              //InputReader class for fast input
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }

                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    // functions to take input//
    static int ni() {
        return input.nextInt();
    }

    static long nl() {
        return input.nextLong();
    }

    static double nd() {
        return input.nextDouble();
    }

    static String ns() {
        return input.readString();
    }

    //functions to give output
    static void pl() {
        pw.println();
    }

    static void p(Object o) {
        pw.print(o + " ");
    }

    static void pws(Object o) {
        pw.print(o + "");
    }

    static void pl(Object o) {
        pw.println(o);
    }
    //time calculation functions

    //starting time
    static void tsc() {
        ts = Calendar.getInstance();
        ts.setTime(new Date());
    }

    //ending time
    static void tec() {
        te = Calendar.getInstance();
        te.setTime(new Date());
    }

    static void pwt() {
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }
}