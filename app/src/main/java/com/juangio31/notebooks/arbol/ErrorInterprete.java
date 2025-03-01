package com.juangio31.notebooks.arbol;

public class ErrorInterprete {
    private final String tipoError;
    private final String descripcion;
    private final int linea;
    private final int columna;

    public ErrorInterprete(String tipoError, String descripcion, int linea, int columna) {
        this.tipoError = tipoError;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "ErrorInterprete{" +
                "tipoError='" + tipoError + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", linea=" + linea +
                ", columna=" + columna +
                '}';
    }
}