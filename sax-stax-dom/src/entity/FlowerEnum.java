package entity;

public enum FlowerEnum {
    FLOWERS("flowers"),
    FLOWER("flower"),
    SOIL("soil"),
    NAME("name"),
    ORIGIN("origin"),
    STEM("stem"),
    SHEET("sheet"),
    SIZE("size"),
    TEMPERATURE("temperature"),
    LIGHTING("lighting"),
    IRRIGATION("irrigation"),
    DATE("date"),
    MULTIPLYING("multiplying"),
    VISUAL("visual"),
    GROWING("growing");

    private String value;

    private FlowerEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}