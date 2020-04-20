import java.io.*;
import java.util.*;
public class BinaryIndexedTree {
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]) throws IOException {
        int n = input.nextInt();
        int BIT[] = new int[n + 1];
        for (int i = 0; i < n; i++)
            ConstructBinary(BIT, n + 1, i + 1);
        for (int i = 0; i < n; i++) System.out.print(BIT[i + 1] + " ");
        getTheSum(BIT, n + 1, 3);
        System.out.println();
    }

    static void ConstructBinary(int BIT[], int n, int x) {
        int a = input.nextInt();
        while (x < n) {
            BIT[x] += a;
            x += x & (-x);
        }
    }

    static void getTheSum(int BIT[], int n, int x) {
        int s = 0;
        while (x > 0) {
            s += BIT[x];
            x -= x & (-x);
        }
        System.out.println(s);
    }
}
