package ru.bmstu.kibamba;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
//Класс поиска
public class GraphAndOrDFSSearcher {
    //База правил
    private final LinkedList<Edge> ruleBase;
    //Флаг есть решения
    private boolean fy;
    //Флаг нет решения
    private boolean fn;
    //Целевая вершина
    private final Node targetNode;
    //Стек открытых вершин
    private final Stack<Node> openedNodes = new Stack<>();
    //Стек открытых правил
    private final Stack<Edge> openedRules = new Stack<>();
    //Список закрытых вершин
    private final LinkedList<Node> closedNodes;
    //Список закрытых правил
    private final LinkedList<Edge> closedRules = new LinkedList<>();
    //Список запрещенных вершин
    private final LinkedList<Node> forbiddenNodes = new LinkedList<>();
    //Список запрещенных правил
    private final LinkedList<Edge> forbiddenRules = new LinkedList<>();

    public GraphAndOrDFSSearcher(LinkedList<Edge> ruleBase, Node targetNode,
                                 LinkedList<Node> inputNodes) {
        this.closedNodes = inputNodes;
        this.targetNode = targetNode;
        this.openedNodes.push(targetNode);
        this.fy = true;
        this.fn = true;
        this.ruleBase = ruleBase;
    }

    //метод ПОИСК
    public void search() {
        setClosedNodesFlagToOne();
        //Print current closed node
        System.out.println("Текущие закрытые вершины " + closedNodes);
        while (this.fy && this.fn) {
            System.out.println("-----------------------------");
            System.out.println("Начало поиска");
            System.out.println("Закрытые вершины " + closedNodes);
            System.out.println("Закрытые правила " + closedRules);

            //Вызываем метод ПОТОМКИ
            boolean existDescendant = descendantsMethod();
            if (!this.fn) {
                System.out.println("Решение найдено");
                System.out.println("Закрытые правила " + closedRules);
                break;
            } else if (!existDescendant && this.openedNodes.size() < 2) {
                //Иначе если нет потомков и в стеке открытых вершин только
                //одна вершина - целевая вершина
                this.fy = false;
                System.out.println("Нет решения");
                break;
            } else if (!existDescendant) {
                //Если в стеке открытых вершин более 2-х вершин, при
                //этом нет новых потомком, то пришли в тупик
                System.out.println("Метод возврата");
                backtracking();
            }
        }


    }

    private void setClosedNodesFlagToOne() {
        this.closedNodes.forEach(closedNode -> {
            closedNode.setFlag(1);
        });
    }

    //метод ПОТОМКИ
    private boolean descendantsMethod() {
        //Флаг нашли правило или нет
        boolean isRuleWasFound = false;

        for (Edge rule : this.ruleBase) {
            System.out.println("Текущее правило " + rule);
            //Если правило уже было пройдено, то пропускаем
            if (rule.getLabel() != 0) {
                System.out.println("Правило уже было пройдено");
                continue;
            }
            //Определим новые подцели и записываем их в стек открытых вершин
            Node currentSubGoal = this.openedNodes.peek();
            if (rule.getOutputNode().equals(currentSubGoal) && !this.forbiddenRules.contains(rule)) {
                //Отмечать правило как посещенное
                rule.setLabel(2);
                this.openedRules.push(rule);
                System.out.println("Правило " + rule + " добавлено в открытые");
                boolean areAllInputNodesClosed = false;

                for (Node inputNode : rule.getInputNodes()) {
                    //Если вершина из входных вершин не закрыта
                    if (!this.closedNodes.contains(inputNode)) {
                        //Добавляем ее в голову стека открытых вершин
                        this.openedNodes.push(inputNode);
                        System.out.println("Вершина " + inputNode +
                                " добавлена в открытые и стала новая целевая");
                        areAllInputNodesClosed = true;
                    }
                }
                //Если все входные вершины нового правила закрыты
                if (!areAllInputNodesClosed) {
                    System.out.println("Новой целевой вершины не было добавлено. " +
                            "Все входные вершины нового правила закрыты");
                    if (rule.getOutputNode().equals(this.targetNode)) {
                        this.fn = false;
                        System.out.println("Решение найдено");
                    } else {
                        System.out.println("Разметка");
                        label();
                        //Условие выхода из разметки
                        //Если есть входная вершина не в закрытых
                        isRuleWasFound = true;
                    }

                } else {
                    isRuleWasFound = true;
                    System.out.println("Найдено новое правило " + rule);
                    System.out.println("Открытые правила " + this.openedRules);
                    System.out.println("Открытые вершины " + this.openedNodes);
                }
                break;
            } else {
                System.out.println("Это не ведет к достижению текущей цели " + this.targetNode);
            }
        }
        return isRuleWasFound;

    }

    //Метод РАЗМЕТКА
    private void label() {
        //Определение доказанных правил и дополнения списка закрытых вершин
        boolean isModuleProved = true;
        while (isModuleProved) {
            //Удаляем из головы стеков открытых правил и вершин как текущее
            //правило и текущая подцель
            Edge currentSubRule = this.openedRules.pop();
            Node currentSubGoal = this.openedNodes.pop();

            //Добавляем их списки закрытых
            this.closedRules.add(currentSubRule);
            this.closedNodes.add(currentSubGoal);

            currentSubRule.setLabel(1);
            currentSubGoal.setFlag(1);

            System.out.println("Правило " + currentSubRule + " и вершина " + currentSubGoal +
                    " добавлены в списках закрытых ");

            if (currentSubGoal.equals(this.targetNode)) {
                this.fn = false;
                System.out.println("Решение найдено");
                break;
            } else {
                //Извлечение без удаления из головы стека открытых правил как
                //текущее правило
                System.out.println("Текущая подцель " + currentSubGoal + " не цель");
                Edge nextRule = this.openedRules.peek();
                boolean isInClosedNodes = true;

                for (Node nextRuleInputNode : nextRule.getInputNodes()) {
                    //Если хотя бы одна из входных вершин правила не закрыта
                    if (!this.closedNodes.contains(nextRuleInputNode)) {
                        isInClosedNodes = false;
                        System.out.println("Нет вершины " + nextRuleInputNode + " из "
                                + Arrays.toString(nextRule.getInputNodes()) +
                                " в закрытых для правила " + nextRule);
                        break;
                    }
                }
                //Если не все вершины текущего правила закрыты
                if (!isInClosedNodes) {
                    isModuleProved = false;
                } else {
                    System.out.println("Все вершины " + Arrays.toString(nextRule.getInputNodes()) +
                            "  текущего правила " + nextRule + " закрытые");
                }
            }
        }
    }

    //метод ВОЗВРАТА
    private void backtracking() {
        //Удаляем их головы стеков открытых правили и вершин как текущее
        //правило и текущая подцель
        Edge currentSubRule = this.openedRules.pop();
        Node currentSubGoal = this.openedNodes.pop();

        //Помечаем их как запрещенные
        currentSubRule.setLabel(-1);
        currentSubGoal.setFlag(-1);

        //Добавляем их в списки запрещенных
        this.forbiddenRules.add(currentSubRule);
        this.forbiddenNodes.add(currentSubGoal);
        System.out.println("Правило " + currentSubRule + " и вершина " + currentSubGoal +
                " добавлены в списках запрещенных ");

        //Удаляем входные вершины запрещенного правила из стека открытых
        for (Node closedRuleInputNode : currentSubRule.getInputNodes()) {
            if (!this.closedNodes.contains(closedRuleInputNode)) {
                this.openedNodes.remove(closedRuleInputNode);
                System.out.println("Удалена вершина " + closedRuleInputNode + " из стека открытых");
            }
        }
    }
}
