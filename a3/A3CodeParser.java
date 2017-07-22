// Generated from A3Code.g4 by ANTLR 4.5.3


import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class A3CodeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, WhiteSpace=9, 
		Char=10, Str=11, Class=12, Program=13, Void=14, If=15, Else=16, For=17, 
		Ret=18, Brk=19, Cnt=20, Callout=21, DecNum=22, HexNum=23, BoolLit=24, 
		Type=25, Ident=26, RelOp=27, AssignOp=28, MulDiv=29, AddOp=30, SubOp=31, 
		AndOp=32, OrOp=33;
	public static final int
		RULE_prog = 0, RULE_field_decls = 1, RULE_field_decl = 2, RULE_method_decl = 3, 
		RULE_params = 4, RULE_nextParams = 5, RULE_block = 6, RULE_var_decls = 7, 
		RULE_var_decl = 8, RULE_statements = 9, RULE_statement = 10, RULE_expr = 11, 
		RULE_location = 12, RULE_num = 13, RULE_literal = 14, RULE_eqOp = 15;
	public static final String[] ruleNames = {
		"prog", "field_decls", "field_decl", "method_decl", "params", "nextParams", 
		"block", "var_decls", "var_decl", "statements", "statement", "expr", "location", 
		"num", "literal", "eqOp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "';'", "','", "'('", "')'", "'='", "'!'", null, null, 
		null, "'class'", "'Program'", "'void'", "'if'", "'else'", "'for'", "'return'", 
		"'break'", "'continue'", "'callout'", null, null, null, null, null, null, 
		null, null, "'+'", "'-'", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "WhiteSpace", "Char", 
		"Str", "Class", "Program", "Void", "If", "Else", "For", "Ret", "Brk", 
		"Cnt", "Callout", "DecNum", "HexNum", "BoolLit", "Type", "Ident", "RelOp", 
		"AssignOp", "MulDiv", "AddOp", "SubOp", "AndOp", "OrOp"
	};
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
	public String getGrammarFileName() { return "A3Code.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	public enum DataType {
		INT, BOOLEAN, VOID, INVALID
	}


	public class TempUtil {
		int incr = 0;

		int GetNext() {
			return (incr ++);
		}
	}

	public class IdUtil {
		int incr = 0;
		
		int GetNext() {
			return (incr ++);
		}	
	}

	TempUtil tempUtil = new TempUtil();
	IdUtil idUtil = new IdUtil();

	public class Symbol {
		int id;
		String name;
		DataType dt;

		Symbol (String n, DataType d) {
			id = idUtil.GetNext();
			name = n;
			dt = d;
		}

		Symbol (DataType d) {
			this("t_" + tempUtil.GetNext(), d);
		}

		boolean Equal (String n) {
			return (name.equals(n));
		}

		DataType GetType () {
			return dt;
		}

		String GetName () {
			return name;
		}

		int GetId() {
			return id;
		}

		void Print() {
			System.out.println(name + "\t" + dt);
		}

		
		
	}

	public class SymTab {
		List<Symbol> st;

		SymTab() {
			st = new ArrayList<>();
		}

		int Find (String n) {
			for (int  i = 0; i < st.size(); i ++) {
				if (st.get(i).Equal(n)) return i;
			}
			
			return -1;
		}

		int insert(String n, DataType d) {
			int id = Find(n);
			if (id != -1) return id;
			
			Symbol s = new Symbol(n, d);
			st.add(s);
			return (s.GetId());
		}

		int Add (DataType d) {
			Symbol s = new Symbol(d);
			st.add(s);
			return (s.GetId());
		}

		DataType GetType (int id) {
			if (id == -1)
				return DataType.INVALID;
			return (getSymById(id).GetType());
		}

		String GetName (int id) {
			if (id == -1)
				return ("");
			return (getSymById(id).GetName());
		}

		Symbol getSymById(int id) {
			for (Symbol s : st) {
				if (s.GetId() == id) {
					return s;
				}
			}

			return null;
		}

		void Print() {
			for (Symbol s : st) {
				s.Print();
			}
		}

		void InsertMultiple(List<Symbol> symbols) {
			for(Symbol s : symbols) {
				this.insert(s.GetName(), s.GetType());
			}
		}

	}

	public class SymTabStack {
		ArrayDeque<SymTab> stack;

		SymTabStack() {
			stack = new ArrayDeque<>();
			stack.add(new SymTab()); //add global symTab
		}

		void push(SymTab st) {
			stack.push(st);
		}

		int size() {
			return stack.size();
		}

		SymTab pop() {
			return stack.pop();
		}

		SymTab getLast() {
			return stack.getLast();
		}

		SymTab getFirst() {
			return stack.getFirst();
		}

		int Find (String n) {
			//search from top of stack (most inner scope)
			ArrayDeque<SymTab> stackCopy = stack.clone();

			int id = -1;
			while (
				!stackCopy.isEmpty() && 
				(id = stackCopy.pop().Find(n)) == -1
			);

			return id;
		}

		DataType GetType (int id) {
			if (id == -1)
				return DataType.INVALID;
			return (getSymById(id).GetType());
		}

		String GetName (int id) {
			if (id == -1)
				return ("");
			return (getSymById(id).GetName());
		}

		Symbol getSymById(int id) {
			for (SymTab st : stack) {
				Symbol s = st.getSymById(id);
				if (s != null)
					return s;
			}

			return null;
		}

		void Print() {
			System.out.println("symtab");
			for (SymTab st : stack) {
				st.Print();
			}
		}
	}

	SymTabStack symTabStack = new SymTabStack();
	SymTab globalSt = new SymTab();

	public class Quad {

		int label;
		String op;
		int src1;
		int src2;
		int dst;


		Quad (int l, int d, int s1, int s2, String o) {
			label = l;
			dst = d;
			src1 = s1;
			src2 = s2;
			op = o;
		}

		void Print () {
			if (symTabStack.GetName(src2).equals("") && op.equals("=")) {
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
					+ op + " " + symTabStack.GetName(src1));
			} else if(symTabStack.GetName(src2).equals("")) {
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
					+ op + " " + symTabStack.GetName(src1));
			} else {
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
					+ symTabStack.GetName(src1) + " " + op + " " + symTabStack.GetName(src2));
			}
		}

	}

	public class QuadTab {

		List<Quad> qt;
		int size;

		QuadTab () {
			qt = new ArrayList<>();
			size = 0;
		}

		int Add(int dst, int src1, int src2, String op) {
				
			qt.add(new Quad(size, dst, src1, src2, op));
			return (qt.size()-1);
		}

		void Print() {
			for(Quad q : qt) {
				q.Print();
			}
		}


	}

	QuadTab q = new QuadTab();


	public A3CodeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(A3CodeParser.Class, 0); }
		public TerminalNode Program() { return getToken(A3CodeParser.Program, 0); }
		public Field_declsContext field_decls() {
			return getRuleContext(Field_declsContext.class,0);
		}
		public Method_declContext method_decl() {
			return getRuleContext(Method_declContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(Class);
			setState(33);
			match(Program);
			setState(34);
			match(T__0);
			setState(35);
			field_decls(0);
			setState(36);
			method_decl();
			setState(37);
			match(T__1);

				/*SymTab globalSt = new SymTab();
				symTabStack.push(globalSt);*/

				//Print
				symTabStack.Print();
				System.out.println("------------------------------------");
				q.Print();

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

	public static class Field_declsContext extends ParserRuleContext {
		public Field_declsContext f;
		public Field_declContext field_decl() {
			return getRuleContext(Field_declContext.class,0);
		}
		public Field_declsContext field_decls() {
			return getRuleContext(Field_declsContext.class,0);
		}
		public Field_declsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterField_decls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitField_decls(this);
		}
	}

	public final Field_declsContext field_decls() throws RecognitionException {
		return field_decls(0);
	}

	private Field_declsContext field_decls(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Field_declsContext _localctx = new Field_declsContext(_ctx, _parentState);
		Field_declsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_field_decls, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Field_declsContext(_parentctx, _parentState);
					_localctx.f = _prevctx;
					_localctx.f = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_field_decls);
					setState(41);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(42);
					field_decl(0);
					setState(43);
					match(T__2);
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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

	public static class Field_declContext extends ParserRuleContext {
		public DataType t;
		public Field_declContext f;
		public Token Type;
		public Token Ident;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public Field_declContext field_decl() {
			return getRuleContext(Field_declContext.class,0);
		}
		public Field_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterField_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitField_decl(this);
		}
	}

	public final Field_declContext field_decl() throws RecognitionException {
		return field_decl(0);
	}

	private Field_declContext field_decl(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Field_declContext _localctx = new Field_declContext(_ctx, _parentState);
		Field_declContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_field_decl, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(51);
			((Field_declContext)_localctx).Type = match(Type);
			setState(52);
			((Field_declContext)_localctx).Ident = match(Ident);

				((Field_declContext)_localctx).t =  DataType.valueOf((((Field_declContext)_localctx).Type!=null?((Field_declContext)_localctx).Type.getText():null).toUpperCase());
				symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t);

			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Field_declContext(_parentctx, _parentState);
					_localctx.f = _prevctx;
					_localctx.f = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_field_decl);
					setState(55);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(56);
					match(T__3);
					setState(57);
					((Field_declContext)_localctx).Ident = match(Ident);

					          	((Field_declContext)_localctx).t =  ((Field_declContext)_localctx).f.t;
					          	symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t);
					          
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class Method_declContext extends ParserRuleContext {
		public Token Type;
		public Token Ident;
		public Token Void;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode Void() { return getToken(A3CodeParser.Void, 0); }
		public Method_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMethod_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMethod_decl(this);
		}
	}

	public final Method_declContext method_decl() throws RecognitionException {
		Method_declContext _localctx = new Method_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_method_decl);
		try {
			setState(80);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				((Method_declContext)_localctx).Type = match(Type);
				setState(65);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(66);
				match(T__4);
				setState(67);
				params();
				setState(68);
				match(T__5);
				setState(69);
				block();

					symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Type!=null?((Method_declContext)_localctx).Type.getText():null).toUpperCase()));

				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((Method_declContext)_localctx).Void = match(Void);
				setState(73);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(74);
				match(T__4);
				setState(75);
				params();
				setState(76);
				match(T__5);
				setState(77);
				block();

					symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Void!=null?((Method_declContext)_localctx).Void.getText():null).toUpperCase()));	

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ParamsContext extends ParserRuleContext {
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public NextParamsContext nextParams() {
			return getRuleContext(NextParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		try {
			setState(88);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				match(Type);
				setState(83);
				match(Ident);
				setState(84);
				nextParams(0);



				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{



				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NextParamsContext extends ParserRuleContext {
		public NextParamsContext n;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public NextParamsContext nextParams() {
			return getRuleContext(NextParamsContext.class,0);
		}
		public NextParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nextParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterNextParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitNextParams(this);
		}
	}

	public final NextParamsContext nextParams() throws RecognitionException {
		return nextParams(0);
	}

	private NextParamsContext nextParams(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NextParamsContext _localctx = new NextParamsContext(_ctx, _parentState);
		NextParamsContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_nextParams, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{



			}
			_ctx.stop = _input.LT(-1);
			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NextParamsContext(_parentctx, _parentState);
					_localctx.n = _prevctx;
					_localctx.n = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_nextParams);
					setState(93);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(94);
					match(T__3);
					setState(95);
					match(Type);
					setState(96);
					match(Ident);


					          
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class BlockContext extends ParserRuleContext {
		public Var_declsContext var_decls;
		public Var_declsContext var_decls() {
			return getRuleContext(Var_declsContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__0);
			setState(104);
			((BlockContext)_localctx).var_decls = var_decls(0);
			setState(105);
			statements();
			setState(106);
			match(T__1);

				symTabStack.push(((BlockContext)_localctx).var_decls.st);

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

	public static class Var_declsContext extends ParserRuleContext {
		public SymTab st;
		public Var_declsContext v;
		public Var_declContext var_decl;
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Var_declsContext var_decls() {
			return getRuleContext(Var_declsContext.class,0);
		}
		public Var_declsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterVar_decls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitVar_decls(this);
		}
	}

	public final Var_declsContext var_decls() throws RecognitionException {
		return var_decls(0);
	}

	private Var_declsContext var_decls(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Var_declsContext _localctx = new Var_declsContext(_ctx, _parentState);
		Var_declsContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_var_decls, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{

				((Var_declsContext)_localctx).st =  new SymTab();

			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Var_declsContext(_parentctx, _parentState);
					_localctx.v = _prevctx;
					_localctx.v = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_var_decls);
					setState(112);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(113);
					((Var_declsContext)_localctx).var_decl = var_decl(0);
					setState(114);
					match(T__2);

					          	((Var_declsContext)_localctx).st =  ((Var_declsContext)_localctx).v.st;
					          	_localctx.st.InsertMultiple(((Var_declsContext)_localctx).var_decl.symbols);
					          
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class Var_declContext extends ParserRuleContext {
		public List<Symbol> symbols;
		public DataType t;
		public Var_declContext v;
		public Token Type;
		public Token Ident;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		return var_decl(0);
	}

	private Var_declContext var_decl(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Var_declContext _localctx = new Var_declContext(_ctx, _parentState);
		Var_declContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_var_decl, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(123);
			((Var_declContext)_localctx).Type = match(Type);
			setState(124);
			((Var_declContext)_localctx).Ident = match(Ident);

				((Var_declContext)_localctx).t =  DataType.valueOf((((Var_declContext)_localctx).Type!=null?((Var_declContext)_localctx).Type.getText():null).toUpperCase());
				((Var_declContext)_localctx).symbols =  new ArrayList();
				_localctx.symbols.add(new Symbol((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));

			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Var_declContext(_parentctx, _parentState);
					_localctx.v = _prevctx;
					_localctx.v = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_var_decl);
					setState(127);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(128);
					match(T__3);
					setState(129);
					((Var_declContext)_localctx).Ident = match(Ident);

					          	((Var_declContext)_localctx).t =  ((Var_declContext)_localctx).v.t;
					          	((Var_declContext)_localctx).symbols =  ((Var_declContext)_localctx).v.symbols;
					          	_localctx.symbols.add(new Symbol((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));
					          
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class StatementsContext extends ParserRuleContext {
		public StatementsContext t;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statements);
		try {
			setState(140);
			switch (_input.LA(1)) {
			case T__0:
			case If:
			case For:
			case Ret:
			case Brk:
			case Cnt:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				statement();
				setState(137);
				((StatementsContext)_localctx).t = statements();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{

					symTabStack.pop();

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class StatementContext extends ParserRuleContext {
		public LocationContext location;
		public EqOpContext eqOp;
		public ExprContext expr;
		public BlockContext b1;
		public BlockContext b2;
		public ExprContext e1;
		public ExprContext e2;
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public EqOpContext eqOp() {
			return getRuleContext(EqOpContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode If() { return getToken(A3CodeParser.If, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode Else() { return getToken(A3CodeParser.Else, 0); }
		public TerminalNode For() { return getToken(A3CodeParser.For, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public TerminalNode Ret() { return getToken(A3CodeParser.Ret, 0); }
		public TerminalNode Brk() { return getToken(A3CodeParser.Brk, 0); }
		public TerminalNode Cnt() { return getToken(A3CodeParser.Cnt, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				((StatementContext)_localctx).location = location();
				setState(143);
				((StatementContext)_localctx).eqOp = eqOp();
				setState(144);
				((StatementContext)_localctx).expr = expr(0);
				setState(145);
				match(T__2);

				//	System.out.println("HEY");
					switch ((((StatementContext)_localctx).eqOp!=null?_input.getText(((StatementContext)_localctx).eqOp.start,((StatementContext)_localctx).eqOp.stop):null))
					{
						case "=":
							q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).expr.id, -1, "=");
							break;
						case "+=":
							break;
						case "-=":
							break;
						default:
							break;
					}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(If);
				setState(149);
				match(T__4);
				setState(150);
				expr(0);
				setState(151);
				match(T__5);
				setState(152);
				block();



				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				match(If);
				setState(156);
				match(T__4);
				setState(157);
				expr(0);
				setState(158);
				match(T__5);
				setState(159);
				((StatementContext)_localctx).b1 = block();
				setState(160);
				match(Else);
				setState(161);
				((StatementContext)_localctx).b2 = block();

					

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(164);
				match(For);
				setState(165);
				match(Ident);
				setState(166);
				match(T__6);
				setState(167);
				((StatementContext)_localctx).e1 = expr(0);
				setState(168);
				match(T__3);
				setState(169);
				((StatementContext)_localctx).e2 = expr(0);
				setState(170);
				block();

					

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(173);
				match(Ret);
				setState(174);
				match(T__2);

					symTabStack.pop();

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(176);
				match(Ret);
				setState(177);
				match(T__4);
				setState(178);
				expr(0);
				setState(179);
				match(T__5);
				setState(180);
				match(T__2);

					symTabStack.pop();

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(183);
				match(Brk);
				setState(184);
				match(T__2);

					

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(186);
				match(Cnt);
				setState(187);
				match(T__2);

					

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(189);
				block();

					

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

	public static class ExprContext extends ParserRuleContext {
		public int id;
		public ExprContext e1;
		public LiteralContext literal;
		public LocationContext location;
		public ExprContext e;
		public Token SubOp;
		public Token MulDiv;
		public ExprContext e2;
		public Token AddOp;
		public Token RelOp;
		public Token AndOp;
		public Token OrOp;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SubOp() { return getToken(A3CodeParser.SubOp, 0); }
		public TerminalNode MulDiv() { return getToken(A3CodeParser.MulDiv, 0); }
		public TerminalNode AddOp() { return getToken(A3CodeParser.AddOp, 0); }
		public TerminalNode RelOp() { return getToken(A3CodeParser.RelOp, 0); }
		public TerminalNode AndOp() { return getToken(A3CodeParser.AndOp, 0); }
		public TerminalNode OrOp() { return getToken(A3CodeParser.OrOp, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			switch (_input.LA(1)) {
			case Char:
			case DecNum:
			case HexNum:
			case BoolLit:
				{
				setState(195);
				((ExprContext)_localctx).literal = literal();

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).literal.id;

				}
				break;
			case Ident:
				{
				setState(198);
				((ExprContext)_localctx).location = location();

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).location.id;

				}
				break;
			case T__4:
				{
				setState(201);
				match(T__4);
				setState(202);
				((ExprContext)_localctx).e = expr(0);
				setState(203);
				match(T__5);

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).e.id;

				}
				break;
			case SubOp:
				{
				setState(206);
				((ExprContext)_localctx).SubOp = match(SubOp);
				setState(207);
				((ExprContext)_localctx).e = expr(8);

					SymTab s = symTabStack.getLast();
					((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e.id));
					q.Add(_localctx.id, ((ExprContext)_localctx).e.id, -1, (((ExprContext)_localctx).SubOp!=null?((ExprContext)_localctx).SubOp.getText():null));

				}
				break;
			case T__7:
				{
				setState(210);
				match(T__7);
				setState(211);
				((ExprContext)_localctx).e = expr(7);

					SymTab s = symTabStack.getLast();
					((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e.id));
					q.Add(_localctx.id, ((ExprContext)_localctx).e.id, -1, "!");

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(246);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(217);
						((ExprContext)_localctx).MulDiv = match(MulDiv);
						setState(218);
						((ExprContext)_localctx).e2 = expr(7);

						          	SymTab s = symTabStack.getLast();

						          	if (s == null)
						          		System.out.println("HHHHHH");

						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).MulDiv!=null?((ExprContext)_localctx).MulDiv.getText():null));
						          
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(221);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(222);
						((ExprContext)_localctx).AddOp = match(AddOp);
						setState(223);
						((ExprContext)_localctx).e2 = expr(6);

						          	SymTab s = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).AddOp!=null?((ExprContext)_localctx).AddOp.getText():null));
						          
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(226);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(227);
						((ExprContext)_localctx).SubOp = match(SubOp);
						setState(228);
						((ExprContext)_localctx).e2 = expr(5);

						          	SymTab s = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).SubOp!=null?((ExprContext)_localctx).SubOp.getText():null));
						          
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(231);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(232);
						((ExprContext)_localctx).RelOp = match(RelOp);
						setState(233);
						((ExprContext)_localctx).e2 = expr(4);

						          	SymTab s = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).RelOp!=null?((ExprContext)_localctx).RelOp.getText():null));
						          
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(236);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(237);
						((ExprContext)_localctx).AndOp = match(AndOp);
						setState(238);
						((ExprContext)_localctx).e2 = expr(3);

						          	SymTab s = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).AndOp!=null?((ExprContext)_localctx).AndOp.getText():null));
						          
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(241);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(242);
						((ExprContext)_localctx).OrOp = match(OrOp);
						setState(243);
						((ExprContext)_localctx).e2 = expr(2);

						          	SymTab s = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  s.Add(s.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).OrOp!=null?((ExprContext)_localctx).OrOp.getText():null));
						          
						}
						break;
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public static class LocationContext extends ParserRuleContext {
		public int id;
		public Token Ident;
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			((LocationContext)_localctx).Ident = match(Ident);

				((LocationContext)_localctx).id =  symTabStack.Find((((LocationContext)_localctx).Ident!=null?((LocationContext)_localctx).Ident.getText():null));

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

	public static class NumContext extends ParserRuleContext {
		public TerminalNode DecNum() { return getToken(A3CodeParser.DecNum, 0); }
		public TerminalNode HexNum() { return getToken(A3CodeParser.HexNum, 0); }
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitNum(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_num);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_la = _input.LA(1);
			if ( !(_la==DecNum || _la==HexNum) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class LiteralContext extends ParserRuleContext {
		public int id;
		public NumContext num;
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public TerminalNode Char() { return getToken(A3CodeParser.Char, 0); }
		public TerminalNode BoolLit() { return getToken(A3CodeParser.BoolLit, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal);
		try {
			setState(261);
			switch (_input.LA(1)) {
			case DecNum:
			case HexNum:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				((LiteralContext)_localctx).num = num();

					((LiteralContext)_localctx).id =  symTabStack.getLast().insert((((LiteralContext)_localctx).num!=null?_input.getText(((LiteralContext)_localctx).num.start,((LiteralContext)_localctx).num.stop):null), DataType.INT);

				}
				break;
			case Char:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(Char);
				}
				break;
			case BoolLit:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				match(BoolLit);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class EqOpContext extends ParserRuleContext {
		public TerminalNode AssignOp() { return getToken(A3CodeParser.AssignOp, 0); }
		public EqOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterEqOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitEqOp(this);
		}
	}

	public final EqOpContext eqOp() throws RecognitionException {
		EqOpContext _localctx = new EqOpContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_eqOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==AssignOp) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return field_decls_sempred((Field_declsContext)_localctx, predIndex);
		case 2:
			return field_decl_sempred((Field_declContext)_localctx, predIndex);
		case 5:
			return nextParams_sempred((NextParamsContext)_localctx, predIndex);
		case 7:
			return var_decls_sempred((Var_declsContext)_localctx, predIndex);
		case 8:
			return var_decl_sempred((Var_declContext)_localctx, predIndex);
		case 11:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean field_decls_sempred(Field_declsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean field_decl_sempred(Field_declContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean nextParams_sempred(NextParamsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean var_decls_sempred(Var_declsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean var_decl_sempred(Var_declContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u010c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4>\n\4\f\4\16\4A\13\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5S\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6[\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7e\n\7\f"+
		"\7\16\7h\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\7\tx\n\t\f\t\16\t{\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0086"+
		"\n\n\f\n\16\n\u0089\13\n\3\13\3\13\3\13\3\13\5\13\u008f\n\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c3\n\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00d9\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7"+
		"\r\u00f9\n\r\f\r\16\r\u00fc\13\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\5\20\u0108\n\20\3\21\3\21\3\21\2\b\4\6\f\20\22\30\22\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\4\3\2\30\31\4\2\t\t\36\36\u0117"+
		"\2\"\3\2\2\2\4*\3\2\2\2\6\64\3\2\2\2\bR\3\2\2\2\nZ\3\2\2\2\f\\\3\2\2\2"+
		"\16i\3\2\2\2\20o\3\2\2\2\22|\3\2\2\2\24\u008e\3\2\2\2\26\u00c2\3\2\2\2"+
		"\30\u00d8\3\2\2\2\32\u00fd\3\2\2\2\34\u0100\3\2\2\2\36\u0107\3\2\2\2 "+
		"\u0109\3\2\2\2\"#\7\16\2\2#$\7\17\2\2$%\7\3\2\2%&\5\4\3\2&\'\5\b\5\2\'"+
		"(\7\4\2\2()\b\2\1\2)\3\3\2\2\2*\61\b\3\1\2+,\f\4\2\2,-\5\6\4\2-.\7\5\2"+
		"\2.\60\3\2\2\2/+\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\5"+
		"\3\2\2\2\63\61\3\2\2\2\64\65\b\4\1\2\65\66\7\33\2\2\66\67\7\34\2\2\67"+
		"8\b\4\1\28?\3\2\2\29:\f\4\2\2:;\7\6\2\2;<\7\34\2\2<>\b\4\1\2=9\3\2\2\2"+
		">A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\7\3\2\2\2A?\3\2\2\2BC\7\33\2\2CD\7\34"+
		"\2\2DE\7\7\2\2EF\5\n\6\2FG\7\b\2\2GH\5\16\b\2HI\b\5\1\2IS\3\2\2\2JK\7"+
		"\20\2\2KL\7\34\2\2LM\7\7\2\2MN\5\n\6\2NO\7\b\2\2OP\5\16\b\2PQ\b\5\1\2"+
		"QS\3\2\2\2RB\3\2\2\2RJ\3\2\2\2S\t\3\2\2\2TU\7\33\2\2UV\7\34\2\2VW\5\f"+
		"\7\2WX\b\6\1\2X[\3\2\2\2Y[\b\6\1\2ZT\3\2\2\2ZY\3\2\2\2[\13\3\2\2\2\\]"+
		"\b\7\1\2]^\b\7\1\2^f\3\2\2\2_`\f\4\2\2`a\7\6\2\2ab\7\33\2\2bc\7\34\2\2"+
		"ce\b\7\1\2d_\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\r\3\2\2\2hf\3\2\2"+
		"\2ij\7\3\2\2jk\5\20\t\2kl\5\24\13\2lm\7\4\2\2mn\b\b\1\2n\17\3\2\2\2op"+
		"\b\t\1\2pq\b\t\1\2qy\3\2\2\2rs\f\4\2\2st\5\22\n\2tu\7\5\2\2uv\b\t\1\2"+
		"vx\3\2\2\2wr\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\21\3\2\2\2{y\3\2\2"+
		"\2|}\b\n\1\2}~\7\33\2\2~\177\7\34\2\2\177\u0080\b\n\1\2\u0080\u0087\3"+
		"\2\2\2\u0081\u0082\f\4\2\2\u0082\u0083\7\6\2\2\u0083\u0084\7\34\2\2\u0084"+
		"\u0086\b\n\1\2\u0085\u0081\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\23\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b"+
		"\5\26\f\2\u008b\u008c\5\24\13\2\u008c\u008f\3\2\2\2\u008d\u008f\b\13\1"+
		"\2\u008e\u008a\3\2\2\2\u008e\u008d\3\2\2\2\u008f\25\3\2\2\2\u0090\u0091"+
		"\5\32\16\2\u0091\u0092\5 \21\2\u0092\u0093\5\30\r\2\u0093\u0094\7\5\2"+
		"\2\u0094\u0095\b\f\1\2\u0095\u00c3\3\2\2\2\u0096\u0097\7\21\2\2\u0097"+
		"\u0098\7\7\2\2\u0098\u0099\5\30\r\2\u0099\u009a\7\b\2\2\u009a\u009b\5"+
		"\16\b\2\u009b\u009c\b\f\1\2\u009c\u00c3\3\2\2\2\u009d\u009e\7\21\2\2\u009e"+
		"\u009f\7\7\2\2\u009f\u00a0\5\30\r\2\u00a0\u00a1\7\b\2\2\u00a1\u00a2\5"+
		"\16\b\2\u00a2\u00a3\7\22\2\2\u00a3\u00a4\5\16\b\2\u00a4\u00a5\b\f\1\2"+
		"\u00a5\u00c3\3\2\2\2\u00a6\u00a7\7\23\2\2\u00a7\u00a8\7\34\2\2\u00a8\u00a9"+
		"\7\t\2\2\u00a9\u00aa\5\30\r\2\u00aa\u00ab\7\6\2\2\u00ab\u00ac\5\30\r\2"+
		"\u00ac\u00ad\5\16\b\2\u00ad\u00ae\b\f\1\2\u00ae\u00c3\3\2\2\2\u00af\u00b0"+
		"\7\24\2\2\u00b0\u00b1\7\5\2\2\u00b1\u00c3\b\f\1\2\u00b2\u00b3\7\24\2\2"+
		"\u00b3\u00b4\7\7\2\2\u00b4\u00b5\5\30\r\2\u00b5\u00b6\7\b\2\2\u00b6\u00b7"+
		"\7\5\2\2\u00b7\u00b8\b\f\1\2\u00b8\u00c3\3\2\2\2\u00b9\u00ba\7\25\2\2"+
		"\u00ba\u00bb\7\5\2\2\u00bb\u00c3\b\f\1\2\u00bc\u00bd\7\26\2\2\u00bd\u00be"+
		"\7\5\2\2\u00be\u00c3\b\f\1\2\u00bf\u00c0\5\16\b\2\u00c0\u00c1\b\f\1\2"+
		"\u00c1\u00c3\3\2\2\2\u00c2\u0090\3\2\2\2\u00c2\u0096\3\2\2\2\u00c2\u009d"+
		"\3\2\2\2\u00c2\u00a6\3\2\2\2\u00c2\u00af\3\2\2\2\u00c2\u00b2\3\2\2\2\u00c2"+
		"\u00b9\3\2\2\2\u00c2\u00bc\3\2\2\2\u00c2\u00bf\3\2\2\2\u00c3\27\3\2\2"+
		"\2\u00c4\u00c5\b\r\1\2\u00c5\u00c6\5\36\20\2\u00c6\u00c7\b\r\1\2\u00c7"+
		"\u00d9\3\2\2\2\u00c8\u00c9\5\32\16\2\u00c9\u00ca\b\r\1\2\u00ca\u00d9\3"+
		"\2\2\2\u00cb\u00cc\7\7\2\2\u00cc\u00cd\5\30\r\2\u00cd\u00ce\7\b\2\2\u00ce"+
		"\u00cf\b\r\1\2\u00cf\u00d9\3\2\2\2\u00d0\u00d1\7!\2\2\u00d1\u00d2\5\30"+
		"\r\n\u00d2\u00d3\b\r\1\2\u00d3\u00d9\3\2\2\2\u00d4\u00d5\7\n\2\2\u00d5"+
		"\u00d6\5\30\r\t\u00d6\u00d7\b\r\1\2\u00d7\u00d9\3\2\2\2\u00d8\u00c4\3"+
		"\2\2\2\u00d8\u00c8\3\2\2\2\u00d8\u00cb\3\2\2\2\u00d8\u00d0\3\2\2\2\u00d8"+
		"\u00d4\3\2\2\2\u00d9\u00fa\3\2\2\2\u00da\u00db\f\b\2\2\u00db\u00dc\7\37"+
		"\2\2\u00dc\u00dd\5\30\r\t\u00dd\u00de\b\r\1\2\u00de\u00f9\3\2\2\2\u00df"+
		"\u00e0\f\7\2\2\u00e0\u00e1\7 \2\2\u00e1\u00e2\5\30\r\b\u00e2\u00e3\b\r"+
		"\1\2\u00e3\u00f9\3\2\2\2\u00e4\u00e5\f\6\2\2\u00e5\u00e6\7!\2\2\u00e6"+
		"\u00e7\5\30\r\7\u00e7\u00e8\b\r\1\2\u00e8\u00f9\3\2\2\2\u00e9\u00ea\f"+
		"\5\2\2\u00ea\u00eb\7\35\2\2\u00eb\u00ec\5\30\r\6\u00ec\u00ed\b\r\1\2\u00ed"+
		"\u00f9\3\2\2\2\u00ee\u00ef\f\4\2\2\u00ef\u00f0\7\"\2\2\u00f0\u00f1\5\30"+
		"\r\5\u00f1\u00f2\b\r\1\2\u00f2\u00f9\3\2\2\2\u00f3\u00f4\f\3\2\2\u00f4"+
		"\u00f5\7#\2\2\u00f5\u00f6\5\30\r\4\u00f6\u00f7\b\r\1\2\u00f7\u00f9\3\2"+
		"\2\2\u00f8\u00da\3\2\2\2\u00f8\u00df\3\2\2\2\u00f8\u00e4\3\2\2\2\u00f8"+
		"\u00e9\3\2\2\2\u00f8\u00ee\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f9\u00fc\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\31\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fd\u00fe\7\34\2\2\u00fe\u00ff\b\16\1\2\u00ff\33\3\2\2\2\u0100"+
		"\u0101\t\2\2\2\u0101\35\3\2\2\2\u0102\u0103\5\34\17\2\u0103\u0104\b\20"+
		"\1\2\u0104\u0108\3\2\2\2\u0105\u0108\7\f\2\2\u0106\u0108\7\32\2\2\u0107"+
		"\u0102\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\37\3\2\2"+
		"\2\u0109\u010a\t\3\2\2\u010a!\3\2\2\2\17\61?RZfy\u0087\u008e\u00c2\u00d8"+
		"\u00f8\u00fa\u0107";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}