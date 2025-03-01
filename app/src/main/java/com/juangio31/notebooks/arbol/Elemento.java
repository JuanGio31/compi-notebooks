package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class Elemento extends Instruccion {
    private LinkedList<Instruccion> contenido;
    private Instruccion orden;

    public Elemento(Instruccion orden, LinkedList<Instruccion> contenido, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.contenido = contenido;
        this.orden = orden;
    }

    public Elemento(LinkedList<Instruccion> contenido, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.contenido = contenido;
        this.orden = null;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
//        if (orden != null) {
//            var aux = orden.interpreter(ast, contexto);
//            stringBuilder.append(aux);
//        }
        stringBuilder.append("<li>");
        String aux = "";
        for (Instruccion e : contenido) {
            var temp = e.interpreter(ast, contexto);
            //stringBuilder.append(temp.toString()); //interpretara una Literal
            aux += temp.toString();
        }
        stringBuilder.append(aux);
        stringBuilder.append("</li>");
        return stringBuilder;
    }
}
