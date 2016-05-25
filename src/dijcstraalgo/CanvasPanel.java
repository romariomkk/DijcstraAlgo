package dijcstraalgo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements CanvasInterf{
    
    static ArrayList<Vertex> vertList = Vertex.vertList;
    //static ArrayList<Edge> edgeList = Edge.edgeList;
    
    final int leftSpace = 0;
    int lowerSpace, upperSpace, semiCircleRadius;
    int vertexRadius, vertexCount;
    int[][] data;
    boolean isUp = true;
    int vertexWeightText = 0;
    Graphics graphics;
    Color edgeColor = Color.BLACK;
    
    public CanvasPanel(int vertexCount, int[][] data){
        vertexRadius = 100/vertexCount;
        this.vertexCount = vertexCount;
        this.data = data;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        graphics = g;
        paintCanvas(graphics);
    }
    
    public void paintCanvas(Graphics g){
        lowerSpace = this.getWidth()/2;
        upperSpace = this.getHeight()/2;
        paintVertexes();
        if (Mode.ALGO_MODE)
            paintEdges();
    }
    
    public void paintVertexes(){
        for (int v = 0; v < vertexCount; v++)

            plotVertex(v, vertList.get(v).getWeight()!=Integer.MAX_VALUE ? vertList.get(v).getWeight() : 0 );
    }
    public void paintEdges(){
        int index = 0;
        for (int i=0; i<data.length; i++){
            for (int j=index; j<data[i].length; j++){
                if (data[i][j] != 0)
                    plotEdge(Edge.getEdgeWhichIs(i+1, j+1));
            }
            index++;
        }
    }
    
    @Override
    public void plotVertexSpec(int vertexIndex, int vertWeight){
        plotVertex(vertexIndex, vertWeight);
    }
    
    private void plotVertex(int vertexIndex, int vertWeight){
        vertexWeightText = vertWeight;
        graphics.setColor(Color.RED);
        int y = upperSpace;
        int x = vertexIndex*(2*lowerSpace-leftSpace)/vertexCount + 2*leftSpace;
        graphics.drawOval(x, y, 2*vertexRadius, 2*vertexRadius);
        graphics.fillOval(x, y, 2*vertexRadius, 2*vertexRadius);

        graphics.setColor(Color.WHITE);
        graphics.drawString(Integer.toString(vertexIndex+1), x+vertexRadius-vertexRadius/4, y+2*vertexRadius-3*vertexRadius/4);

        graphics.setColor(Color.MAGENTA);
        graphics.setFont(new Font("Calibri", Font.BOLD, 14));
        graphics.drawString(Integer.toString(vertexWeightText), x + 2*vertexRadius, y + 3*vertexRadius);
    }
    
    @Override
    public void plotEdgeSpec(Edge edge){
        plotEdge(edge);
    }
    
    private void plotEdge(Edge edge){
        int from = edge.getFromWhere().getNumber(),
            to = edge.getToWhere().getNumber();
        String length = Integer.toString(edge.getLength());
        edgeColor = edge.getColor();
        
        int y = upperSpace;
        int a = (from-1)*(2*lowerSpace-leftSpace)/vertexCount +2*leftSpace;
        int b = (to-1)*(2*lowerSpace-leftSpace)/vertexCount +2*leftSpace;
        int x = (a+b)/2;
        semiCircleRadius = Math.abs(b-a)/2;
        
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(edgeColor);
        g2.setStroke(new BasicStroke(vertexRadius/2));
        if (Math.abs(from-to) == 1){
            g2.drawLine(x-semiCircleRadius+2*vertexRadius, y+vertexRadius, x+semiCircleRadius, y+vertexRadius);
            g2.drawString(length, x, y);
        }else if (isUp){
            g2.drawArc(x-semiCircleRadius+vertexRadius, y-semiCircleRadius, 2*semiCircleRadius, 2*semiCircleRadius, 0, 180);
            g2.drawString(length, x, y - semiCircleRadius + 2*vertexRadius);
            isUp = false;
        }else if (!isUp){
            g2.drawArc(x-semiCircleRadius+vertexRadius, y-semiCircleRadius+2*vertexRadius, 2*semiCircleRadius, 2*semiCircleRadius, 0, -180);
            g2.drawString(length, x, y + semiCircleRadius + vertexRadius);
            isUp = true;
        }
    } 
    
}
