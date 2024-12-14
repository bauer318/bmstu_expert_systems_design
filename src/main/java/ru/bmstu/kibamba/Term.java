package ru.bmstu.kibamba;

public class Term {
    private Variable variable;
    private String constant;
    private boolean isVariable;

    public Term(Variable variable){
        this.isVariable = true;
        this.variable = variable;
    }

    public Term(String constant){
        this.isVariable = false;
        this.constant = constant;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public boolean isVariable() {
        return isVariable;
    }

    public void setVariable(boolean variable) {
        isVariable = variable;
    }
}
