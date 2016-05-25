package dijcstraalgo;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLReader {
    
    int vertexNum;
    int[][] data;
    private StringTokenizer tokenizer;
    
    public XMLReader(){
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
    
    public int[][] getData(){
        return data;
    }
    
    public int getVertexNumber(){
        return vertexNum;
    }
    
}
