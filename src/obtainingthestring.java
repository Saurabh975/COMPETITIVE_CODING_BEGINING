
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class obtainingthestring {
    static Calendar ts, te;                                                 //For time calculation
    static int mod9 = (int) 1e9 + 7;

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
        new Thread(null, null, "BlackRise", (long) 1e5)          //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
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

        int n=input.nextInt();
        char ch1[]=input.readString().trim().toCharArray();
        char ch2[]=input.readString().trim().toCharArray();
        int c=0;
        ArrayList<Integer> ar=new ArrayList<>();
        char ch1t[]=ch1.clone();
        char ch2t[]=ch2.clone();
        Arrays.sort(ch1t);Arrays.sort(ch2t);
        int flag=0;
        for(int i=0;i<n;i++)if(ch1t[i]!=ch2t[i])flag=1;
        if(flag ==1) {pw.println(-1);System.exit(0);}
        for(int i=0;i<n-1;i++)
        {
            if(ch1[i]!=ch2[i])
            {
                int j=i+1;
                for(;j<n;j++)if(ch1[j]==ch2[i])break;
                c+=2*(j-i)-1;
                for(int k=i+1;k<=j;k++)ar.add(k);
                for(int k=j-1;k>=i+1;k--)ar.add(k);
                char temp=ch1[i];
                ch1[i]=ch1[j];
                ch1[j]=temp;
            }
        }
        if(c<=10000){
            pw.println(c);
            Iterator it=ar.iterator();
            while(it.hasNext())pw.print(it.next()+" ");
            pw.println();
        }
        else pw.println(-1);
        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }
}
