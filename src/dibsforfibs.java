import java.io.*;
import java.util.*;
public class dibsforfibs {
    static int mod=(int)1e9+7;
    //Reader class for fast input
    //---------------------------------------------------------//
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    //---------------------Reader class Ends---------------------------//
    //-------------------Main Function Starts--------------------------//
    public static void main(String args[]) throws IOException {
        Reader input = new Reader();
        PrintWriter pw = new PrintWriter(System.out, true);
        int t = input.nextInt();
        while (t-- > 0) {
            long s1=0,s2=0,fib=0;
            int m = input.nextInt();
            int n = input.nextInt();
            for(int i=0;i<m;i++)s1=s1+(input.nextLong()*m)%mod;
            for(int i=0;i<m;i++)s2=s2+(input.nextLong()*m)%mod;

             if(n==1)pw.println(s1%mod);
             else if(n==2)pw.println(s2%mod);
             else pw.println(fibonacci(s1,s2,m,n)%mod);

        }
    }
    static long fibonacci(long s1,long s2,int m,int n)
    {
        long a=s1;long b=s2;
        for(int i=2;i<n;i++)
        {
            long t=a;
            a=b;
            b=(t+a)%mod;
        }
        return b%mod;
    }
}