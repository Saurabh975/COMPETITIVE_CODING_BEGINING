
// Author @ BlackRise :) //

import java.io.*;
import java.util.*;

public class chefAndSurpriseChessboard {
    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);

    static int dp0[][][] = new int[205][205][205];
    static int dp1[][][] = new int[205][205][205];

//    static long iter=0;

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

        int n = ni();
        int m = ni();
        String ar[] = new String[n];
        for (int i = 0; i < n; i++) ar[i] = ns().trim();
        int zero[][] = new int[n][m];
        int one[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (((i + j) & 1) == 0 && ar[i].charAt(j) == '1')
                    zero[i][j]++;
                else if (((i + j) & 1) == 1 && ar[i].charAt(j) == '0') zero[i][j]++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (((i + j) & 1) == 0 && ar[i].charAt(j) == '0')
                    one[i][j]++;
                else if (((i + j) & 1) == 1 && ar[i].charAt(j) == '1') one[i][j]++;
            }
        }


        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);//map.put(min,1);
        int min=Math.min(m,n);
        map.put(min*min,min);
        //pl(map);
        //int min;
        for (int i = 0; i <=n; i++) {
            for (int j = 0; j <=m; j++) {
                for (int k = 1; k <= 204; k++) {
                    if (i + k - 1 >= n || j + k - 1 >= m) continue;
                    int onekakittajornahai = 0, zerokakitnajornahai = 0;
                    for (int a = 0; a < k; a++) {
                        onekakittajornahai += one[i + a][j + k - 1] + one[i + k - 1][j + a];
                        zerokakitnajornahai += zero[i + a][j + k - 1] + zero[i + k - 1][j + a];
                    }
                    onekakittajornahai -= one[i + k - 1][j + k - 1];
                    zerokakitnajornahai -= zero[i + k - 1][j + k - 1];

                    onekakittajornahai += dp1[k - 1][i][j];
                    zerokakitnajornahai += dp0[k - 1][i][j];

                    dp1[k][i][j] = onekakittajornahai;
                    dp0[k][i][j] = zerokakitnajornahai;

                    min = Math.min(onekakittajornahai, zerokakitnajornahai);

                    if (map.containsKey(min)) {
                        if (map.get(min) < k) map.put(min, k);
                    } else {
                        if (map.get(map.floorKey(min)) < k) map.put(min, k);
                        if(map.get(map.ceilingKey(min+1))<=k)map.remove(map.ceilingKey(min+1));
                    }
                }
            }
        }

        int q = ni();
        //pl(map);
        while (q-- > 0)
            pl(map.get(map.floorKey(ni())));


        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
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

    static void tsc()                                          //calculates the starting time of execution
    {
        ts = Calendar.getInstance();
        ts.setTime(new Date());
    }

    static void tec()                                          //calculates the ending time of execution
    {
        te = Calendar.getInstance();
        te.setTime(new Date());
    }

    static void pwt()                                          //prints the time taken for execution
    {
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }
}