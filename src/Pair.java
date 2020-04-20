 class Pair implements Comparable<Pair>
{
    int x,y;
    Pair(int x,int y)  //constructor
    {
        this.x=x;
        this.y=y;
    }
    public int compareTo(Pair other)   //method for comparing pairs, useful in sorting and...
    {
        if(this.x!=other.x)   //first x is preffered, the pair with higher x is greater
            return this.x-other.x;
        return this.y-other.y;  //if x is same, the one with higher y is greater
    }
    public String toString()  //makes printing of pairs simple
    {
        return "("+x+","+y+")";
    }
}