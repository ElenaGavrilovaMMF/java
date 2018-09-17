package service;

import dao.FlowersDOMBuilder;
import dao.FlowersSAXBuilder;
import dao.FlowersStAXBuilder;

public class FlowerBuilderFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    public AbstractFlowerBuilder createFlowerBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowersDOMBuilder();
            case STAX:
                return new FlowersStAXBuilder();
            case SAX:
                return new FlowersSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
