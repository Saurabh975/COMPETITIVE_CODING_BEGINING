
import java.io.*;
import java.util.*;

public class happyvertices {
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

    static HashMap<Integer, ArrayList<Integer>> adl;

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
        adl = new HashMap<>();
        for (int i = 0; i < n; i++) adl.put(i, new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int x = input.nextInt() - 1;
            int y = input.nextInt() - 1;
            adl.get(x).add(y);
            adl.get(y).add(x);
        }
        int vis[] = new int[n];
        int children[] = new int[n];
        int parent[] = new int[n];

        for(int i=0; i<n; i++){
            if(vis[i]==1)
                continue;

            Queue<Integer> queueNode = new LinkedList<>();
            vis[i] = 1;
            parent[i] = i;
            queueNode.add(i);

            while(queueNode.peek()!=null){
                int head = (Integer)queueNode.remove(); count = 0;
                Iterator itr = adl.get(head).iterator();
                while(itr.hasNext()){
                    int child = (Integer)itr.next();
                    if(vis[child]==0){
                        count++;
                        vis[child] = 1;
                        parent[child] = head;
                        queueNode.add(child);
                    }
                }
                children[head] = count;
            }

        }
        count=0;
        for (int i = 0; i < n; i++)
            if (children[i] > children[parent[i]]) count++;

        pw.println(count);
        te = Calendar.getInstance();
        te.setTime(new Date());
        pw.printf("\nExecution time was :- %f s\n", (te.getTimeInMillis() - ts.getTimeInMillis()) / 1000000.00);

    }

    static int count = 0;

//    static void dfs(int x, int vis[]) {
//
//        vis[x] = 1;
//        if (adl.get(x).size() > 1) count++;
//        Iterator<Integer> i = adl.get(x).iterator();
//        while (i.hasNext()) {
//            int a = i.next();
//            if (vis[a] == 0) dfs(a, vis);
//        }
//    }
}

