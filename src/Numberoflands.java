
import java.io.*;
import java.util.*;

public class Numberoflands {
    static Calendar ts, te;                                                 //For time calculation

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

    static HashMap<Integer, ArrayList<Integer>> adl;

//
///////Main function starts here... threading has been used to increase the stack size in case we use recursion which involves higher stack size...////////
//

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
        //adl=new HashMap<>();
        n = input.nextInt();
        m = input.nextInt();
        int ar[][] = new int[n + 2][m + 2];

        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < m + 1; j++)
                ar[i][j] = input.nextInt();

//        for(int i=0;i<n+2;i++) {
//            for (int j = 1; j < n + 1; j++)
//                pw.append(ar[i][j] + " ");
//            pw.flush();
//            pw.println();
//        }
        int vis[][] = new int[n + 2][m + 2];
        int c = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (ar[i][j] == 1 && vis[i][j] == 0) {
                    c++;
                    pw.println(i + " " + j);
                    countingland(ar, vis, i, j);
                }
            }
        }
        pw.println(c);
        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000000.00);
    }

    static int m, n;

    static void countingland(int ar[][], int vis[][], int i, int j) {
        vis[i][j] = 1;
        int p = i, q = j;

        if (ar[p][q] == 1 && vis[p][q] == 0)
            countingland(ar, vis, p, q);

        if (ar[p - 1][q] == 1 && vis[p - 1][q] == 0)
            countingland(ar, vis, p - 1, q);

        if (ar[p + 1][q] == 1 && vis[p + 1][q] == 0)
            countingland(ar, vis, p + 1, q);

        if (ar[p][q - 1] == 1 && vis[p][q - 1] == 0)
            countingland(ar, vis, p, q - 1);

        if (ar[p][q + 1] == 1 && vis[p][q + 1] == 0)
            countingland(ar, vis, p, q + 1);

        if (ar[p - 1][q - 1] == 1 && vis[p - 1][q - 1] == 0)
            countingland(ar, vis, p - 1, q - 1);

        if (ar[p + 1][q + 1] == 1 && vis[p + 1][q + 1] == 0)
            countingland(ar, vis, p + 1, q + 1);

        if (ar[p + 1][q - 1] == 1 && vis[p + 1][q - 1] == 0)
            countingland(ar, vis, p + 1, q - 1);

        if (ar[p - 1][q + 1] == 1 && vis[p - 1][q + 1] == 0)
            countingland(ar, vis, p - 1, q + 1);


    }
}


