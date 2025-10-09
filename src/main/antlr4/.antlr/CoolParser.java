// Generated from /Users/troy/Cool2LLVM/Cool2LLVM/src/main/antlr4/CoolParser.g4 by ANTLR 4.13.1

package grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, ELSE=2, FI=3, IF=4, IN=5, INHERITS=6, ISVOID=7, LET=8, LOOP=9, 
		POOL=10, THEN=11, WHILE=12, CASE=13, ESAC=14, NEW=15, OF=16, NOT=17, TRUE=18, 
		FALSE=19, Integer=20, TypeID=21, ID=22, Self=23, SelfType=24, String=25, 
		WS=26, LINE_COMMENT=27, BLOCK_COMMENT=28, SEMICOLON=29, LBRACE=30, RBRACE=31, 
		LPAREN=32, RPAREN=33, COMMA=34, COLON=35, ASSIGN=36, AT=37, DOT=38, ARROW=39, 
		PLUS=40, MINUS=41, MULT=42, DIV=43, TILDE=44, LT=45, LE=46, EQ=47;
	public static final int
		RULE_program = 0, RULE_class = 1, RULE_feature = 2, RULE_formal = 3, RULE_expr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "class", "feature", "formal", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "'self'", 
			"'SELF_TYPE'", null, null, null, null, "';'", "'{'", "'}'", "'('", "')'", 
			"','", "':'", "'<-'", "'@'", "'.'", "'=>'", "'+'", "'-'", "'*'", "'/'", 
			"'~'", "'<'", "'<='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "ISVOID", "LET", 
			"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", 
			"TRUE", "FALSE", "Integer", "TypeID", "ID", "Self", "SelfType", "String", 
			"WS", "LINE_COMMENT", "BLOCK_COMMENT", "SEMICOLON", "LBRACE", "RBRACE", 
			"LPAREN", "RPAREN", "COMMA", "COLON", "ASSIGN", "AT", "DOT", "ARROW", 
			"PLUS", "MINUS", "MULT", "DIV", "TILDE", "LT", "LE", "EQ"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ClassContext class_;
		public List<ClassContext> classes = new ArrayList<ClassContext>();
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ClassContext> class_() {
			return getRuleContexts(ClassContext.class);
		}
		public ClassContext class_(int i) {
			return getRuleContext(ClassContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				((ProgramContext)_localctx).class_ = class_();
				((ProgramContext)_localctx).classes.add(((ProgramContext)_localctx).class_);
				setState(11);
				match(SEMICOLON);
				}
				}
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(17);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassContext extends ParserRuleContext {
		public Token class_name;
		public Token parent_name;
		public FeatureContext feature;
		public List<FeatureContext> features = new ArrayList<FeatureContext>();
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> TypeID() { return getTokens(CoolParser.TypeID); }
		public TerminalNode TypeID(int i) {
			return getToken(CoolParser.TypeID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public ClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class; }
	}

	public final ClassContext class_() throws RecognitionException {
		ClassContext _localctx = new ClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(CLASS);
			setState(20);
			((ClassContext)_localctx).class_name = match(TypeID);
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(21);
				match(INHERITS);
				setState(22);
				((ClassContext)_localctx).parent_name = match(TypeID);
				}
			}

			setState(25);
			match(LBRACE);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(26);
				((ClassContext)_localctx).feature = feature();
				((ClassContext)_localctx).features.add(((ClassContext)_localctx).feature);
				setState(27);
				match(SEMICOLON);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureContext extends ParserRuleContext {
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
	 
		public FeatureContext() { }
		public void copyFrom(FeatureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodFeatureContext extends FeatureContext {
		public Token method_name;
		public FormalContext formal;
		public List<FormalContext> formal_params = new ArrayList<FormalContext>();
		public Token return_type;
		public ExprContext body;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TypeID() { return getToken(CoolParser.TypeID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public MethodFeatureContext(FeatureContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AttributeFeatureContext extends FeatureContext {
		public Token attribute_name;
		public Token attribute_type;
		public ExprContext initializer;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TypeID() { return getToken(CoolParser.TypeID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AttributeFeatureContext(FeatureContext ctx) { copyFrom(ctx); }
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_feature);
		int _la;
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new MethodFeatureContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((MethodFeatureContext)_localctx).method_name = match(ID);
				setState(37);
				match(LPAREN);
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(38);
					((MethodFeatureContext)_localctx).formal = formal();
					((MethodFeatureContext)_localctx).formal_params.add(((MethodFeatureContext)_localctx).formal);
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(39);
						match(COMMA);
						setState(40);
						((MethodFeatureContext)_localctx).formal = formal();
						((MethodFeatureContext)_localctx).formal_params.add(((MethodFeatureContext)_localctx).formal);
						}
						}
						setState(45);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(48);
				match(RPAREN);
				setState(49);
				match(COLON);
				setState(50);
				((MethodFeatureContext)_localctx).return_type = match(TypeID);
				setState(51);
				match(LBRACE);
				setState(52);
				((MethodFeatureContext)_localctx).body = expr(0);
				setState(53);
				match(RBRACE);
				}
				break;
			case 2:
				_localctx = new AttributeFeatureContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				((AttributeFeatureContext)_localctx).attribute_name = match(ID);
				setState(56);
				match(COLON);
				setState(57);
				((AttributeFeatureContext)_localctx).attribute_type = match(TypeID);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(58);
					match(ASSIGN);
					setState(59);
					((AttributeFeatureContext)_localctx).initializer = expr(0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalContext extends ParserRuleContext {
		public Token parameter_name;
		public Token parameter_type;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TypeID() { return getToken(CoolParser.TypeID, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			((FormalContext)_localctx).parameter_name = match(ID);
			setState(65);
			match(COLON);
			setState(66);
			((FormalContext)_localctx).parameter_type = match(TypeID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TypeID() { return getToken(CoolParser.TypeID, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComplementExprContext extends ExprContext {
		public TerminalNode TILDE() { return getToken(CoolParser.TILDE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ComplementExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MinusExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MinusExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LetExprContext extends ExprContext {
		public Token ID;
		public List<Token> identifiers = new ArrayList<Token>();
		public Token TypeID;
		public List<Token> types = new ArrayList<Token>();
		public ExprContext expr;
		public List<ExprContext> initializers = new ArrayList<ExprContext>();
		public ExprContext body;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public List<TerminalNode> COLON() { return getTokens(CoolParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CoolParser.COLON, i);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> TypeID() { return getTokens(CoolParser.TypeID); }
		public TerminalNode TypeID(int i) {
			return getToken(CoolParser.TypeID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(CoolParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(CoolParser.ASSIGN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public LetExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodCallExprContext extends ExprContext {
		public ExprContext target;
		public Token referenced_type;
		public Token method_name;
		public ExprContext expr;
		public List<ExprContext> arguments = new ArrayList<ExprContext>();
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode AT() { return getToken(CoolParser.AT, 0); }
		public TerminalNode TypeID() { return getToken(CoolParser.TypeID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public MethodCallExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LtExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LtExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsvoidExprContext extends ExprContext {
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DivExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockExprContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> exprs = new ArrayList<ExprContext>();
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ExprContext {
		public Token identifier;
		public ExprContext expression;
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalExprContext extends ExprContext {
		public ExprContext condition;
		public ExprContext then_expr;
		public ExprContext else_expr;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ConditionalExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseLiteralExprContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public FalseLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplicitMethodCallExprContext extends ExprContext {
		public Token method_name;
		public ExprContext expr;
		public List<ExprContext> arguments = new ArrayList<ExprContext>();
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public ImplicitMethodCallExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralExprContext extends ExprContext {
		public TerminalNode Integer() { return getToken(CoolParser.Integer, 0); }
		public IntegerLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralExprContext extends ExprContext {
		public TerminalNode String() { return getToken(CoolParser.String, 0); }
		public StringLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode MULT() { return getToken(CoolParser.MULT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LoopExprContext extends ExprContext {
		public ExprContext condition;
		public ExprContext body;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LoopExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode EQ() { return getToken(CoolParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueLiteralExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TrueLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LeExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LeExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CaseExprContext extends ExprContext {
		public ExprContext case_expr;
		public Token ID;
		public List<Token> identifiers = new ArrayList<Token>();
		public Token TypeID;
		public List<Token> types = new ArrayList<Token>();
		public ExprContext expr;
		public List<ExprContext> sub_exprs = new ArrayList<ExprContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(CoolParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CoolParser.COLON, i);
		}
		public List<TerminalNode> ARROW() { return getTokens(CoolParser.ARROW); }
		public TerminalNode ARROW(int i) {
			return getToken(CoolParser.ARROW, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> TypeID() { return getTokens(CoolParser.TypeID); }
		public TerminalNode TypeID(int i) {
			return getToken(CoolParser.TypeID, i);
		}
		public CaseExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public IdentifierExprContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new AssignExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(69);
				((AssignExprContext)_localctx).identifier = match(ID);
				setState(70);
				match(ASSIGN);
				setState(71);
				((AssignExprContext)_localctx).expression = expr(25);
				}
				break;
			case 2:
				{
				_localctx = new ImplicitMethodCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(72);
				((ImplicitMethodCallExprContext)_localctx).method_name = match(ID);
				setState(73);
				match(LPAREN);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17597594513808L) != 0)) {
					{
					setState(74);
					((ImplicitMethodCallExprContext)_localctx).expr = expr(0);
					((ImplicitMethodCallExprContext)_localctx).arguments.add(((ImplicitMethodCallExprContext)_localctx).expr);
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(75);
						match(COMMA);
						setState(76);
						((ImplicitMethodCallExprContext)_localctx).expr = expr(0);
						((ImplicitMethodCallExprContext)_localctx).arguments.add(((ImplicitMethodCallExprContext)_localctx).expr);
						}
						}
						setState(81);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(84);
				match(RPAREN);
				}
				break;
			case 3:
				{
				_localctx = new ConditionalExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				match(IF);
				setState(86);
				((ConditionalExprContext)_localctx).condition = expr(0);
				setState(87);
				match(THEN);
				setState(88);
				((ConditionalExprContext)_localctx).then_expr = expr(0);
				setState(89);
				match(ELSE);
				setState(90);
				((ConditionalExprContext)_localctx).else_expr = expr(0);
				setState(91);
				match(FI);
				}
				break;
			case 4:
				{
				_localctx = new LoopExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				match(WHILE);
				setState(94);
				((LoopExprContext)_localctx).condition = expr(0);
				setState(95);
				match(LOOP);
				setState(96);
				((LoopExprContext)_localctx).body = expr(0);
				setState(97);
				match(POOL);
				}
				break;
			case 5:
				{
				_localctx = new BlockExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				match(LBRACE);
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(100);
					((BlockExprContext)_localctx).expr = expr(0);
					((BlockExprContext)_localctx).exprs.add(((BlockExprContext)_localctx).expr);
					setState(101);
					match(SEMICOLON);
					}
					}
					setState(105); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 17597594513808L) != 0) );
				setState(107);
				match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new LetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				match(LET);
				setState(110);
				((LetExprContext)_localctx).ID = match(ID);
				((LetExprContext)_localctx).identifiers.add(((LetExprContext)_localctx).ID);
				setState(111);
				match(COLON);
				setState(112);
				((LetExprContext)_localctx).TypeID = match(TypeID);
				((LetExprContext)_localctx).types.add(((LetExprContext)_localctx).TypeID);
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(113);
					match(ASSIGN);
					setState(114);
					((LetExprContext)_localctx).expr = expr(0);
					((LetExprContext)_localctx).initializers.add(((LetExprContext)_localctx).expr);
					}
				}

				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(117);
					match(COMMA);
					setState(118);
					((LetExprContext)_localctx).ID = match(ID);
					((LetExprContext)_localctx).identifiers.add(((LetExprContext)_localctx).ID);
					setState(119);
					match(COLON);
					setState(120);
					((LetExprContext)_localctx).TypeID = match(TypeID);
					((LetExprContext)_localctx).types.add(((LetExprContext)_localctx).TypeID);
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(121);
						match(ASSIGN);
						setState(122);
						((LetExprContext)_localctx).expr = expr(0);
						((LetExprContext)_localctx).initializers.add(((LetExprContext)_localctx).expr);
						}
					}

					}
					}
					setState(129);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(130);
				match(IN);
				setState(131);
				((LetExprContext)_localctx).body = expr(19);
				}
				break;
			case 7:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(CASE);
				setState(133);
				((CaseExprContext)_localctx).case_expr = expr(0);
				setState(134);
				match(OF);
				setState(142); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(135);
					((CaseExprContext)_localctx).ID = match(ID);
					((CaseExprContext)_localctx).identifiers.add(((CaseExprContext)_localctx).ID);
					setState(136);
					match(COLON);
					setState(137);
					((CaseExprContext)_localctx).TypeID = match(TypeID);
					((CaseExprContext)_localctx).types.add(((CaseExprContext)_localctx).TypeID);
					setState(138);
					match(ARROW);
					setState(139);
					((CaseExprContext)_localctx).expr = expr(0);
					((CaseExprContext)_localctx).sub_exprs.add(((CaseExprContext)_localctx).expr);
					setState(140);
					match(SEMICOLON);
					}
					}
					setState(144); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(146);
				match(ESAC);
				}
				break;
			case 8:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(NEW);
				setState(149);
				match(TypeID);
				}
				break;
			case 9:
				{
				_localctx = new IsvoidExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(ISVOID);
				setState(151);
				expr(16);
				}
				break;
			case 10:
				{
				_localctx = new ComplementExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(TILDE);
				setState(153);
				expr(11);
				}
				break;
			case 11:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				match(NOT);
				setState(155);
				expr(7);
				}
				break;
			case 12:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				match(LPAREN);
				setState(157);
				expr(0);
				setState(158);
				match(RPAREN);
				}
				break;
			case 13:
				{
				_localctx = new IdentifierExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160);
				match(ID);
				}
				break;
			case 14:
				{
				_localctx = new IntegerLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(Integer);
				}
				break;
			case 15:
				{
				_localctx = new StringLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				match(String);
				}
				break;
			case 16:
				{
				_localctx = new TrueLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				match(TRUE);
				}
				break;
			case 17:
				{
				_localctx = new FalseLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(209);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(207);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						((MultExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(168);
						match(MULT);
						setState(169);
						((MultExprContext)_localctx).right = expr(16);
						}
						break;
					case 2:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						((DivExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(170);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(171);
						match(DIV);
						setState(172);
						((DivExprContext)_localctx).right = expr(15);
						}
						break;
					case 3:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						((PlusExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(174);
						match(PLUS);
						setState(175);
						((PlusExprContext)_localctx).right = expr(14);
						}
						break;
					case 4:
						{
						_localctx = new MinusExprContext(new ExprContext(_parentctx, _parentState));
						((MinusExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(177);
						match(MINUS);
						setState(178);
						((MinusExprContext)_localctx).right = expr(13);
						}
						break;
					case 5:
						{
						_localctx = new LtExprContext(new ExprContext(_parentctx, _parentState));
						((LtExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(180);
						match(LT);
						setState(181);
						((LtExprContext)_localctx).right = expr(11);
						}
						break;
					case 6:
						{
						_localctx = new LeExprContext(new ExprContext(_parentctx, _parentState));
						((LeExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(183);
						match(LE);
						setState(184);
						((LeExprContext)_localctx).right = expr(10);
						}
						break;
					case 7:
						{
						_localctx = new EqExprContext(new ExprContext(_parentctx, _parentState));
						((EqExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(185);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(186);
						match(EQ);
						setState(187);
						((EqExprContext)_localctx).right = expr(9);
						}
						break;
					case 8:
						{
						_localctx = new MethodCallExprContext(new ExprContext(_parentctx, _parentState));
						((MethodCallExprContext)_localctx).target = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(188);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(191);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==AT) {
							{
							setState(189);
							match(AT);
							setState(190);
							((MethodCallExprContext)_localctx).referenced_type = match(TypeID);
							}
						}

						setState(193);
						match(DOT);
						setState(194);
						((MethodCallExprContext)_localctx).method_name = match(ID);
						setState(195);
						match(LPAREN);
						setState(204);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17597594513808L) != 0)) {
							{
							setState(196);
							((MethodCallExprContext)_localctx).expr = expr(0);
							((MethodCallExprContext)_localctx).arguments.add(((MethodCallExprContext)_localctx).expr);
							setState(201);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(197);
								match(COMMA);
								setState(198);
								((MethodCallExprContext)_localctx).expr = expr(0);
								((MethodCallExprContext)_localctx).arguments.add(((MethodCallExprContext)_localctx).expr);
								}
								}
								setState(203);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(206);
						match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(211);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 24);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u00d5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u000e\b\u0000\u000b\u0000\f"+
		"\u0000\u000f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u0018\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002*\b\u0002\n\u0002\f\u0002-\t\u0002\u0003\u0002/\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002=\b\u0002\u0003\u0002?\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004N\b"+
		"\u0004\n\u0004\f\u0004Q\t\u0004\u0003\u0004S\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004h\b\u0004\u000b\u0004\f\u0004i\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"t\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004|\b\u0004\u0005\u0004~\b\u0004\n\u0004\f\u0004"+
		"\u0081\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0004\u0004\u008f\b\u0004\u000b\u0004\f\u0004\u0090\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00a6\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00c0\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00c8"+
		"\b\u0004\n\u0004\f\u0004\u00cb\t\u0004\u0003\u0004\u00cd\b\u0004\u0001"+
		"\u0004\u0005\u0004\u00d0\b\u0004\n\u0004\f\u0004\u00d3\t\u0004\u0001\u0004"+
		"\u0000\u0001\b\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000\u00f8\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0002\u0013\u0001\u0000\u0000\u0000\u0004>"+
		"\u0001\u0000\u0000\u0000\u0006@\u0001\u0000\u0000\u0000\b\u00a5\u0001"+
		"\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\f\u0005\u001d"+
		"\u0000\u0000\f\u000e\u0001\u0000\u0000\u0000\r\n\u0001\u0000\u0000\u0000"+
		"\u000e\u000f\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f"+
		"\u0010\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011"+
		"\u0012\u0005\u0000\u0000\u0001\u0012\u0001\u0001\u0000\u0000\u0000\u0013"+
		"\u0014\u0005\u0001\u0000\u0000\u0014\u0017\u0005\u0015\u0000\u0000\u0015"+
		"\u0016\u0005\u0006\u0000\u0000\u0016\u0018\u0005\u0015\u0000\u0000\u0017"+
		"\u0015\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0001\u0000\u0000\u0000\u0019\u001f\u0005\u001e\u0000\u0000\u001a"+
		"\u001b\u0003\u0004\u0002\u0000\u001b\u001c\u0005\u001d\u0000\u0000\u001c"+
		"\u001e\u0001\u0000\u0000\u0000\u001d\u001a\u0001\u0000\u0000\u0000\u001e"+
		"!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f \u0001"+
		"\u0000\u0000\u0000 \"\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000"+
		"\u0000\"#\u0005\u001f\u0000\u0000#\u0003\u0001\u0000\u0000\u0000$%\u0005"+
		"\u0016\u0000\u0000%.\u0005 \u0000\u0000&+\u0003\u0006\u0003\u0000\'(\u0005"+
		"\"\u0000\u0000(*\u0003\u0006\u0003\u0000)\'\u0001\u0000\u0000\u0000*-"+
		"\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000"+
		"\u0000,/\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000.&\u0001\u0000"+
		"\u0000\u0000./\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u000001\u0005"+
		"!\u0000\u000012\u0005#\u0000\u000023\u0005\u0015\u0000\u000034\u0005\u001e"+
		"\u0000\u000045\u0003\b\u0004\u000056\u0005\u001f\u0000\u00006?\u0001\u0000"+
		"\u0000\u000078\u0005\u0016\u0000\u000089\u0005#\u0000\u00009<\u0005\u0015"+
		"\u0000\u0000:;\u0005$\u0000\u0000;=\u0003\b\u0004\u0000<:\u0001\u0000"+
		"\u0000\u0000<=\u0001\u0000\u0000\u0000=?\u0001\u0000\u0000\u0000>$\u0001"+
		"\u0000\u0000\u0000>7\u0001\u0000\u0000\u0000?\u0005\u0001\u0000\u0000"+
		"\u0000@A\u0005\u0016\u0000\u0000AB\u0005#\u0000\u0000BC\u0005\u0015\u0000"+
		"\u0000C\u0007\u0001\u0000\u0000\u0000DE\u0006\u0004\uffff\uffff\u0000"+
		"EF\u0005\u0016\u0000\u0000FG\u0005$\u0000\u0000G\u00a6\u0003\b\u0004\u0019"+
		"HI\u0005\u0016\u0000\u0000IR\u0005 \u0000\u0000JO\u0003\b\u0004\u0000"+
		"KL\u0005\"\u0000\u0000LN\u0003\b\u0004\u0000MK\u0001\u0000\u0000\u0000"+
		"NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000"+
		"\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000RJ\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000T\u00a6"+
		"\u0005!\u0000\u0000UV\u0005\u0004\u0000\u0000VW\u0003\b\u0004\u0000WX"+
		"\u0005\u000b\u0000\u0000XY\u0003\b\u0004\u0000YZ\u0005\u0002\u0000\u0000"+
		"Z[\u0003\b\u0004\u0000[\\\u0005\u0003\u0000\u0000\\\u00a6\u0001\u0000"+
		"\u0000\u0000]^\u0005\f\u0000\u0000^_\u0003\b\u0004\u0000_`\u0005\t\u0000"+
		"\u0000`a\u0003\b\u0004\u0000ab\u0005\n\u0000\u0000b\u00a6\u0001\u0000"+
		"\u0000\u0000cg\u0005\u001e\u0000\u0000de\u0003\b\u0004\u0000ef\u0005\u001d"+
		"\u0000\u0000fh\u0001\u0000\u0000\u0000gd\u0001\u0000\u0000\u0000hi\u0001"+
		"\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000kl\u0005\u001f\u0000\u0000l\u00a6\u0001\u0000"+
		"\u0000\u0000mn\u0005\b\u0000\u0000no\u0005\u0016\u0000\u0000op\u0005#"+
		"\u0000\u0000ps\u0005\u0015\u0000\u0000qr\u0005$\u0000\u0000rt\u0003\b"+
		"\u0004\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000t\u007f"+
		"\u0001\u0000\u0000\u0000uv\u0005\"\u0000\u0000vw\u0005\u0016\u0000\u0000"+
		"wx\u0005#\u0000\u0000x{\u0005\u0015\u0000\u0000yz\u0005$\u0000\u0000z"+
		"|\u0003\b\u0004\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000"+
		"|~\u0001\u0000\u0000\u0000}u\u0001\u0000\u0000\u0000~\u0081\u0001\u0000"+
		"\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0082\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0005\u0005\u0000\u0000\u0083\u00a6\u0003\b\u0004\u0013"+
		"\u0084\u0085\u0005\r\u0000\u0000\u0085\u0086\u0003\b\u0004\u0000\u0086"+
		"\u008e\u0005\u0010\u0000\u0000\u0087\u0088\u0005\u0016\u0000\u0000\u0088"+
		"\u0089\u0005#\u0000\u0000\u0089\u008a\u0005\u0015\u0000\u0000\u008a\u008b"+
		"\u0005\'\u0000\u0000\u008b\u008c\u0003\b\u0004\u0000\u008c\u008d\u0005"+
		"\u001d\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e\u0087\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005\u000e\u0000\u0000\u0093\u00a6\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0005\u000f\u0000\u0000\u0095\u00a6\u0005"+
		"\u0015\u0000\u0000\u0096\u0097\u0005\u0007\u0000\u0000\u0097\u00a6\u0003"+
		"\b\u0004\u0010\u0098\u0099\u0005,\u0000\u0000\u0099\u00a6\u0003\b\u0004"+
		"\u000b\u009a\u009b\u0005\u0011\u0000\u0000\u009b\u00a6\u0003\b\u0004\u0007"+
		"\u009c\u009d\u0005 \u0000\u0000\u009d\u009e\u0003\b\u0004\u0000\u009e"+
		"\u009f\u0005!\u0000\u0000\u009f\u00a6\u0001\u0000\u0000\u0000\u00a0\u00a6"+
		"\u0005\u0016\u0000\u0000\u00a1\u00a6\u0005\u0014\u0000\u0000\u00a2\u00a6"+
		"\u0005\u0019\u0000\u0000\u00a3\u00a6\u0005\u0012\u0000\u0000\u00a4\u00a6"+
		"\u0005\u0013\u0000\u0000\u00a5D\u0001\u0000\u0000\u0000\u00a5H\u0001\u0000"+
		"\u0000\u0000\u00a5U\u0001\u0000\u0000\u0000\u00a5]\u0001\u0000\u0000\u0000"+
		"\u00a5c\u0001\u0000\u0000\u0000\u00a5m\u0001\u0000\u0000\u0000\u00a5\u0084"+
		"\u0001\u0000\u0000\u0000\u00a5\u0094\u0001\u0000\u0000\u0000\u00a5\u0096"+
		"\u0001\u0000\u0000\u0000\u00a5\u0098\u0001\u0000\u0000\u0000\u00a5\u009a"+
		"\u0001\u0000\u0000\u0000\u00a5\u009c\u0001\u0000\u0000\u0000\u00a5\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a1\u0001\u0000\u0000\u0000\u00a5\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a6\u00d1\u0001\u0000\u0000\u0000\u00a7\u00a8"+
		"\n\u000f\u0000\u0000\u00a8\u00a9\u0005*\u0000\u0000\u00a9\u00d0\u0003"+
		"\b\u0004\u0010\u00aa\u00ab\n\u000e\u0000\u0000\u00ab\u00ac\u0005+\u0000"+
		"\u0000\u00ac\u00d0\u0003\b\u0004\u000f\u00ad\u00ae\n\r\u0000\u0000\u00ae"+
		"\u00af\u0005(\u0000\u0000\u00af\u00d0\u0003\b\u0004\u000e\u00b0\u00b1"+
		"\n\f\u0000\u0000\u00b1\u00b2\u0005)\u0000\u0000\u00b2\u00d0\u0003\b\u0004"+
		"\r\u00b3\u00b4\n\n\u0000\u0000\u00b4\u00b5\u0005-\u0000\u0000\u00b5\u00d0"+
		"\u0003\b\u0004\u000b\u00b6\u00b7\n\t\u0000\u0000\u00b7\u00b8\u0005.\u0000"+
		"\u0000\u00b8\u00d0\u0003\b\u0004\n\u00b9\u00ba\n\b\u0000\u0000\u00ba\u00bb"+
		"\u0005/\u0000\u0000\u00bb\u00d0\u0003\b\u0004\t\u00bc\u00bf\n\u0018\u0000"+
		"\u0000\u00bd\u00be\u0005%\u0000\u0000\u00be\u00c0\u0005\u0015\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005&\u0000\u0000\u00c2"+
		"\u00c3\u0005\u0016\u0000\u0000\u00c3\u00cc\u0005 \u0000\u0000\u00c4\u00c9"+
		"\u0003\b\u0004\u0000\u00c5\u00c6\u0005\"\u0000\u0000\u00c6\u00c8\u0003"+
		"\b\u0004\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00cb\u0001\u0000"+
		"\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cd\u0001\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cc\u00c4\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0005!\u0000"+
		"\u0000\u00cf\u00a7\u0001\u0000\u0000\u0000\u00cf\u00aa\u0001\u0000\u0000"+
		"\u0000\u00cf\u00ad\u0001\u0000\u0000\u0000\u00cf\u00b0\u0001\u0000\u0000"+
		"\u0000\u00cf\u00b3\u0001\u0000\u0000\u0000\u00cf\u00b6\u0001\u0000\u0000"+
		"\u0000\u00cf\u00b9\u0001\u0000\u0000\u0000\u00cf\u00bc\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d3\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\t\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d1\u0001\u0000\u0000\u0000\u0014\u000f\u0017\u001f+.<>ORis{"+
		"\u007f\u0090\u00a5\u00bf\u00c9\u00cc\u00cf\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}