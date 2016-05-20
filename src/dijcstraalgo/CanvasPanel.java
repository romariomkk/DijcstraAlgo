package dijcstraalgo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel{

    final int leftSpace = 0;
    int lowerSpace, upperSpace, semiCircleRadius;
    int vertexRadius, vertexCount;
    int[][] data;
    boolean isUp = true;
    Graphics graphics;
    
    public CanvasPanel(int vertexCount, int[][] data){
        vertexRadius = 100/vertexCount;
        this.vertexCount = vertexCount;
        this.data = data;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        graphics = g;
        paintVertexes(graphics);
    }
    
    public void paintVertexes(Graphics g){
        lowerSpace = this.getWidth()/2;
        upperSpace = this.getHeight()/2;
        
        plotVertex(vertexCount);
        
        int index = 0;
        for (int i=0; i<data.length; i++){
            for (int j=index; j<data[i].length; j++)
                if (data[i][j] != 0)
                    plotEdge(i+1, j+1, Integer.toString(data[i][j]));
            index++;
        }
    }
    
    private void plotVertex(int vertexCount){
        for (int v = 0; v < vertexCount; v++){
            graphics.setColor(Color.RED);
            int y = upperSpace;
            int x = v*(2*lowerSpace-leftSpace)/vertexCount + 2*leftSpace;
            graphics.drawOval(x, y, 2*vertexRadius, 2*vertexRadius);
            graphics.fillOval(x, y, 2*vertexRadius, 2*vertexRadius);
            graphics.setColor(Color.WHITE);
            graphics.drawString(Integer.toString(v+1), x+vertexRadius-vertexRadius/4, y+2*vertexRadius-3*vertexRadius/4);
        }
    }
    
    public void plotEdge(int from, int to, String weight){
        int y = upperSpace;
        int a = (from-1)*(2*lowerSpace-leftSpace)/vertexCount +2*leftSpace;
        int b = (to-1)*(2*lowerSpace-leftSpace)/vertexCount +2*leftSpace;
        int x = (a+b)/2;
        semiCircleRadius = Math.abs(b-a)/2;
        
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(vertexRadius/2));
        if (Math.abs(from-to) == 1){
            g2.drawLine(x-semiCircleRadius+2*vertexRadius, y+vertexRadius, x+semiCircleRadius, y+vertexRadius);
        }else if (isUp){
            g2.drawArc(x-semiCircleRadius+vertexRadius, y-semiCircleRadius, 2*semiCircleRadius, 2*semiCircleRadius, 0, 180);
            isUp = false;
        }else if (!isUp){
            g2.drawArc(x-semiCircleRadius+vertexRadius, y-semiCircleRadius+2*vertexRadius, 2*semiCircleRadius, 2*semiCircleRadius, 0, -180);
            isUp = true;
        }
    }
}
