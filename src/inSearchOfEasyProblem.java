import java.io.*;
import java.util.*;
public class inSearchOfEasyProblem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int flag=0;
        for (int i = 0; i < n ; i++) {
            if(sc.nextInt()==1)flag=1;
        }
        System.out.println(flag==1?"HARD":"Easy");
    }
}
