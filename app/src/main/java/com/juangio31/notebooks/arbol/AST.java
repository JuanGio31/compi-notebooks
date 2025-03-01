package com.juangio31.notebooks.arbol;

import java.util.LinkedList;

public class AST {
    private LinkedList<Instruccion> instruccions;
    private Contexto contexto;  //alojamiento de las variables

    public AST(LinkedList<Instruccion> instruccions) {
        this.instruccions = instruccions;
        this.contexto = new Contexto();
    }

    public LinkedList<Instruccion> getInstruccions() {
        return instruccions;
    }

    public void setInstruccions(LinkedList<Instruccion> instruccions) {
        this.instruccions = instruccions;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }
}
