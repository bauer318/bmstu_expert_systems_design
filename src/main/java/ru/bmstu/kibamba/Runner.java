package ru.bmstu.kibamba;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Runner {
    //Есть решение
    private static boolean fy = true;
    //Нет решения
    private static boolean fn = true;
    //Список закрытых вершин
    private static List<Node> closedNodes = new ArrayList<>();

    public static void main(String[] args) {
        //Список открытых вершин - стек
        Stack<Node> openNodes = new Stack<>();

        Node zeroNode = new Node(0); //Вершина источника
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node fourthNode = new Node(4);
        Node fifthNode = new Node(5);
        Node sixthNode = new Node(6); //Целевая вершина

        List<Edge> knowledgeBase = List.of(
                new Edge(zeroNode, firstNode, "101"),
                new Edge(zeroNode, thirdNode, "103"),
                new Edge(thirdNode, fourthNode, "104"),
                new Edge(firstNode, secondNode, "102"),
                new Edge(thirdNode, fifthNode, "105"),
                new Edge(secondNode, fourthNode, "109"),
                new Edge(secondNode, sixthNode, "108"),
                new Edge(fifthNode, fourthNode, "107"),
                new Edge(sixthNode, fifthNode, "106")
        );


        //Нуловой шаг
        addNode(zeroNode, openNodes);
        List<Node> sourceNodeDescendants = getNodeChildren(zeroNode, knowledgeBase);
        addNode(sourceNodeDescendants.get(0), openNodes);

        //Поиск в глубину
        while (fy && fn) {
            descendantsMethod(knowledgeBase, openNodes, sixthNode);
            if (!fy) {
                break;
            } else {
                if (openNodes.isEmpty()) {
                    fn = false;
                    break;
                }
                Node openingNode = openNodes.peek();
                List<Node> openingNodeDescendants = getNodeChildren(openingNode, knowledgeBase);
                if (openingNodeDescendants.isEmpty()) {
                    if (!openNodes.isEmpty()) {
                        Node deletedNode = openNodes.pop();
                        deletedNode.setFlag(-1);
                        closedNodes.add(deletedNode);
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

    //Метод потомки
    private static void descendantsMethod(List<Edge> knowledgeBase, Stack<Node> openNodes, Node targetNode) {
        for (Edge edge : knowledgeBase) {
            if (openNodes.isEmpty()) {
                return;
            }
            Node openingNode = openNodes.peek();
            if (isSampleEdge(edge, openingNode)) {
                Node closedNode = edge.getEnd();
                if (isCurrentNodeTargetNode(closedNode, targetNode)) {
                    fy = false;
                    openNodes.push(closedNode);
                    break;
                } else {
                    if (!closedNodes.contains(closedNode)) {
                        addNode(closedNode, openNodes);
                        edge.setLabel(1);
                        break;
                    }
                }
            }
        }
    }

    //Добавляет вершину в голову стека
    private static void addNode(Node sourceNode, Stack<Node> openNodes) {
        openNodes.push(sourceNode);
    }

    //Получение потомков вершины (незапрещенные)
    private static List<Node> getNodeChildren(Node node, List<Edge> knowledgeBase) {
        List<Node> result = new ArrayList<>();
        knowledgeBase.forEach(edge -> {
            if (edge.getBegin().equals(node) && !closedNodes.contains(edge.getEnd())) {
                result.add(edge.getEnd());
            }
        });

        return result;
    }

    //Является ли текущая вершина целевой 
    private static boolean isCurrentNodeTargetNode(Node currentNode, Node targetNode) {
        return targetNode.equals(currentNode);
    }

    //Является ли ребро результат поиски по образцу
    private static boolean isSampleEdge(Edge edge, Node openingNode) {
        return edge.getBegin().equals(openingNode) && edge.getLabel() == 0;
    }
}
