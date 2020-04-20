import java.io.*;
import java.util.*;
public class ChangeTheSign {
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
            int n = input.nextInt();
            ArrayList<Integer> ar = new ArrayList<>();
            int s = 0;
            for (int i = 0; i < n; i++) {
                int x = input.nextInt();
                ar.add(x);

            }
            long sl = 0, sr = 0;
            int term = 0;
            for (int i = n-1; i >= 0; i--) {
                term=0;
                if (ar.get(i) > 0) {
                    sr = -ar.get(i);
                    for (int j = i + 1; j < n; j++) {
                        sr += ar.get(j);
                        if (sr <= 0) {
                            term = 1;break;
                            //pw.println("$"+sr+"   "+ar.get(i));
                        }
                    }
                    sl = -ar.get(i);
                    for (int j = i - 1; j >= 0 ; j--) {
                        sl += ar.get(j);
                        if (sl <= 0) {
                            term = 1;break;
                            //pw.println("!"+sl+"   "+ar.get(i));
                        }
                    }
                    if (term != 1) ar.set(i, -ar.get(i));
                }
            }
            for (int i = 0; i < n; i++) pw.append(ar.get(i) + " ");
            pw.flush();
            pw.println();
        }
    }
}


