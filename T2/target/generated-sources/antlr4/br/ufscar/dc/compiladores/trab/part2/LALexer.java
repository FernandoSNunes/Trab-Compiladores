// Generated from br/ufscar/dc/compiladores/trab/part2/LA.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.trab.part2;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, PALAVRA_CHAVE=54, NUM_INT=55, NUM_REAL=56, CADEIA=57, ERRO_CADEIA=58, 
		OP_REL=59, OP_COMPR=60, OP_ARIT=61, DELIM=62, ABREPAR=63, FECHAPAR=64, 
		ABRECOL=65, FECHACOL=66, VIRGULA=67, IDENT=68, COMENTARIO=69, ERRO_COMENTARIO=70, 
		WS=71, ERRO=72;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
			"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48", 
			"T__49", "T__50", "T__51", "T__52", "PALAVRA_CHAVE", "NUM_INT", "NUM_REAL", 
			"CADEIA", "ESC_SEQ", "ERRO_CADEIA", "OP_REL", "OP_COMPR", "OP_ARIT", 
			"DELIM", "ABREPAR", "FECHAPAR", "ABRECOL", "FECHACOL", "VIRGULA", "IDENT", 
			"COMENTARIO", "ERRO_COMENTARIO", "WS", "ERRO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'algoritmo'", "'fim_algoritmo'", "'declare'", "'constante'", "'='", 
			"'tipo'", "'.'", "'literal'", "'inteiro'", "'real'", "'logico'", "'^'", 
			"'verdadeiro'", "'falso'", "'registro'", "'fim_registro'", "'procedimento'", 
			"'fim_procedimento'", "'funcao'", "'fim_funcao'", "'var'", "'leia'", 
			"'escreva'", "'se'", "'entao'", "'senao'", "'fim_se'", "'caso'", "'seja'", 
			"'fim_caso'", "'para'", "'<-'", "'ate'", "'faca'", "'fim_para'", "'enquanto'", 
			"'fim_enquanto'", "'retorne'", "'..'", "'-'", "'+'", "'*'", "'/'", "'%'", 
			"'&'", "'<>'", "'>='", "'<='", "'>'", "'<'", "'nao'", "'ou'", "'e'", 
			null, null, null, null, null, null, null, null, "':'", "'('", "')'", 
			"'['", "']'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "PALAVRA_CHAVE", "NUM_INT", "NUM_REAL", 
			"CADEIA", "ERRO_CADEIA", "OP_REL", "OP_COMPR", "OP_ARIT", "DELIM", "ABREPAR", 
			"FECHAPAR", "ABRECOL", "FECHACOL", "VIRGULA", "IDENT", "COMENTARIO", 
			"ERRO_COMENTARIO", "WS", "ERRO"
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


	public LALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 69:
			COMENTARIO_action((RuleContext)_localctx, actionIndex);
			break;
		case 71:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMENTARIO_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2J\u0328\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3)"+
		"\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3"+
		"\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\5\67\u02c9\n\67\38\68\u02cc\n8\r8\168\u02cd"+
		"\39\69\u02d1\n9\r9\169\u02d2\39\39\69\u02d7\n9\r9\169\u02d8\59\u02db\n"+
		"9\3:\3:\3:\7:\u02e0\n:\f:\16:\u02e3\13:\3:\3:\3;\3;\3;\3<\3<\3=\3=\3="+
		"\3=\3=\3=\3=\3=\3=\5=\u02f5\n=\3>\3>\3>\3>\3>\3>\3>\5>\u02fe\n>\3?\3?"+
		"\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\7F\u0310\nF\fF\16F\u0313\13"+
		"F\3G\3G\7G\u0317\nG\fG\16G\u031a\13G\3G\5G\u031d\nG\3G\3G\3G\3H\3H\3I"+
		"\3I\3I\3J\3J\2\2K\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9q:s;u\2w<y={>}?\177@\u0081A\u0083B\u0085C\u0087D\u0089"+
		"E\u008bF\u008dG\u008fH\u0091I\u0093J\3\2\t\4\2\'\'``\5\2\f\f$$^^\5\2,"+
		"-//\61\61\4\2C\\c|\6\2\62;C\\aac|\5\2\f\f}}\177\177\5\2\13\f\17\17\"\""+
		"\2\u035b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{"+
		"\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\3\u0095\3\2\2\2\5\u009f"+
		"\3\2\2\2\7\u00ad\3\2\2\2\t\u00b5\3\2\2\2\13\u00bf\3\2\2\2\r\u00c1\3\2"+
		"\2\2\17\u00c6\3\2\2\2\21\u00c8\3\2\2\2\23\u00d0\3\2\2\2\25\u00d8\3\2\2"+
		"\2\27\u00dd\3\2\2\2\31\u00e4\3\2\2\2\33\u00e6\3\2\2\2\35\u00f1\3\2\2\2"+
		"\37\u00f7\3\2\2\2!\u0100\3\2\2\2#\u010d\3\2\2\2%\u011a\3\2\2\2\'\u012b"+
		"\3\2\2\2)\u0132\3\2\2\2+\u013d\3\2\2\2-\u0141\3\2\2\2/\u0146\3\2\2\2\61"+
		"\u014e\3\2\2\2\63\u0151\3\2\2\2\65\u0157\3\2\2\2\67\u015d\3\2\2\29\u0164"+
		"\3\2\2\2;\u0169\3\2\2\2=\u016e\3\2\2\2?\u0177\3\2\2\2A\u017c\3\2\2\2C"+
		"\u017f\3\2\2\2E\u0183\3\2\2\2G\u0188\3\2\2\2I\u0191\3\2\2\2K\u019a\3\2"+
		"\2\2M\u01a7\3\2\2\2O\u01af\3\2\2\2Q\u01b2\3\2\2\2S\u01b4\3\2\2\2U\u01b6"+
		"\3\2\2\2W\u01b8\3\2\2\2Y\u01ba\3\2\2\2[\u01bc\3\2\2\2]\u01be\3\2\2\2_"+
		"\u01c1\3\2\2\2a\u01c4\3\2\2\2c\u01c7\3\2\2\2e\u01c9\3\2\2\2g\u01cb\3\2"+
		"\2\2i\u01cf\3\2\2\2k\u01d2\3\2\2\2m\u02c8\3\2\2\2o\u02cb\3\2\2\2q\u02d0"+
		"\3\2\2\2s\u02dc\3\2\2\2u\u02e6\3\2\2\2w\u02e9\3\2\2\2y\u02f4\3\2\2\2{"+
		"\u02fd\3\2\2\2}\u02ff\3\2\2\2\177\u0301\3\2\2\2\u0081\u0303\3\2\2\2\u0083"+
		"\u0305\3\2\2\2\u0085\u0307\3\2\2\2\u0087\u0309\3\2\2\2\u0089\u030b\3\2"+
		"\2\2\u008b\u030d\3\2\2\2\u008d\u0314\3\2\2\2\u008f\u0321\3\2\2\2\u0091"+
		"\u0323\3\2\2\2\u0093\u0326\3\2\2\2\u0095\u0096\7c\2\2\u0096\u0097\7n\2"+
		"\2\u0097\u0098\7i\2\2\u0098\u0099\7q\2\2\u0099\u009a\7t\2\2\u009a\u009b"+
		"\7k\2\2\u009b\u009c\7v\2\2\u009c\u009d\7o\2\2\u009d\u009e\7q\2\2\u009e"+
		"\4\3\2\2\2\u009f\u00a0\7h\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7o\2\2\u00a2"+
		"\u00a3\7a\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7i\2\2"+
		"\u00a6\u00a7\7q\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa"+
		"\7v\2\2\u00aa\u00ab\7o\2\2\u00ab\u00ac\7q\2\2\u00ac\6\3\2\2\2\u00ad\u00ae"+
		"\7f\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7e\2\2\u00b0\u00b1\7n\2\2\u00b1"+
		"\u00b2\7c\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7g\2\2\u00b4\b\3\2\2\2\u00b5"+
		"\u00b6\7e\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7u\2\2"+
		"\u00b9\u00ba\7v\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd"+
		"\7v\2\2\u00bd\u00be\7g\2\2\u00be\n\3\2\2\2\u00bf\u00c0\7?\2\2\u00c0\f"+
		"\3\2\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7r\2\2\u00c4"+
		"\u00c5\7q\2\2\u00c5\16\3\2\2\2\u00c6\u00c7\7\60\2\2\u00c7\20\3\2\2\2\u00c8"+
		"\u00c9\7n\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7g\2\2"+
		"\u00cc\u00cd\7t\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7n\2\2\u00cf\22\3\2"+
		"\2\2\u00d0\u00d1\7k\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4"+
		"\7g\2\2\u00d4\u00d5\7k\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7q\2\2\u00d7"+
		"\24\3\2\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7c\2\2\u00db"+
		"\u00dc\7n\2\2\u00dc\26\3\2\2\2\u00dd\u00de\7n\2\2\u00de\u00df\7q\2\2\u00df"+
		"\u00e0\7i\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7e\2\2\u00e2\u00e3\7q\2\2"+
		"\u00e3\30\3\2\2\2\u00e4\u00e5\7`\2\2\u00e5\32\3\2\2\2\u00e6\u00e7\7x\2"+
		"\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7f\2\2\u00ea\u00eb"+
		"\7c\2\2\u00eb\u00ec\7f\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7k\2\2\u00ee"+
		"\u00ef\7t\2\2\u00ef\u00f0\7q\2\2\u00f0\34\3\2\2\2\u00f1\u00f2\7h\2\2\u00f2"+
		"\u00f3\7c\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f6\7q\2\2"+
		"\u00f6\36\3\2\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7"+
		"i\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7u\2\2\u00fc\u00fd\7v\2\2\u00fd\u00fe"+
		"\7t\2\2\u00fe\u00ff\7q\2\2\u00ff \3\2\2\2\u0100\u0101\7h\2\2\u0101\u0102"+
		"\7k\2\2\u0102\u0103\7o\2\2\u0103\u0104\7a\2\2\u0104\u0105\7t\2\2\u0105"+
		"\u0106\7g\2\2\u0106\u0107\7i\2\2\u0107\u0108\7k\2\2\u0108\u0109\7u\2\2"+
		"\u0109\u010a\7v\2\2\u010a\u010b\7t\2\2\u010b\u010c\7q\2\2\u010c\"\3\2"+
		"\2\2\u010d\u010e\7r\2\2\u010e\u010f\7t\2\2\u010f\u0110\7q\2\2\u0110\u0111"+
		"\7e\2\2\u0111\u0112\7g\2\2\u0112\u0113\7f\2\2\u0113\u0114\7k\2\2\u0114"+
		"\u0115\7o\2\2\u0115\u0116\7g\2\2\u0116\u0117\7p\2\2\u0117\u0118\7v\2\2"+
		"\u0118\u0119\7q\2\2\u0119$\3\2\2\2\u011a\u011b\7h\2\2\u011b\u011c\7k\2"+
		"\2\u011c\u011d\7o\2\2\u011d\u011e\7a\2\2\u011e\u011f\7r\2\2\u011f\u0120"+
		"\7t\2\2\u0120\u0121\7q\2\2\u0121\u0122\7e\2\2\u0122\u0123\7g\2\2\u0123"+
		"\u0124\7f\2\2\u0124\u0125\7k\2\2\u0125\u0126\7o\2\2\u0126\u0127\7g\2\2"+
		"\u0127\u0128\7p\2\2\u0128\u0129\7v\2\2\u0129\u012a\7q\2\2\u012a&\3\2\2"+
		"\2\u012b\u012c\7h\2\2\u012c\u012d\7w\2\2\u012d\u012e\7p\2\2\u012e\u012f"+
		"\7e\2\2\u012f\u0130\7c\2\2\u0130\u0131\7q\2\2\u0131(\3\2\2\2\u0132\u0133"+
		"\7h\2\2\u0133\u0134\7k\2\2\u0134\u0135\7o\2\2\u0135\u0136\7a\2\2\u0136"+
		"\u0137\7h\2\2\u0137\u0138\7w\2\2\u0138\u0139\7p\2\2\u0139\u013a\7e\2\2"+
		"\u013a\u013b\7c\2\2\u013b\u013c\7q\2\2\u013c*\3\2\2\2\u013d\u013e\7x\2"+
		"\2\u013e\u013f\7c\2\2\u013f\u0140\7t\2\2\u0140,\3\2\2\2\u0141\u0142\7"+
		"n\2\2\u0142\u0143\7g\2\2\u0143\u0144\7k\2\2\u0144\u0145\7c\2\2\u0145."+
		"\3\2\2\2\u0146\u0147\7g\2\2\u0147\u0148\7u\2\2\u0148\u0149\7e\2\2\u0149"+
		"\u014a\7t\2\2\u014a\u014b\7g\2\2\u014b\u014c\7x\2\2\u014c\u014d\7c\2\2"+
		"\u014d\60\3\2\2\2\u014e\u014f\7u\2\2\u014f\u0150\7g\2\2\u0150\62\3\2\2"+
		"\2\u0151\u0152\7g\2\2\u0152\u0153\7p\2\2\u0153\u0154\7v\2\2\u0154\u0155"+
		"\7c\2\2\u0155\u0156\7q\2\2\u0156\64\3\2\2\2\u0157\u0158\7u\2\2\u0158\u0159"+
		"\7g\2\2\u0159\u015a\7p\2\2\u015a\u015b\7c\2\2\u015b\u015c\7q\2\2\u015c"+
		"\66\3\2\2\2\u015d\u015e\7h\2\2\u015e\u015f\7k\2\2\u015f\u0160\7o\2\2\u0160"+
		"\u0161\7a\2\2\u0161\u0162\7u\2\2\u0162\u0163\7g\2\2\u01638\3\2\2\2\u0164"+
		"\u0165\7e\2\2\u0165\u0166\7c\2\2\u0166\u0167\7u\2\2\u0167\u0168\7q\2\2"+
		"\u0168:\3\2\2\2\u0169\u016a\7u\2\2\u016a\u016b\7g\2\2\u016b\u016c\7l\2"+
		"\2\u016c\u016d\7c\2\2\u016d<\3\2\2\2\u016e\u016f\7h\2\2\u016f\u0170\7"+
		"k\2\2\u0170\u0171\7o\2\2\u0171\u0172\7a\2\2\u0172\u0173\7e\2\2\u0173\u0174"+
		"\7c\2\2\u0174\u0175\7u\2\2\u0175\u0176\7q\2\2\u0176>\3\2\2\2\u0177\u0178"+
		"\7r\2\2\u0178\u0179\7c\2\2\u0179\u017a\7t\2\2\u017a\u017b\7c\2\2\u017b"+
		"@\3\2\2\2\u017c\u017d\7>\2\2\u017d\u017e\7/\2\2\u017eB\3\2\2\2\u017f\u0180"+
		"\7c\2\2\u0180\u0181\7v\2\2\u0181\u0182\7g\2\2\u0182D\3\2\2\2\u0183\u0184"+
		"\7h\2\2\u0184\u0185\7c\2\2\u0185\u0186\7e\2\2\u0186\u0187\7c\2\2\u0187"+
		"F\3\2\2\2\u0188\u0189\7h\2\2\u0189\u018a\7k\2\2\u018a\u018b\7o\2\2\u018b"+
		"\u018c\7a\2\2\u018c\u018d\7r\2\2\u018d\u018e\7c\2\2\u018e\u018f\7t\2\2"+
		"\u018f\u0190\7c\2\2\u0190H\3\2\2\2\u0191\u0192\7g\2\2\u0192\u0193\7p\2"+
		"\2\u0193\u0194\7s\2\2\u0194\u0195\7w\2\2\u0195\u0196\7c\2\2\u0196\u0197"+
		"\7p\2\2\u0197\u0198\7v\2\2\u0198\u0199\7q\2\2\u0199J\3\2\2\2\u019a\u019b"+
		"\7h\2\2\u019b\u019c\7k\2\2\u019c\u019d\7o\2\2\u019d\u019e\7a\2\2\u019e"+
		"\u019f\7g\2\2\u019f\u01a0\7p\2\2\u01a0\u01a1\7s\2\2\u01a1\u01a2\7w\2\2"+
		"\u01a2\u01a3\7c\2\2\u01a3\u01a4\7p\2\2\u01a4\u01a5\7v\2\2\u01a5\u01a6"+
		"\7q\2\2\u01a6L\3\2\2\2\u01a7\u01a8\7t\2\2\u01a8\u01a9\7g\2\2\u01a9\u01aa"+
		"\7v\2\2\u01aa\u01ab\7q\2\2\u01ab\u01ac\7t\2\2\u01ac\u01ad\7p\2\2\u01ad"+
		"\u01ae\7g\2\2\u01aeN\3\2\2\2\u01af\u01b0\7\60\2\2\u01b0\u01b1\7\60\2\2"+
		"\u01b1P\3\2\2\2\u01b2\u01b3\7/\2\2\u01b3R\3\2\2\2\u01b4\u01b5\7-\2\2\u01b5"+
		"T\3\2\2\2\u01b6\u01b7\7,\2\2\u01b7V\3\2\2\2\u01b8\u01b9\7\61\2\2\u01b9"+
		"X\3\2\2\2\u01ba\u01bb\7\'\2\2\u01bbZ\3\2\2\2\u01bc\u01bd\7(\2\2\u01bd"+
		"\\\3\2\2\2\u01be\u01bf\7>\2\2\u01bf\u01c0\7@\2\2\u01c0^\3\2\2\2\u01c1"+
		"\u01c2\7@\2\2\u01c2\u01c3\7?\2\2\u01c3`\3\2\2\2\u01c4\u01c5\7>\2\2\u01c5"+
		"\u01c6\7?\2\2\u01c6b\3\2\2\2\u01c7\u01c8\7@\2\2\u01c8d\3\2\2\2\u01c9\u01ca"+
		"\7>\2\2\u01caf\3\2\2\2\u01cb\u01cc\7p\2\2\u01cc\u01cd\7c\2\2\u01cd\u01ce"+
		"\7q\2\2\u01ceh\3\2\2\2\u01cf\u01d0\7q\2\2\u01d0\u01d1\7w\2\2\u01d1j\3"+
		"\2\2\2\u01d2\u01d3\7g\2\2\u01d3l\3\2\2\2\u01d4\u01d5\7c\2\2\u01d5\u01d6"+
		"\7n\2\2\u01d6\u01d7\7i\2\2\u01d7\u01d8\7q\2\2\u01d8\u01d9\7t\2\2\u01d9"+
		"\u01da\7k\2\2\u01da\u01db\7v\2\2\u01db\u01dc\7o\2\2\u01dc\u02c9\7q\2\2"+
		"\u01dd\u01de\7f\2\2\u01de\u01df\7g\2\2\u01df\u01e0\7e\2\2\u01e0\u01e1"+
		"\7n\2\2\u01e1\u01e2\7c\2\2\u01e2\u01e3\7t\2\2\u01e3\u02c9\7g\2\2\u01e4"+
		"\u01e5\7n\2\2\u01e5\u01e6\7k\2\2\u01e6\u01e7\7v\2\2\u01e7\u01e8\7g\2\2"+
		"\u01e8\u01e9\7t\2\2\u01e9\u01ea\7c\2\2\u01ea\u02c9\7n\2\2\u01eb\u01ec"+
		"\7k\2\2\u01ec\u01ed\7p\2\2\u01ed\u01ee\7v\2\2\u01ee\u01ef\7g\2\2\u01ef"+
		"\u01f0\7k\2\2\u01f0\u01f1\7t\2\2\u01f1\u02c9\7q\2\2\u01f2\u01f3\7n\2\2"+
		"\u01f3\u01f4\7g\2\2\u01f4\u01f5\7k\2\2\u01f5\u02c9\7c\2\2\u01f6\u01f7"+
		"\7g\2\2\u01f7\u01f8\7u\2\2\u01f8\u01f9\7e\2\2\u01f9\u01fa\7t\2\2\u01fa"+
		"\u01fb\7g\2\2\u01fb\u01fc\7x\2\2\u01fc\u02c9\7c\2\2\u01fd\u01fe\7h\2\2"+
		"\u01fe\u01ff\7k\2\2\u01ff\u0200\7o\2\2\u0200\u0201\7a\2\2\u0201\u0202"+
		"\7c\2\2\u0202\u0203\7n\2\2\u0203\u0204\7i\2\2\u0204\u0205\7q\2\2\u0205"+
		"\u0206\7t\2\2\u0206\u0207\7k\2\2\u0207\u0208\7v\2\2\u0208\u0209\7o\2\2"+
		"\u0209\u02c9\7q\2\2\u020a\u020b\7t\2\2\u020b\u020c\7g\2\2\u020c\u020d"+
		"\7c\2\2\u020d\u02c9\7n\2\2\u020e\u020f\7>\2\2\u020f\u02c9\7/\2\2\u0210"+
		"\u0211\7n\2\2\u0211\u0212\7q\2\2\u0212\u0213\7i\2\2\u0213\u0214\7k\2\2"+
		"\u0214\u0215\7e\2\2\u0215\u02c9\7q\2\2\u0216\u0217\7u\2\2\u0217\u02c9"+
		"\7g\2\2\u0218\u0219\7g\2\2\u0219\u021a\7p\2\2\u021a\u021b\7v\2\2\u021b"+
		"\u021c\7c\2\2\u021c\u02c9\7q\2\2\u021d\u021e\7u\2\2\u021e\u021f\7g\2\2"+
		"\u021f\u0220\7p\2\2\u0220\u0221\7c\2\2\u0221\u02c9\7q\2\2\u0222\u0223"+
		"\7h\2\2\u0223\u0224\7k\2\2\u0224\u0225\7o\2\2\u0225\u0226\7a\2\2\u0226"+
		"\u0227\7u\2\2\u0227\u02c9\7g\2\2\u0228\u0229\7e\2\2\u0229\u022a\7c\2\2"+
		"\u022a\u022b\7u\2\2\u022b\u02c9\7q\2\2\u022c\u022d\7u\2\2\u022d\u022e"+
		"\7g\2\2\u022e\u022f\7l\2\2\u022f\u02c9\7c\2\2\u0230\u0231\7\60\2\2\u0231"+
		"\u02c9\7\60\2\2\u0232\u0233\7h\2\2\u0233\u0234\7k\2\2\u0234\u0235\7o\2"+
		"\2\u0235\u0236\7a\2\2\u0236\u0237\7e\2\2\u0237\u0238\7c\2\2\u0238\u0239"+
		"\7u\2\2\u0239\u02c9\7q\2\2\u023a\u023b\7r\2\2\u023b\u023c\7c\2\2\u023c"+
		"\u023d\7t\2\2\u023d\u02c9\7c\2\2\u023e\u023f\7c\2\2\u023f\u0240\7v\2\2"+
		"\u0240\u02c9\7g\2\2\u0241\u0242\7h\2\2\u0242\u0243\7c\2\2\u0243\u0244"+
		"\7e\2\2\u0244\u02c9\7c\2\2\u0245\u0246\7h\2\2\u0246\u0247\7k\2\2\u0247"+
		"\u0248\7o\2\2\u0248\u0249\7a\2\2\u0249\u024a\7r\2\2\u024a\u024b\7c\2\2"+
		"\u024b\u024c\7t\2\2\u024c\u02c9\7c\2\2\u024d\u024e\7g\2\2\u024e\u024f"+
		"\7p\2\2\u024f\u0250\7s\2\2\u0250\u0251\7w\2\2\u0251\u0252\7c\2\2\u0252"+
		"\u0253\7p\2\2\u0253\u0254\7v\2\2\u0254\u02c9\7q\2\2\u0255\u0256\7h\2\2"+
		"\u0256\u0257\7k\2\2\u0257\u0258\7o\2\2\u0258\u0259\7a\2\2\u0259\u025a"+
		"\7g\2\2\u025a\u025b\7p\2\2\u025b\u025c\7s\2\2\u025c\u025d\7w\2\2\u025d"+
		"\u025e\7c\2\2\u025e\u025f\7p\2\2\u025f\u0260\7v\2\2\u0260\u02c9\7q\2\2"+
		"\u0261\u02c9\t\2\2\2\u0262\u0263\7t\2\2\u0263\u0264\7g\2\2\u0264\u0265"+
		"\7i\2\2\u0265\u0266\7k\2\2\u0266\u0267\7u\2\2\u0267\u0268\7v\2\2\u0268"+
		"\u0269\7t\2\2\u0269\u02c9\7q\2\2\u026a\u026b\7h\2\2\u026b\u026c\7k\2\2"+
		"\u026c\u026d\7o\2\2\u026d\u026e\7a\2\2\u026e\u026f\7t\2\2\u026f\u0270"+
		"\7g\2\2\u0270\u0271\7i\2\2\u0271\u0272\7k\2\2\u0272\u0273\7u\2\2\u0273"+
		"\u0274\7v\2\2\u0274\u0275\7t\2\2\u0275\u02c9\7q\2\2\u0276\u0277\7v\2\2"+
		"\u0277\u0278\7k\2\2\u0278\u0279\7r\2\2\u0279\u02c9\7q\2\2\u027a\u027b"+
		"\7r\2\2\u027b\u027c\7t\2\2\u027c\u027d\7q\2\2\u027d\u027e\7e\2\2\u027e"+
		"\u027f\7g\2\2\u027f\u0280\7f\2\2\u0280\u0281\7k\2\2\u0281\u0282\7o\2\2"+
		"\u0282\u0283\7g\2\2\u0283\u0284\7p\2\2\u0284\u0285\7v\2\2\u0285\u02c9"+
		"\7q\2\2\u0286\u0287\7x\2\2\u0287\u0288\7c\2\2\u0288\u02c9\7t\2\2\u0289"+
		"\u028a\7h\2\2\u028a\u028b\7k\2\2\u028b\u028c\7o\2\2\u028c\u028d\7a\2\2"+
		"\u028d\u028e\7r\2\2\u028e\u028f\7t\2\2\u028f\u0290\7q\2\2\u0290\u0291"+
		"\7e\2\2\u0291\u0292\7g\2\2\u0292\u0293\7f\2\2\u0293\u0294\7k\2\2\u0294"+
		"\u0295\7o\2\2\u0295\u0296\7g\2\2\u0296\u0297\7p\2\2\u0297\u0298\7v\2\2"+
		"\u0298\u02c9\7q\2\2\u0299\u029a\7h\2\2\u029a\u029b\7w\2\2\u029b\u029c"+
		"\7p\2\2\u029c\u029d\7e\2\2\u029d\u029e\7c\2\2\u029e\u02c9\7q\2\2\u029f"+
		"\u02a0\7t\2\2\u02a0\u02a1\7g\2\2\u02a1\u02a2\7v\2\2\u02a2\u02a3\7q\2\2"+
		"\u02a3\u02a4\7t\2\2\u02a4\u02a5\7p\2\2\u02a5\u02c9\7g\2\2\u02a6\u02a7"+
		"\7h\2\2\u02a7\u02a8\7k\2\2\u02a8\u02a9\7o\2\2\u02a9\u02aa\7a\2\2\u02aa"+
		"\u02ab\7h\2\2\u02ab\u02ac\7w\2\2\u02ac\u02ad\7p\2\2\u02ad\u02ae\7e\2\2"+
		"\u02ae\u02af\7c\2\2\u02af\u02c9\7q\2\2\u02b0\u02b1\7e\2\2\u02b1\u02b2"+
		"\7q\2\2\u02b2\u02b3\7p\2\2\u02b3\u02b4\7u\2\2\u02b4\u02b5\7v\2\2\u02b5"+
		"\u02b6\7c\2\2\u02b6\u02b7\7p\2\2\u02b7\u02b8\7v\2\2\u02b8\u02c9\7g\2\2"+
		"\u02b9\u02ba\7h\2\2\u02ba\u02bb\7c\2\2\u02bb\u02bc\7n\2\2\u02bc\u02bd"+
		"\7u\2\2\u02bd\u02c9\7q\2\2\u02be\u02bf\7x\2\2\u02bf\u02c0\7g\2\2\u02c0"+
		"\u02c1\7t\2\2\u02c1\u02c2\7f\2\2\u02c2\u02c3\7c\2\2\u02c3\u02c4\7f\2\2"+
		"\u02c4\u02c5\7g\2\2\u02c5\u02c6\7k\2\2\u02c6\u02c7\7t\2\2\u02c7\u02c9"+
		"\7q\2\2\u02c8\u01d4\3\2\2\2\u02c8\u01dd\3\2\2\2\u02c8\u01e4\3\2\2\2\u02c8"+
		"\u01eb\3\2\2\2\u02c8\u01f2\3\2\2\2\u02c8\u01f6\3\2\2\2\u02c8\u01fd\3\2"+
		"\2\2\u02c8\u020a\3\2\2\2\u02c8\u020e\3\2\2\2\u02c8\u0210\3\2\2\2\u02c8"+
		"\u0216\3\2\2\2\u02c8\u0218\3\2\2\2\u02c8\u021d\3\2\2\2\u02c8\u0222\3\2"+
		"\2\2\u02c8\u0228\3\2\2\2\u02c8\u022c\3\2\2\2\u02c8\u0230\3\2\2\2\u02c8"+
		"\u0232\3\2\2\2\u02c8\u023a\3\2\2\2\u02c8\u023e\3\2\2\2\u02c8\u0241\3\2"+
		"\2\2\u02c8\u0245\3\2\2\2\u02c8\u024d\3\2\2\2\u02c8\u0255\3\2\2\2\u02c8"+
		"\u0261\3\2\2\2\u02c8\u0262\3\2\2\2\u02c8\u026a\3\2\2\2\u02c8\u0276\3\2"+
		"\2\2\u02c8\u027a\3\2\2\2\u02c8\u0286\3\2\2\2\u02c8\u0289\3\2\2\2\u02c8"+
		"\u0299\3\2\2\2\u02c8\u029f\3\2\2\2\u02c8\u02a6\3\2\2\2\u02c8\u02b0\3\2"+
		"\2\2\u02c8\u02b9\3\2\2\2\u02c8\u02be\3\2\2\2\u02c9n\3\2\2\2\u02ca\u02cc"+
		"\4\62;\2\u02cb\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02cb\3\2\2\2\u02cd"+
		"\u02ce\3\2\2\2\u02cep\3\2\2\2\u02cf\u02d1\4\62;\2\u02d0\u02cf\3\2\2\2"+
		"\u02d1\u02d2\3\2\2\2\u02d2\u02d0\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02da"+
		"\3\2\2\2\u02d4\u02d6\7\60\2\2\u02d5\u02d7\4\62;\2\u02d6\u02d5\3\2\2\2"+
		"\u02d7\u02d8\3\2\2\2\u02d8\u02d6\3\2\2\2\u02d8\u02d9\3\2\2\2\u02d9\u02db"+
		"\3\2\2\2\u02da\u02d4\3\2\2\2\u02da\u02db\3\2\2\2\u02dbr\3\2\2\2\u02dc"+
		"\u02e1\7$\2\2\u02dd\u02e0\5u;\2\u02de\u02e0\n\3\2\2\u02df\u02dd\3\2\2"+
		"\2\u02df\u02de\3\2\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df\3\2\2\2\u02e1\u02e2"+
		"\3\2\2\2\u02e2\u02e4\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e4\u02e5\7$\2\2\u02e5"+
		"t\3\2\2\2\u02e6\u02e7\7^\2\2\u02e7\u02e8\7$\2\2\u02e8v\3\2\2\2\u02e9\u02ea"+
		"\4$$\2\u02eax\3\2\2\2\u02eb\u02f5\7@\2\2\u02ec\u02ed\7@\2\2\u02ed\u02f5"+
		"\7?\2\2\u02ee\u02f5\7>\2\2\u02ef\u02f0\7>\2\2\u02f0\u02f5\7?\2\2\u02f1"+
		"\u02f2\7>\2\2\u02f2\u02f5\7@\2\2\u02f3\u02f5\7?\2\2\u02f4\u02eb\3\2\2"+
		"\2\u02f4\u02ec\3\2\2\2\u02f4\u02ee\3\2\2\2\u02f4\u02ef\3\2\2\2\u02f4\u02f1"+
		"\3\2\2\2\u02f4\u02f3\3\2\2\2\u02f5z\3\2\2\2\u02f6\u02fe\7g\2\2\u02f7\u02f8"+
		"\7q\2\2\u02f8\u02fe\7w\2\2\u02f9\u02fa\7p\2\2\u02fa\u02fb\7c\2\2\u02fb"+
		"\u02fe\7q\2\2\u02fc\u02fe\7(\2\2\u02fd\u02f6\3\2\2\2\u02fd\u02f7\3\2\2"+
		"\2\u02fd\u02f9\3\2\2\2\u02fd\u02fc\3\2\2\2\u02fe|\3\2\2\2\u02ff\u0300"+
		"\t\4\2\2\u0300~\3\2\2\2\u0301\u0302\7<\2\2\u0302\u0080\3\2\2\2\u0303\u0304"+
		"\7*\2\2\u0304\u0082\3\2\2\2\u0305\u0306\7+\2\2\u0306\u0084\3\2\2\2\u0307"+
		"\u0308\7]\2\2\u0308\u0086\3\2\2\2\u0309\u030a\7_\2\2\u030a\u0088\3\2\2"+
		"\2\u030b\u030c\7.\2\2\u030c\u008a\3\2\2\2\u030d\u0311\t\5\2\2\u030e\u0310"+
		"\t\6\2\2\u030f\u030e\3\2\2\2\u0310\u0313\3\2\2\2\u0311\u030f\3\2\2\2\u0311"+
		"\u0312\3\2\2\2\u0312\u008c\3\2\2\2\u0313\u0311\3\2\2\2\u0314\u0318\7}"+
		"\2\2\u0315\u0317\n\7\2\2\u0316\u0315\3\2\2\2\u0317\u031a\3\2\2\2\u0318"+
		"\u0316\3\2\2\2\u0318\u0319\3\2\2\2\u0319\u031c\3\2\2\2\u031a\u0318\3\2"+
		"\2\2\u031b\u031d\7\17\2\2\u031c\u031b\3\2\2\2\u031c\u031d\3\2\2\2\u031d"+
		"\u031e\3\2\2\2\u031e\u031f\7\177\2\2\u031f\u0320\bG\2\2\u0320\u008e\3"+
		"\2\2\2\u0321\u0322\4}}\2\u0322\u0090\3\2\2\2\u0323\u0324\t\b\2\2\u0324"+
		"\u0325\bI\3\2\u0325\u0092\3\2\2\2\u0326\u0327\13\2\2\2\u0327\u0094\3\2"+
		"\2\2\17\2\u02c8\u02cd\u02d2\u02d8\u02da\u02df\u02e1\u02f4\u02fd\u0311"+
		"\u0318\u031c\4\3G\2\3I\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}