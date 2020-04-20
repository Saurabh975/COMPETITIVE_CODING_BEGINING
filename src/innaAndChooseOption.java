import java.io.*;
import java.util.*;


public class innaAndChooseOption {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int testCount = sc.nextInt();
        for (int k = 1; k <= testCount; k++)
        {

            String cards = sc.next();
            List<String> ans = new ArrayList<>();
            for (int a : new int[]{1, 2, 3, 4, 6, 12}) {
                int b = 12 / a;
                boolean win = false;
                for (int i = 0; i < b; ++i) {
                    boolean allX = true;
                    for (int j = 0; j < a; ++j) {
                        if (cards.charAt(b * j + i) != 'X') {
                            allX = false;
                            break;
                        }
                    }
                    if (allX) {
                        win = true;
                        break;
                    }
                }
                if (win) {
                    ans.add(a+"x"+b);
                }
            }
            System.out.print(ans.size()+" ");
            for (String s : ans) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}