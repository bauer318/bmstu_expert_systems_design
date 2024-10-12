package ru.bmstu.kibamba;

//Вершина
public class Node {
    private int number;
    private int flag;

    public Node(int number){
        this.number = number;
        flag = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return number == node.number && flag == node.flag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (number ^(number >>> 32));
        result = prime * result + (flag ^(flag>>> 0));

        return result;
    }

    @Override
    public String toString(){
        return String.valueOf(this.number);
    }
}
