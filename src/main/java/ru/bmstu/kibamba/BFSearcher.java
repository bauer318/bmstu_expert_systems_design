package ru.bmstu.kibamba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BFSearcher {
    //Список ребра - база знания
    private final LinkedList<Edge> edges;
    //Очереди открытых вершин
    private final Queue<Node> openedNodes;
    //Список закрытых вершин
    private final LinkedList<Node> closedNodes = new LinkedList<>();
    //Флаг есть решение
    private boolean fy;

    //Флаг нет решения
    private boolean fn;
    //Целевая вершина
    private Node targetNode;

    public BFSearcher(LinkedList<Edge> edges, Node sourceNode) {
        this.fy = true;
        this.fn = true;
        this.openedNodes = new LinkedList<>();
        this.openedNodes.add(sourceNode);
        this.edges = edges;
    }

    //Метод поиск
    public void search() {
        while (fy && fn) {
            int descendant = descendantsMethod();
            Node deletedNode = openedNodes.remove();
            if (descendant != 0) {
                closedNodes.add(deletedNode);
                if (!fy) {
                    closedNodes.add(this.targetNode);
                    System.out.println("Список открытых вершин\n"+this.openedNodes);
                    System.out.println("Список закрытых вершин\n"+this.closedNodes);
                    LinkedList<Edge> path = findPath();
                    printPath(path);
                    break;
                }
            } else {
                if (openedNodes.isEmpty()) {
                    fn = false;
                    System.out.println("Нет решения");
                    break;
                }
            }
        }
    }

    //Метод потомки
    private int descendantsMethod() {
        int result = 0;
        Node sampleNode = this.openedNodes.peek();
        for (Edge edge : this.edges.stream().filter(e -> e.getLabel() != 1).collect(Collectors.toList())) {
            assert sampleNode != null;
            if (sampleNode.equals(this.targetNode)) {
                fy = false;
            } else {
                if (edge.getBegin().equals(sampleNode)) {
                    if (edge.getEnd().equals(this.targetNode)) {
                        fy = false;
                        break;
                    } else {
                        this.openedNodes.add(edge.getEnd());
                        edge.setLabel(1);
                        result++;
                    }
                }
            }
        }
        return result;
    }

    //Найдет потомки родительской вершины среди закрытых
    private LinkedList<Edge> findDescendants(Node parentNode) {
        LinkedList<Edge> descendants = new LinkedList<>();
        for (Edge edge : this.edges.stream().filter(e -> e.getBegin().equals(parentNode)).collect(Collectors.toList())) {
            if (this.closedNodes.contains(edge.getEnd())) {
                descendants.add(edge);
            }
        }
        return descendants;
    }


    //Найдет путь к целевой вершины среди закрытых вершин
    private LinkedList<Edge> findPath() {
        List<LinkedList<Edge>> paths = new ArrayList<>();
        Node sourceNode = closedNodes.pop();
        LinkedList<Edge> descendants = findDescendants(sourceNode);
        for (Edge edge : descendants) {
            LinkedList<Edge> currentPath = new LinkedList<>();
            currentPath.add(edge);
            findPathFrom(edge.getEnd(), currentPath);
            if (currentPath.stream().map(Edge::getEnd).collect(Collectors.toList()).contains(this.targetNode)) {
                paths.add(currentPath);
            }
        }
        if (paths.isEmpty()) {
            return null;
        }
        return findOptimalPath(paths);
    }

    //Найдет путь к целевой вершины от родительской вершины
    private void findPathFrom(Node parentNode, LinkedList<Edge> path) {
        LinkedList<Edge> descendants = findDescendants(parentNode);
        for (Edge edge : descendants) {
            if (edge.getEnd().equals(this.targetNode)) {
                path.add(edge);
                return;
            }
            path.add(edge);
            findPathFrom(edge.getEnd(), path);
        }
    }

    /*Найдешь оптимальный путь
    * Но так как выйдем из цикла поиска после того, как нашли
    * целевую вершину с первой вершиной раскрытия, то
    * будет только один путь
    * */
    private LinkedList<Edge> findOptimalPath(List<LinkedList<Edge>> paths) {
        if (paths.isEmpty()) {
            return null;
        }
        int pathMinEdgeNumber = paths.get(0).size();
        LinkedList<Edge> optimalPath = paths.get(0);
        for (LinkedList<Edge> path : paths.stream().skip(1L).collect(Collectors.toList())) {
            if (path.size() < pathMinEdgeNumber) {
                pathMinEdgeNumber = path.size();
                optimalPath = path;
            }
        }
        return optimalPath;
    }

    private void printPath(LinkedList<Edge> path) {
        if (path == null) {
            return;
        }
        System.out.println("Путь");
        Edge firstEdge = path.getFirst();
        StringBuilder pathSb = new StringBuilder(firstEdge.getBegin().toString())
                .append("->")
                .append(firstEdge.getEnd().toString())
                .append("->");

        for (Edge edge : path.stream().skip(1L).collect(Collectors.toList())) {
            pathSb.append(edge.getEnd()).append("->");
        }
        System.out.println(pathSb.substring(0, pathSb.lastIndexOf("-")));
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }
}
