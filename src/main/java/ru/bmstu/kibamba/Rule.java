package ru.bmstu.kibamba;

import java.util.List;

//Правило
public class Rule {
    //Входные атомы
    private List<Predicate> inputAtoms;
    //Количество входных атомов
    private int inputAtomsNumber;
    //Выходной атом
    private Predicate outputAtom;
    //Номер правила
    private int number;
    //Флаг
    private int label;


    public Rule(List<Predicate> inputAtoms, Predicate outputAtom, int number) {
        this.inputAtoms = inputAtoms;
        this.outputAtom = outputAtom;
        this.number = number;
        this.inputAtomsNumber = inputAtoms.size();
    }

    public List<Predicate> getInputAtoms() {
        return inputAtoms;
    }

    public int getInputAtomsNumber() {
        return inputAtomsNumber;
    }

    public void setInputAtomsNumber(int inputAtomsNumber) {
        this.inputAtomsNumber = inputAtomsNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setInputAtoms(List<Predicate> inputAtoms) {
        this.inputAtoms = inputAtoms;
    }

    public Predicate getOutputAtom() {
        return outputAtom;
    }

    public void setOutputAtom(Predicate outputAtom) {
        this.outputAtom = outputAtom;
    }
}
