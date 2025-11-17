import Logic.XML_Parser;
import Records.Quest;
import org.w3c.dom.NodeList;

import java.util.List;

public interface Initializer {
    static void initializeProgram() {
        NodeList nodeList = XML_Parser.parseXMLFile();
        List<Quest> questList = XML_Parser.createAndSaveRecords(nodeList);

        iterateList(questList);
    }

    private static void iterateList(List<Quest> questList) {
        for (Object a : questList) {
            System.out.println(a.toString());
        }
    }
}
