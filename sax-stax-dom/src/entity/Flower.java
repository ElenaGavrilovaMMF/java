package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flower", propOrder = {
        "name",
        "origin",
        "visual",
        "growing",
        "multiplying",
        "date"
})
public class Flower {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String origin;
    @XmlElement(required = true)
    protected Visual visual = new Visual();
    @XmlElement(required = true)
    protected Growing growing = new Growing();
    @XmlElement(required = true)
    protected String multiplying;
    @XmlElement(required = true)
    protected LocalDate date;
    @XmlAttribute(name = "soil")
    protected String soil;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement
    @XmlType(name = "visual", propOrder = {
            "stem",
            "sheet",
            "size"
    })
    public static class Visual {
        private String stem;
        private String sheet;
        @XmlSchemaType(name = "positiveInteger")
        private Integer size;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement
    @XmlType(name = "growing", propOrder = {
            "temperature",
            "lighting",
            "irrigation"
    })
    public static class Growing {
        @XmlSchemaType(name = "positiveInteger")
        private Integer temperature;
        private Boolean lighting;
        @XmlSchemaType(name = "positiveInteger")
        private Integer irrigation;
    }
}
