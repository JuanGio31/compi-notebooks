package com.juangio31.notebooks.arbol;

import java.util.HashMap;

public class Contexto {
    private HashMap<String, Object> variables;

    public Contexto() {
        this.variables = new HashMap<>();
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void agregar(Simbolo s) {
        Simbolo busqueda = (Simbolo) this.variables.get(s.getId());
        if (busqueda == null) {
            this.variables.put(s.getId(), s);
        } else {
            //actualizar el valor de la variable
            busqueda.setValor(s.getValor());
        }
    }

    public Simbolo obtenerVariable(String id) {
        return (Simbolo) this.variables.get(id);
    }
}
