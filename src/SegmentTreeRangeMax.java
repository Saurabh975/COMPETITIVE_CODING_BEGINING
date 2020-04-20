import java.io.*;
import java.util.*;
public class SegmentTreeRangeMax {
    public static void main(String[] args)
    {                                 //threading has been used to increase the stack size.
        new Thread(null, null, "BlackRise", (long) 1e9)  //the last parameter is stack size which is desired, it is safe to chose 10^9 for all ques.
        {
            public void run() {
                Blackrise();
            }
        }.start();
    }
     static void Blackrise()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ar[] = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();
        // take size of segment tree as (2* n + 1)
        int segment_tree[] = new int[4 * n];
        constructSegmentTree(ar, segment_tree, 0, n - 1, 0);
        while (true) {
            int x = sc.nextInt();
            switch (x) {
                case 1:
                    int index = sc.nextInt();
                    int val = sc.nextInt();
                    int diff = val - ar[index];
                    ar[index] = val;
                    constructSegmentTree(ar,segment_tree, 0, n - 1, 0);
                    break;
                case 2:
                    int query_start = sc.nextInt();
                    int query_end = sc.nextInt();
                    System.out.println(getMax(segment_tree,0,n-1,0,query_start,query_end));
                    break;
                case 3:
                    printtree(segment_tree);
                    break;
                case 4 :
                    System.exit(0);

            }
        }
    }

    static int constructSegmentTree(int ar[], int segtre[], int start, int end, int position) {
        if (start == end) {
            segtre[position] = ar[start];
            return ar[end];
        }
        int mid = (start + end) >> 1;

        segtre[position] = Integer.max(constructSegmentTree(ar, segtre, start, mid, position * 2 + 1), constructSegmentTree(ar, segtre, mid + 1, end, position * 2 + 2));
        return segtre[position];
    }

    static int getMax(int segtre[],int seg_start, int seg_end, int position, int querystart, int queryend)
    {
        if(position >= segtre.length || seg_end < querystart || seg_start > queryend)return Integer.MIN_VALUE;
        if(querystart>=seg_start && queryend<=seg_end)
            return segtre[position];

        int mid= (seg_end + seg_start) >> 1;
        return Integer.max(getMax(segtre,seg_start,mid,2*position+1,querystart,queryend),getMax(segtre,mid+1,seg_end,2*position +2,querystart,queryend));
    }

    static void printtree(int seg_tre[])
    {
        Arrays.stream(seg_tre).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}