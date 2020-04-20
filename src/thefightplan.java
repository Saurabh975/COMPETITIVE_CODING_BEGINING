
import java.io.*;
import java.util.*;

public class thefightplan {
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

    public static void main(String[] args) {
        new Thread(null, null, "New Thread", (long)1e9)          //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {
                newmain();
            }
        }.start();
    }

    static void newmain() {
        InputReader input = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);
        int n=input.nextInt();
        int m=input.nextInt();
        int t=input.nextInt();
        int c=input.nextInt();
        ArrayList<Integer> ar[]=new ArrayList[n];
        for(int i=0;i<n;i++)ar[i]=new ArrayList<>();
        for(int i=0;i<m;i++)
        {
            int x=input.nextInt()-1;
            int y=input.nextInt()-1;
            ar[x].add(y);
            ar[y].add(x);
        }int p=1;
        for(int i=0;i<n;i++)Collections.sort(ar[i]);
        Queue<Integer> qans=new LinkedList<>();
        int u=input.nextInt()-1;
        int v=input.nextInt()-1;
        Queue<Integer> q=new LinkedList<>();
        int count=0;
        int found=0;
        q.add(u);
        int vis[]=new int[m];
        while(!q.isEmpty())
        {
            int x=q.remove();
            if(x==v){
                qans.add(v+1);
                count++;
                break;}

            count++;
            vis[x]=1;
            for(int i=0;i<ar[x].size();i++)
            {
                int a=ar[x].get(i);
                //pw.append(a+" "+x+"\t");
                if(a!=v && vis[a]!=1)
                    q.add(a);
                else if(a==v)
                {
                    count++;found=1;
                    qans.add(x+1);qans.add(v+1);break;
                }
            }
            if(found==1)break;
            qans.add(x+1);
        }
        pw.flush();
        pw.println(count);
        while(!qans.isEmpty())pw.append(qans.remove()+" ");
        pw.flush();
        pw.println();
    }
}
