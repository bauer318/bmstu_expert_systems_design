package ru.bmstu.kibamba;

//Ребро
public class Edge {
    //Массив входных вершин
    private final Node[] inputNodes;
    //Выходная вершина
    private final Node outputNode;
    //Номер
    private final int number;
    //Метка
    private int label;

    public Edge(Node outputNode, Node[] inputNodes, int number) {
        this.inputNodes = inputNodes;
        this.outputNode = outputNode;
        this.number = number;
        this.label = 0;
    }

    public Node[] getInputNodes() {
        return inputNodes;
    }
    public Node getOutputNode() {
        return outputNode;
    }
    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("("
                .concat(String.valueOf(this.number)).concat(" : ").concat(this.outputNode.toString()));
        result.append(" <- [");
        for (Node node : this.inputNodes) {
            result.append(node.toString()).append(",");
        }
        result = new StringBuilder(result.substring(0, result.length() - 1));
        result.append("]").append(")");

        return result.toString();
    }
}
