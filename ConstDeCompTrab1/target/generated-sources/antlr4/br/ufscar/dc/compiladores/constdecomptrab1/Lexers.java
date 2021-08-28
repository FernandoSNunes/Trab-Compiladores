// Generated from br\u005Cufscar\dc\compiladores\constdecomptrab1\Lexers.g4 by ANTLR 4.7.2
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
		PALAVRA_CHAVE=1, NUM_INT=2, NUMREAL=3, IDENT=4, CADEIA=5, ERRO_CADEIA=6, 
		COMENTARIO=7, ERRO_COMENTARIO=8, WS=9, ERRO=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PALAVRA_CHAVE", "NUM_INT", "NUMREAL", "IDENT", "CADEIA", "ESC_SEQ", 
			"ERRO_CADEIA", "COMENTARIO", "ERRO_COMENTARIO", "WS", "ERRO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PALAVRA_CHAVE", "NUM_INT", "NUMREAL", "IDENT", "CADEIA", "ERRO_CADEIA", 
			"COMENTARIO", "ERRO_COMENTARIO", "WS", "ERRO"
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
		case 7:
			COMENTARIO_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\u015e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
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
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\5\2\u0121\n\2\3\3\6\3\u0124\n\3\r\3\16\3\u0125\3\4\6"+
		"\4\u0129\n\4\r\4\16\4\u012a\3\4\3\4\6\4\u012f\n\4\r\4\16\4\u0130\5\4\u0133"+
		"\n\4\3\5\3\5\7\5\u0137\n\5\f\5\16\5\u013a\13\5\3\6\3\6\3\6\7\6\u013f\n"+
		"\6\f\6\16\6\u0142\13\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\7\t\u014d\n"+
		"\t\f\t\16\t\u0150\13\t\3\t\5\t\u0153\n\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\2\2\r\3\3\5\4\7\5\t\6\13\7\r\2\17\b\21\t\23\n\25\13\27\f"+
		"\3\2\f\4\2--\61\61\4\2,,//\4\2>?gg\5\2\'(@@``\4\2]]__\4\2C\\c|\5\2\62"+
		";C\\c|\5\2\f\f$$^^\5\2\f\f}}\177\177\5\2\13\f\17\17\"\"\2\u0196\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\u0120\3\2\2\2\5\u0123"+
		"\3\2\2\2\7\u0128\3\2\2\2\t\u0134\3\2\2\2\13\u013b\3\2\2\2\r\u0145\3\2"+
		"\2\2\17\u0148\3\2\2\2\21\u014a\3\2\2\2\23\u0157\3\2\2\2\25\u0159\3\2\2"+
		"\2\27\u015c\3\2\2\2\31\32\7c\2\2\32\33\7n\2\2\33\34\7i\2\2\34\35\7q\2"+
		"\2\35\36\7t\2\2\36\37\7k\2\2\37 \7v\2\2 !\7o\2\2!\u0121\7q\2\2\"#\7f\2"+
		"\2#$\7g\2\2$%\7e\2\2%&\7n\2\2&\'\7c\2\2\'(\7t\2\2(\u0121\7g\2\2)\u0121"+
		"\7<\2\2*+\7n\2\2+,\7k\2\2,-\7v\2\2-.\7g\2\2./\7t\2\2/\60\7c\2\2\60\u0121"+
		"\7n\2\2\61\62\7k\2\2\62\63\7p\2\2\63\64\7v\2\2\64\65\7g\2\2\65\66\7k\2"+
		"\2\66\67\7t\2\2\67\u0121\7q\2\289\7n\2\29:\7g\2\2:;\7k\2\2;\u0121\7c\2"+
		"\2<\u0121\4*+\2=>\7g\2\2>?\7u\2\2?@\7e\2\2@A\7t\2\2AB\7g\2\2BC\7x\2\2"+
		"C\u0121\7c\2\2DE\7h\2\2EF\7k\2\2FG\7o\2\2GH\7a\2\2HI\7c\2\2IJ\7n\2\2J"+
		"K\7i\2\2KL\7q\2\2LM\7t\2\2MN\7k\2\2NO\7v\2\2OP\7o\2\2P\u0121\7q\2\2QR"+
		"\7t\2\2RS\7g\2\2ST\7c\2\2T\u0121\7n\2\2U\u0121\t\2\2\2VW\7>\2\2W\u0121"+
		"\7/\2\2X\u0121\t\3\2\2YZ\7n\2\2Z[\7q\2\2[\\\7i\2\2\\]\7k\2\2]^\7e\2\2"+
		"^\u0121\7q\2\2_\u0121\t\4\2\2`a\7p\2\2ab\7c\2\2b\u0121\7q\2\2cd\7q\2\2"+
		"d\u0121\7w\2\2ef\7>\2\2f\u0121\7?\2\2gh\7u\2\2h\u0121\7g\2\2ij\7@\2\2"+
		"j\u0121\7?\2\2kl\7g\2\2lm\7p\2\2mn\7v\2\2no\7c\2\2o\u0121\7q\2\2pq\7u"+
		"\2\2qr\7g\2\2rs\7p\2\2st\7c\2\2t\u0121\7q\2\2uv\7h\2\2vw\7k\2\2wx\7o\2"+
		"\2xy\7a\2\2yz\7u\2\2z\u0121\7g\2\2{|\7e\2\2|}\7c\2\2}~\7u\2\2~\u0121\7"+
		"q\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081\u0082\7l\2\2\u0082\u0121"+
		"\7c\2\2\u0083\u0084\7\60\2\2\u0084\u0121\7\60\2\2\u0085\u0086\7h\2\2\u0086"+
		"\u0087\7k\2\2\u0087\u0088\7o\2\2\u0088\u0089\7a\2\2\u0089\u008a\7e\2\2"+
		"\u008a\u008b\7c\2\2\u008b\u008c\7u\2\2\u008c\u0121\7q\2\2\u008d\u008e"+
		"\7r\2\2\u008e\u008f\7c\2\2\u008f\u0090\7t\2\2\u0090\u0121\7c\2\2\u0091"+
		"\u0092\7c\2\2\u0092\u0093\7v\2\2\u0093\u0121\7g\2\2\u0094\u0095\7h\2\2"+
		"\u0095\u0096\7c\2\2\u0096\u0097\7e\2\2\u0097\u0121\7c\2\2\u0098\u0099"+
		"\7h\2\2\u0099\u009a\7k\2\2\u009a\u009b\7o\2\2\u009b\u009c\7a\2\2\u009c"+
		"\u009d\7r\2\2\u009d\u009e\7c\2\2\u009e\u009f\7t\2\2\u009f\u0121\7c\2\2"+
		"\u00a0\u00a1\7g\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7s\2\2\u00a3\u00a4"+
		"\7w\2\2\u00a4\u00a5\7c\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7v\2\2\u00a7"+
		"\u0121\7q\2\2\u00a8\u00a9\7>\2\2\u00a9\u0121\7@\2\2\u00aa\u00ab\7h\2\2"+
		"\u00ab\u00ac\7k\2\2\u00ac\u00ad\7o\2\2\u00ad\u00ae\7a\2\2\u00ae\u00af"+
		"\7g\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7s\2\2\u00b1\u00b2\7w\2\2\u00b2"+
		"\u00b3\7c\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7v\2\2\u00b5\u0121\7q\2\2"+
		"\u00b6\u0121\t\5\2\2\u00b7\u00b8\7t\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba"+
		"\7i\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7u\2\2\u00bc\u00bd\7v\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\u0121\7q\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1\7k\2\2"+
		"\u00c1\u00c2\7o\2\2\u00c2\u00c3\7a\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5"+
		"\7g\2\2\u00c5\u00c6\7i\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7u\2\2\u00c8"+
		"\u00c9\7v\2\2\u00c9\u00ca\7t\2\2\u00ca\u0121\7q\2\2\u00cb\u0121\7\60\2"+
		"\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7r\2\2\u00cf\u0121"+
		"\7q\2\2\u00d0\u00d1\7r\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7q\2\2\u00d3"+
		"\u00d4\7e\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7f\2\2\u00d6\u00d7\7k\2\2"+
		"\u00d7\u00d8\7o\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7p\2\2\u00da\u00db"+
		"\7v\2\2\u00db\u0121\7q\2\2\u00dc\u00dd\7x\2\2\u00dd\u00de\7c\2\2\u00de"+
		"\u0121\7t\2\2\u00df\u00e0\7h\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7o\2\2"+
		"\u00e2\u00e3\7a\2\2\u00e3\u00e4\7r\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6"+
		"\7q\2\2\u00e6\u00e7\7e\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7f\2\2\u00e9"+
		"\u00ea\7k\2\2\u00ea\u00eb\7o\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed\7p\2\2"+
		"\u00ed\u00ee\7v\2\2\u00ee\u0121\7q\2\2\u00ef\u00f0\7h\2\2\u00f0\u00f1"+
		"\7w\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7e\2\2\u00f3\u00f4\7c\2\2\u00f4"+
		"\u0121\7q\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7v\2\2"+
		"\u00f8\u00f9\7q\2\2\u00f9\u00fa\7t\2\2\u00fa\u00fb\7p\2\2\u00fb\u0121"+
		"\7g\2\2\u00fc\u00fd\7h\2\2\u00fd\u00fe\7k\2\2\u00fe\u00ff\7o\2\2\u00ff"+
		"\u0100\7a\2\2\u0100\u0101\7h\2\2\u0101\u0102\7w\2\2\u0102\u0103\7p\2\2"+
		"\u0103\u0104\7e\2\2\u0104\u0105\7c\2\2\u0105\u0121\7q\2\2\u0106\u0121"+
		"\t\6\2\2\u0107\u0108\7e\2\2\u0108\u0109\7q\2\2\u0109\u010a\7p\2\2\u010a"+
		"\u010b\7u\2\2\u010b\u010c\7v\2\2\u010c\u010d\7c\2\2\u010d\u010e\7p\2\2"+
		"\u010e\u010f\7v\2\2\u010f\u0121\7g\2\2\u0110\u0111\7h\2\2\u0111\u0112"+
		"\7c\2\2\u0112\u0113\7n\2\2\u0113\u0114\7u\2\2\u0114\u0121\7q\2\2\u0115"+
		"\u0116\7x\2\2\u0116\u0117\7g\2\2\u0117\u0118\7t\2\2\u0118\u0119\7f\2\2"+
		"\u0119\u011a\7c\2\2\u011a\u011b\7f\2\2\u011b\u011c\7g\2\2\u011c\u011d"+
		"\7k\2\2\u011d\u011e\7t\2\2\u011e\u0121\7q\2\2\u011f\u0121\7.\2\2\u0120"+
		"\31\3\2\2\2\u0120\"\3\2\2\2\u0120)\3\2\2\2\u0120*\3\2\2\2\u0120\61\3\2"+
		"\2\2\u01208\3\2\2\2\u0120<\3\2\2\2\u0120=\3\2\2\2\u0120D\3\2\2\2\u0120"+
		"Q\3\2\2\2\u0120U\3\2\2\2\u0120V\3\2\2\2\u0120X\3\2\2\2\u0120Y\3\2\2\2"+
		"\u0120_\3\2\2\2\u0120`\3\2\2\2\u0120c\3\2\2\2\u0120e\3\2\2\2\u0120g\3"+
		"\2\2\2\u0120i\3\2\2\2\u0120k\3\2\2\2\u0120p\3\2\2\2\u0120u\3\2\2\2\u0120"+
		"{\3\2\2\2\u0120\177\3\2\2\2\u0120\u0083\3\2\2\2\u0120\u0085\3\2\2\2\u0120"+
		"\u008d\3\2\2\2\u0120\u0091\3\2\2\2\u0120\u0094\3\2\2\2\u0120\u0098\3\2"+
		"\2\2\u0120\u00a0\3\2\2\2\u0120\u00a8\3\2\2\2\u0120\u00aa\3\2\2\2\u0120"+
		"\u00b6\3\2\2\2\u0120\u00b7\3\2\2\2\u0120\u00bf\3\2\2\2\u0120\u00cb\3\2"+
		"\2\2\u0120\u00cc\3\2\2\2\u0120\u00d0\3\2\2\2\u0120\u00dc\3\2\2\2\u0120"+
		"\u00df\3\2\2\2\u0120\u00ef\3\2\2\2\u0120\u00f5\3\2\2\2\u0120\u00fc\3\2"+
		"\2\2\u0120\u0106\3\2\2\2\u0120\u0107\3\2\2\2\u0120\u0110\3\2\2\2\u0120"+
		"\u0115\3\2\2\2\u0120\u011f\3\2\2\2\u0121\4\3\2\2\2\u0122\u0124\4\62;\2"+
		"\u0123\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126"+
		"\3\2\2\2\u0126\6\3\2\2\2\u0127\u0129\4\62;\2\u0128\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u0132\3\2"+
		"\2\2\u012c\u012e\7\60\2\2\u012d\u012f\4\62;\2\u012e\u012d\3\2\2\2\u012f"+
		"\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2"+
		"\2\2\u0132\u012c\3\2\2\2\u0132\u0133\3\2\2\2\u0133\b\3\2\2\2\u0134\u0138"+
		"\t\7\2\2\u0135\u0137\t\b\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\n\3\2\2\2\u013a\u0138\3\2\2\2"+
		"\u013b\u0140\7$\2\2\u013c\u013f\5\r\7\2\u013d\u013f\n\t\2\2\u013e\u013c"+
		"\3\2\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144\7$"+
		"\2\2\u0144\f\3\2\2\2\u0145\u0146\7^\2\2\u0146\u0147\7$\2\2\u0147\16\3"+
		"\2\2\2\u0148\u0149\4$$\2\u0149\20\3\2\2\2\u014a\u014e\7}\2\2\u014b\u014d"+
		"\n\n\2\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0153\7\17"+
		"\2\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2\2\2\u0154"+
		"\u0155\7\177\2\2\u0155\u0156\b\t\2\2\u0156\22\3\2\2\2\u0157\u0158\4}}"+
		"\2\u0158\24\3\2\2\2\u0159\u015a\t\13\2\2\u015a\u015b\b\13\3\2\u015b\26"+
		"\3\2\2\2\u015c\u015d\13\2\2\2\u015d\30\3\2\2\2\r\2\u0120\u0125\u012a\u0130"+
		"\u0132\u0138\u013e\u0140\u014e\u0152\4\3\t\2\3\13\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}