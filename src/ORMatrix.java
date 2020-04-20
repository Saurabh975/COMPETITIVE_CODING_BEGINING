
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class ORMatrix {
    static Calendar ts, te;                                                 //For time calculation
    static int mod9 = (int) 1e9 + 7;
    static InputReader input = new InputReader(System.in);
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

    static int ni()
    {
        return input.nextInt();
    }
    static long nl()
    {
        return input.nextLong();
    }
    static double nd()
    {
        return input.nextDouble();
    }
    static String ns()
    {
        return input.readString();
    }



    ///////Main function starts here... threading has been used to increase the stack size in case we use recursion which involves higher stack size...////////


    public static void main(String[] args) {
        new Thread(null, null, "BlackRise", (long) 1e5)          //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {
                Blackrise();
            }
        }.start();
    }

    static void Blackrise() {                                                       //The name Blackrise is my pen name... you can change the name according to your wish........

        PrintWriter pw = new PrintWriter(System.out, true);
        ts = Calendar.getInstance();
        ts.setTime(new Date());

        int t=ni();
        while(t-->0) {
            int n = ni();
            int m = ni();
            HashMap<Integer,Integer> row_m=new HashMap<>();
            HashMap<Integer,Integer> col_m=new HashMap<>();int flag=0;
            int ar[][]=new int[n+2][m+2];
            for(int i=0;i<n;i++)row_m.put(i,-1);
            for(int i=0;i<m;i++)col_m.put(i,-1);
            for(int i=0;i<n;i++)
            {
                char ch[]=ns().trim().toCharArray();
                for(int j=0;j<m;j++)
                {
                    ar[i][j]=ch[j]-48;
                    if(ch[j]=='1'){ flag=1;
                        row_m.put(i,1);col_m.put(j,1);
                    }
                }
            }
            if(flag==0){
                for(int i=0;i<n;i++) {
                    for (int j = 0; j < m; j++)

                        pw.print(-1 + " ");
                    pw.println();
                }
                continue;
            }
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(ar[i][j]==1){pw.print(0+" ");}
                    else if(row_m.get(i)==1 || col_m.get(j)==1)   pw.print(1+" ");
                    else pw.print(2+" ");
                }
                pw.println();
            }

        }


        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }

}
