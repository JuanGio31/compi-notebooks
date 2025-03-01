package com.juangio31.notebooks.arbol;

public class Parrafo extends Instruccion {

    private final Instruccion parrafo;

    public Parrafo(Instruccion parrafo, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.parrafo = parrafo;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("<p>");
        stringBuilder.append(parrafo.interpreter(ast, contexto).toString());
        stringBuilder.append("</p>");
        return stringBuilder;
    }
}
