/* Definir el paquete */
package com.juangio31.notebooks.analyzers;
import java_cup.runtime.Symbol;

%%
%public
%class CodeScan
%unicode
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();

    private Symbol symbol(int tipo){
        return new Symbol(tipo, yyline, yycolumn);
    }

    private Symbol symbol(int tipo, Object value){
        return new Symbol(tipo, yyline, yycolumn, value);
    }
%}

salto = \r|\n|\r\n
whiteSpace     = [ \t\f]

id =        [a-zA-Z_][a-zA-Z0-9_]*
entero =    [0-9]+
decimal =   [0-9]+ "." [0-9]+
//digito =    [\d]+ (\.[\d]+)?


%eofval{
    return symbol(CParserSym.EOF);
%eofval}

%state STRING

%%

/* Reglas de análisis */
<YYINITIAL>    "print"   {     return symbol(CParserSym.PRINT);       }
<YYINITIAL>    "format"  {     return symbol(CParserSym.FORMAT);      }

<YYINITIAL> {
    //{digito}  {     return symbol(CParserSym.DIGITO, yytext());       }
    {entero}  {     return symbol(CParserSym.INT, yytext());       }
    {decimal} {     return symbol(CParserSym.DOUBLE, yytext());       }

    {id}      {     return symbol(CParserSym.ID, yytext());           }

    "+"       {     return symbol(CParserSym.SUMA);        }
    "-"       {     return symbol(CParserSym.RESTA);       }
    "*"       {     return symbol(CParserSym.MULT);        }
    "/"       {     return symbol(CParserSym.DIV);         }
    \^       {     return symbol(CParserSym.POTENCIA);    }
    "("       {     return symbol(CParserSym.PAR_I);       }   //parentesis izquierdo (abierto)
    ")"       {     return symbol(CParserSym.PAR_D);       }   //parentesis derecho (cerrado)
    "="       {     return symbol(CParserSym.IGUAL);       }

    {salto}+  {     return symbol(CParserSym.FINLINEA);    }

    \"        { string.setLength(0); yybegin(STRING); }

    {whiteSpace}        {/*no hacer nada*/}
}

<STRING> {
   \"       {
                yybegin(YYINITIAL);
                return symbol(CParserSym.TEXTO, string.toString());
            }

   [^\n\r\"\\]+   { string.append( yytext() ); }
   \\t            { string.append('\t'); }
   \\n            { string.append('\n'); }

   \\r            { string.append('\r'); }
   \\\"           { string.append('\"'); }
   \\             { string.append('\\'); }
}

.         { System.err.println("Carácter no reconocido: " + yytext()); }