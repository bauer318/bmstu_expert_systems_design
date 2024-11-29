package ru.bmstu.kibamba;

import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Node node_1 = new Node(1);
        Node node_2 = new Node(2);
        Node node_3 = new Node(3);
        Node node_4 = new Node(4);
        Node node_5 = new Node(5);
        Node node_6 = new Node(6);
        Node node_7 = new Node(7);
        Node node_8 = new Node(8);
        Node node_9 = new Node(9);
        Node node_10 = new Node(10);
        Node node_11 = new Node(11);
        Node node_12 = new Node(12);
        Node node_13 = new Node(13);
        Node node_14 = new Node(14);
        Node node_15 = new Node(15);
        Node node_16 = new Node(16);
        Node node_17 = new Node(17);
        Node node_18 = new Node(18);
        Node node_19 = new Node(19);
        Node node_20 = new Node(20);
        Node node_21 = new Node(21);
        Node node_22 = new Node(22);
        Node node_23 = new Node(23);
        Node node_24 = new Node(24);




        LinkedList<Node> inputData = new LinkedList<>(List.of(
                node_5, node_6,node_1,node_16, node_20, node_21, node_7, node_12
        ));

        LinkedList<Edge> edgeLinkedList = new LinkedList<>(List.of(
                new Edge(node_3, arrayOf(List.of(node_1, node_2)), 101),
                new Edge(node_7, arrayOf(List.of(node_2, node_3, node_4)), 102),
                new Edge(node_4, arrayOf(List.of(node_5, node_6)), 103),
                new Edge(node_3, arrayOf(List.of(node_8, node_23)), 104),
                new Edge(node_13, arrayOf(List.of(node_7, node_9)), 105),
                new Edge(node_9, arrayOf(List.of(node_4, node_16, node_10)), 106),
                new Edge(node_10, arrayOf(List.of(node_11, node_12)), 107),
                new Edge(node_24, arrayOf(List.of(node_19, node_14)), 108),
                new Edge(node_13, arrayOf(List.of(node_9, node_19)), 109),
                new Edge(node_9, arrayOf(List.of(node_10, node_15)), 110),
                new Edge(node_19, arrayOf(List.of(node_15, node_17)), 111),
                new Edge(node_15, arrayOf(List.of(node_11, node_18)), 112),
                new Edge(node_11, arrayOf(List.of(node_20, node_21)), 113),
                new Edge(node_19, arrayOf(List.of(node_17, node_22)), 114),
                new Edge(node_17, arrayOf(List.of(node_12, node_18, node_22)), 115)
        ));

        GraphAndOrDFSSearcher dfsSearcher = new GraphAndOrDFSSearcher(edgeLinkedList,node_13,inputData);
        dfsSearcher.search();
    }

    private static Node[] arrayOf(List<Node> nodes) {
        return nodes.toArray(new Node[0]);
    }
}
