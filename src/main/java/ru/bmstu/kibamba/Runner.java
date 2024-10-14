package ru.bmstu.kibamba;

import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Node zeroNode = new Node(0); //Вершина источника
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node fourthNode = new Node(4);
        Node fifthNode = new Node(5);
        Node sixthNode = new Node(6); //Целевая вершина

        LinkedList<Node> inputData = new LinkedList<>(List.of(
                zeroNode, thirdNode
        ));

        LinkedList<Edge> edgeLinkedList = new LinkedList<>(List.of(
                new Edge(secondNode, fourthNode, 109),
                new Edge(secondNode, sixthNode, 108),
                new Edge(fifthNode, fourthNode, 107),
                new Edge(fifthNode,sixthNode, 106),
                new Edge(thirdNode, fifthNode, 105),
                new Edge(thirdNode, fourthNode, 104),
                new Edge(zeroNode, thirdNode, 103),
                new Edge(firstNode, secondNode, 102),
                new Edge(zeroNode, firstNode, 101)
        ));

        GraphAndOrBFSSearcher bfsSearcher = new GraphAndOrBFSSearcher(edgeLinkedList, inputData, sixthNode);
        bfsSearcher.search();
    }
}
