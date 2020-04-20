
    import java.io.*;
    import java.util.*;
    public class KruskalsMST {
        static Calendar ts, te;

        static class InputReader {
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

        public static void main(String[] args) {
            new Thread(null, null, "BlackRise", (long) 1e9)          //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
            {
                public void run() {
                    Blackrise();
                }
            }.start();
        }

        static void Blackrise() {
            InputReader input = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out, true);
            ts = Calendar.getInstance();
            ts.setTime(new Date());
            int n = input.nextInt();
            int m = input.nextInt();
            PriorityQueue<Vector> pq = new PriorityQueue<>();
            ArrayList<Vector> ar = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int v = input.nextInt(), u = input.nextInt(), d = input.nextInt();
                Vector vec = new Vector(d, v, u);

                pq.add(vec);
            }
            pw.println(pq);
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) arr[i] = i;

            int count = 0;

            while (!pq.isEmpty() && count < n)                             // A method called DSU path compression method has been used here....the complexity is log *n not log n...
            {
                Vector v = pq.remove();                                     //refer to https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/ to know about the method.
                //pw.println(pq);
                int b = root(v.y, arr);
                int c = root(v.z, arr);

                if (b != c) {
                    ar.add(v);
                    count++;
                    arr[b] = arr[c];
                }
            }
            pw.println(ar);
            te = Calendar.getInstance();
            te.setTime(new Date());
            pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000000.00);

        }

        static class Vector implements Comparable<Vector>     //defining vector class according to need...this stores three values... i.e. distance,source node and destination node.
        {
            int x, y, z;

            Vector(int x, int y, int z)  //constructor
            {
                this.x = x;
                this.y = y;
                this.z = z;
            }

            public int compareTo(Vector other) {
                if (this.x != other.x)
                    return this.x - other.x;
                if (this.y != other.y)
                    return this.y - other.y;
                return this.z - other.z;
            }

            public String toString() {
                return "(" + x + "," + y + "," + z + ")";
            }
        }

        static int root(int x, int arr[]) {
            while (arr[x] != x)
                x = arr[x];
            return x;
        }
    }
