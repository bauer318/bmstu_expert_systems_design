package ru.bmstu.kibamba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GraphAndOrBFSSearcher {
    private LinkedList<Edge> ruleBase;
    private LinkedList<Node> closedNodes;
    private LinkedList<Edge> closedRules = new LinkedList<>();
    private boolean fy;
    private boolean fn;
    private Node targetNode;

    public GraphAndOrBFSSearcher(LinkedList<Edge> ruleBase, LinkedList<Node> inputData, Node targetNode) {
        this.fy = true;
        this.fn = true;
        this.ruleBase = ruleBase;
        this.closedNodes = inputData;
        this.targetNode = targetNode;
    }

    //Не оптимальное определение пути
    private ArrayList<LinkedList<Edge>> findPath() {
        List<Edge> edgeEndingTargetNode = this.closedRules.stream().filter(
                rule -> rule.getEnd().equals(this.targetNode)
        ).toList();
        ArrayList<LinkedList<Edge>> paths = new ArrayList<>();
        for (Edge targetEdge : edgeEndingTargetNode) {
            LinkedList<Edge> path = new LinkedList<>();
            path.add(targetEdge);
            Optional<Edge> edge = getEdgeEndingWith(targetEdge.getBegin());
            while (edge.isPresent()) {
                addPath(edge.get(), path);
                edge = getEdgeEndingWith(edge.get().getBegin());
            }
            paths.add(path);
        }

        return paths;
    }


    private void addPath(Edge edge, LinkedList<Edge> path) {
        path.add(edge);
        this.closedRules.remove(edge);
    }

    private Optional<Edge> getEdgeEndingWith(Node endNode) {
        return this.closedRules.stream().filter(e -> e.getEnd().equals(endNode)).findFirst();
    }

    private void printPath(ArrayList<LinkedList<Edge>> paths){
        System.out.println("Путь");
        for(LinkedList<Edge> path : paths){
            printPath(path);
        }
    }

    private void printPath(LinkedList<Edge> path){
        List<Edge> pathList = path.stream().toList();
        var lastIndex = pathList.size()-1;
        System.out.print(pathList.get(lastIndex).getBegin());
        for(int i = lastIndex; i>0; i--){
            System.out.print("->"+pathList.get(i).getEnd());
        }
        System.out.print("->"+pathList.get(0).getEnd());
    }

    private int descendantsMethod() {
        int activeRulesNumber = 0;
        for (Edge edge : this.ruleBase) {
            if (this.fy) {
                if (this.closedNodes.contains(this.targetNode)) {
                    break;
                }
                //Поиск активного правила
                if (edge.getLabel() == 0) {
                    //условие покрытия входа правила
                    if (this.closedNodes.contains(edge.getBegin())) {
                        edge.setLabel(1);
                        edge.getEnd().setFlag(1);
                        this.closedNodes.add(edge.getEnd());
                        this.closedRules.add(edge);
                        activeRulesNumber++;
                        if (edge.getEnd().equals(this.targetNode)) {
                            this.fy = false;
                        }
                    }
                }
            }
        }
        return activeRulesNumber;
    }

    public void search() {
        while (this.fy && this.fn) {
            int activeRulesNumber = descendantsMethod();
            if (!this.fy) {
                //Вывод список закрытых правил
                System.out.println("Список закрытых правил");
                System.out.println(closedRules);
                //Вывод список закрытых вершин
                System.out.println("Список закрытых вершин");
                System.out.println(closedNodes);
                //Нахождение пути
                ArrayList<LinkedList<Edge>> path = findPath();
                //Вывод путь
                printPath(path);
            } else if (activeRulesNumber == 0) {
                System.out.println("Нет решения");
                fn = false;
            }
        }
    }
}
