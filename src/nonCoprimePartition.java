import java.util.*;
public class nonCoprimePartition {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if(n<=2)System.out.println("No");
        else
        {
            System.out.print(1+ " "+n+"\n"+(n-1)+ " ");
            for(int i=1;i<n-1;i++)System.out.print(i+ " ");
            System.out.println();
        }
    }
}
