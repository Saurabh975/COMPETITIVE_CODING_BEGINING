
// Author @ BlackRise :) //

import java.io.*;
import java.util.*;

public class chefAndOperations {
    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);

//    static long iter=0;

    public static void main(String[] args) {                                 //threading has been used to increase the stack size.

        new Thread(null, null, "BlackRise", 1 << 25)  //the last parameter is stack size which is desired,
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

        int t=ni();
        while(t-->0)
        {
            int n=ni();
            long ar1[]=new long[n+1];
            long ar2[]=new long[n+1];
            for(int i=0;i<n;i++)ar1[i]=ni();
            for(int j=0;j<n;j++)ar2[j]=ni()-ar1[j];
            int flag=0;
            for(int i=0;i<n-2 && flag==0;i++)
            {
                long a=ar2[i],b=ar2[i+1]-2*a,c=ar2[i+2]-3*a;
                if(b<0 || c<0)flag=1;
                else if(c==0 && b!=0)flag=1;

                ar2[i+1]=b;ar2[i+2]=c;
            }
            if(flag==0 && ar2[n-2]==0 && ar2[n-1]==0)pl("TAK");
            else pl("NIE");
        }

        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
    }
    static boolean isPrime(int n)
    {
        //boolean flag=false;
        for(int i=2;i*i<=n;i++)
            if(n%i==0)return false;
        return true;
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