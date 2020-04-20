
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.text.*;

public class Vision {
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

        int t = input.nextInt();
        while (t-- > 0) {
            double px = input.nextDouble();
            double py = input.nextDouble();
            double pz = input.nextDouble();

            double qx = input.nextDouble();
            double qy = input.nextDouble();
            double qz = input.nextDouble();

            double dx = input.nextDouble();
            double dy = input.nextDouble();
            double dz = input.nextDouble();

            double cx = input.nextDouble();
            double cy = input.nextDouble();
            double cz = input.nextDouble();
            double r1 = input.nextDouble();
            double x,y,z;
            if(cx-px-dx ==0)
                x=0;
            else
                x = (qx * (cx - px) - dx * px) / (cx - px - dx);
            if(cy-py-dy ==0)
                y=0;
            else
                y = (qy * (cy - py) - dy * py) / (cy - py - dy);
            if(cz-pz-dz ==0)
                z=0;
            else
                z = (qz * (cz - pz) - dz * pz) / (cz - pz - dz);

            double d1 = Math.sqrt((px - cx) * (px - cx) + (py - cy) * (py - cy) + (pz - cz) * (pz - cz));
            double d2 = Math.sqrt((px - x) * (px - x) + (py - y) * (py - y) + (pz - z) * (pz - z));

            double r2 = r1 * d1 / d2;

            double d3 = Math.sqrt((qx - x) * (qx - x) + (qy - y) * (qy - y) + (qz - z) * (qz - z));

            double dreq = r2 - d3;

            double time = dreq / (Math.sqrt(dx * dx + dy * dy + dz * dz));

            pw.println(x +" "+y+" "+z+" "+time);
        }

        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000.00);
    }
}