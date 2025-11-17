package Logic;

import Records.Quest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface XML_Parser {

   static NodeList parseXMLFile() {
       try {
           // Prepares the factory for use
           DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
           // creates an instance of document builder which is used to parse the xml document
           DocumentBuilder documentBuilder = dbF.newDocumentBuilder();

           File xmlFile = new File("src/Files/QuestDB_XML.xml");
           Document document = documentBuilder.parse(xmlFile);
           document.getDocumentElement().normalize();

           NodeList nodeList = document.getElementsByTagName("Quest");

           return nodeList;

       } catch (IllegalArgumentException e) {
           System.out.println("Caught an IllegalArgument Exception whilst parsing the xml file in the document object.");
       }catch (ParserConfigurationException e) {
           System.out.println("Unexpected error when parsing input.");
       } catch (IOException e ) {
           System.out.println("Error when the document object is parsing the xml file.");
       } catch (SAXException e) {
           System.out.println("Error with parsing the xml file (invalid syntax, unexpected tokens, e.t.c) ," +
                   " io while reading xml");
       }
       return null;
    }

    static List<Quest> createAndSaveRecords(NodeList nodeList) {
       List<Quest> questList = new ArrayList<>(nodeList.getLength());


       for (int i = 0; i < nodeList.getLength(); i++) {
           Node node = nodeList.item(i);
           if (node.getNodeType() != Node.ELEMENT_NODE) {continue;}

           Element element = (Element) node;

           String title = getTheTextOfAnElement(element,"QuestTitle");
           if (title == null || title.isBlank()) {throw new IllegalArgumentException("Missing Quest title.");}

           String text = getTheTextOfAnElement(element,"QuestText");
           if (text == null || text.isBlank()) {throw new IllegalArgumentException("Missing Quest text.");}

           String coinsReward = getTheTextOfAnElement(element,"QuestRewardGold");
           int gold = RewardParser.parseTheRewardInGold(coinsReward);
           int silver = RewardParser.parseTheRewardInSilver(coinsReward);
           int copper = RewardParser.parseTheRewardInCopper(coinsReward);

           String itemReward = getTheTextOfAnElement(element,"QuestRewardItem");

           String experienceInText = getTheTextOfAnElement(element,"Experience");
           if (experienceInText == null || experienceInText.isBlank()) {
               throw new IllegalArgumentException("Missing Experience");
           }

           int experience;
           try {
               experience = Integer.parseInt(experienceInText);
           } catch (NumberFormatException nfe) {
               throw new IllegalArgumentException("Invalid number whilst parsing.");
           }


           Quest quest = new Quest(title,text,gold,silver,copper,itemReward,experience);
           questList.add(quest);
       }
       return questList;
    }

    static String getTheTextOfAnElement(Element element, String tagName) {
       NodeList nodesOfAnElement = element.getElementsByTagName(tagName);
       if (nodesOfAnElement == null || nodesOfAnElement.getLength() == 0) {return null;}
       Element ele = (Element) nodesOfAnElement.item(0);
       String text = ele.getTextContent();
       return text == null ? null : text.trim();
   }
}
