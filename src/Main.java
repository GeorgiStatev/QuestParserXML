import Logic.XML_Parser;
import org.w3c.dom.NodeList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NodeList nodeList = XML_Parser.parseXMLFile();
        List questList = XML_Parser.createAndSaveRecords(nodeList);

        for (Object a : questList) {
            System.out.println(a.toString());
        }
    }
}
