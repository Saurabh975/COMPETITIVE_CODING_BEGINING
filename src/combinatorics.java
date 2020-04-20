import java.io.*;
import java.util.*;
public class combinatorics {
    public static void main(String args[]) throws IOException
    {
        Scanner input=new Scanner(System.in);
        for(int i=0;i<1000;i++)
        {
            for(int j=0;j<=i;j++)
            {
                if(j==0 || j==i)pascalTriangle[i][j]=1;
                else pascalTriangle[i][j]=pascalTriangle[i-1][j]+pascalTriangle[i-1][j-1];
            }
        }
        int n=input.nextInt();
        int k=input.nextInt();
        int c=0;
        for(int i=0;i<n;i++)
            if(input.nextInt()%2==0)
                c++;
        System.out.println(pascal(c,k));
    }
    static int pascal(int n, int r)
    {
        return pascalTriangle[n][r];
    }
    static int[][] pascalTriangle=new int[1000][1000];
}
