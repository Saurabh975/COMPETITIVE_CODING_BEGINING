import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.lang.*;
public class functionHeight {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger n=new BigInteger(sc.next());
        BigInteger m=new BigInteger(sc.next());
        System.out.println((m.mod(n).equals(new BigInteger("0")))? m.divide(n) : m.divide(n).add(new BigInteger("1")));
    }
}
