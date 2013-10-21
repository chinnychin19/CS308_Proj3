package view.workspace;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;


public class Parser {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    
    public Parser () {
        // TODO Auto-generated constructor stub
    }

    public void loadPreferences (File prefFile) {
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = dBuilder.parse(prefFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }

        if (doc.hasChildNodes()) {
            parse(doc.getChildNodes());
        }

    }

    private void parse (NodeList childNodes) {

        for (int count = 0; count < childNodes.getLength(); count++) {
            Node tempNode = childNodes.item(count);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = tempNode.getNodeName();

                if (tempNode.hasAttributes()) {
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    Node node = nodeMap.item(0);
                    
                    if ("background".equals(nodeName)) {       
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    
                    if ("turtles".equals(nodeName)){
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    
                    if ("file".equals(nodeName)){
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    
                    if ("turtleImage".equals(nodeName)){
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    
                    if ("grid".equals(nodeName)){
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    
                    if ("status".equals(nodeName)){
                        System.out.println(nodeName + " " +node.getNodeValue());
                    }
                    

                }

            }

            if (tempNode.hasChildNodes()) {
                parse(tempNode.getChildNodes());
            }
        }

    }

    public static void main (String[] args) {
        Parser p = new Parser();
        File f = new File("pref.xml");
        p.loadPreferences(f);
    }

}
