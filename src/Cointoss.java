
import java.io.*;
import java.util.*;
import java.text.*;

public class Cointoss {
    static Calendar ts, te;                                                 //For time calculation
    static int MOD = (int) 1e9 + 7;

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

    //static HashMap<Integer, ArrayList<Pair>> adl;

    //
    ///////Main function starts here... threading has been used to increase the stack size in case we use recursion which involves higher stack size...////////
    //

    public static void main(String[] args) {
        new Thread(null, null, "BlackRise", Integer.MAX_VALUE - 1)          //the last parameter is stack size which is desired, it is safe to chose Max value for all question.
        {
            public void run() {
                Blackrise();
            }
        }.start();
    }

    static void Blackrise() {                                                           //The name Blackrise is my pen name... you can change the name according to your wish........
        InputReader input = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);
        ts = Calendar.getInstance();
        ts.setTime(new Date());
        int t = input.nextInt();
        while (t-- > 0) {
            long trib[][] = new long[][]{ {0,0,1}, {1,0,1}, {0,1,1} };
            pw.println(power(trib,input.nextLong())%MOD);
        }

        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }

    static long power(long trib[][],long n)
    {
//        if(n==0 || n==1 || n==2)return 0;
//        if(n==3) return 7;
//        if(n==4) return 13;
        long tribo[][] = new long[][]{ {0,0,1}, {1,0,1}, {0,1,1} };
        while(n>0)
        {
            if(n%2==0)
            {
                matexp(trib,trib);n/=2;
            }
            else
            {
                matexp(tribo,trib);n--;
            }
        }
        return tribo[2][2];
    }

    static void matexp(long F[][], long M[][])
    {
        long a =( (F[0][0]%MOD)*(M[0][0]%MOD) + (F[0][1]%MOD)*(M[1][0]%MOD) + (F[0][2]%MOD)*(M[2][0]%MOD) ) % MOD;
        long d =( (F[1][0]%MOD)*(M[0][0]%MOD) + (F[1][1]%MOD)*(M[1][0]%MOD) + (F[1][2]%MOD)*(M[2][0]%MOD) ) % MOD;
        long g =( (F[2][0]%MOD)*(M[0][0]%MOD) + (F[2][1]%MOD)*(M[1][0]%MOD) + (F[2][2]%MOD)*(M[2][0]%MOD) ) % MOD;

        long b =( (F[0][0]%MOD)*(M[0][1]%MOD) + (F[0][1]%MOD)*(M[1][1]%MOD) + (F[0][2]%MOD)*(M[2][1]%MOD) ) % MOD;
        long e =( (F[1][0]%MOD)*(M[0][1]%MOD) + (F[1][1]%MOD)*(M[1][1]%MOD) + (F[1][2]%MOD)*(M[2][1]%MOD) ) % MOD;
        long h =( (F[2][0]%MOD)*(M[0][1]%MOD) + (F[2][1]%MOD)*(M[1][1]%MOD) + (F[2][2]%MOD)*(M[2][1]%MOD) ) % MOD;

        long c =( (F[0][0]%MOD)*(M[0][2]%MOD) + (F[0][1]%MOD)*(M[1][2]%MOD) + (F[0][2]%MOD)*(M[2][2]%MOD) ) % MOD;
        long f =( (F[1][0]%MOD)*(M[0][2]%MOD) + (F[1][1]%MOD)*(M[1][2]%MOD) + (F[1][2]%MOD)*(M[2][2]%MOD) ) % MOD;
        long i =( (F[2][0]%MOD)*(M[0][2]%MOD) + (F[2][1]%MOD)*(M[1][2]%MOD) + (F[2][2]%MOD)*(M[2][2]%MOD) ) % MOD;

        F[0][0] = a;
        F[0][1] = b;
        F[0][2] = c;

        F[1][0] = d;
        F[1][1] = e;
        F[1][2] = f;

        F[2][0] = g;
        F[2][1] = h;
        F[2][2] = i;

    }
}