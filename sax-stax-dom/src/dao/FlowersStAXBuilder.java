package dao;

import entity.Flower;
import entity.FlowerEnum;
import org.apache.log4j.Logger;
import service.AbstractFlowerBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FlowersStAXBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = Logger.getLogger(FlowersStAXBuilder.class);
    private HashSet<Flower> flowers = new HashSet<>();
    private XMLInputFactory inputFactory;

    public FlowersStAXBuilder() {
        logger.info("stax");
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildSetFlowers(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum.FLOWER) {
                        Flower fl = buildCard(reader);
                        flowers.add(fl);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            logger.error("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            logger.error("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Flower buildCard(XMLStreamReader reader) throws XMLStreamException {
        Flower fl = new Flower();
        fl.setSoil(reader.getAttributeValue(null, FlowerEnum.SOIL.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            fl.setName(getXMLText(reader));
                            break;
                        case ORIGIN:
                            fl.setOrigin(getXMLText(reader));
                            break;
                        case VISUAL:
                            fl.setVisual(getXMLVisual(reader));
                            break;
                        case GROWING:
                            fl.setGrowing(getXMLGrowing(reader));
                            break;
                        case DATE:
                            fl.setDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case MULTIPLYING:
                            fl.setMultiplying(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum.FLOWER) {
                        return fl;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Card");
    }

    private Flower.Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
        Flower.Visual visual = new Flower.Visual();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case STEM:
                            visual.setStem(getXMLText(reader));
                            break;
                        case SHEET:
                            visual.setSheet(getXMLText(reader));
                            break;
                        case SIZE:
                            visual.setSize(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum.VISUAL) {
                        return visual;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Visual");
    }

    private Flower.Growing getXMLGrowing(XMLStreamReader reader) throws XMLStreamException {
        Flower.Growing growing = new Flower.Growing();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            growing.setTemperature(Integer.valueOf(getXMLText(reader)));
                            break;
                        case LIGHTING:
                            growing.setLighting(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case IRRIGATION:
                            growing.setIrrigation(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum.GROWING) {
                        return growing;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Visual");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}