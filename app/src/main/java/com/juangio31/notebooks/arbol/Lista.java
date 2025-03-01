package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class Lista extends Instruccion {
    private final LinkedList<Instruccion> elementos;

    public Lista(LinkedList<Instruccion> elementos, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.elementos = elementos;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        String tipoLista;
        if (tipo == Tipo.OL) {
            tipoLista = "ol>"; //lista ordenada
        } else {
            tipoLista = "ul>"; //lista no ordenada
        }
        stringBuilder.append("<").append(tipoLista);
        for (Instruccion e : elementos) {
            var temp = e.interpreter(ast, contexto);
            stringBuilder.append(temp.toString());
        }
        stringBuilder.append("</").append(tipoLista);
        return stringBuilder;
    }
}