// Generated from br\u005Cufscar\dc\compiladores\t4\CF.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.t4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PALAVRA_CHAVE=9, 
		CNPJ=10, CPF=11, NUM_INT=12, NUM_REAL=13, COMENTARIO=14, DELIM=15, VIRGULA=16, 
		PORCENTAGEM=17, BARRA=18, ATRIBUICAO=19, NEGATIVO=20, UNIDADE_MEDIDA=21, 
		IDENT=22, WS=23, ERRO=24;
	public static final int
		RULE_programa = 0, RULE_produtos = 1, RULE_precos = 2, RULE_nome = 3, 
		RULE_impostos = 4, RULE_porcentagens = 5, RULE_taxas = 6, RULE_loja = 7, 
		RULE_nota = 8, RULE_itens = 9, RULE_infos = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "produtos", "precos", "nome", "impostos", "porcentagens", 
			"taxas", "loja", "nota", "itens", "infos"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PRODUTOS'", "'IMPOSTOS'", "'TAXAS_EXTRAS'", "'LOJA'", "'NOME'", 
			"'CNPJ'", "'NOTA'", "'CPF'", null, null, null, null, null, null, "':'", 
			"','", "'%'", "'/'", "'='", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "PALAVRA_CHAVE", 
			"CNPJ", "CPF", "NUM_INT", "NUM_REAL", "COMENTARIO", "DELIM", "VIRGULA", 
			"PORCENTAGEM", "BARRA", "ATRIBUICAO", "NEGATIVO", "UNIDADE_MEDIDA", "IDENT", 
			"WS", "ERRO"
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
	public String getGrammarFileName() { return "CF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public ProdutosContext produtos() {
			return getRuleContext(ProdutosContext.class,0);
		}
		public ImpostosContext impostos() {
			return getRuleContext(ImpostosContext.class,0);
		}
		public LojaContext loja() {
			return getRuleContext(LojaContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CFParser.EOF, 0); }
		public TaxasContext taxas() {
			return getRuleContext(TaxasContext.class,0);
		}
		public List<NotaContext> nota() {
			return getRuleContexts(NotaContext.class);
		}
		public NotaContext nota(int i) {
			return getRuleContext(NotaContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			produtos();
			setState(23);
			impostos();
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(24);
				taxas();
				}
			}

			setState(27);
			loja();
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				nota();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__6 );
			setState(33);
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

	public static class ProdutosContext extends ParserRuleContext {
		public TerminalNode DELIM() { return getToken(CFParser.DELIM, 0); }
		public List<PrecosContext> precos() {
			return getRuleContexts(PrecosContext.class);
		}
		public PrecosContext precos(int i) {
			return getRuleContext(PrecosContext.class,i);
		}
		public ProdutosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_produtos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterProdutos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitProdutos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitProdutos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProdutosContext produtos() throws RecognitionException {
		ProdutosContext _localctx = new ProdutosContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_produtos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__0);
			setState(36);
			match(DELIM);
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(37);
				precos();
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class PrecosContext extends ParserRuleContext {
		public List<NomeContext> nome() {
			return getRuleContexts(NomeContext.class);
		}
		public NomeContext nome(int i) {
			return getRuleContext(NomeContext.class,i);
		}
		public List<TerminalNode> ATRIBUICAO() { return getTokens(CFParser.ATRIBUICAO); }
		public TerminalNode ATRIBUICAO(int i) {
			return getToken(CFParser.ATRIBUICAO, i);
		}
		public List<TerminalNode> BARRA() { return getTokens(CFParser.BARRA); }
		public TerminalNode BARRA(int i) {
			return getToken(CFParser.BARRA, i);
		}
		public List<TerminalNode> UNIDADE_MEDIDA() { return getTokens(CFParser.UNIDADE_MEDIDA); }
		public TerminalNode UNIDADE_MEDIDA(int i) {
			return getToken(CFParser.UNIDADE_MEDIDA, i);
		}
		public List<TerminalNode> NUM_INT() { return getTokens(CFParser.NUM_INT); }
		public TerminalNode NUM_INT(int i) {
			return getToken(CFParser.NUM_INT, i);
		}
		public List<TerminalNode> NUM_REAL() { return getTokens(CFParser.NUM_REAL); }
		public TerminalNode NUM_REAL(int i) {
			return getToken(CFParser.NUM_REAL, i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(CFParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(CFParser.VIRGULA, i);
		}
		public PrecosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterPrecos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitPrecos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitPrecos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrecosContext precos() throws RecognitionException {
		PrecosContext _localctx = new PrecosContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_precos);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(43);
					nome();
					setState(48);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VIRGULA) {
						{
						{
						setState(44);
						match(VIRGULA);
						setState(45);
						nome();
						}
						}
						setState(50);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(51);
					match(ATRIBUICAO);
					setState(52);
					_la = _input.LA(1);
					if ( !(_la==NUM_INT || _la==NUM_REAL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(53);
					match(BARRA);
					setState(54);
					match(UNIDADE_MEDIDA);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(58); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class NomeContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(CFParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(CFParser.IDENT, i);
		}
		public NomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterNome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitNome(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitNome(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NomeContext nome() throws RecognitionException {
		NomeContext _localctx = new NomeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_nome);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				match(IDENT);
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
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

	public static class ImpostosContext extends ParserRuleContext {
		public TerminalNode DELIM() { return getToken(CFParser.DELIM, 0); }
		public List<PorcentagensContext> porcentagens() {
			return getRuleContexts(PorcentagensContext.class);
		}
		public PorcentagensContext porcentagens(int i) {
			return getRuleContext(PorcentagensContext.class,i);
		}
		public ImpostosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impostos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterImpostos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitImpostos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitImpostos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImpostosContext impostos() throws RecognitionException {
		ImpostosContext _localctx = new ImpostosContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_impostos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__1);
			setState(66);
			match(DELIM);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(67);
				porcentagens();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class PorcentagensContext extends ParserRuleContext {
		public List<NomeContext> nome() {
			return getRuleContexts(NomeContext.class);
		}
		public NomeContext nome(int i) {
			return getRuleContext(NomeContext.class,i);
		}
		public List<TerminalNode> ATRIBUICAO() { return getTokens(CFParser.ATRIBUICAO); }
		public TerminalNode ATRIBUICAO(int i) {
			return getToken(CFParser.ATRIBUICAO, i);
		}
		public List<TerminalNode> PORCENTAGEM() { return getTokens(CFParser.PORCENTAGEM); }
		public TerminalNode PORCENTAGEM(int i) {
			return getToken(CFParser.PORCENTAGEM, i);
		}
		public List<TerminalNode> NUM_INT() { return getTokens(CFParser.NUM_INT); }
		public TerminalNode NUM_INT(int i) {
			return getToken(CFParser.NUM_INT, i);
		}
		public List<TerminalNode> NUM_REAL() { return getTokens(CFParser.NUM_REAL); }
		public TerminalNode NUM_REAL(int i) {
			return getToken(CFParser.NUM_REAL, i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(CFParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(CFParser.VIRGULA, i);
		}
		public List<TerminalNode> NEGATIVO() { return getTokens(CFParser.NEGATIVO); }
		public TerminalNode NEGATIVO(int i) {
			return getToken(CFParser.NEGATIVO, i);
		}
		public PorcentagensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_porcentagens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterPorcentagens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitPorcentagens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitPorcentagens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PorcentagensContext porcentagens() throws RecognitionException {
		PorcentagensContext _localctx = new PorcentagensContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_porcentagens);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(88); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(73);
					nome();
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VIRGULA) {
						{
						{
						setState(74);
						match(VIRGULA);
						setState(75);
						nome();
						}
						}
						setState(80);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(81);
					match(ATRIBUICAO);
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NEGATIVO) {
						{
						setState(82);
						match(NEGATIVO);
						}
					}

					setState(85);
					_la = _input.LA(1);
					if ( !(_la==NUM_INT || _la==NUM_REAL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(86);
					match(PORCENTAGEM);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(90); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class TaxasContext extends ParserRuleContext {
		public TerminalNode DELIM() { return getToken(CFParser.DELIM, 0); }
		public List<PorcentagensContext> porcentagens() {
			return getRuleContexts(PorcentagensContext.class);
		}
		public PorcentagensContext porcentagens(int i) {
			return getRuleContext(PorcentagensContext.class,i);
		}
		public TaxasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taxas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterTaxas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitTaxas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitTaxas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaxasContext taxas() throws RecognitionException {
		TaxasContext _localctx = new TaxasContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_taxas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__2);
			setState(93);
			match(DELIM);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(94);
				porcentagens();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class LojaContext extends ParserRuleContext {
		public List<TerminalNode> DELIM() { return getTokens(CFParser.DELIM); }
		public TerminalNode DELIM(int i) {
			return getToken(CFParser.DELIM, i);
		}
		public List<TerminalNode> ATRIBUICAO() { return getTokens(CFParser.ATRIBUICAO); }
		public TerminalNode ATRIBUICAO(int i) {
			return getToken(CFParser.ATRIBUICAO, i);
		}
		public TerminalNode CNPJ() { return getToken(CFParser.CNPJ, 0); }
		public NomeContext nome() {
			return getRuleContext(NomeContext.class,0);
		}
		public LojaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loja; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterLoja(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitLoja(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitLoja(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LojaContext loja() throws RecognitionException {
		LojaContext _localctx = new LojaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_loja);
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(T__3);
				setState(101);
				match(DELIM);
				setState(102);
				match(T__4);
				setState(103);
				match(ATRIBUICAO);
				{
				setState(104);
				nome();
				}
				setState(105);
				match(T__5);
				setState(106);
				match(ATRIBUICAO);
				setState(107);
				match(CNPJ);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(T__3);
				setState(110);
				match(DELIM);
				setState(111);
				match(T__5);
				setState(112);
				match(ATRIBUICAO);
				setState(113);
				match(CNPJ);
				setState(114);
				match(T__3);
				setState(115);
				match(DELIM);
				setState(116);
				match(T__4);
				setState(117);
				match(ATRIBUICAO);
				setState(118);
				nome();
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

	public static class NotaContext extends ParserRuleContext {
		public TerminalNode DELIM() { return getToken(CFParser.DELIM, 0); }
		public List<ItensContext> itens() {
			return getRuleContexts(ItensContext.class);
		}
		public ItensContext itens(int i) {
			return getRuleContext(ItensContext.class,i);
		}
		public InfosContext infos() {
			return getRuleContext(InfosContext.class,0);
		}
		public NotaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nota; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterNota(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitNota(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitNota(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotaContext nota() throws RecognitionException {
		NotaContext _localctx = new NotaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nota);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__6);
			setState(122);
			match(DELIM);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NUM_INT || _la==NUM_REAL) {
				{
				{
				setState(123);
				itens();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(129);
				infos();
				}
			}

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

	public static class ItensContext extends ParserRuleContext {
		public List<NomeContext> nome() {
			return getRuleContexts(NomeContext.class);
		}
		public NomeContext nome(int i) {
			return getRuleContext(NomeContext.class,i);
		}
		public List<TerminalNode> NUM_INT() { return getTokens(CFParser.NUM_INT); }
		public TerminalNode NUM_INT(int i) {
			return getToken(CFParser.NUM_INT, i);
		}
		public List<TerminalNode> NUM_REAL() { return getTokens(CFParser.NUM_REAL); }
		public TerminalNode NUM_REAL(int i) {
			return getToken(CFParser.NUM_REAL, i);
		}
		public ItensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterItens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitItens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitItens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItensContext itens() throws RecognitionException {
		ItensContext _localctx = new ItensContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_itens);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(134); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(132);
					_la = _input.LA(1);
					if ( !(_la==NUM_INT || _la==NUM_REAL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(133);
					nome();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(136); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class InfosContext extends ParserRuleContext {
		public TerminalNode CPF() { return getToken(CFParser.CPF, 0); }
		public InfosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).enterInfos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CFListener ) ((CFListener)listener).exitInfos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CFVisitor ) return ((CFVisitor<? extends T>)visitor).visitInfos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfosContext infos() throws RecognitionException {
		InfosContext _localctx = new InfosContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_infos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__7);
			setState(139);
			match(CPF);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u0090\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\5\2\34\n\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\2\3"+
		"\2\3\3\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4"+
		"\64\13\4\3\4\3\4\3\4\3\4\3\4\6\4;\n\4\r\4\16\4<\3\5\6\5@\n\5\r\5\16\5"+
		"A\3\6\3\6\3\6\7\6G\n\6\f\6\16\6J\13\6\3\7\3\7\3\7\7\7O\n\7\f\7\16\7R\13"+
		"\7\3\7\3\7\5\7V\n\7\3\7\3\7\3\7\6\7[\n\7\r\7\16\7\\\3\b\3\b\3\b\7\bb\n"+
		"\b\f\b\16\be\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\tz\n\t\3\n\3\n\3\n\7\n\177\n\n\f\n\16\n\u0082"+
		"\13\n\3\n\5\n\u0085\n\n\3\13\3\13\6\13\u0089\n\13\r\13\16\13\u008a\3\f"+
		"\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\16\17\2\u0093\2"+
		"\30\3\2\2\2\4%\3\2\2\2\6:\3\2\2\2\b?\3\2\2\2\nC\3\2\2\2\fZ\3\2\2\2\16"+
		"^\3\2\2\2\20y\3\2\2\2\22{\3\2\2\2\24\u0088\3\2\2\2\26\u008c\3\2\2\2\30"+
		"\31\5\4\3\2\31\33\5\n\6\2\32\34\5\16\b\2\33\32\3\2\2\2\33\34\3\2\2\2\34"+
		"\35\3\2\2\2\35\37\5\20\t\2\36 \5\22\n\2\37\36\3\2\2\2 !\3\2\2\2!\37\3"+
		"\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\7\2\2\3$\3\3\2\2\2%&\7\3\2\2&*\7\21\2\2"+
		"\')\5\6\4\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\5\3\2\2\2,*\3\2"+
		"\2\2-\62\5\b\5\2./\7\22\2\2/\61\5\b\5\2\60.\3\2\2\2\61\64\3\2\2\2\62\60"+
		"\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66\7\25\2\2\66\67"+
		"\t\2\2\2\678\7\24\2\289\7\27\2\29;\3\2\2\2:-\3\2\2\2;<\3\2\2\2<:\3\2\2"+
		"\2<=\3\2\2\2=\7\3\2\2\2>@\7\30\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2"+
		"\2\2B\t\3\2\2\2CD\7\4\2\2DH\7\21\2\2EG\5\f\7\2FE\3\2\2\2GJ\3\2\2\2HF\3"+
		"\2\2\2HI\3\2\2\2I\13\3\2\2\2JH\3\2\2\2KP\5\b\5\2LM\7\22\2\2MO\5\b\5\2"+
		"NL\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2SU\7\25\2"+
		"\2TV\7\26\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\t\2\2\2XY\7\23\2\2Y[\3\2"+
		"\2\2ZK\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\r\3\2\2\2^_\7\5\2\2_"+
		"c\7\21\2\2`b\5\f\7\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\17\3\2\2"+
		"\2ec\3\2\2\2fg\7\6\2\2gh\7\21\2\2hi\7\7\2\2ij\7\25\2\2jk\5\b\5\2kl\7\b"+
		"\2\2lm\7\25\2\2mn\7\f\2\2nz\3\2\2\2op\7\6\2\2pq\7\21\2\2qr\7\b\2\2rs\7"+
		"\25\2\2st\7\f\2\2tu\7\6\2\2uv\7\21\2\2vw\7\7\2\2wx\7\25\2\2xz\5\b\5\2"+
		"yf\3\2\2\2yo\3\2\2\2z\21\3\2\2\2{|\7\t\2\2|\u0080\7\21\2\2}\177\5\24\13"+
		"\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085\5\26\f\2\u0084\u0083\3"+
		"\2\2\2\u0084\u0085\3\2\2\2\u0085\23\3\2\2\2\u0086\u0087\t\2\2\2\u0087"+
		"\u0089\5\b\5\2\u0088\u0086\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\25\3\2\2\2\u008c\u008d\7\n\2\2\u008d\u008e"+
		"\7\r\2\2\u008e\27\3\2\2\2\21\33!*\62<AHPU\\cy\u0080\u0084\u008a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}