import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.text.*;

public class RhezoandPrime {

    static Calendar ts, te;                                                 //For time calculation
    static int mod9 = 1000000007;

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
        new Thread(null, null, "BlackRise", (int)1e9 +7)          //the last parameter is stack size which is desired, it is safe to chose Max value for all question.
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
        int prime[]=new int[5010];
        prime[0]=0;prime[1]=1;prime[2]=2;prime[3]=3;prime[4]=3;prime[5]=5;prime[6]=5;prime[7]=7;prime[8]=7;
        for(int i=9;i<5010;i++) {
            int x = nextSmallestPrime(i, prime);
            if(x==0)prime[i]=i;
            else prime[i]=prime[i-1];
        }
        int n=input.nextInt();
        if(n==1){pw.println(0); System.exit(0);}
        int ar[]=new int[n];
        ar[0]=input.nextInt();

        for(int i=1;i<n;i++)
            ar[i]=input.nextInt()+ar[i-1];

        pw.println(maxprimesum(ar,prime,0,n));

        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }

    static int nextSmallestPrime(int n,int prime[])
    {
        int flag=0;int i;
        for( i=2;i*i<=n && flag==0;i++)
            if(n%i==0)flag=1;
        return flag;
    }

    static long maxprimesum(int ar[], int prime[], int i, int n)
    {

        if(i>=n)return 0;
        int x=prime[n-i];
        long sum=0;
        if(i!=0)
         sum=ar[i+x-1]-ar[i-1];
        else if(i==0)
            sum=ar[i+x-1];
       // else
         //   sum=ar[n-1]-ar[n-2];
       // System.out.println(sum +" "+ x + " "+ ar[i+x-1]);
        return Math.max(sum,maxprimesum(ar,prime,i+1,n));
    }
} 