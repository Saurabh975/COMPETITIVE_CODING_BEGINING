import java.io.*;
import java.util.*;
public class lucas {
    public static void main(String[] args) throws IOException {
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                char ch[] = br.readLine().trim().toCharArray();
                int ar[] = new int[26];
                ArrayList<Integer> arr = new ArrayList<>();
                for (int i = 0; i < ch.length; i++) ar[ch[i] - 'a']++;
                for (int i = 0; i < 26; i++) if (ar[i] != 0) arr.add(ar[i]);
                if(arr.isEmpty()){System.out.println("UNFIT");continue;}
                Collections.sort(arr);
                String s = "FIT";
                if (arr.get(0) == 1 && arr.get(1) == 2) {
                    for (int i = 2; i < arr.size(); i++)
                        if (arr.get(i) != arr.get(i - 1) + arr.get(i - 2)) {
                            s = "UNFIT";
                            break;
                        }
                } else s = "UNFIT";
                System.out.println(s);
            }
        }
    }
}
