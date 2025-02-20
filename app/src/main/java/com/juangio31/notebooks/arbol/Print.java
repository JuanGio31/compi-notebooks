package arbol;

public class Print extends Instruccion {
    private Instruccion cadena;

    public Print(Instruccion cadena, int linea, int columna) {
        super(Tipo.VOID, linea, columna);
        this.cadena = cadena;
    }

    @Override
    public Object interpreter(AST ast, Contexto contexto) {
        System.out.println(this.cadena.interpreter(ast, contexto));
        return 1;
    }
}
