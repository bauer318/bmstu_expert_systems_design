package ru.bmstu.kibamba;

import java.util.List;
import java.util.stream.Collectors;

public class DirectLogicalInferenceEngine {
    //Список закрытых вершин - рабочая память
    private List<Predicate> closedAtoms;
    //Список правил
    private List<Rule> rules;
    //Целевой атом
    private Predicate targetAtom;
    //флаг есть решение
    private boolean fy;
    //флаг нет решения
    private boolean fn;

    public DirectLogicalInferenceEngine(List<Predicate> facts, List<Rule> rules, Predicate targetAtom) {
        this.closedAtoms = facts;
        this.rules = rules;
        this.targetAtom = targetAtom;
        this.fy = true;
        this.fn = true;
    }

    public void search() {
        while (fy && fn) {
            for (Rule rule : this.rules.
                    stream().filter(r -> r.getLabel() != 1).toList()) {
                //Цель шага - проверить покрытие фактов
                if(isContainsAllRuleInputAtoms(rule)){
                    //Если покрытие, то выходной атом правила - в факты
                    this.closedAtoms.add(rule.getOutputAtom());
                    rule.setLabel(1);
                }
            }
        }
    }

    private boolean isContainsAllRuleInputAtoms(Rule rule) {

        for (Predicate inputAtom : rule.getInputAtoms()) {
            if (!this.closedAtoms.contains(inputAtom)) {
                return false;
            }
        }
        return true;
    }
}
