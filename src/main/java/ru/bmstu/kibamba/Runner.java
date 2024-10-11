package ru.bmstu.kibamba;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Runner {
    private static boolean fy = true;
    private static boolean fn = true;

    public static void main(String[] args) {
        Stack<Node> openNodes = new Stack<>();

        Node zeroNode = new Node(0); //source node
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node fourthNode = new Node(4);
        Node fifthNode = new Node(5);
        Node sixthNode = new Node(6); //target node

        List<Edge> knowledgeBase = List.of(
                new Edge(zeroNode, firstNode, "101"),
                new Edge(zeroNode, thirdNode, "103"),
                new Edge(thirdNode, fourthNode, "104"),
                new Edge(thirdNode, fifthNode, "105"),
                new Edge(firstNode, secondNode, "102"),
                new Edge(secondNode, fourthNode, "109"),
                new Edge(secondNode, sixthNode, "108"),
                new Edge(fifthNode, fourthNode, "107"),
                new Edge(sixthNode, fifthNode, "106")
        );

        List<Node> closedNodes = new ArrayList<>();

        addNode(thirdNode, openNodes); //step 0
        List<Node> sourceNodeDescendants = getNodeChildren(thirdNode, knowledgeBase);
        addNode(sourceNodeDescendants.get(0), openNodes);

        while (fy && fn) {
            descendantsMethod(knowledgeBase, openNodes, sixthNode);
            if (!fy) {
                break;
            } else {
                Node openingNode = openNodes.peek();
                List<Node> openingNodeDescendants = getNodeChildren(openingNode, knowledgeBase);
                if (openingNodeDescendants.isEmpty()) {
                    if (!openNodes.isEmpty()) {
                        Node deletedNode = openNodes.pop();
                        deletedNode.setFlag(-1);
                        closedNodes.add(deletedNode);
                    } else {
                        fn = false;
                    }
                }
            }
        }

        if (!fn) {
            System.out.println("Нет решения");
        }
        if (!fy) {
            StringBuilder result = new StringBuilder();
            createResultToPrint(openNodes, result);
            System.out.println("Путь\n" + result.substring(0, result.lastIndexOf("-")));
        }


    }

    private static void createResultToPrint(Stack<Node> openNodes, StringBuilder result) {
        if (openNodes.isEmpty()) {
            return;
        }
        Node nodeToPrint = openNodes.pop();

        createResultToPrint(openNodes, result);

        result.append(nodeToPrint.getNumber()).append("->");

    }

    private static void descendantsMethod(List<Edge> knowledgeBase, Stack<Node> openNodes, Node targetNode) {
        for (Edge edge : knowledgeBase) {
            Node openingNode = openNodes.peek();
            if (isSampleEdge(edge, openingNode)) {
                Node closedNode = edge.getEnd();
                if (isCurrentNodeTargetNode(closedNode, targetNode)) {
                    fy = false;
                    openNodes.push(closedNode);
                    break;
                } else {
                    if (closedNode.getFlag() != -1) {
                        addNode(closedNode, openNodes);
                        edge.setLabel(1);
                        break;
                    }
                }
            }
        }
    }

    private static void addNode(Node sourceNode, Stack<Node> openNodes) {
        openNodes.push(sourceNode);
    }

    private static List<Node> getNodeChildren(Node node, List<Edge> knowledgeBase) {
        List<Node> result = new ArrayList<>();
        knowledgeBase.forEach(edge -> {
            if (edge.getBegin().equals(node)) {
                result.add(edge.getEnd());
            }
        });

        return result;
    }

    private static boolean isCurrentNodeTargetNode(Node currentNode, Node targetNode) {
        return targetNode.equals(currentNode);
    }

    private static boolean isSampleEdge(Edge edge, Node openingNode) {
        return edge.getBegin().equals(openingNode) && edge.getLabel() == 0;
    }
}
