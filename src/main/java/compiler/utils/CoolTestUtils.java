package compiler.utils;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.*;

import grammar.CoolLexer;
import grammar.CoolParser;

public class CoolTestUtils {

    public static CoolParser.ExprContext parseExpr(String src) {
        CharStream input = CharStreams.fromString(src);
        CoolLexer lexer = new CoolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        return parser.expr(); // Parse directly from expr rule
    }

    public static CoolParser.ProgramContext parseProgram(String src) {
        CharStream input = CharStreams.fromString(src);
        CoolLexer lexer = new CoolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        return parser.program(); // for full pipeline tests
    }

    public static CoolParser.ClassContext parseClass(String src) {
        CharStream input = CharStreams.fromString(src);
        CoolLexer lexer = new CoolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        return parser.class_(); // Parse directly from class rule
    }
}
