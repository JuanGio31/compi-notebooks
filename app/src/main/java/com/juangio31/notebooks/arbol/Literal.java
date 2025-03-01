package com.juangio31.notebooks.arbol;

public class Literal extends Instruccion {
    private final Object valor;

    public Literal(Object valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valor = valor;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        return this.valor;
    }
}
