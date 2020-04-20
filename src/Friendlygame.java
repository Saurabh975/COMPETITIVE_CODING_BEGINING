import java.io.*;
import java.util.*;
public class Friendlygame {
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
            int k = input.nextInt();
            ArrayList<Integer> ar1 = new ArrayList<>();
            ArrayList<Integer> ar2 = new ArrayList<>();
            int s1=0,s2=0;
            for(int i=0;i<n;i++)
            {
                int x=input.nextInt();
                if(i%2==0){
                    ar1.add(x);
                    s1+=x;
                }
                else {
                    ar2.add(x);
                    s2+=x;
                }
            }
            Collections.sort(ar1);
            Collections.sort(ar2);
            int i=ar1.size()-1,j=0;
            while(s1>=s2 && k!=0)
            {
                if(ar1.get(i)>ar2.get(j)){
                    s2=s2-ar2.get(j)+ar1.get(i);
                    s1=s1-ar1.get(i)+ar2.get(j);
                    i--;j++;k--;
                }
                if(ar1.get(i)<=ar2.get(j))break;

            }
            if(s2<=s1)pw.println("NO");
            else pw.println("YES");

        }

    }
}
