package dao;

import entity.Flower;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.AbstractFlowerBuilder;
import service.FlowerHandler;

import java.io.IOException;
import java.util.Set;

public class FlowersSAXBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = Logger.getLogger(FlowersSAXBuilder.class);
    private Set<Flower> flowers;
    private FlowerHandler fh;
    private XMLReader reader;

    public FlowersSAXBuilder() {
        logger.info("sax");
        fh = new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(fh);
        } catch (SAXException e) {
            logger.error("ошибка SAX парсера: " + e);
        }
    }

    public Set<Flower> getFlowers() {

        return flowers;
    }

    public void buildSetFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.error("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            logger.error("ошибка I/О потока: " + e);
        }
        flowers = fh.getFlowers();
    }
}