package com.juangio31.notebooks.arbol;

public class AccesoVariable extends Instruccion {
    private String id;

    public AccesoVariable(String id, int linea, int columna) {
        super(Tipo.VOID, linea, columna);
        this.id = id;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        var valor = contexto.obtenerVariable(this.id);
        if (valor == null) {
            return new ErrorInterprete("Semantico", "La variable no existe", this.linea, this.columna);
        }
        this.tipo = valor.getTipo();
        return valor.getValor();
    }
}