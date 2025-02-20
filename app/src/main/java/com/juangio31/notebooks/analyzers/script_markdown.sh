#!/bin/bash
java -jar jflex-full-1.9.1.jar markdown_scan.flex
java -jar java-cup-11b.jar -parser MarkdownParser -symbols MParserSym markdown_parser.cup
rm MarkdownScan.java~
