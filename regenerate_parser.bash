#!/bin/bash

# first move to the subdirectory containing the grammar files
cd src/antlr4

# First regenerate the lexer
antlr4 CoolLexer.g4 -o ../java/grammar
# Then regenerate the parser
antlr4 CoolParser.g4 -lib ../java/grammar -o ../java/grammar -no-listener -visitor