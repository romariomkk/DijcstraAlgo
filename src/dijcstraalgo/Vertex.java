package dijcstraalgo;

import java.util.ArrayList;
import java.util.Collections;


public class Vertex implements Comparable{
    private final int number;
    private ArrayList<String> routeToMe = new ArrayList<>();
    private int weight = Integer.MAX_VALUE;
    private boolean isTagged = false;
    private ArrayList<Vertex> adjacentVertices = new ArrayList<>();
    private ArrayList<Edge> adjacentEdges = new ArrayList<>();
    
    public Vertex(int number){
        this.number = number;
    }
    
    public void setTagged(boolean tagged) {
        this.isTagged = tagged;
    }

    public boolean isTagged() {
        return isTagged;
    }   

    public void addAdjacentVertex(Vertex vertex){
        adjacentVertices.add(vertex);
    }
    
    public ArrayList<Vertex> getAdjacentVertices(){
        return adjacentVertices;
    }
    
    public ArrayList<String> getRouteToMe() {
        return routeToMe;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumber(){
        return number;
    }
    
    public Vertex addRouteToMe(ArrayList<String> routeToMe) {
        for (String elem : routeToMe)
            addRouteToMe(elem);
        return this;
    }
    public Vertex addRouteToMe(String routeToMe) {
        this.routeToMe.add(routeToMe);
        return this;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addAdjacentEdge(int weight, Vertex to){
        adjacentEdges.add(new Edge(weight, to));
    }
    
    public ArrayList<Edge> getAdjacentEdges(){
        return adjacentEdges;
    }
    
    @Override
    public String toString() {
        return "Vertex{" + number + '}';
    }

    @Override
    public int compareTo(Object o) {
        return ((Vertex)o).getWeight() - this.getWeight();
    }
    
}
