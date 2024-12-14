package ru.bmstu.kibamba;

public class Variable {
    private String name;
    private String value;
    private int label;

    public Variable(String name, int label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
