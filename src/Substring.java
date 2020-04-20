
import java.io.*;
import java.util.*;
import java.text.*;

public class Substring {
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

        int t=input.nextInt();
        while(t-->0) {

            String st=input.readString();
            int k=input.nextInt();
            int l=st.length();
            int ans=0;
            for(int i=0;i<l-k+1;i++)
            {
                for(int j=i+k;j<=l;j++)
                {
                    //pw.println(st.substring(i,j));
                    char ch[]=st.substring(i,j).toCharArray();
                    int l1=ch.length;
                    Arrays.sort(ch);
                    int dis=1;
                    for(int x=1;x<l1;x++)if(ch[x]!=ch[x-1])dis++;
                    int flag=0;
                    int sub=l1/dis;
                    if(sub*dis != l1)flag=1;
                    //pw.println(Arrays.toString(ch)+" "+dis+ " "+ sub+" ");

                    if(dis<k)flag=1;
                    int p=0;
                    while(p<l1-1 && flag==0)
                    {
                        //pw.println(ch[p]+" "+ch[p+sub-1]);
                        if(ch[p]==ch[p+sub-1]){p+=sub+1;continue;}
                        else flag=1;
                    }
                    if(flag==0)ans++;
                    //pw.println(flag);
                }
            }
            pw.println(ans);
        }

        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000000.00);
    }

}