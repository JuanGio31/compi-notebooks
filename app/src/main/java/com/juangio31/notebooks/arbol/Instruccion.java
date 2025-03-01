package com.juangio31.notebooks.arbol;

abstract public class Instruccion {
    Tipo tipo;
    int linea;
    int columna;

    public Instruccion(Tipo tipo, int linea, int columna) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public abstract Object interpreter(AST ast, Contexto contexto);
}
