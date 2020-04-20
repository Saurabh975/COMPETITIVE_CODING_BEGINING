
// Author @ BlackRise //
// BIT MESRA, Ranchi  //

import java.io.*;
import java.util.*;

public class partyInvitation {

    static long fac[] = new long[200005];
    static long inv[] = new long[200005];
    static ArrayList<Integer> ar[];
    static int size[];
    static long sol[];
    static long n, m;
    static int hasBoss[];

    static void Blackrise() {     //The name Blackrise is my pen name... you can change the name according to your wish
        int t = ni();
        tsc();                    //calculates the starting time of execution

        fac[0] = 1;
        for (int i = 1; i < 200005; i++) fac[i] = (fac[i - 1] * i) % mod9;

        inv[200004] = pow(fac[200004], mod9 - 2);
        for (int i = 200003; i >= 0; i--) inv[i] = ((i + 1) * inv[i + 1]) % mod9;

        while (t-- > 0) {

            {
                size = new int[200005];
                sol = new long[200005];
                ar = new ArrayList[200005];
                for (int i = 0; i < 200005; i++) ar[i] = new ArrayList<>();
                hasBoss = new int[200005];
            }

            n = ni();
            m = ni();
            while (m-- > 0) {
                int x = ni();
                int y = ni();
                ar[x + 1].add(y + 1);
                hasBoss[y + 1] = 1;
            }
            for (int i = 1; i <= n; i++) {
                if (hasBoss[i + 1] == 0)
                    ar[1].add(i + 1);
            }
            n++;
            traverse(1, 0);
            traverse(1, 1);
            pl(sol[1]);
//            for (int i = 0; i < 20005; i++) p(sol[i]);
//            pl();
        }

        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
    }

    static long pow(long n, int m) {
        if (n == 1 || m == 0) return 1;
        if (m == 1) return n;

        long pow = pow(n, m / 2);
        return (((pow * pow) % mod9) * (m % 2 == 0 ? 1 : n)) % mod9;
    }

    static long C(int a, int b) {
        return (((fac[a] * inv[b]) % mod9) * inv[a - b]) % mod9;
    }

    static void traverse(int i, int signal) {
        if (signal == 0) {
            size[i] = 1;
            for (Integer it : ar[i]) {
                traverse(it, 0);
                size[i] += size[it];
            }
        } else {
            sol[i] = 1;
            int l = size[i] - 1; // one place is occupied by the boss itself so we remove it.
            for (Integer it : ar[i]) {
                traverse(it, 1);
                sol[i] = (((sol[i] * sol[it]) % mod9) * C(l, size[it])) % mod9;
                l -= size[it];
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = 1000000007;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);
    static HashMap<Integer, ArrayList<Integer>> adl;

    public static void main(String[] args) {                    //threading has been used to increase the stack size.

        new Thread(null, null, "BlackRise", 1 << 25)
                //the last parameter is stack size which is desired,
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