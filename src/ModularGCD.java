
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class ModularGCD {
    static Calendar ts, te;                                                 //For time calculation
    static long mod9 = (long) 1e9+7;

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


    ///////Main function starts here... threading has been used to increase the stack size in case we use recursion which involves higher stack size...////////


    public static void main(String[] args) {
        new Thread(null, null, "BlackRise", (long) 1e9)          //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {
                Blackrise();
            }
        }.start();
    }

    static void Blackrise() {                                                       //The name Blackrise is my pen name... you can change the name according to your wish........
        InputReader input = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);
        ts = Calendar.getInstance();
        ts.setTime(new Date());

        int t=input.nextInt();
        while(t-->0)
        {
            long a=input.nextLong();
            long b=input.nextLong();
            long n=input.nextLong();
            if(a-b==0){pw.println((long)(power(a,n,mod9)+power(b,n,mod9))%mod9);continue;};

            long anbn=(power(a,n,a-b)+power(b,n,a-b))%(a-b);

            pw.println(gcd(a-b,anbn)%1000000007);
        }

        te = Calendar.getInstance();
        te.setTime(new Date());
        //pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }
    static long power(long x, long y, long m)
    {
        if (y == 0)
            return 1;

        long p = power(x, y / 2, m) % m;
        p = mulmod(p,p,m)%m;

        if (y % 2 == 0)
            return p;
        else
            return mulmod(x,p,m)%m;
    }
    static long gcd(long a, long b)
    {
        // Everything divides 0
        if (a == 0 )
            return b;
        if(b==0)return a;

            // base case
        else if (a == b)
            return a;

            // a is greater else b is greater
        else if (a > b)
            return gcd(a%b,b);
        else
            return gcd(a, b%a);
    }

    static long mulmod(long a, long b, long mod)
    {
        long res = 0; // Initialize result
        a = a % mod;
        while (b > 0)
        {
            // If b is odd, add 'a' to result
            if (b % 2 == 1)
                res = (res + a) % mod;

            // Multiply 'a' with 2
            a = (a * 2) % mod;

            // Divide b by 2
            b /= 2;
        }

        // Return result
        return res % mod;
    }


}
