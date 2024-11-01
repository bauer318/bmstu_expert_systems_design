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
        Node node_31 = new Node(31);
        Node node_32 = new Node(32);
        Node node_33 = new Node(33);

        LinkedList<Node> inputData = new LinkedList<>(List.of(
                node_5, node_6, node_12, node_13,node_10
        ));

        LinkedList<Edge> edgeLinkedList = new LinkedList<>(List.of(
                new Edge(node_33, arrayOf(List.of(node_21, node_15)), 108),
                new Edge(node_14, arrayOf(List.of(node_7, node_2, node_4)), 102),
                new Edge(node_3, arrayOf(List.of(node_8, node_31)), 104),
                new Edge(node_3, arrayOf(List.of(node_1, node_2)), 101),
                new Edge(node_4, arrayOf(List.of(node_5, node_6)), 103),
                new Edge(node_9, arrayOf(List.of(node_4, node_10, node_11)), 106),
                new Edge(node_11, arrayOf(List.of(node_12, node_13)), 107),
                new Edge(node_9, arrayOf(List.of(node_18, node_32)), 111),
                new Edge(node_14, arrayOf(List.of(node_9, node_21)), 110),
                new Edge(node_21, arrayOf(List.of(node_19, node_20)), 112),
                new Edge(node_15, arrayOf(List.of(node_16, node_17)), 108)
        ));

        GraphAndOrBFSSearcher bfsSearcher = new GraphAndOrBFSSearcher(edgeLinkedList, inputData, node_14);
        bfsSearcher.search();
    }

    private static Node[] arrayOf(List<Node> nodes) {
        return nodes.toArray(new Node[0]);
    }
}
