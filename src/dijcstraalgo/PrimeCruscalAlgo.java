package dijcstraalgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeCruscalAlgo extends javax.swing.JFrame {

    class Tree{
        
        private List<Edge> edges = new ArrayList<>();
        private List<Vertex> vertices = new ArrayList<>();
        
        public boolean add(Edge edge){
            if (!vertices.contains(edge.getFromWhere()))
                vertices.add(edge.getFromWhere());
            if (!vertices.contains(edge.getToWhere()))
                vertices.add(edge.getToWhere());
            return edges.add(edge);
        }
        public boolean remove(Edge edge){
            return edges.remove(edge);
        }
        public List<Vertex> getVertices(){
            return vertices;
        }
        public List<Edge> getEdges(){
            return edges;
        }
        
        public boolean hasCyclesMadeBy(Vertex vert){
            if (vert.isTagged()){
                return true;
            }
            vert.setTagged(true);
            ArrayList<Vertex> temp = adjVerts(vert);
            for (int i=0; i<temp.size(); i++){
                Vertex newV = temp.get(i);
                hasCyclesMadeBy(newV);
            }            
            return false;
        }
        
        private ArrayList<Vertex> adjVerts(Vertex vert){
            ArrayList<Vertex> adj = new ArrayList<>();
            edges.forEach((edge)->{
                if (edge.getFromWhere().equals(vert)){
                    adj.add(edge.getToWhere());
                }else if (edge.getToWhere().equals(vert)){
                    adj.add(edge.getToWhere());
                }
            });
            return adj;
        }
        
    }
    
    XMLReader reader;
    int vertexNum;
    int[][] data;
    CanvasInterf canvas;
    ArrayList<Vertex> vertList = new ArrayList<>();

    List<Edge> edgeList = new ArrayList<>(),
               queue = new ArrayList<>();
    Tree tree = new Tree();
    
    ArrayList<ArrayList<Integer>> dataList;
    
    
    public PrimeCruscalAlgo() {
        Mode.ALGO_MODE = false;
        readXML();
        initComponents();
        processAdjacence();
    }

    private void readXML(){
        reader = new XMLReader();
        vertexNum = reader.getVertexNumber();
        data = reader.getData();
    }
    
    private void processAdjacence(){
        fillVerticeList();
        setAdjacentForVertices();
    }
    
    private void fillVerticeList(){
        for (int i=0; i<data.length; i++){
            Vertex.vertList.add(new Vertex(i+1));
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
        cutToFit();
    }
    
    private void cutToFit(){
        for (int i=0; i<Edge.edgeList.size(); i++){
            Edge currEdge = Edge.edgeList.get(i);
            for (int j=0; j<Edge.edgeList.size(); j++){
                if ((i!=j) && currEdge.getFromWhere().equals(Edge.edgeList.get(j).getToWhere()) &&
                    Edge.edgeList.get(j).getFromWhere().equals(currEdge.getToWhere())){
                    Edge.edgeList.remove(Edge.edgeList.get(j));
                }
            }
        }
        edgeList = Edge.edgeList;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startBut = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resPane = new javax.swing.JTextArea();
        canvasPanel = new CanvasPanel(vertexNum, data);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("START");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refreshButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(startBut, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startBut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButton)
                .addContainerGap())
        );

        resPane.setColumns(20);
        resPane.setRows(5);
        jScrollPane2.setViewportView(resPane);

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButActionPerformed
        //paintAllAgain();
        runAlgo();
    }//GEN-LAST:event_startButActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        //paintAllAgain();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void runAlgo(){
        new Thread(()->{
            queue = edgeList;
            Collections.sort(queue);
            while(!queue.isEmpty()){
                Edge edgeToAdd = queue.stream().findFirst().get();
                tree.add(edgeToAdd);
                if (tree.hasCyclesMadeBy(edgeToAdd.getFromWhere()) && tree.hasCyclesMadeBy(edgeToAdd.getToWhere())){
                    tree.remove(edgeToAdd);
                }
                queue.remove(edgeToAdd);
            }
            
            printTree();
        }).start();
    }
    
    private void prepareAlgo(){
        canvas = (CanvasInterf)canvasPanel;
        
    }
    
    private void dataAsList(){
        dataList = new ArrayList<>(data.length);
            for (int i=0; i<data.length; i++){
                dataList.add(new ArrayList<>());
                for (int j=0; j<data[i].length; j++){
                    dataList.get(i).add(data[i][j]);
                    System.out.print(dataList.get(i).get(j) + "\t");
                }
                System.out.println("");
            }
    }
    
    private void printTree(){
        int sum = 0;
        int index = 1;
        for (Edge elem : tree.getEdges()){
            System.out.println(elem.toString() + ' ' + index++);
            sum += elem.getLength();
        }
        System.out.println(sum);
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
            java.util.logging.Logger.getLogger(PrimeCruscalAlgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimeCruscalAlgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimeCruscalAlgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimeCruscalAlgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrimeCruscalAlgo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel canvasPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextArea resPane;
    private javax.swing.JButton startBut;
    // End of variables declaration//GEN-END:variables
}
