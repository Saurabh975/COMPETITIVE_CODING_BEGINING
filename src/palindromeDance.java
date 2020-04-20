import java.io.*;
import java.util.*;
public class palindromeDance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
        int ar[] = new int[n];
        for (int i = 0; i < n; i++) ar[i] = sc.nextInt();

        int sum = 0;
        for (int i = 0; i < n / 2; i++) {
            if (ar[i] == 1 && ar[n - 1 - i] == 0 || ar[i] == 0 && ar[n - 1 - i] == 1) {
                System.out.println(-1);
                System.exit(0);
            }
            if (ar[i] == 2)
                sum += ar[n - 1 - i] == 0 ? a : ar[n - 1 - i] == 1 ? b : a >= b ?2* b : 2* a;
            else if (ar[n - 1 - i] == 2)
                sum += ar[i] == 0 ? a : ar[i] == 1 ? b : a >= b ? 2*b : 2*a;
        }
        System.out.println((n & 1) == 1 ? ar[n / 2] == 2 ? a >= b ? sum + b : sum + a : sum : sum);
    }
}
