
import java.io.*;
import java.util.*;

public class changeTheSign2 {
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

    //static HashMap<Integer, ArrayList<Integer>> adl;

    //
    ///////Main unction starts here... threading has been used t increase the stack size in case we use recursion which involnes a great values of numbers to be calculated/////////
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

        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();

            long arr[] = new long[n + 2];
            arr[0] = (long) Math.pow(10, 9) + 1;
            arr[n + 1] = (long) Math.pow(10, 9) + 1;
            int sign[] = new int[n + 1];

            for (int i = 1; i <= n; i++)
                arr[i] = input.nextLong();

            ArrayList<Integer> possiblePositionsList = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (arr[i - 1] > arr[i] && arr[i] < arr[i + 1])
                    possiblePositionsList.add(i);
            }

            int sz = possiblePositionsList.size();
            if (sz > 0) {
                int possiblePositions[] = new int[sz], index = 0;

                for (Integer a : possiblePositionsList)
                    possiblePositions[index++] = a;


                int q[] = new int[sz], top = 0;
                q[0] = possiblePositions[0];

                for (int i = 1; i < sz; i++) {
                    if (possiblePositions[i] - possiblePositions[i - 1] == 2) {
                        if (arr[possiblePositions[i]] * -1 + arr[possiblePositions[i] - 1] + arr[possiblePositions[i - 1]] * -1 > 0) {
                            empty(arr, q, top, sign);
                            top = -1;
                        }
                    } else {
                        empty(arr, q, top, sign);
                        top = -1;
                    }
                    q[++top] = possiblePositions[i];
                }
                empty(arr, q, top, sign);
            }

            for (int i = 1; i <= n; i++) {
                if (sign[i] == -1)
                    pw.print("-");
                pw.print(+arr[i] + " ");
            }
            pw.println();
        }
        pw.flush();
        te=Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000000.00);

    }

    public static void empty(long arr[], int q[], int top, int sign[]) {
        //q is an array containing a sequence of alternating positions
        long k[] = new long[top + 2];
        k[top] = arr[q[top]];
        k[top + 1] = 0;
        for (int i = top - 1; i >= 0; i--)
            k[i] = (k[i + 1] > k[i + 2] + arr[q[i]]) ? k[i + 1] : (k[i + 2] + arr[q[i]]);
        int i = 0;
        while (i <= top) {
            if (k[i] == k[i + 1])//i was not included in final sum
                i++;                                     
            else {
                sign[q[i]] = -1;
                i += 2;
            }
        }
    }
}









