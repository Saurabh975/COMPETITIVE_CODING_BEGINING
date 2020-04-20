import java.io.*;
import java.util.*;
public class loveTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ar[] = new int[n + 1];
        for (int i = 1; i <= n; i++) ar[i] = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            if (ar[ar[ar[i]]] == ar[i]) {
                int a = ar[i];
                int b = ar[a];
                int c = ar[b];
                if (a != b && b != c && a == c) {
                    System.out.println("YES");
                    System.exit(0);
                }
            }
            
        }
        System.out.println("NO");
    }
}
