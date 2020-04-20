
// Author @ BlackRise :) //

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class minValRectangle {
    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);

    public static void main(String[] args) {                                 //threading has been used to increase the stack size.
        new Thread(null, null, "BlackRise", (long) 1e9)  //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {
                Blackrise();
            }
        }.start();
    }

    static void Blackrise() {                                                //The name Blackrise is my pen name... you can change the name according to your wish

        tsc();                                                               //calculates the starting time of execution

        int t=ni();
        while(t-->0) {
            int n = ni();
            HashMap<Integer,Integer> m=new HashMap<>();
            PriorityQueue<Integer> pq=new PriorityQueue<>();
            PriorityQueue<three> pq1 = new PriorityQueue<>();

            for(int i=0;i<n;i++)
            {
                int x=ni();
                if(m.containsKey(x))m.put(x,m.get(x)+1);
                else m.put(x,1);
            } int flag=0;
            for (Map.Entry<Integer, Integer> i : m.entrySet())
            {
                //p(i);
                int x=i.getKey();
                int y=i.getValue();
                if(y>=4){p(x+" "+x+" "+x+" "+x);flag=1;break;}

                else if(y>=2) pq.add(x);
            }
            //pl(pq);
            if(flag==0) {
                int l = pq.size();
                //pl(pq);
                for (int i = 0; i < l - 1; i++) {
                    int x1 = pq.peek();
                    pq.poll();
                    int x2 = pq.peek();
                    pq1.add(new three(((1.0*x1/(1.0*x2)) + (1.0*x2/(1.0*x1))), x1, x2));
                }
                 //pl(pq1);
                p(pq1.peek().y + " " + pq1.peek().y + " " + pq1.peek().z + " " + pq1.peek().z);
            }
            pl();
            pq.clear();
            pq1.clear();
        }
        tec();                  //calculates the ending time of execution
        pwt();                  //prints the time taken to execute the program
    }



    static class three implements Comparable<three>
    {
        int y,z; double x;
        three(double x,int y,int z)  //constructor
        {
            this.x=x;
            this.y=y;
            this.z=z;
        }
        public int compareTo(three other)   //method for comparing pairs, useful in sorting and...
        {
            if(this.x>other.x)   //first x is preffered, the pair with higher x is greater
                return 1;
            else return -1;  //if x is same, the one with higher y is greater
        }
        public String toString()  //makes printing of pairs simple
        {
            return "("+x+","+y+","+z+")";
        }


    }


///////////////////////////////////////////////////////////////////////////////////////////////////


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

