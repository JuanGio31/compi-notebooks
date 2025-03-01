package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class Normal extends Instruccion {
    private final LinkedList<Instruccion> textos;

    public Normal(LinkedList<Instruccion> textos, int linea, int columna) {
        super(Tipo.TEXTO, linea, columna);
        this.textos = textos;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        String temp = "";
        for (int i = 0; i < textos.size(); i++) {
            var txt = textos.get(i).interpreter(ast, contexto);
            temp += txt.toString();
        }
        return temp;
    }
}
