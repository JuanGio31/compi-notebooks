package com.juangio31.notebooks.arbol;

public class Asignacion extends Instruccion {
    private String id;
    private Instruccion expr;

    public Asignacion(String id, Instruccion expr, int linea, int columna) {
        super(Tipo.VOID, linea, columna);
        this.id = id;
        this.expr = expr;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        var valorInterpretado = this.expr.interpreter(ast, contexto);

        if (valorInterpretado instanceof ErrorInterprete) {
            return valorInterpretado;
        }

        if (expr.tipo == Tipo.INT || expr.tipo == Tipo.DOUBLE) {
            var sym = new Simbolo(expr.tipo, this.id, valorInterpretado);
            contexto.agregar(sym);
            return null;
        }
        return new ErrorInterprete("Semantico", "No es numero", this.linea, this.columna);
    }
}
