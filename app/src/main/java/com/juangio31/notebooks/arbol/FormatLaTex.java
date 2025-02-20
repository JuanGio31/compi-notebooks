package arbol;

public class FormatLaTex extends Instruccion {
    private String string;

    public FormatLaTex(String string, int linea, int columna) {
        super(Tipo.VOID, linea, columna);
        this.string = string;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        return null;
    }
}
