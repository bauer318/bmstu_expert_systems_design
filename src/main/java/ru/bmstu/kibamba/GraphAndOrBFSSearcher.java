package ru.bmstu.kibamba;

import java.util.LinkedList;

public class GraphAndOrBFSSearcher {
    private final LinkedList<Edge> ruleBase;
    private final LinkedList<Node> closedNodes;
    private final LinkedList<Edge> closedRules = new LinkedList<>();
    private boolean fy;
    private boolean fn;
    private final Node targetNode;

    public GraphAndOrBFSSearcher(LinkedList<Edge> ruleBase, LinkedList<Node> inputData, Node targetNode) {
        this.fy = true;
        this.fn = true;
        this.ruleBase = ruleBase;
        this.closedNodes = inputData;
        this.targetNode = targetNode;
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
                    boolean isActiveEdge = true;
                    for (Node edgeInputNode : edge.getInputNodes()) {
                        if (!this.closedNodes.contains(edgeInputNode)) {
                            isActiveEdge = false;
                            break;
                        }
                    }
                    if (isActiveEdge) {
                        edge.setLabel(1);
                        edge.getOutputNode().setFlag(1);
                        this.closedNodes.add(edge.getOutputNode());
                        this.closedRules.add(edge);
                        activeRulesNumber++;
                        if (edge.getOutputNode().equals(this.targetNode)) {
                            this.fy = false;
                        }
                    }
                }
            } else {
                break;
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
            } else if (activeRulesNumber == 0) {
                System.out.println("Нет решения");
                fn = false;
            }
        }
    }
}
