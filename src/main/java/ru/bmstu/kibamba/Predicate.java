package ru.bmstu.kibamba;

import java.util.Arrays;
import java.util.Objects;

public class Predicate {
    private String name;
    private int argumentsNumber;
    private Term[] terms;

    public Predicate(String name, int argumentsNumber, Term[] terms) {
        this.name = name;
        this.argumentsNumber = argumentsNumber;
        this.terms = terms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArgumentsNumber() {
        return argumentsNumber;
    }

    public void setArgumentsNumber(int argumentsNumber) {
        this.argumentsNumber = argumentsNumber;
    }

    public Term[] getTerms() {
        return terms;
    }

    public void setTerms(Term[] terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predicate predicate = (Predicate) o;
        return argumentsNumber == predicate.argumentsNumber &&
                Objects.equals(name, predicate.name) &&
                Arrays.equals(terms, predicate.terms);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, argumentsNumber);
        result = 31 * result + Arrays.hashCode(terms);
        return result;
    }
}
