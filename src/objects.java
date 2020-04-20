public class objects {
    public static void main(String args[]) {
        A ob = new A();
        ob.x=1;ob.y=4;
        ob.update(ob.x,ob.y);
        System.out.println(ob.x+" "+ob.y);
    }
}

class A{
    int x,y;
    void update(int c,int d)
    {
        x=c+d;
    }
}