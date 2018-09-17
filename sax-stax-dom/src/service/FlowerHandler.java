package service;

import entity.Flower;
import entity.FlowerEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> withText;

    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerEnum.NAME, FlowerEnum.MULTIPLYING);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("flower".equals(localName)) {
            current = new Flower();
            if (attrs.getLength() == 1) {
                current.setSoil(attrs.getValue(0));
            }
        } else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("flower".equals(localName)) {
            flowers.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case STEM:
                    current.getVisual().setStem(s);
                    break;
                case SHEET:
                    current.getVisual().setSheet(s);
                    break;
                case SIZE:
                    current.getVisual().setSize(Integer.valueOf(s));
                case TEMPERATURE:
                    current.getGrowing().setTemperature(Integer.valueOf(s));
                    break;
                case LIGHTING:
                    current.getGrowing().setLighting(Boolean.valueOf(s));
                    break;
                case IRRIGATION:
                    current.getGrowing().setIrrigation(Integer.valueOf(s));
                    break;
                case DATE:
                    current.setDate(LocalDate.parse(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}