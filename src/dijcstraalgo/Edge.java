package dijcstraalgo;


public class Edge implements Comparable{
    private int length;
    private Vertex  vertex2;
    
    public Edge(int length, Vertex to){
        this.length = length;
        this.vertex2 = to;
    }

    @Override
    public int compareTo(Object o) {
        return this.length - ((Edge)o).length;
    }
    
    public int getLength(){
        return length;
    }
    
    public Vertex getToWhere(){
        return vertex2;
    }
    
}
