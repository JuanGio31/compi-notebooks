#!/bin/bash
rm CodeScan.java
rm CParser.java
rm CParserSym.java
java -jar jflex-full-1.9.1.jar code_lexer.flex
java -jar java-cup-11b.jar -parser CParser -symbols CParserSym code_parser.cup
rm CodeScan.java~
#script para crear los analizadores para lenguaje parecido a python