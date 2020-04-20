// Author @ BlackRise :) //

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class realWorldProblem {
    static Calendar ts, te;                                                  //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out, true);
    static HashMap<Integer, ArrayList<Integer>> adl;
    static long ar[]=new long[1000006+5];
    static long paid[]=new long[1000006+5];
    static ArrayList<customer> arr=new ArrayList<>();
    static Queue<customer> pos=new LinkedList<>();
    static Queue<customer> neg=new LinkedList<>();
    static ArrayList<String> ans = new ArrayList<>();

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

        int n=ni();
        int k=ni();
        for(int i=0;i<k;i++)
        {
            int l=ni();
            int r=ni();
            long p=ni();
            p/=(r-l+1);
            ar[l]+=p;ar[r+1]-=p;
        }
        for(int i=2;i<=n+1;i++)
            ar[i]=ar[i]+ar[i-1];


        for(int i=1;i<=n;i++)
        {   paid[i]=nl();
            arr.add(new customer(i,paid[i],ar[i]));
        }


        for(customer i: arr)
        {
            if(i.diff==0)continue;
            else if(i.diff>0)pos.add(i);
            else neg.add(i);
        }

        if(pos.isEmpty()){pl(0);System.exit(0);}

        long c=0;
        while(!pos.isEmpty())
        {
            customer i= pos.poll();
            customer j=neg.poll();
            //if(i.diff>=-j.diff)
            //c++;
            if(i.diff+j.diff==0){ans.add(i.i+" "+j.i+" "+(-j.diff));}
            else if(i.diff> -j.diff){
                ans.add(i.i+" "+j.i+" "+(-j.diff));
                i.diff+=j.diff;
                ((LinkedList<customer>) pos).addLast(i);
            }
            else
            {
                ans.add(i.i+" "+j.i+" "+(i.diff));
                j.diff+=i.diff;
                ((LinkedList<customer>) neg).addLast(j);
            }

        }

        pl(ans.size());
        for(String i: ans) pl(i);


        tec();                  //calculates the ending time of execution
        pwt();                //prints the time taken to execute the program
    }

    static class customer
    {
        long i,dediya,denahai,diff;
        customer(long i, long dediya, long denahai)
        {
            this.i=i;
            this.dediya=dediya;
            this.denahai=denahai;
            diff=this.denahai - this.dediya;
        }
        public String toString()
        {
            return "("+i+":"+diff+")";
        }
    }
    static class three
    {
        long x,y,z;
        three(long x, long y, long z)
        {
            this.x=x;
            this.y=y;
            this.z=z;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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