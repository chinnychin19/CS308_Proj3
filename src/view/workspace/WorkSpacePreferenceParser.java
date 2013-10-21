package view.workspace;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import view.Constants;


public class WorkSpacePreferenceParser {
    private WorkSpacePreferencesController myController;
    
    public WorkSpacePreferenceParser (WorkSpacePreferencesController controller) {
        myController=controller;
    }

    public void loadPreferences (File prefFile) {
        String f = prefFile.toString();
        System.out.println(f.substring(f.length()-4, f.length()));

        if (!f.substring(f.length()-4, f.length()).equals(".xml")){
            JOptionPane.showMessageDialog(null, Constants.NOT_XML_MESSAGE);
            return;
        }
        
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
            String name =doc.getChildNodes().item(0).getNodeName();
            
            if (name.equals("preferences")){
                parse(doc.getChildNodes());
            }
                
            else{
                JOptionPane.showMessageDialog(null, Constants.WRONG_PREF_FILE_MESSAGE);
            }
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
                        myController.setBGColor(Integer.parseInt(node.getNodeValue()));
                    }
                    
                    if ("pen".equals(nodeName)) {
                        myController.setPenColor(Integer.parseInt(node.getNodeValue()));
                    }
                    
                    if ("turtles".equals(nodeName)){
                        //TODO: myController.METHOD(Integer.parseInt(node.getNodeValue()));
                    }
                    
                    if ("file".equals(nodeName)){
                        //myController.readFile(node.getNodeValue());
                    }
                    
                    if ("turtleImage".equals(nodeName)){
                        myController.changeImage(Integer.parseInt(node.getNodeValue()));
                    }
                    

                }

            }

            if (tempNode.hasChildNodes()) {
                parse(tempNode.getChildNodes());
            }
        }

    }

//    public static void main (String[] args) {
//        WorkSpacePreferenceParser p = new WorkSpacePreferenceParser();
//        File f = new File("pref.xml");
//        p.loadPreferences(f);
//    }

}
