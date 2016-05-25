package dijcstraalgo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;

public class DijcstraMap extends javax.swing.JFrame {

    final int INF = Integer.MAX_VALUE;
    Integer vertexNum;
    int[][] data;
    ArrayList<Edge> edgeList = new ArrayList<>();
    ArrayList<Vertex> vertList = new ArrayList<>();
    ArrayList<Vertex> queue = new ArrayList<>(),
                      tempQueue = new ArrayList<>();
    String[] boxSet;
    CanvasInterf canvas;
    
    public DijcstraMap() {
        Mode.ALGO_MODE = true;
        readXML();
        initComponents();
        processAdjacence();
        initFrameElements();
    }

    private void readXML(){
        XMLReader reader = new XMLReader();
        data = reader.getData();
        vertexNum = reader.getVertexNumber();
    }    
    
    private void printXMLdata(){
        for (int[] data1 : data) {
            for (int j = 0; j < data1.length; j++) {
                System.out.print(data1[j] + "\t");
            }
            System.out.println("");
        }
    }
    
    private void processAdjacence(){
        fillVerticeList();
        setAdjacentForVertices();
    }

    private void fillVerticeList(){
        boxSet = new String[data.length];
        for (int i=0; i<data.length; i++){
            Vertex.vertList.add(new Vertex(i+1));
            boxSet[i] = Integer.toString(i+1);
        } 
        vertList = Vertex.vertList;
    }
    private void setAdjacentForVertices(){
        for (int i=0; i<data.length; i++)
            for (int j=0; j<data[i].length; j++)
                if (data[i][j] != 0){
                    vertList.get(i).addAdjacentVertex(vertList.get(j));
                    Edge edgeToAdd = new Edge(data[i][j], vertList.get(i), vertList.get(j));
                    Edge.edgeList.add(edgeToAdd);
                    vertList.get(i).addAdjacentEdge(edgeToAdd);
                }
        edgeList = Edge.edgeList;
    }
    
    private void printAdjacency(){
        vertList.stream().forEach((elem) -> {
            System.out.println("Vertex " + elem.getNumber() + elem.getAdjacentVertices());
        });
    }
    
    private void initFrameElements(){
        startPointBox.setModel(new DefaultComboBoxModel(boxSet));
        finalPointBox.setModel(new DefaultComboBoxModel(boxSet));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startPointBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        finalPointBox = new javax.swing.JComboBox<>();
        startBut = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        canvasPanel = new CanvasPanel(vertexNum, data);
        infoPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resPane = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startPointBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("START");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FINISH");

        finalPointBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        startBut.setBackground(new java.awt.Color(204, 204, 255));
        startBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/play.png"))); // NOI18N
        startBut.setOpaque(false);
        startBut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        startBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButActionPerformed(evt);
            }
        });

        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(refreshButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(startPointBox, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(finalPointBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2))
                        .addComponent(startBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finalPointBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startPointBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(startBut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(refreshButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 791, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        resPane.setColumns(20);
        resPane.setRows(5);
        jScrollPane2.setViewportView(resPane);

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButActionPerformed
        paintAllAgain();
        runAlgo(Integer.parseInt(startPointBox.getSelectedItem().toString()));
    }//GEN-LAST:event_startButActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        paintAllAgain();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void paintAllAgain(){
        edgeList.forEach((edge)->{
            edge.setColor(Color.BLACK);
        });
        repaint();
    }
    
    private void runAlgo(int start){
        new Thread(()->{
            prepareAlgo();
            
            Vertex startV = vertList.get(start-1);
            startV.setWeight(0);
            vertList.stream().forEach((elem)->{
                elem.addRouteToMe(Integer.toString(startV.getNumber()));
            });
            queue.add(startV);
            
            while(!queue.isEmpty()){
                Vertex currVertex = queue.stream().sorted().findFirst().get();
                if (currVertex == null){
                    break;
                }
                queue.remove(currVertex);
                
                for (Edge edge : currVertex.getAdjacentEdges()){
                    
                    Vertex targetV = edge.getToWhere();
                    if (targetV.isTagged())
                        continue;
                    
                    if (!queue.contains(targetV)){
                        queue.add(targetV);
                    }
                    
                    int newWeight = currVertex.getWeight() + edge.getLength();
                    
                    if (targetV.getWeight() > newWeight){
                        targetV.setWeight(newWeight);
                        targetV.getRouteToMe().clear();
                        targetV.addRouteToMe(currVertex.getRouteToMe())
                               .addRouteToMe(Integer.toString(targetV.getNumber()));
                    }
                }                
                currVertex.setTagged(true);
                System.out.println("FROM " + (start) + " to " + currVertex.getNumber() + ": " + currVertex.getWeight() + "_" + currVertex.getRouteToMe());     
            }
            printToTextPane(start);
            
            displayRouteRequested();
            
        }).start();
    }
    
    private void prepareAlgo(){
        vertList.forEach((vertex)->{
                vertex.getRouteToMe().clear();
                vertex.setWeight(INF);
                vertex.setTagged(false);
                Collections.sort(vertex.getAdjacentEdges());
            });
        queue.clear();
        resPane.setText("");
        canvas = (CanvasInterf)canvasPanel;
    }
    
    private void printToTextPane(int start){
        vertList.forEach((currVertex)->{
            resPane.append("FROM " + (start) + " to " + currVertex.getNumber() + ": " + currVertex.getWeight() + "_" + currVertex.getRouteToMe() + '\n');
        });
    }    

    private void displayRouteRequested(){
        try{
            int to = Integer.parseInt(finalPointBox.getSelectedItem().toString());
            Vertex currVertex = vertList.get(to-1);
            ArrayList<String> tempRoute = currVertex.getRouteToMe();
            for (int ind=0; ind < tempRoute.size()-1; ind++){
                //canvas.plotVertexSpec(Integer.parseInt(currVertex.getRouteToMe().get(key)), vertList.get(to).getWeight());
                Edge reqEdge = Edge.getEdgeWhichIs(Integer.parseInt(tempRoute.get(ind)), 
                                                        Integer.parseInt(tempRoute.get(ind+1)));
                reqEdge.setColor(Color.YELLOW);
                canvas.plotEdgeSpec(reqEdge);
                repaint();
                Thread.sleep(1000);
            }
        }catch(InterruptedException exc){
            System.err.println("Interrupted Exception");
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DijcstraMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DijcstraMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DijcstraMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DijcstraMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DijcstraMap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel canvasPanel;
    private javax.swing.JComboBox<String> finalPointBox;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextArea resPane;
    private javax.swing.JButton startBut;
    private javax.swing.JComboBox<String> startPointBox;
    // End of variables declaration//GEN-END:variables
}
