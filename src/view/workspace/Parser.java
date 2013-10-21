package view.workspace;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

    public Parser () {
        // TODO Auto-generated constructor stub
    }
    
    public void loadPreferences(File prefFile){
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
        for (int count=0; count < childNodes.getLength(); count++){
            
        }
        
    }

}
