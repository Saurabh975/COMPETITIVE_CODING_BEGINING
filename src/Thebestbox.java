import java.util.*;
public class Thebestbox {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0)
        {
            double p=sc.nextDouble();
            double s=sc.nextDouble();

            double l1 = (p-Math.sqrt((p*p)-(24*s)))/12;
            double l2 = (p+Math.sqrt((p*p)-(24*s)))/12;
            double b1 = (p-8*l1)/4;
            double b2 = (p-8*l2)/4;
            double res1 = Math.pow(l1,2)*b1;
            double res2 = Math.pow(l2,2)*b2;
            res1=Math.max(res1,res2);
            System.out.println(Math.round(res1*100D)/100D);
        }
    }
}
