import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class KUCHBHI {
    int c;

    public static void main(String[] args) {                                 //threading has been used to increase the stack size.
        int ar[][]=new int[10][];
        for(int i=0;i<10;i++)ar[i]=new int[(int)(Math.random()*45)];

        for(int i=0;i<10;i++)System.out.println(ar[i].length);
    }
}