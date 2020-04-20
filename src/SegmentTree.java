import java.io.*;
import java.util.*;
public class SegmentTree {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ar[] = new int[n];

        int seg[] = new int[2*n +1];
        for (int i = 0; i < n; i++) ar[i] = sc.nextInt();
        ConstructSegmentTree(seg, ar, n, 0, n - 1, 0);
        for (int i = 0; i < 2* n + 1; i++) System.out.print(seg[i] + " ");
        System.out.println();
    }

    static int ConstructSegmentTree(int seg[], int ar[], int n, int front, int rear, int pos) {

        if (front == rear) {
            seg[pos] = ar[front];
            return ar[rear];
        }

        int mid = (front + rear) >> 1;

        seg[pos] = (ConstructSegmentTree(seg, ar, n, front, mid, pos * 2 + 1) + ConstructSegmentTree(seg, ar, n, mid + 1, rear, pos * 2 + 2)); // sum of all elements
        return seg[pos];

    }
}