package com.juangio31.notebooks.arbol;

public class Aritmeticos extends Instruccion {
    private Instruccion operando_1;
    private Instruccion operando_2;
    private Operacion operacion;
    private Instruccion opUnico;

    //Operacion -> negacion ej: -2, -6
    public Aritmeticos(Instruccion opUnico, Operacion operacion, int linea, int columna) {
        super(Tipo.INT, linea, columna);
        this.opUnico = opUnico;
        this.operacion = operacion;
    }

    public Aritmeticos(Instruccion operando_1, Instruccion operando_2, Operacion operacion, int linea, int columna) {
        super(Tipo.INT, linea, columna);
        this.operando_1 = operando_1;
        this.operando_2 = operando_2;
        this.operacion = operacion;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        Object opLeft = null, opRigth = null, unico = null;
        if (opUnico != null) {
            unico = this.opUnico.interpreter(ast, contexto);
            if (unico instanceof ErrorInterprete) return unico;
        } else {
            opLeft = this.operando_1.interpreter(ast, contexto);
            if (opLeft instanceof ErrorInterprete) {
                return opLeft;
            }

            opRigth = this.operando_2.interpreter(ast, contexto);
            if (opRigth instanceof ErrorInterprete) {
                return opRigth;
            }
        }
        return switch (operacion) {
            case SUMA -> sumar(opLeft, opRigth);
            case RESTA -> restar(opLeft, opRigth);
            case MULTIPLICACION -> multiplicar(opLeft, opRigth);
            case DIVISION -> dividir(opLeft, opRigth);
            case POTENCIA -> potenciar(opLeft, opRigth);
            case NEGACION -> negacion(unico);
        };
    }

    public Object negacion(Object unico) {
        if (this.opUnico.tipo == Tipo.INT) return (int) unico * -1;
        if (this.opUnico.tipo == Tipo.DOUBLE) return (double) unico * -1;

        return new ErrorInterprete("Semantico", "no es un numero", this.linea, this.columna);
    }

    public Object sumar(Object exprIzq, Object exprDer) {
        switch (this.operando_1.tipo) {
            case INT -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.INT;
                        return (int) exprIzq + (int) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq + (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            case DOUBLE -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq + (double) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (double) exprIzq + (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            default -> {
                return new ErrorInterprete(
                        "Semantico",
                        "uno de los datos no es un numero",
                        this.linea,
                        this.columna);
            }
        }
    }

    public Object restar(Object exprIzq, Object exprDer) {
        switch (this.operando_1.tipo) {
            case INT -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.INT;
                        return (int) exprIzq - (int) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq - (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            case DOUBLE -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq - (double) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (double) exprIzq - (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            default -> {
                return new ErrorInterprete(
                        "Semantico",
                        "uno de los datos no es un numero",
                        this.linea,
                        this.columna);
            }
        }
    }

    public Object multiplicar(Object exprIzq, Object exprDer) {
        switch (this.operando_1.tipo) {
            case INT -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.INT;
                        return (int) exprIzq * (int) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq * (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            case DOUBLE -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq * (double) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (double) exprIzq * (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            default -> {
                return new ErrorInterprete(
                        "Semantico",
                        "uno de los datos no es un numero",
                        this.linea,
                        this.columna);
            }
        }
    }

    public Object dividir(Object exprIzq, Object exprDer) {
        var aux = (double) exprDer;
        if (aux == 0.0) return new ErrorInterprete("Semantico", "Division por cero", this.linea, this.columna);

        switch (this.operando_1.tipo) {
            case INT -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.INT;
                        return (int) exprIzq / (int) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq / (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            case DOUBLE -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.DOUBLE;
                        return (int) exprIzq / (double) exprDer;
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return (double) exprIzq / (double) exprDer;
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            default -> {
                return new ErrorInterprete(
                        "Semantico",
                        "uno de los datos no es un numero",
                        this.linea,
                        this.columna);
            }
        }
    }

    public Object potenciar(Object exprIzq, Object exprDer) {
        switch (this.operando_1.tipo) {
            case INT -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.INT;
                        return (int) Math.pow((int) exprIzq, (int) exprDer);
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return Math.pow((int) exprIzq, (double) exprDer);
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            case DOUBLE -> {
                switch (this.operando_2.tipo) {
                    case INT -> {
                        this.tipo = Tipo.DOUBLE;
                        return Math.pow((int) exprIzq, (double) exprDer);
                    }
                    case DOUBLE -> {
                        this.tipo = Tipo.DOUBLE;
                        return Math.pow((double) exprIzq, (double) exprDer);
                    }
                    default -> {
                        return new ErrorInterprete(
                                "Semantico",
                                "uno de los datos no es un numero",
                                this.linea,
                                this.columna);
                    }
                }
            }
            default -> {
                return new ErrorInterprete(
                        "Semantico",
                        "uno de los datos no es un numero",
                        this.linea,
                        this.columna);
            }
        }
    }
}
