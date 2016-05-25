package dijcstraalgo;

import java.awt.Color;
import java.util.ArrayList;


public class Edge implements Comparable{
    private int length;
    private Vertex vertex1;
    private Vertex  vertex2;
    private Color color;
    
    static ArrayList<Edge> edgeList = new ArrayList<>();
    
    public Edge(int length, Vertex from, Vertex to){
        this.length = length;
        this.vertex1 = from;
        this.vertex2 = to;
        this.color = Color.BLACK;
    }

    @Override
    public int compareTo(Object o) {
        return this.length - ((Edge)o).length;
    }
    
    public int getLength(){
        return length;
    }
        
    public Vertex getFromWhere() {
        return vertex1;
    }
    
    public Vertex getToWhere(){
        return vertex2;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Color setColor(Color color) {
        this.color = color;
        return this.color;
    }
    
    public static Edge getEdgeWhichIs(int from, int to){
        Edge toReturn = null;
        for (Edge edge : edgeList){
            if ((edge.getFromWhere().getNumber() == from && edge.getToWhere().getNumber() == to) ||
                (edge.getToWhere().getNumber() == from && edge.getFromWhere().getNumber() == to)){
                toReturn = edge;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return "Edge{" + "length=" + length + ", from=" + vertex1 + ", to=" + vertex2 + '}';
    }
    
    
    
}
