package dijcstraalgo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.*;
import org.w3c.dom.*;

public class DijcstraMap extends javax.swing.JFrame {

    StringTokenizer tokenizer;
    Integer vertexNum;
    static int[][] data;
    ArrayList<Vertex> vertList = new ArrayList<>();
    final int INF = Integer.MAX_VALUE;
    ArrayList<Vertex> queue = new ArrayList<>(),
                      tempQueue = new ArrayList<>();
    ArrayList<Edge> edgeList = new ArrayList<>();
    String[] boxSet;
    
    public DijcstraMap() {
        readXMLfile();
        //printXMLdata();
        initComponents();
        processAdjacence();
        //printAdjacency();
        initFrameElements();
    }

    private  void readXMLfile(){
        try {
            //File xmlFile = new File("src/dijcstraalgo/Dijcstra_graph_data.xml");
            File xmlFile = new File("src/dijcstraalgo/source200.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            
            doc.normalize();
            
            vertexNum = Integer.parseInt(doc.getElementsByTagName("vertex").item(0).getAttributes().getNamedItem("count").getNodeValue());
            data = new int[vertexNum][vertexNum];
            
            NodeList dataList = doc.getElementsByTagName("row");
            for (int index=0; index<dataList.getLength(); index++){
                Node itemNode = dataList.item(index);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element)itemNode;
                    tokenizer = new StringTokenizer(elem.getTextContent());
                    for (int col=0; col<vertexNum; col++){
                        data[index][col] = Integer.parseInt(tokenizer.nextToken());
                    }
                }
            }           
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DijcstraMap.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            vertList.add(new Vertex(i+1));
            boxSet[i] = Integer.toString(i+1);
        } 
    }
    private void setAdjacentForVertices(){
        for (int i=0; i<data.length; i++)
            for (int j=0; j<data[i].length; j++)
                if (data[i][j] != 0){
                    vertList.get(i).addAdjacentVertex(vertList.get(j));
                    vertList.get(i).addAdjacentEdge(data[i][j], vertList.get(j));
                    edgeList.add(new Edge(data[i][j], vertList.get(j)));
                }
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
        startBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButMouseEntered(evt);
            }
        });
        startBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(startPointBox, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(finalPointBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(startBut)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(finalPointBox, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(startPointBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startBut)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButMouseEntered
        startBut.setOpaque(true);
    }//GEN-LAST:event_startButMouseEntered

    private void startButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButActionPerformed
        runAlgo(Integer.parseInt(startPointBox.getSelectedItem().toString()));
    }//GEN-LAST:event_startButActionPerformed

    private void runAlgo(int start){
        new Thread(()->{
            prepareAlgo();
            
            Vertex startV = vertList.get(start-1);
            startV.setWeight(0);
            vertList.stream().forEach((elem)->{
                elem.addRouteToMe(Integer.toString(startV.getNumber()));
            });
            queue.add(startV);
            
            for (int v=0; v<queue.size(); v++){
                Vertex currVertex = queue.get(v);
                for (Edge edge : currVertex.getAdjacentEdges()){
                    
                    Vertex targetV = edge.getToWhere();
//                    if (targetV.isTagged())
//                        continue;
                    
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
            
        }).start();
    }
    
    private boolean anyNotTagged(Vertex currVertex){
        return currVertex.getAdjacentVertices().stream().anyMatch((elem) -> (!elem.isTagged()));
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
    }
    
    private void printToTextPane(int start){
        vertList.forEach((currVertex)->{
            resPane.append("FROM " + (start) + " to " + currVertex.getNumber() + ": " + currVertex.getWeight() + "_" + currVertex.getRouteToMe() + '\n');
        });
        
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
    private javax.swing.JTextArea resPane;
    private javax.swing.JButton startBut;
    private javax.swing.JComboBox<String> startPointBox;
    // End of variables declaration//GEN-END:variables
}
