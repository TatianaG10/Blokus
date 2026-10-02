package Structures;

public class Pair<E,F> {
    private E first;
    private F second;

    public Pair(E e, F f) {
        first = e;
        second = f;
    }

    public E getFirst() { return first; }
    public F getSecond() { return second; }
}
