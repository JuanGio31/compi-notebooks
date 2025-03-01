package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class Header extends Instruccion {
    private final Instruccion nivelHeader;
    private final LinkedList<Instruccion> texto;
    private final Instruccion contenido;

    public Header(Instruccion nivelHeader, LinkedList<Instruccion> texto, int linea, int columna) {
        super(Tipo.HEADER, linea, columna);
        this.nivelHeader = nivelHeader;
        this.texto = texto;
        this.contenido = null;
    }

    public Header(Instruccion nivelHeader, Instruccion contenido, int linea, int columna) {
        super(Tipo.HEADER, linea, columna);
        this.nivelHeader = nivelHeader;
        this.contenido = contenido;
        this.texto = null;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        var q = nivelHeader.interpreter(ast, contexto);
        int nivel = q.toString().trim().length();
        if (texto == null) {
            var temp = this.contenido.interpreter(ast, contexto);
            return "<h" + nivel + ">" + temp.toString() + "</h" + nivel + ">";
        }

        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("<h").append(nivel).append(">");
        for (Instruccion e : texto) {
            stringBuilder.append(e.interpreter(ast, contexto).toString()); //interpretara una Literal
        }
        stringBuilder.append("</h").append(nivel).append(">");
        return stringBuilder;
    }
}
