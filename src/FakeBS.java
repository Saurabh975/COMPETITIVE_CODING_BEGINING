import java.io.*;
import java.util.*;
public class FakeBS {
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
            int q = input.nextInt();
            int ar[] = new int[n];
            int dup[]=new int[n];
            HashMap<Integer,Integer> hmp=new HashMap<>();
            for(int i=0;i<n;i++)
            {
                int x=input.nextInt();
                ar[i]=x;
                dup[i]=x;
                hmp.put(x,i);
            }
            Arrays.sort(ar);
            while(q-->0)
            {
                int c=0;
                int x=input.nextInt();
                int ind=hmp.get(x);
                int si=Arrays.binarySearch(ar,x);
                int cl=0;
                int cr=0;
                int pl=si;
                int pr=n-1-si;
                int front=0,rear=n-1;
                while(front<=rear)
                {
                    int mid=(front+rear)/2;
                    if(mid==ind)break;
                    else if(mid < ind)
                    {
                        if (dup[mid] > dup[ind])
                        {
                            cl++;
                            front=mid+1;pl--;
                        }
                        else {front=mid+1; pl--;}
                    }
                    else if(mid > ind)
                    {
                        if(dup[mid]<dup[ind])
                        {
                            cr++;
                            rear=mid-1;pr--;
                        }
                        else {rear=mid - 1; pr--;}
                    }
                }
                int s=(int)Math.max(cl,cr);
                if(cl>cr && pl>=0)c=s;
                else if(cr>cl && pr>=0)c=s;
                else if(cl==cr && pl>=0 && pr>=0)c=s;
                else c=-1;
                pw.println(c);
            }
        }
    }
}
