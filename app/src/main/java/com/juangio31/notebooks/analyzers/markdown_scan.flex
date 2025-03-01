/* Definir el paquete */
package com.juangio31.notebooks.analyzers;
import java_cup.runtime.Symbol;

%%
%public
%class MarkdownScan
%unicode
%cup
%line
%column
//%debug

%{
  private Symbol symbol(int tipo){
      return new Symbol(tipo, yyline, yycolumn);
  }

  private Symbol symbol(int tipo, Object value){
      return new Symbol(tipo, yyline, yycolumn, value);
  }
%}

/* Expresiones regulares */
hd = "#"{1,6}[" "]
BoldItalic = \*\*\*
Bold = \*\*
Italic = \*
ListItemNum = [1-9][0-9]*\.[" "]
ListItemHyphen = [+][" "]
WhiteSpace = [ \t\r]+
endl = [\n]
Texto = [.]+


%eofval{
    return symbol(MParserSym.EOF);
%eofval}

%%
//^"###### "        { return symbol(MParserSym.HEADER6, yytext()); }
//^"##### "        { return symbol(MParserSym.HEADER5, yytext()); }
//^"#### "        { return symbol(MParserSym.HEADER4, yytext()); }
//^"### "        { return symbol(MParserSym.HEADER3, yytext()); }
//^"## "        { return symbol(MParserSym.HEADER2, yytext()); }
//^"# "        { return symbol(MParserSym.HEADER1, yytext()); }

^ {hd}  {return symbol(MParserSym.HD, yytext());}

{BoldItalic}   { return symbol(MParserSym.BOLD_ITALIC, yytext()); }

{Bold}         { return symbol(MParserSym.BOLD, yytext()); }

{Italic}       { return symbol(MParserSym.ITALIC, yytext()); }

^{ListItemNum}  {   String str = yytext().toString();
                    String numeroStr = str.split("\\.")[0]; // Toma la parte antes del punto
                    return symbol(MParserSym.LIST_ITEM_NUM, numeroStr); }

^{ListItemHyphen} { return symbol(MParserSym.LIST_ITEM_HYPHEN, yytext()); }

{endl}+         {return symbol(MParserSym.ENDL);}

{WhiteSpace}   { /* Ignorar espacios en blanco */ }

{Texto}         {return symbol(MParserSym.TEXT, yytext()); }

//este registra el texto
[^]         {return symbol(MParserSym.TEXT, yytext()); }