
// Author @ BlackRise :) //

import java.io.*;
import java.util.*;

public class productArray {
    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);


    public static void main(String[] args) {                                 //threading has been used to increase the stack size.

        new Thread(null, null, "BlackRise", (long) 1e9)  //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {

                try {
                    Blackrise();
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
        int ar[] = new int[n + 1];
        Three ans[] = new Three[n - 1];
        for(int i=0;i<n-1;i++)ans[i]=new Three();

        ArrayList<Integer> neg = new ArrayList<>();
        ArrayList<Integer> zero = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            ar[i] = ni();
            if (ar[i] < 0) neg.add(i);
            else if (ar[i] == 0) zero.add(i);
            else pos.add(i);
        }

        if (neg.size() % 2 == 0) {
                pos.addAll(neg);
            neg = new ArrayList<>();
        }

        else {
            int remneg = -1;
            int max = Integer.MIN_VALUE;
            for (int i : neg)
                if (ar[i] > max) {
                    max = ar[i];
                    remneg = i;
                }
            for (int i : neg)
                if (i != remneg) pos.add(i);
            neg = new ArrayList<>();
            neg.add(remneg);
        }

        int index = 0;
        if (zero.size() == 0 && neg.size() == 0) {
            int l = pos.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = pos.get(i);
                ans[index].z = pos.get(i + 1);
                index++;
            }
        }

        else if (neg.size() == 1 && zero.size() == 0) {
            ans[index].x = 2;
            ans[index].y = neg.get(0);
            index++;
            int l = pos.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = pos.get(i);
                ans[index].z = pos.get(i + 1);
                index++;
            }
        }

        else if (zero.size() > 0 && neg.size() == 0) {
            int l = zero.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = zero.get(i);
                ans[index].z = zero.get(i + 1);
                index++;
            }

            if (index < n - 1) {
                ans[index].x = 2;
                ans[index].y = zero.get(l - 1);
                index++;
            }

            l = pos.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = pos.get(i);
                ans[index].z = pos.get(i + 1);
                index++;
            }
        }

        else {
            zero.add(neg.get(0));
            int l = zero.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = zero.get(i);
                ans[index].z = zero.get(i + 1);
                index++;
            }

            if (index < n - 1) {
                ans[index].x = 2;
                ans[index].y = zero.get(l - 1);
                index++;
            }

            l = pos.size();
            for (int i = 0; i < l - 1; i++) {
                ans[index].x = 1;
                ans[index].y = pos.get(i);
                ans[index].z = pos.get(i + 1);
                index++;
            }
        }

        for (int i = 0; i < n - 1; i++) ans[i].print();
        pl();

        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
    }

    static class Three {
        int x, y, z;

        Three() {
            x = 0;
            y = 0;
            z = 0;
        }

        public void print() {
            if (x == 1)
                pl(x + " " + y + " " + z);
            else pl(x + " " + y);
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

