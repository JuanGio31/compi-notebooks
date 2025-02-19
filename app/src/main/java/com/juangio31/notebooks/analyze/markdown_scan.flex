/* Definir el paquete */
package analyzers;
import java_cup.runtime.*;

%%
%public
%class MarkdownScan
%unicode
%cup
%line
%column

//expr lexicas
//LineTerminator = \r|\n|\r\n
//InputCharacter = [^\r\n]
//BoldText    =   ["*"]([^*]|\*+[^/*])*["*"]+
//ItalicText  =   ["*"][.]*[\s]*[.]+["*"]
//BoldItalic  =   ["***"][.]*[\s]*[.]+["***"]

// Comment can be the last line of the file, without line terminator.
//cadena  =   {InputCharacter}+ {LineTerminator}?
//WhiteSpace     = {LineTerminator}? | [ \t\f]?

inicioHead = [^[^\t ]]
header = #{1,6}[ ]{1}[^ \n][^\r\n]*[\r|\n|\r\n]?
//header = [^#{1,6} [^\n]*$]
inciso  =   [\d]+ ["."]
blanco = [ \t\f]+

style = \* [^\n\r\\][\s\S]*? [^\n\r\\]\*
//bold = \*\*\* [^\n\r\\][\s\S]*? [^\n\r\\]\*\*\*
//bolIt = \*\* [^\n\r\\][\s\S]*? [^\n\r\\]\*\*


%{
  private Symbol symbol(int tipo){
      return new Symbol(tipo, yyline, yycolumn);
  }

  private Symbol symbol(int tipo, Object value){
      return new Symbol(tipo, yyline, yycolumn, value);
  }

//  StringBuffer strBold = new StringBuffer();
//  StringBuffer strItalic = new StringBuffer();
//  StringBuffer strBoldItalic = new StringBuffer();
%}


%eofval{
    return symbol(MParserSym.EOF);
%eofval}

//%state BOLD
//%state ITALIC
//%state BOLD_ITALIC

%%

/* Reglas de análisis */
<YYINITIAL> {
    "+"           {   return symbol(MParserSym.PLUS, yytext());      }

    {inciso}      {   return symbol(MParserSym.INCISO, yytext());    }

    //{WhiteSpace}    {/*no hacer nada*/}

    }

//{inicioLinea}
{inicioHead}{header}  {
            String str = yytext().toString();
            char[] simbolo = str.toCharArray();
            int count = 0;

            for(int i = 0; i < simbolo.length; i++) {
                if (simbolo[i]!='#'){
                    break;
                }
                count++;
            }
            switch (count){
                case 1 :
                    return symbol(MParserSym.HEADER_1, str.substring(2));
                case 2:
                    return symbol(MParserSym.HEADER_2, str.substring(3));
                case 3 :
                    return symbol(MParserSym.HEADER_3, str.substring(4));
                case 4:
                    return symbol(MParserSym.HEADER_4, str.substring(5));
                case 5 :
                    return symbol(MParserSym.HEADER_5, str.substring(6));
                case 6:
                    return symbol(MParserSym.HEADER_6, str.substring(7));
            }
      }

//{WhiteSpace}{style}{WhiteSpace}
[\s]*{style}[\s]*
    { String str = yytext().toString().trim();
                char[] temp = str.toCharArray();
                int countFirt = 0, countEnd = 0;

                for(int i = 0; i < temp.length; i++) {
                  if(temp[i]!='*'){
                      break;
                  }
                  countFirt++;
                }

                for(int i = temp.length-1; i > 0; i--) {
                  if(temp[i]!='*'){
                      break;
                  }
                  countEnd++;
                }

                if ((countEnd==1 && countFirt >=1) || (countEnd>=1 && countFirt==1)){
                    return symbol(MParserSym.ITALIC, str.substring(1,str.length()-1));
                }
                if ((countEnd==2 && countFirt >=2) || (countEnd>=2 && countFirt ==2)){
                    return symbol(MParserSym.BOLD, str.substring(2,str.length()-2));
                }
                if ((countEnd==3 && countFirt >=3) || (countEnd>=3 && countFirt ==3)){
                    return symbol(MParserSym.BOLD_ITALIC, str.substring(3,str.length()-3));
                }
      }

//<BOLD_ITALIC> {
//    "***"  {     yybegin(YYINITIAL);
//                  return symbol(MParserSym.BOLD_ITALIC, strBoldItalic.toString());
//            }
//    [^\n\r\"\\]+                   { strBoldItalic.append( yytext() ); }
//    \\t                            { strBoldItalic.append('\t'); }
//    \\n                            { strBoldItalic.append('\n'); }
//
//    \\r                            { strBoldItalic.append('\r'); }
//    \\\"                           { strBoldItalic.append('\"'); }
//    \\                             { strBoldItalic.append('\\'); }
//}
//
//<BOLD> {
//    "**"  {     yybegin(YYINITIAL);
//          System.out.println("bold");
//                  return symbol(MParserSym.BOLD, strBold.toString());
//            }
//    [^\n\r\"\\]+                   { strBold.append( yytext() ); }
//    \\t                            { strBold.append('\t'); }
//    \\n                            { strBold.append('\n'); }
//
//    \\r                            { strBold.append('\r'); }
//    \\\"                           { strBold.append('\"'); }
//    \\                             { strBold.append('\\'); }
//}
//
//<ITALIC> {
//    "*"  {     yybegin(YYINITIAL);
//                  return symbol(MParserSym.ITALIC, strItalic.toString());
//            }
//    [^\n\r\"\\]+                   { strItalic.append( yytext() ); }
//    \\t                            { strItalic.append('\t'); }
//    \\n                            { strItalic.append('\n'); }
//
//    \\r                            { strItalic.append('\r'); }
//    \\\"                           { strItalic.append('\"'); }
//    \\                             { strItalic.append('\\'); }
//}

//{blanco}    {/*no hacer nada*/}
.         { System.err.println("Carácter no reconocido: " + yytext()); }