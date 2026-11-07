package ejercicios.preguntas;

class Triple<A, B, C> {
    private final A first; private final B second; private final C third;
    Triple(A first, B second, C third) { this.first = first; this.second = second; this.third = third; }
    A getFirst() { return first; }
    B getSecond() { return second; }
    C getThird() { return third; }
}
