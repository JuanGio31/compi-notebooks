package com.juangio31.notebooks;

import com.juangio31.notebooks.analyzers.CParser;
import com.juangio31.notebooks.analyzers.CodeScan;
import com.juangio31.notebooks.arbol.AST;
import com.juangio31.notebooks.arbol.Contexto;
import com.juangio31.notebooks.arbol.ErrorInterprete;
import com.juangio31.notebooks.arbol.Instruccion;
import com.juangio31.notebooks.arbol.Print;

import java.io.StringReader;
import java.util.LinkedList;

public class AnalizadorGeneral {

    public void analisisCodigo(String texto, LinkedList<Object> salida, LinkedList<ErrorInterprete> error) {
//        String tmp = "variable1 = 2 + true \n" +
//                "print(variable1)\n" +
//                "print(\"Este es un mensaje desde mi proyecto de compi\")\n" +
//                "print(2^8)" +
//
//                "\n";
        try {
            StringReader reader = new StringReader(texto);
            CodeScan codeScan = new CodeScan(reader);
            CParser parser = new CParser(codeScan);
            var resultado = parser.parse();
            var ast = new AST((LinkedList<Instruccion>) resultado.value);
            var contexto = new Contexto();

            for (int i = 0; i < ast.getInstruccions().size(); i++) {
                var result = ast.getInstruccions().get(i).interpreter(ast, contexto);

                if (result == null) {
                    continue;
                }

                if (result instanceof ErrorInterprete err) {
                    error.add(err);
                    continue;
                }
                salida.add(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
