package ru.bmstu.kibamba;

//Ребро
public class Edge {
    private Node begin;
    private Node end;
    private int number;
    private int label;

    public Edge(Node begin, Node end, int number) {
        this.begin = begin;
        this.end = end;
        this.number = number;
        this.label = 0;
    }

    public Node getBegin() {
        return begin;
    }

    public void setBegin(Node begin) {
        this.begin = begin;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.begin + "->" + this.end;
    }
}
