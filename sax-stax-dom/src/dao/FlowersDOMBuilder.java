package dao;

import entity.Flower;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.AbstractFlowerBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FlowersDOMBuilder extends AbstractFlowerBuilder {

    private static final Logger logger = Logger.getLogger(FlowersDOMBuilder.class);
    private Set<Flower> flower;
    private DocumentBuilder docBuilder;

    public FlowersDOMBuilder() {
        logger.info("dom");
        this.flower = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Ошибка конфигурации парсера: " + e);
        }

    }

    public Set<Flower> getFlowers() {

        return flower;
    }

    public void buildSetFlowers(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower newFlower = buildCard(flowerElement);
                flower.add(newFlower);
            }
        } catch (IOException e) {
            logger.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.error("Parsing failure: " + e);
        }
    }

    private Flower buildCard(Element flowerElement) {
        Flower flower = new Flower();
        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setSoil(flowerElement.getAttribute("soil"));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));
        Flower.Visual visual = flower.getVisual();
        Element visualElement = (Element) flowerElement.getElementsByTagName("visual").item(0);
        visual.setStem(getElementTextContent(visualElement, "stem"));
        visual.setSheet(getElementTextContent(visualElement, "sheet"));
        visual.setSize(Integer.valueOf(getElementTextContent(visualElement, "size")));
        Flower.Growing growing = flower.getGrowing();
        Element growingElement = (Element) flowerElement.getElementsByTagName("growing").item(0);
        growing.setTemperature(Integer.valueOf(getElementTextContent(growingElement, "temperature")));
        growing.setLighting(Boolean.valueOf(getElementTextContent(growingElement, "lighting")));
        growing.setIrrigation(Integer.valueOf(getElementTextContent(growingElement, "irrigation")));
        flower.setMultiplying(getElementTextContent(flowerElement, "multiplying"));
        flower.setDate(LocalDate.parse(getElementTextContent(flowerElement, "date")));
        return flower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}