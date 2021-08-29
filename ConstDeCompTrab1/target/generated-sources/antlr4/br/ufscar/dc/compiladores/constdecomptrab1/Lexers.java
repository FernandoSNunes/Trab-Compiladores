// Generated from br/ufscar/dc/compiladores/constdecomptrab1/Lexers.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.constdecomptrab1;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Lexers extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PALAVRA_CHAVE=1, NUM_INT=2, NUM_REAL=3, CADEIA=4, ERRO_CADEIA=5, OP_REL=6, 
		OP_COMPR=7, OP_ARIT=8, DELIM=9, ABREPAR=10, FECHAPAR=11, ABRECOL=12, FECHACOL=13, 
		VIRGULA=14, IDENT=15, COMENTARIO=16, ERRO_COMENTARIO=17, WS=18, ERRO=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PALAVRA_CHAVE", "NUM_INT", "NUM_REAL", "CADEIA", "ESC_SEQ", "ERRO_CADEIA", 
			"OP_REL", "OP_COMPR", "OP_ARIT", "DELIM", "ABREPAR", "FECHAPAR", "ABRECOL", 
			"FECHACOL", "VIRGULA", "IDENT", "COMENTARIO", "ERRO_COMENTARIO", "WS", 
			"ERRO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "':'", "'('", "')'", 
			"'['", "']'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PALAVRA_CHAVE", "NUM_INT", "NUM_REAL", "CADEIA", "ERRO_CADEIA", 
			"OP_REL", "OP_COMPR", "OP_ARIT", "DELIM", "ABREPAR", "FECHAPAR", "ABRECOL", 
			"FECHACOL", "VIRGULA", "IDENT", "COMENTARIO", "ERRO_COMENTARIO", "WS", 
			"ERRO"
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


	public Lexers(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lexers.g4"; }

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
		case 16:
			COMENTARIO_action((RuleContext)_localctx, actionIndex);
			break;
		case 18:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u0180\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u0121\n\2\3\3\6\3\u0124\n\3\r"+
		"\3\16\3\u0125\3\4\6\4\u0129\n\4\r\4\16\4\u012a\3\4\3\4\6\4\u012f\n\4\r"+
		"\4\16\4\u0130\5\4\u0133\n\4\3\5\3\5\3\5\7\5\u0138\n\5\f\5\16\5\u013b\13"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"\u014d\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0156\n\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\7\21\u0168\n"+
		"\21\f\21\16\21\u016b\13\21\3\22\3\22\7\22\u016f\n\22\f\22\16\22\u0172"+
		"\13\22\3\22\5\22\u0175\n\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3"+
		"\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\2\r\7\17\b\21\t\23\n\25\13\27\f\31"+
		"\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25\3\2\t\4\2\'\'``\5\2\f\f$$^^"+
		"\5\2,-//\61\61\4\2C\\c|\6\2\62;C\\aac|\5\2\f\f}}\177\177\5\2\13\f\17\17"+
		"\"\"\2\u01b4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3\u0120\3\2\2\2\5\u0123\3\2\2\2\7"+
		"\u0128\3\2\2\2\t\u0134\3\2\2\2\13\u013e\3\2\2\2\r\u0141\3\2\2\2\17\u014c"+
		"\3\2\2\2\21\u0155\3\2\2\2\23\u0157\3\2\2\2\25\u0159\3\2\2\2\27\u015b\3"+
		"\2\2\2\31\u015d\3\2\2\2\33\u015f\3\2\2\2\35\u0161\3\2\2\2\37\u0163\3\2"+
		"\2\2!\u0165\3\2\2\2#\u016c\3\2\2\2%\u0179\3\2\2\2\'\u017b\3\2\2\2)\u017e"+
		"\3\2\2\2+,\7c\2\2,-\7n\2\2-.\7i\2\2./\7q\2\2/\60\7t\2\2\60\61\7k\2\2\61"+
		"\62\7v\2\2\62\63\7o\2\2\63\u0121\7q\2\2\64\65\7f\2\2\65\66\7g\2\2\66\67"+
		"\7e\2\2\678\7n\2\289\7c\2\29:\7t\2\2:\u0121\7g\2\2;<\7n\2\2<=\7k\2\2="+
		">\7v\2\2>?\7g\2\2?@\7t\2\2@A\7c\2\2A\u0121\7n\2\2BC\7k\2\2CD\7p\2\2DE"+
		"\7v\2\2EF\7g\2\2FG\7k\2\2GH\7t\2\2H\u0121\7q\2\2IJ\7n\2\2JK\7g\2\2KL\7"+
		"k\2\2L\u0121\7c\2\2MN\7g\2\2NO\7u\2\2OP\7e\2\2PQ\7t\2\2QR\7g\2\2RS\7x"+
		"\2\2S\u0121\7c\2\2TU\7h\2\2UV\7k\2\2VW\7o\2\2WX\7a\2\2XY\7c\2\2YZ\7n\2"+
		"\2Z[\7i\2\2[\\\7q\2\2\\]\7t\2\2]^\7k\2\2^_\7v\2\2_`\7o\2\2`\u0121\7q\2"+
		"\2ab\7t\2\2bc\7g\2\2cd\7c\2\2d\u0121\7n\2\2ef\7>\2\2f\u0121\7/\2\2gh\7"+
		"n\2\2hi\7q\2\2ij\7i\2\2jk\7k\2\2kl\7e\2\2l\u0121\7q\2\2mn\7u\2\2n\u0121"+
		"\7g\2\2op\7g\2\2pq\7p\2\2qr\7v\2\2rs\7c\2\2s\u0121\7q\2\2tu\7u\2\2uv\7"+
		"g\2\2vw\7p\2\2wx\7c\2\2x\u0121\7q\2\2yz\7h\2\2z{\7k\2\2{|\7o\2\2|}\7a"+
		"\2\2}~\7u\2\2~\u0121\7g\2\2\177\u0080\7e\2\2\u0080\u0081\7c\2\2\u0081"+
		"\u0082\7u\2\2\u0082\u0121\7q\2\2\u0083\u0084\7u\2\2\u0084\u0085\7g\2\2"+
		"\u0085\u0086\7l\2\2\u0086\u0121\7c\2\2\u0087\u0088\7\60\2\2\u0088\u0121"+
		"\7\60\2\2\u0089\u008a\7h\2\2\u008a\u008b\7k\2\2\u008b\u008c\7o\2\2\u008c"+
		"\u008d\7a\2\2\u008d\u008e\7e\2\2\u008e\u008f\7c\2\2\u008f\u0090\7u\2\2"+
		"\u0090\u0121\7q\2\2\u0091\u0092\7r\2\2\u0092\u0093\7c\2\2\u0093\u0094"+
		"\7t\2\2\u0094\u0121\7c\2\2\u0095\u0096\7c\2\2\u0096\u0097\7v\2\2\u0097"+
		"\u0121\7g\2\2\u0098\u0099\7h\2\2\u0099\u009a\7c\2\2\u009a\u009b\7e\2\2"+
		"\u009b\u0121\7c\2\2\u009c\u009d\7h\2\2\u009d\u009e\7k\2\2\u009e\u009f"+
		"\7o\2\2\u009f\u00a0\7a\2\2\u00a0\u00a1\7r\2\2\u00a1\u00a2\7c\2\2\u00a2"+
		"\u00a3\7t\2\2\u00a3\u0121\7c\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7p\2\2"+
		"\u00a6\u00a7\7s\2\2\u00a7\u00a8\7w\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa"+
		"\7p\2\2\u00aa\u00ab\7v\2\2\u00ab\u0121\7q\2\2\u00ac\u00ad\7h\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7o\2\2\u00af\u00b0\7a\2\2\u00b0\u00b1\7g\2\2"+
		"\u00b1\u00b2\7p\2\2\u00b2\u00b3\7s\2\2\u00b3\u00b4\7w\2\2\u00b4\u00b5"+
		"\7c\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7\7v\2\2\u00b7\u0121\7q\2\2\u00b8"+
		"\u0121\t\2\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7i\2"+
		"\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7u\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\u0121\7q\2\2\u00c1\u00c2\7h\2\2\u00c2\u00c3\7k\2\2\u00c3"+
		"\u00c4\7o\2\2\u00c4\u00c5\7a\2\2\u00c5\u00c6\7t\2\2\u00c6\u00c7\7g\2\2"+
		"\u00c7\u00c8\7i\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7u\2\2\u00ca\u00cb"+
		"\7v\2\2\u00cb\u00cc\7t\2\2\u00cc\u0121\7q\2\2\u00cd\u0121\7\60\2\2\u00ce"+
		"\u00cf\7v\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7r\2\2\u00d1\u0121\7q\2\2"+
		"\u00d2\u00d3\7r\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6"+
		"\7e\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7f\2\2\u00d8\u00d9\7k\2\2\u00d9"+
		"\u00da\7o\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7v\2\2"+
		"\u00dd\u0121\7q\2\2\u00de\u00df\7x\2\2\u00df\u00e0\7c\2\2\u00e0\u0121"+
		"\7t\2\2\u00e1\u00e2\7h\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7o\2\2\u00e4"+
		"\u00e5\7a\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7q\2\2"+
		"\u00e8\u00e9\7e\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7f\2\2\u00eb\u00ec"+
		"\7k\2\2\u00ec\u00ed\7o\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7p\2\2\u00ef"+
		"\u00f0\7v\2\2\u00f0\u0121\7q\2\2\u00f1\u00f2\7h\2\2\u00f2\u00f3\7w\2\2"+
		"\u00f3\u00f4\7p\2\2\u00f4\u00f5\7e\2\2\u00f5\u00f6\7c\2\2\u00f6\u0121"+
		"\7q\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7v\2\2\u00fa"+
		"\u00fb\7q\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7p\2\2\u00fd\u0121\7g\2\2"+
		"\u00fe\u00ff\7h\2\2\u00ff\u0100\7k\2\2\u0100\u0101\7o\2\2\u0101\u0102"+
		"\7a\2\2\u0102\u0103\7h\2\2\u0103\u0104\7w\2\2\u0104\u0105\7p\2\2\u0105"+
		"\u0106\7e\2\2\u0106\u0107\7c\2\2\u0107\u0121\7q\2\2\u0108\u0109\7e\2\2"+
		"\u0109\u010a\7q\2\2\u010a\u010b\7p\2\2\u010b\u010c\7u\2\2\u010c\u010d"+
		"\7v\2\2\u010d\u010e\7c\2\2\u010e\u010f\7p\2\2\u010f\u0110\7v\2\2\u0110"+
		"\u0121\7g\2\2\u0111\u0112\7h\2\2\u0112\u0113\7c\2\2\u0113\u0114\7n\2\2"+
		"\u0114\u0115\7u\2\2\u0115\u0121\7q\2\2\u0116\u0117\7x\2\2\u0117\u0118"+
		"\7g\2\2\u0118\u0119\7t\2\2\u0119\u011a\7f\2\2\u011a\u011b\7c\2\2\u011b"+
		"\u011c\7f\2\2\u011c\u011d\7g\2\2\u011d\u011e\7k\2\2\u011e\u011f\7t\2\2"+
		"\u011f\u0121\7q\2\2\u0120+\3\2\2\2\u0120\64\3\2\2\2\u0120;\3\2\2\2\u0120"+
		"B\3\2\2\2\u0120I\3\2\2\2\u0120M\3\2\2\2\u0120T\3\2\2\2\u0120a\3\2\2\2"+
		"\u0120e\3\2\2\2\u0120g\3\2\2\2\u0120m\3\2\2\2\u0120o\3\2\2\2\u0120t\3"+
		"\2\2\2\u0120y\3\2\2\2\u0120\177\3\2\2\2\u0120\u0083\3\2\2\2\u0120\u0087"+
		"\3\2\2\2\u0120\u0089\3\2\2\2\u0120\u0091\3\2\2\2\u0120\u0095\3\2\2\2\u0120"+
		"\u0098\3\2\2\2\u0120\u009c\3\2\2\2\u0120\u00a4\3\2\2\2\u0120\u00ac\3\2"+
		"\2\2\u0120\u00b8\3\2\2\2\u0120\u00b9\3\2\2\2\u0120\u00c1\3\2\2\2\u0120"+
		"\u00cd\3\2\2\2\u0120\u00ce\3\2\2\2\u0120\u00d2\3\2\2\2\u0120\u00de\3\2"+
		"\2\2\u0120\u00e1\3\2\2\2\u0120\u00f1\3\2\2\2\u0120\u00f7\3\2\2\2\u0120"+
		"\u00fe\3\2\2\2\u0120\u0108\3\2\2\2\u0120\u0111\3\2\2\2\u0120\u0116\3\2"+
		"\2\2\u0121\4\3\2\2\2\u0122\u0124\4\62;\2\u0123\u0122\3\2\2\2\u0124\u0125"+
		"\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\6\3\2\2\2\u0127"+
		"\u0129\4\62;\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u0132\3\2\2\2\u012c\u012e\7\60\2\2\u012d"+
		"\u012f\4\62;\2\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u012c\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\b\3\2\2\2\u0134\u0139\7$\2\2\u0135\u0138\5\13\6\2"+
		"\u0136\u0138\n\3\2\2\u0137\u0135\3\2\2\2\u0137\u0136\3\2\2\2\u0138\u013b"+
		"\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b"+
		"\u0139\3\2\2\2\u013c\u013d\7$\2\2\u013d\n\3\2\2\2\u013e\u013f\7^\2\2\u013f"+
		"\u0140\7$\2\2\u0140\f\3\2\2\2\u0141\u0142\4$$\2\u0142\16\3\2\2\2\u0143"+
		"\u014d\7@\2\2\u0144\u0145\7@\2\2\u0145\u014d\7?\2\2\u0146\u014d\7>\2\2"+
		"\u0147\u0148\7>\2\2\u0148\u014d\7?\2\2\u0149\u014a\7>\2\2\u014a\u014d"+
		"\7@\2\2\u014b\u014d\7?\2\2\u014c\u0143\3\2\2\2\u014c\u0144\3\2\2\2\u014c"+
		"\u0146\3\2\2\2\u014c\u0147\3\2\2\2\u014c\u0149\3\2\2\2\u014c\u014b\3\2"+
		"\2\2\u014d\20\3\2\2\2\u014e\u0156\7g\2\2\u014f\u0150\7q\2\2\u0150\u0156"+
		"\7w\2\2\u0151\u0152\7p\2\2\u0152\u0153\7c\2\2\u0153\u0156\7q\2\2\u0154"+
		"\u0156\7(\2\2\u0155\u014e\3\2\2\2\u0155\u014f\3\2\2\2\u0155\u0151\3\2"+
		"\2\2\u0155\u0154\3\2\2\2\u0156\22\3\2\2\2\u0157\u0158\t\4\2\2\u0158\24"+
		"\3\2\2\2\u0159\u015a\7<\2\2\u015a\26\3\2\2\2\u015b\u015c\7*\2\2\u015c"+
		"\30\3\2\2\2\u015d\u015e\7+\2\2\u015e\32\3\2\2\2\u015f\u0160\7]\2\2\u0160"+
		"\34\3\2\2\2\u0161\u0162\7_\2\2\u0162\36\3\2\2\2\u0163\u0164\7.\2\2\u0164"+
		" \3\2\2\2\u0165\u0169\t\5\2\2\u0166\u0168\t\6\2\2\u0167\u0166\3\2\2\2"+
		"\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\"\3"+
		"\2\2\2\u016b\u0169\3\2\2\2\u016c\u0170\7}\2\2\u016d\u016f\n\7\2\2\u016e"+
		"\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2"+
		"\2\2\u0171\u0174\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0175\7\17\2\2\u0174"+
		"\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\7\177"+
		"\2\2\u0177\u0178\b\22\2\2\u0178$\3\2\2\2\u0179\u017a\4}}\2\u017a&\3\2"+
		"\2\2\u017b\u017c\t\b\2\2\u017c\u017d\b\24\3\2\u017d(\3\2\2\2\u017e\u017f"+
		"\13\2\2\2\u017f*\3\2\2\2\17\2\u0120\u0125\u012a\u0130\u0132\u0137\u0139"+
		"\u014c\u0155\u0169\u0170\u0174\4\3\22\2\3\24\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}