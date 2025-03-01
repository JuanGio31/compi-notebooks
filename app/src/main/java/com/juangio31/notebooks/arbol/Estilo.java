package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class Estilo extends Instruccion {
    private final LinkedList<Instruccion> texto;

    public Estilo(LinkedList<Instruccion> texto, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.texto = texto;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        String inicio, fin;
        if (this.tipo == Tipo.BOLD) {
            inicio = "<b>";
            fin = "</b>";
        } else if (this.tipo == Tipo.BOLD_ITALIC) {
            inicio = "<b><i>";
            fin = "</i></b>";
        } else {
            inicio = "<i>";
            fin = "</i>";
        }

        stringBuilder.append(inicio);
        for (Instruccion e : texto) {
            stringBuilder.append(e.interpreter(ast, contexto).toString()); //interpretara una Literal
        }
        stringBuilder.append(fin);
        return stringBuilder;
    }
}
