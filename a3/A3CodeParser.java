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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, WhiteSpace=11, Char=12, Str=13, Class=14, Program=15, Void=16, 
		If=17, Else=18, For=19, Ret=20, Brk=21, Cnt=22, Callout=23, DecNum=24, 
		HexNum=25, BoolLit=26, Type=27, Ident=28, RelOp=29, AssignOp=30, MulDiv=31, 
		AddOp=32, SubOp=33, AndOp=34, OrOp=35;
	public static final int
		RULE_prog = 0, RULE_field_decls = 1, RULE_field_decl = 2, RULE_inited_field_decl = 3, 
		RULE_method_decls = 4, RULE_method_decl = 5, RULE_params = 6, RULE_nextParams = 7, 
		RULE_block = 8, RULE_var_decls = 9, RULE_var_decl = 10, RULE_statements = 11, 
		RULE_statement = 12, RULE_methodCall = 13, RULE_args = 14, RULE_someArgs = 15, 
		RULE_calloutArgs = 16, RULE_expr = 17, RULE_location = 18, RULE_num = 19, 
		RULE_literal = 20, RULE_eqOp = 21;
	public static final String[] ruleNames = {
		"prog", "field_decls", "field_decl", "inited_field_decl", "method_decls", 
		"method_decl", "params", "nextParams", "block", "var_decls", "var_decl", 
		"statements", "statement", "methodCall", "args", "someArgs", "calloutArgs", 
		"expr", "location", "num", "literal", "eqOp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "';'", "','", "'['", "']'", "'='", "'('", "')'", "'!'", 
		null, null, null, "'class'", "'Program'", "'void'", "'if'", "'else'", 
		"'for'", "'return'", "'break'", "'continue'", "'callout'", null, null, 
		null, null, null, null, null, null, "'+'", "'-'", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "WhiteSpace", 
		"Char", "Str", "Class", "Program", "Void", "If", "Else", "For", "Ret", 
		"Brk", "Cnt", "Callout", "DecNum", "HexNum", "BoolLit", "Type", "Ident", 
		"RelOp", "AssignOp", "MulDiv", "AddOp", "SubOp", "AndOp", "OrOp"
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
		INT, BOOLEAN, VOID, 
		STR, INVALID
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
		String arrSize; //decimal or hex string, null for non array types

		Symbol (String n, DataType d) {
			this(n, d, null);
		}

		Symbol (String n, DataType d, String s) {
			id = idUtil.GetNext();
			name = n;
			dt = d;
			arrSize = s;
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
			String type = dt + "";
			if (arrSize != null) {
				type = "ARRAY(" + dt + "," + arrSize + ")";
			}
			System.out.println(name + "\t" + type);
		}

		
		
	}

	public class SymTab {
		List<Symbol> st;

		SymTab() {
			st = new ArrayList<>();
		}

		int Find (String n) {
			for (int  i = 0; i < st.size(); i ++) {
				Symbol s = st.get(i);
				if (s.Equal(n))
					return s.GetId();
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

		int insert(String n, DataType d, String s) {
			int id = Find(n);
			if (id != -1) return id;
			
			Symbol sym = new Symbol(n, d, s);
			st.add(sym);
			return (sym.GetId());
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
	}

	public class SymTabStack {
		ArrayDeque<SymTab> stack;
		ArrayDeque<SymTab> popped;

		SymTabStack() {
			stack = new ArrayDeque<>();
			popped = new ArrayDeque<>();
			push(new SymTab()); //add global symTab
		}

		void push(SymTab st) {
			st.Print();
			stack.addLast(st);
		}

		int size() {
			return stack.size();
		}

		SymTab pop() {
			SymTab st = stack.removeLast();;
			popped.addLast(st);
			return st;
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
				(id = stackCopy.removeLast().Find(n)) == -1
			);

			return id;
		}

		DataType GetType (int id) {
			if (id == -1) {
				return DataType.INVALID;
			}
			return (getSymById(id).GetType());
		}

		String GetName (int id) {
			if (id == -1) {
				return ("");
			}
			return (getSymById(id).GetName());
		}

		Symbol getSymById(int id) {
			//search all (including the popped symtabs)

			for (SymTab st : stack) {
				Symbol s = st.getSymById(id);
				if (s != null) {
					return s;
				}
			}

			for (SymTab st : popped) {
				Symbol s = st.getSymById(id);
				if (s != null){
					return s;
				}
			}

			return null;
		}

		void Print() {
			ArrayDeque<SymTab> stackClone = stack.clone();
			int i=0;
			while (!stackClone.isEmpty()) {
				System.out.println("Symtab " + i);
				stackClone.removeFirst().Print();
				System.out.println();
				i++;
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

		void Print() {
			switch (op) {
				case "=": //assignment operation ('=')
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
						+ op + " " + symTabStack.GetName(src1));
					break;

				case "!":
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = !" + symTabStack.GetName(src1));
					break;

				case "[]r": //array access read
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
						+ symTabStack.GetName(src1) + " [ " + symTabStack.GetName(src2) + " ] ");
					break;

				case "[]w": //array access write
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + 
						" [ " + symTabStack.GetName(src1) + " ] = " + symTabStack.GetName(src2));
					break;

				case "method_decl":
					System.out.println(symTabStack.GetName(src1) + ": ");
					break;

				case "param":
					System.out.println("L_" + label + ": " + symTabStack.GetName(src1) + " param");
					break;

				/*case "ret":
					System.out.println("L_" + label + ": ret " + symTabStack.GetName(dst) + symTabStack.GetName(src1) );
					break;*/

				default:
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
						+ symTabStack.GetName(src1) + " " + op + " " + symTabStack.GetName(src2));
					break;
			}
		}

		void debug() {
			System.out.println(label + "\t" + op + "\t" + src1 + "\t" + src2 + "\t" + dst);
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
			return (size ++);
		}

		void Print() {
			for(Quad q : qt) {
				q.Print();
			}
		}

		void debug() {
			for(Quad q : qt) {
				q.debug();
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
		public Method_declsContext method_decls() {
			return getRuleContext(Method_declsContext.class,0);
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
			setState(44);
			match(Class);
			setState(45);
			match(Program);
			setState(46);
			match(T__0);
			setState(47);
			field_decls(0);
			setState(48);
			method_decls(0);
			setState(49);
			match(T__1);

				//Print
				symTabStack.Print();
				System.out.println("------------------------------------");
				q.debug();
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
		public Inited_field_declContext inited_field_decl() {
			return getRuleContext(Inited_field_declContext.class,0);
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
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(61);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new Field_declsContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decls);
						setState(53);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(54);
						field_decl(0);
						setState(55);
						match(T__2);
						}
						break;
					case 2:
						{
						_localctx = new Field_declsContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decls);
						setState(57);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(58);
						inited_field_decl();
						setState(59);
						match(T__2);
						}
						break;
					}
					} 
				}
				setState(65);
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

	public static class Field_declContext extends ParserRuleContext {
		public DataType t;
		public Field_declContext f;
		public Token Type;
		public Token Ident;
		public NumContext num;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
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
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(67);
				((Field_declContext)_localctx).Type = match(Type);
				setState(68);
				((Field_declContext)_localctx).Ident = match(Ident);

					((Field_declContext)_localctx).t =  DataType.valueOf((((Field_declContext)_localctx).Type!=null?((Field_declContext)_localctx).Type.getText():null).toUpperCase());
					symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t);

				}
				break;
			case 2:
				{
				setState(70);
				((Field_declContext)_localctx).Type = match(Type);
				setState(71);
				((Field_declContext)_localctx).Ident = match(Ident);
				setState(72);
				match(T__4);
				setState(73);
				((Field_declContext)_localctx).num = num();
				setState(74);
				match(T__5);

					((Field_declContext)_localctx).t =  DataType.valueOf((((Field_declContext)_localctx).Type!=null?((Field_declContext)_localctx).Type.getText():null).toUpperCase());
					symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t, (((Field_declContext)_localctx).num!=null?_input.getText(((Field_declContext)_localctx).num.start,((Field_declContext)_localctx).num.stop):null));

				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(91);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new Field_declContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decl);
						setState(79);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(80);
						match(T__3);
						setState(81);
						((Field_declContext)_localctx).Ident = match(Ident);

						          	((Field_declContext)_localctx).t =  ((Field_declContext)_localctx).f.t;
						          	symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t);
						          
						}
						break;
					case 2:
						{
						_localctx = new Field_declContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decl);
						setState(83);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(84);
						match(T__3);
						setState(85);
						((Field_declContext)_localctx).Ident = match(Ident);
						setState(86);
						match(T__4);
						setState(87);
						((Field_declContext)_localctx).num = num();
						setState(88);
						match(T__5);

						          	((Field_declContext)_localctx).t =  ((Field_declContext)_localctx).f.t;
						          	symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t, (((Field_declContext)_localctx).num!=null?_input.getText(((Field_declContext)_localctx).num.start,((Field_declContext)_localctx).num.stop):null));
						          
						}
						break;
					}
					} 
				}
				setState(95);
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

	public static class Inited_field_declContext extends ParserRuleContext {
		public Token Type;
		public Token Ident;
		public LiteralContext literal;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Inited_field_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inited_field_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterInited_field_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitInited_field_decl(this);
		}
	}

	public final Inited_field_declContext inited_field_decl() throws RecognitionException {
		Inited_field_declContext _localctx = new Inited_field_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_inited_field_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			((Inited_field_declContext)_localctx).Type = match(Type);
			setState(97);
			((Inited_field_declContext)_localctx).Ident = match(Ident);
			setState(98);
			match(T__6);
			setState(99);
			((Inited_field_declContext)_localctx).literal = literal();

				int id = symTabStack.getFirst()
					.insert((((Inited_field_declContext)_localctx).Ident!=null?((Inited_field_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Inited_field_declContext)_localctx).Type!=null?((Inited_field_declContext)_localctx).Type.getText():null).toUpperCase()));
				q.Add(id, ((Inited_field_declContext)_localctx).literal.id, -1, "=");

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

	public static class Method_declsContext extends ParserRuleContext {
		public Method_declsContext m;
		public Method_declContext method_decl() {
			return getRuleContext(Method_declContext.class,0);
		}
		public Method_declsContext method_decls() {
			return getRuleContext(Method_declsContext.class,0);
		}
		public Method_declsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMethod_decls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMethod_decls(this);
		}
	}

	public final Method_declsContext method_decls() throws RecognitionException {
		return method_decls(0);
	}

	private Method_declsContext method_decls(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Method_declsContext _localctx = new Method_declsContext(_ctx, _parentState);
		Method_declsContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_method_decls, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{


			}
			_ctx.stop = _input.LT(-1);
			setState(111);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Method_declsContext(_parentctx, _parentState);
					_localctx.m = _prevctx;
					_localctx.m = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_method_decls);
					setState(105);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(106);
					method_decl();

					          
					}
					} 
				}
				setState(113);
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
		enterRule(_localctx, 10, RULE_method_decl);
		try {
			setState(130);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				((Method_declContext)_localctx).Type = match(Type);
				setState(115);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(116);
				match(T__7);
				setState(117);
				params();
				setState(118);
				match(T__8);
				setState(119);
				block();

					System.out.println("resolve method");
					int id = symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Type!=null?((Method_declContext)_localctx).Type.getText():null).toUpperCase()));
					q.Add(-1, id, -1, "method_decl");

				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				((Method_declContext)_localctx).Void = match(Void);
				setState(123);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(124);
				match(T__7);
				setState(125);
				params();
				setState(126);
				match(T__8);
				setState(127);
				block();

					System.out.println("resolve method");
					int id = symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Void!=null?((Method_declContext)_localctx).Void.getText():null).toUpperCase()));	
					q.Add(-1, id, -1, "method_decl");

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
		enterRule(_localctx, 12, RULE_params);
		try {
			setState(138);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				match(Type);
				setState(133);
				match(Ident);
				setState(134);
				nextParams(0);



				}
				break;
			case T__8:
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_nextParams, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{



			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
					setState(143);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(144);
					match(T__3);
					setState(145);
					match(Type);
					setState(146);
					match(Ident);


					          
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public int qId;
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
		enterRule(_localctx, 16, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__0);
			setState(154);
			var_decls(0);
			setState(155);
			statements();
			setState(156);
			match(T__1);

				System.out.println("resolve block");
				((BlockContext)_localctx).qId =  -1;

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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_var_decls, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{

				((Var_declsContext)_localctx).st =  new SymTab();
				symTabStack.push(_localctx.st);

			}
			_ctx.stop = _input.LT(-1);
			setState(169);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
					setState(162);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(163);
					((Var_declsContext)_localctx).var_decl = var_decl(0);
					setState(164);
					match(T__2);

					          	((Var_declsContext)_localctx).st =  ((Var_declsContext)_localctx).v.st;
					          	for (AbstractMap.SimpleEntry<String, DataType> p : ((Var_declsContext)_localctx).var_decl.symbols) {
					          		_localctx.st.insert(p.getKey(), p.getValue());
					          	}
					          
					}
					} 
				}
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public List<AbstractMap.SimpleEntry<String, DataType>> symbols;
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_var_decl, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173);
			((Var_declContext)_localctx).Type = match(Type);
			setState(174);
			((Var_declContext)_localctx).Ident = match(Ident);

				((Var_declContext)_localctx).t =  DataType.valueOf((((Var_declContext)_localctx).Type!=null?((Var_declContext)_localctx).Type.getText():null).toUpperCase());
				((Var_declContext)_localctx).symbols =  new ArrayList<>();
				_localctx.symbols.add(new AbstractMap.SimpleEntry<String, DataType>((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));

			}
			_ctx.stop = _input.LT(-1);
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
					setState(177);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(178);
					match(T__3);
					setState(179);
					((Var_declContext)_localctx).Ident = match(Ident);

					          	((Var_declContext)_localctx).t =  ((Var_declContext)_localctx).v.t;
					          	((Var_declContext)_localctx).symbols =  ((Var_declContext)_localctx).v.symbols;
					          	_localctx.symbols.add(new AbstractMap.SimpleEntry<String, DataType>((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));
					          
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 22, RULE_statements);
		try {
			setState(190);
			switch (_input.LA(1)) {
			case T__0:
			case If:
			case For:
			case Ret:
			case Brk:
			case Cnt:
			case Callout:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				statement();
				setState(187);
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
		public int qId;
		public LocationContext location;
		public EqOpContext eqOp;
		public ExprContext expr;
		public BlockContext b1;
		public BlockContext b2;
		public ExprContext e1;
		public ExprContext e2;
		public MethodCallContext methodCall;
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
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
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
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				((StatementContext)_localctx).location = location();
				setState(193);
				((StatementContext)_localctx).eqOp = eqOp();
				setState(194);
				((StatementContext)_localctx).expr = expr(0);
				setState(195);
				match(T__2);

					switch ((((StatementContext)_localctx).eqOp!=null?_input.getText(((StatementContext)_localctx).eqOp.start,((StatementContext)_localctx).eqOp.stop):null))
					{
						case "=":
							if (((StatementContext)_localctx).location.isArrAccess){
								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, ((StatementContext)_localctx).expr.id, "[]w");
							} else {
								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).expr.id, -1, "=");
							}
							break;

						case "+=":
							if (((StatementContext)_localctx).location.isArrAccess){
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, "[]r");

								int secondTempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(secondTempId, tempId, ((StatementContext)_localctx).expr.id, "+");

								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, secondTempId, "[]w");
							} else {
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).expr.id, "+");

								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, tempId, -1, "=");
							}
							break;

						case "-=":
							if (((StatementContext)_localctx).location.isArrAccess){
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, "[]r");

								int secondTempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(secondTempId, tempId, ((StatementContext)_localctx).expr.id, "-");

								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, secondTempId, "[]w");
							} else {
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								((StatementContext)_localctx).qId =  q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).expr.id, "-");

								((StatementContext)_localctx).qId =  q.Add(((StatementContext)_localctx).location.id, tempId, -1, "=");
							}
							break;
						default:
							break;
					}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				match(If);
				setState(199);
				match(T__7);
				setState(200);
				expr(0);
				setState(201);
				match(T__8);
				setState(202);
				block();

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(205);
				match(If);
				setState(206);
				match(T__7);
				setState(207);
				expr(0);
				setState(208);
				match(T__8);
				setState(209);
				((StatementContext)_localctx).b1 = block();
				setState(210);
				match(Else);
				setState(211);
				((StatementContext)_localctx).b2 = block();

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				match(For);
				setState(215);
				match(Ident);
				setState(216);
				match(T__6);
				setState(217);
				((StatementContext)_localctx).e1 = expr(0);
				setState(218);
				match(T__3);
				setState(219);
				((StatementContext)_localctx).e2 = expr(0);
				setState(220);
				block();

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(223);
				match(Ret);
				setState(224);
				match(T__2);

					((StatementContext)_localctx).qId =  q.Add(-1, -1, -1, "ret");

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(226);
				match(Ret);
				setState(227);
				match(T__7);
				setState(228);
				((StatementContext)_localctx).expr = expr(0);
				setState(229);
				match(T__8);
				setState(230);
				match(T__2);

					((StatementContext)_localctx).qId =  q.Add(-1, ((StatementContext)_localctx).expr.id, -1, "ret");

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(233);
				match(Brk);
				setState(234);
				match(T__2);

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(236);
				match(Cnt);
				setState(237);
				match(T__2);

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(239);
				block();

					((StatementContext)_localctx).qId =  -1;

				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(242);
				((StatementContext)_localctx).methodCall = methodCall();
				setState(243);
				match(T__2);

					q.Add(-1, ((StatementContext)_localctx).methodCall.id, ((StatementContext)_localctx).methodCall.argsCount, "call");
					((StatementContext)_localctx).qId =  -1;

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

	public static class MethodCallContext extends ParserRuleContext {
		public int id;
		public int argsCount;
		public Token Ident;
		public ArgsContext args;
		public Token Str;
		public CalloutArgsContext calloutArgs;
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode Callout() { return getToken(A3CodeParser.Callout, 0); }
		public TerminalNode Str() { return getToken(A3CodeParser.Str, 0); }
		public CalloutArgsContext calloutArgs() {
			return getRuleContext(CalloutArgsContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMethodCall(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_methodCall);
		try {
			setState(261);
			switch (_input.LA(1)) {
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				((MethodCallContext)_localctx).Ident = match(Ident);
				setState(249);
				match(T__7);
				setState(250);
				((MethodCallContext)_localctx).args = args();
				setState(251);
				match(T__8);

					((MethodCallContext)_localctx).id =  symTabStack.Find((((MethodCallContext)_localctx).Ident!=null?((MethodCallContext)_localctx).Ident.getText():null));
					((MethodCallContext)_localctx).argsCount =  ((MethodCallContext)_localctx).args.count;

				}
				break;
			case Callout:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(Callout);
				setState(255);
				match(T__7);
				setState(256);
				((MethodCallContext)_localctx).Str = match(Str);
				setState(257);
				((MethodCallContext)_localctx).calloutArgs = calloutArgs(0);
				setState(258);
				match(T__8);

					((MethodCallContext)_localctx).id =  symTabStack.getLast().insert((((MethodCallContext)_localctx).Str!=null?((MethodCallContext)_localctx).Str.getText():null), DataType.STR);
					((MethodCallContext)_localctx).argsCount =  ((MethodCallContext)_localctx).calloutArgs.count;

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

	public static class ArgsContext extends ParserRuleContext {
		public int count;
		public SomeArgsContext someArgs;
		public SomeArgsContext someArgs() {
			return getRuleContext(SomeArgsContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_args);
		try {
			setState(267);
			switch (_input.LA(1)) {
			case T__7:
			case T__9:
			case Char:
			case Callout:
			case DecNum:
			case HexNum:
			case BoolLit:
			case Ident:
			case SubOp:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				((ArgsContext)_localctx).someArgs = someArgs(0);

					((ArgsContext)_localctx).count =  ((ArgsContext)_localctx).someArgs.count;

				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{

					((ArgsContext)_localctx).count =  0;

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

	public static class SomeArgsContext extends ParserRuleContext {
		public int count;
		public SomeArgsContext t;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SomeArgsContext someArgs() {
			return getRuleContext(SomeArgsContext.class,0);
		}
		public SomeArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_someArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterSomeArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitSomeArgs(this);
		}
	}

	public final SomeArgsContext someArgs() throws RecognitionException {
		return someArgs(0);
	}

	private SomeArgsContext someArgs(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SomeArgsContext _localctx = new SomeArgsContext(_ctx, _parentState);
		SomeArgsContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_someArgs, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(270);
			expr(0);

				((SomeArgsContext)_localctx).count =  1;

			}
			_ctx.stop = _input.LT(-1);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SomeArgsContext(_parentctx, _parentState);
					_localctx.t = _prevctx;
					_localctx.t = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_someArgs);
					setState(273);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(274);
					match(T__3);
					setState(275);
					expr(0);

					          	((SomeArgsContext)_localctx).count =  ((SomeArgsContext)_localctx).t.count + 1;
					          
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class CalloutArgsContext extends ParserRuleContext {
		public int count;
		public CalloutArgsContext c;
		public ExprContext expr;
		public Token Str;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CalloutArgsContext calloutArgs() {
			return getRuleContext(CalloutArgsContext.class,0);
		}
		public TerminalNode Str() { return getToken(A3CodeParser.Str, 0); }
		public CalloutArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calloutArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterCalloutArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitCalloutArgs(this);
		}
	}

	public final CalloutArgsContext calloutArgs() throws RecognitionException {
		return calloutArgs(0);
	}

	private CalloutArgsContext calloutArgs(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CalloutArgsContext _localctx = new CalloutArgsContext(_ctx, _parentState);
		CalloutArgsContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_calloutArgs, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{

				((CalloutArgsContext)_localctx).count =  0;

			}
			_ctx.stop = _input.LT(-1);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(295);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new CalloutArgsContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_calloutArgs);
						setState(286);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(287);
						match(T__3);
						setState(288);
						((CalloutArgsContext)_localctx).expr = expr(0);

						          	q.Add(-1, ((CalloutArgsContext)_localctx).expr.id, -1, "param");
						          	
						          	((CalloutArgsContext)_localctx).count =  ((CalloutArgsContext)_localctx).c.count + 1;
						          
						}
						break;
					case 2:
						{
						_localctx = new CalloutArgsContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_calloutArgs);
						setState(291);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(292);
						match(T__3);
						setState(293);
						((CalloutArgsContext)_localctx).Str = match(Str);

						          	int id = symTabStack.getLast().insert((((CalloutArgsContext)_localctx).Str!=null?((CalloutArgsContext)_localctx).Str.getText():null), DataType.STR);
						          	q.Add(-1, id, -1, "param");

						          	((CalloutArgsContext)_localctx).count =  ((CalloutArgsContext)_localctx).c.count + 1;
						          
						}
						break;
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class ExprContext extends ParserRuleContext {
		public int id;
		public ExprContext e1;
		public LiteralContext literal;
		public LocationContext location;
		public ExprContext e;
		public Token SubOp;
		public MethodCallContext methodCall;
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
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(301);
				((ExprContext)_localctx).literal = literal();

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).literal.id;

				}
				break;
			case 2:
				{
				setState(304);
				((ExprContext)_localctx).location = location();

					if (((ExprContext)_localctx).location.isArrAccess) {
						((ExprContext)_localctx).id =  symTabStack.getLast().Add(symTabStack.GetType(((ExprContext)_localctx).location.id));
						q.Add(_localctx.id, ((ExprContext)_localctx).location.id, ((ExprContext)_localctx).location.offset, "[]r");
					} else {
						((ExprContext)_localctx).id =  ((ExprContext)_localctx).location.id;
					}

				}
				break;
			case 3:
				{
				setState(307);
				match(T__7);
				setState(308);
				((ExprContext)_localctx).e = expr(0);
				setState(309);
				match(T__8);

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).e.id;

				}
				break;
			case 4:
				{
				setState(312);
				((ExprContext)_localctx).SubOp = match(SubOp);
				setState(313);
				((ExprContext)_localctx).e = expr(9);

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e.id));
					int zeroId = st.insert("0", DataType.INT);
					q.Add(_localctx.id, zeroId, ((ExprContext)_localctx).e.id, (((ExprContext)_localctx).SubOp!=null?((ExprContext)_localctx).SubOp.getText():null));

				}
				break;
			case 5:
				{
				setState(316);
				match(T__9);
				setState(317);
				((ExprContext)_localctx).e = expr(8);

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e.id));
					q.Add(_localctx.id, ((ExprContext)_localctx).e.id, -1, "!");

				}
				break;
			case 6:
				{
				setState(320);
				((ExprContext)_localctx).methodCall = methodCall();

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).methodCall.id));
					int argsNumId = st.insert(Integer.toString(((ExprContext)_localctx).methodCall.argsCount), DataType.INT);
					q.Add(_localctx.id, ((ExprContext)_localctx).methodCall.id, ((ExprContext)_localctx).methodCall.argsCount, "call");

				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(357);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(355);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(325);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(326);
						((ExprContext)_localctx).MulDiv = match(MulDiv);
						setState(327);
						((ExprContext)_localctx).e2 = expr(8);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).MulDiv!=null?((ExprContext)_localctx).MulDiv.getText():null));
						          
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(330);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(331);
						((ExprContext)_localctx).AddOp = match(AddOp);
						setState(332);
						((ExprContext)_localctx).e2 = expr(7);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).AddOp!=null?((ExprContext)_localctx).AddOp.getText():null));
						          
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(335);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(336);
						((ExprContext)_localctx).SubOp = match(SubOp);
						setState(337);
						((ExprContext)_localctx).e2 = expr(6);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).SubOp!=null?((ExprContext)_localctx).SubOp.getText():null));
						          
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(340);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(341);
						((ExprContext)_localctx).RelOp = match(RelOp);
						setState(342);
						((ExprContext)_localctx).e2 = expr(5);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).RelOp!=null?((ExprContext)_localctx).RelOp.getText():null));
						          
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(345);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(346);
						((ExprContext)_localctx).AndOp = match(AndOp);
						setState(347);
						((ExprContext)_localctx).e2 = expr(4);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).AndOp!=null?((ExprContext)_localctx).AndOp.getText():null));
						          
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(350);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(351);
						((ExprContext)_localctx).OrOp = match(OrOp);
						setState(352);
						((ExprContext)_localctx).e2 = expr(3);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).OrOp!=null?((ExprContext)_localctx).OrOp.getText():null));
						          
						}
						break;
					}
					} 
				}
				setState(359);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public int offset;
		public boolean isArrAccess;
		public Token Ident;
		public ExprContext expr;
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
		enterRule(_localctx, 36, RULE_location);
		try {
			setState(368);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(360);
				((LocationContext)_localctx).Ident = match(Ident);

					((LocationContext)_localctx).id =  symTabStack.Find((((LocationContext)_localctx).Ident!=null?((LocationContext)_localctx).Ident.getText():null));
					((LocationContext)_localctx).isArrAccess =  false;

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				((LocationContext)_localctx).Ident = match(Ident);
				setState(363);
				match(T__4);
				setState(364);
				((LocationContext)_localctx).expr = expr(0);
				setState(365);
				match(T__5);

					((LocationContext)_localctx).id =  symTabStack.Find((((LocationContext)_localctx).Ident!=null?((LocationContext)_localctx).Ident.getText():null));
					
					SymTab st = symTabStack.getLast();

					((LocationContext)_localctx).offset =  st.Add(DataType.INT);
					int idOfFour = st.insert("4", DataType.INT);
					q.Add(_localctx.offset, idOfFour, ((LocationContext)_localctx).expr.id, "*");

					((LocationContext)_localctx).isArrAccess =  true;

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
		enterRule(_localctx, 38, RULE_num);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
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
		enterRule(_localctx, 40, RULE_literal);
		try {
			setState(377);
			switch (_input.LA(1)) {
			case DecNum:
			case HexNum:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				((LiteralContext)_localctx).num = num();

					((LiteralContext)_localctx).id =  symTabStack.getLast().insert((((LiteralContext)_localctx).num!=null?_input.getText(((LiteralContext)_localctx).num.start,((LiteralContext)_localctx).num.stop):null), DataType.INT);

				}
				break;
			case Char:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				match(Char);
				}
				break;
			case BoolLit:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
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
		enterRule(_localctx, 42, RULE_eqOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
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
		case 4:
			return method_decls_sempred((Method_declsContext)_localctx, predIndex);
		case 7:
			return nextParams_sempred((NextParamsContext)_localctx, predIndex);
		case 9:
			return var_decls_sempred((Var_declsContext)_localctx, predIndex);
		case 10:
			return var_decl_sempred((Var_declContext)_localctx, predIndex);
		case 15:
			return someArgs_sempred((SomeArgsContext)_localctx, predIndex);
		case 16:
			return calloutArgs_sempred((CalloutArgsContext)_localctx, predIndex);
		case 17:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean field_decls_sempred(Field_declsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean field_decl_sempred(Field_declContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean method_decls_sempred(Method_declsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean nextParams_sempred(NextParamsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean var_decls_sempred(Var_declsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean var_decl_sempred(Var_declContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean someArgs_sempred(SomeArgsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean calloutArgs_sempred(CalloutArgsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 7);
		case 12:
			return precpred(_ctx, 6);
		case 13:
			return precpred(_ctx, 5);
		case 14:
			return precpred(_ctx, 4);
		case 15:
			return precpred(_ctx, 3);
		case 16:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u0180\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3@\n\3\f\3\16\3C\13"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4P\n\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6p\n\6\f\6\16\6s\13\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0085\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u008d\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00aa\n\13\f\13\16\13\u00ad\13\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00b8\n\f\f\f\16\f\u00bb\13\f"+
		"\3\r\3\r\3\r\3\r\5\r\u00c1\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00f9\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0108\n\17\3\20\3\20\3\20\3\20\5\20"+
		"\u010e\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0119\n"+
		"\21\f\21\16\21\u011c\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\7\22\u012a\n\22\f\22\16\22\u012d\13\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0146\n\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0166\n\23"+
		"\f\23\16\23\u0169\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0173"+
		"\n\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u017c\n\26\3\27\3\27\3\27"+
		"\2\13\4\6\n\20\24\26 \"$\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,\2\4\3\2\32\33\4\2\t\t  \u0191\2.\3\2\2\2\4\66\3\2\2\2\6O\3\2\2\2"+
		"\bb\3\2\2\2\nh\3\2\2\2\f\u0084\3\2\2\2\16\u008c\3\2\2\2\20\u008e\3\2\2"+
		"\2\22\u009b\3\2\2\2\24\u00a1\3\2\2\2\26\u00ae\3\2\2\2\30\u00c0\3\2\2\2"+
		"\32\u00f8\3\2\2\2\34\u0107\3\2\2\2\36\u010d\3\2\2\2 \u010f\3\2\2\2\"\u011d"+
		"\3\2\2\2$\u0145\3\2\2\2&\u0172\3\2\2\2(\u0174\3\2\2\2*\u017b\3\2\2\2,"+
		"\u017d\3\2\2\2./\7\20\2\2/\60\7\21\2\2\60\61\7\3\2\2\61\62\5\4\3\2\62"+
		"\63\5\n\6\2\63\64\7\4\2\2\64\65\b\2\1\2\65\3\3\2\2\2\66A\b\3\1\2\678\f"+
		"\5\2\289\5\6\4\29:\7\5\2\2:@\3\2\2\2;<\f\4\2\2<=\5\b\5\2=>\7\5\2\2>@\3"+
		"\2\2\2?\67\3\2\2\2?;\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\5\3\2\2\2"+
		"CA\3\2\2\2DE\b\4\1\2EF\7\35\2\2FG\7\36\2\2GP\b\4\1\2HI\7\35\2\2IJ\7\36"+
		"\2\2JK\7\7\2\2KL\5(\25\2LM\7\b\2\2MN\b\4\1\2NP\3\2\2\2OD\3\2\2\2OH\3\2"+
		"\2\2P_\3\2\2\2QR\f\6\2\2RS\7\6\2\2ST\7\36\2\2T^\b\4\1\2UV\f\5\2\2VW\7"+
		"\6\2\2WX\7\36\2\2XY\7\7\2\2YZ\5(\25\2Z[\7\b\2\2[\\\b\4\1\2\\^\3\2\2\2"+
		"]Q\3\2\2\2]U\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\7\3\2\2\2a_\3\2\2"+
		"\2bc\7\35\2\2cd\7\36\2\2de\7\t\2\2ef\5*\26\2fg\b\5\1\2g\t\3\2\2\2hi\b"+
		"\6\1\2ij\b\6\1\2jq\3\2\2\2kl\f\4\2\2lm\5\f\7\2mn\b\6\1\2np\3\2\2\2ok\3"+
		"\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\13\3\2\2\2sq\3\2\2\2tu\7\35\2\2"+
		"uv\7\36\2\2vw\7\n\2\2wx\5\16\b\2xy\7\13\2\2yz\5\22\n\2z{\b\7\1\2{\u0085"+
		"\3\2\2\2|}\7\22\2\2}~\7\36\2\2~\177\7\n\2\2\177\u0080\5\16\b\2\u0080\u0081"+
		"\7\13\2\2\u0081\u0082\5\22\n\2\u0082\u0083\b\7\1\2\u0083\u0085\3\2\2\2"+
		"\u0084t\3\2\2\2\u0084|\3\2\2\2\u0085\r\3\2\2\2\u0086\u0087\7\35\2\2\u0087"+
		"\u0088\7\36\2\2\u0088\u0089\5\20\t\2\u0089\u008a\b\b\1\2\u008a\u008d\3"+
		"\2\2\2\u008b\u008d\b\b\1\2\u008c\u0086\3\2\2\2\u008c\u008b\3\2\2\2\u008d"+
		"\17\3\2\2\2\u008e\u008f\b\t\1\2\u008f\u0090\b\t\1\2\u0090\u0098\3\2\2"+
		"\2\u0091\u0092\f\4\2\2\u0092\u0093\7\6\2\2\u0093\u0094\7\35\2\2\u0094"+
		"\u0095\7\36\2\2\u0095\u0097\b\t\1\2\u0096\u0091\3\2\2\2\u0097\u009a\3"+
		"\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\21\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\u009c\7\3\2\2\u009c\u009d\5\24\13\2\u009d\u009e\5"+
		"\30\r\2\u009e\u009f\7\4\2\2\u009f\u00a0\b\n\1\2\u00a0\23\3\2\2\2\u00a1"+
		"\u00a2\b\13\1\2\u00a2\u00a3\b\13\1\2\u00a3\u00ab\3\2\2\2\u00a4\u00a5\f"+
		"\4\2\2\u00a5\u00a6\5\26\f\2\u00a6\u00a7\7\5\2\2\u00a7\u00a8\b\13\1\2\u00a8"+
		"\u00aa\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2"+
		"\2\2\u00ab\u00ac\3\2\2\2\u00ac\25\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af"+
		"\b\f\1\2\u00af\u00b0\7\35\2\2\u00b0\u00b1\7\36\2\2\u00b1\u00b2\b\f\1\2"+
		"\u00b2\u00b9\3\2\2\2\u00b3\u00b4\f\4\2\2\u00b4\u00b5\7\6\2\2\u00b5\u00b6"+
		"\7\36\2\2\u00b6\u00b8\b\f\1\2\u00b7\u00b3\3\2\2\2\u00b8\u00bb\3\2\2\2"+
		"\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\27\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bc\u00bd\5\32\16\2\u00bd\u00be\5\30\r\2\u00be\u00c1\3\2\2"+
		"\2\u00bf\u00c1\b\r\1\2\u00c0\u00bc\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\31"+
		"\3\2\2\2\u00c2\u00c3\5&\24\2\u00c3\u00c4\5,\27\2\u00c4\u00c5\5$\23\2\u00c5"+
		"\u00c6\7\5\2\2\u00c6\u00c7\b\16\1\2\u00c7\u00f9\3\2\2\2\u00c8\u00c9\7"+
		"\23\2\2\u00c9\u00ca\7\n\2\2\u00ca\u00cb\5$\23\2\u00cb\u00cc\7\13\2\2\u00cc"+
		"\u00cd\5\22\n\2\u00cd\u00ce\b\16\1\2\u00ce\u00f9\3\2\2\2\u00cf\u00d0\7"+
		"\23\2\2\u00d0\u00d1\7\n\2\2\u00d1\u00d2\5$\23\2\u00d2\u00d3\7\13\2\2\u00d3"+
		"\u00d4\5\22\n\2\u00d4\u00d5\7\24\2\2\u00d5\u00d6\5\22\n\2\u00d6\u00d7"+
		"\b\16\1\2\u00d7\u00f9\3\2\2\2\u00d8\u00d9\7\25\2\2\u00d9\u00da\7\36\2"+
		"\2\u00da\u00db\7\t\2\2\u00db\u00dc\5$\23\2\u00dc\u00dd\7\6\2\2\u00dd\u00de"+
		"\5$\23\2\u00de\u00df\5\22\n\2\u00df\u00e0\b\16\1\2\u00e0\u00f9\3\2\2\2"+
		"\u00e1\u00e2\7\26\2\2\u00e2\u00e3\7\5\2\2\u00e3\u00f9\b\16\1\2\u00e4\u00e5"+
		"\7\26\2\2\u00e5\u00e6\7\n\2\2\u00e6\u00e7\5$\23\2\u00e7\u00e8\7\13\2\2"+
		"\u00e8\u00e9\7\5\2\2\u00e9\u00ea\b\16\1\2\u00ea\u00f9\3\2\2\2\u00eb\u00ec"+
		"\7\27\2\2\u00ec\u00ed\7\5\2\2\u00ed\u00f9\b\16\1\2\u00ee\u00ef\7\30\2"+
		"\2\u00ef\u00f0\7\5\2\2\u00f0\u00f9\b\16\1\2\u00f1\u00f2\5\22\n\2\u00f2"+
		"\u00f3\b\16\1\2\u00f3\u00f9\3\2\2\2\u00f4\u00f5\5\34\17\2\u00f5\u00f6"+
		"\7\5\2\2\u00f6\u00f7\b\16\1\2\u00f7\u00f9\3\2\2\2\u00f8\u00c2\3\2\2\2"+
		"\u00f8\u00c8\3\2\2\2\u00f8\u00cf\3\2\2\2\u00f8\u00d8\3\2\2\2\u00f8\u00e1"+
		"\3\2\2\2\u00f8\u00e4\3\2\2\2\u00f8\u00eb\3\2\2\2\u00f8\u00ee\3\2\2\2\u00f8"+
		"\u00f1\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f9\33\3\2\2\2\u00fa\u00fb\7\36\2"+
		"\2\u00fb\u00fc\7\n\2\2\u00fc\u00fd\5\36\20\2\u00fd\u00fe\7\13\2\2\u00fe"+
		"\u00ff\b\17\1\2\u00ff\u0108\3\2\2\2\u0100\u0101\7\31\2\2\u0101\u0102\7"+
		"\n\2\2\u0102\u0103\7\17\2\2\u0103\u0104\5\"\22\2\u0104\u0105\7\13\2\2"+
		"\u0105\u0106\b\17\1\2\u0106\u0108\3\2\2\2\u0107\u00fa\3\2\2\2\u0107\u0100"+
		"\3\2\2\2\u0108\35\3\2\2\2\u0109\u010a\5 \21\2\u010a\u010b\b\20\1\2\u010b"+
		"\u010e\3\2\2\2\u010c\u010e\b\20\1\2\u010d\u0109\3\2\2\2\u010d\u010c\3"+
		"\2\2\2\u010e\37\3\2\2\2\u010f\u0110\b\21\1\2\u0110\u0111\5$\23\2\u0111"+
		"\u0112\b\21\1\2\u0112\u011a\3\2\2\2\u0113\u0114\f\4\2\2\u0114\u0115\7"+
		"\6\2\2\u0115\u0116\5$\23\2\u0116\u0117\b\21\1\2\u0117\u0119\3\2\2\2\u0118"+
		"\u0113\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2"+
		"\2\2\u011b!\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e\b\22\1\2\u011e\u011f"+
		"\b\22\1\2\u011f\u012b\3\2\2\2\u0120\u0121\f\5\2\2\u0121\u0122\7\6\2\2"+
		"\u0122\u0123\5$\23\2\u0123\u0124\b\22\1\2\u0124\u012a\3\2\2\2\u0125\u0126"+
		"\f\4\2\2\u0126\u0127\7\6\2\2\u0127\u0128\7\17\2\2\u0128\u012a\b\22\1\2"+
		"\u0129\u0120\3\2\2\2\u0129\u0125\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012b\u012c\3\2\2\2\u012c#\3\2\2\2\u012d\u012b\3\2\2\2\u012e"+
		"\u012f\b\23\1\2\u012f\u0130\5*\26\2\u0130\u0131\b\23\1\2\u0131\u0146\3"+
		"\2\2\2\u0132\u0133\5&\24\2\u0133\u0134\b\23\1\2\u0134\u0146\3\2\2\2\u0135"+
		"\u0136\7\n\2\2\u0136\u0137\5$\23\2\u0137\u0138\7\13\2\2\u0138\u0139\b"+
		"\23\1\2\u0139\u0146\3\2\2\2\u013a\u013b\7#\2\2\u013b\u013c\5$\23\13\u013c"+
		"\u013d\b\23\1\2\u013d\u0146\3\2\2\2\u013e\u013f\7\f\2\2\u013f\u0140\5"+
		"$\23\n\u0140\u0141\b\23\1\2\u0141\u0146\3\2\2\2\u0142\u0143\5\34\17\2"+
		"\u0143\u0144\b\23\1\2\u0144\u0146\3\2\2\2\u0145\u012e\3\2\2\2\u0145\u0132"+
		"\3\2\2\2\u0145\u0135\3\2\2\2\u0145\u013a\3\2\2\2\u0145\u013e\3\2\2\2\u0145"+
		"\u0142\3\2\2\2\u0146\u0167\3\2\2\2\u0147\u0148\f\t\2\2\u0148\u0149\7!"+
		"\2\2\u0149\u014a\5$\23\n\u014a\u014b\b\23\1\2\u014b\u0166\3\2\2\2\u014c"+
		"\u014d\f\b\2\2\u014d\u014e\7\"\2\2\u014e\u014f\5$\23\t\u014f\u0150\b\23"+
		"\1\2\u0150\u0166\3\2\2\2\u0151\u0152\f\7\2\2\u0152\u0153\7#\2\2\u0153"+
		"\u0154\5$\23\b\u0154\u0155\b\23\1\2\u0155\u0166\3\2\2\2\u0156\u0157\f"+
		"\6\2\2\u0157\u0158\7\37\2\2\u0158\u0159\5$\23\7\u0159\u015a\b\23\1\2\u015a"+
		"\u0166\3\2\2\2\u015b\u015c\f\5\2\2\u015c\u015d\7$\2\2\u015d\u015e\5$\23"+
		"\6\u015e\u015f\b\23\1\2\u015f\u0166\3\2\2\2\u0160\u0161\f\4\2\2\u0161"+
		"\u0162\7%\2\2\u0162\u0163\5$\23\5\u0163\u0164\b\23\1\2\u0164\u0166\3\2"+
		"\2\2\u0165\u0147\3\2\2\2\u0165\u014c\3\2\2\2\u0165\u0151\3\2\2\2\u0165"+
		"\u0156\3\2\2\2\u0165\u015b\3\2\2\2\u0165\u0160\3\2\2\2\u0166\u0169\3\2"+
		"\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168%\3\2\2\2\u0169\u0167"+
		"\3\2\2\2\u016a\u016b\7\36\2\2\u016b\u0173\b\24\1\2\u016c\u016d\7\36\2"+
		"\2\u016d\u016e\7\7\2\2\u016e\u016f\5$\23\2\u016f\u0170\7\b\2\2\u0170\u0171"+
		"\b\24\1\2\u0171\u0173\3\2\2\2\u0172\u016a\3\2\2\2\u0172\u016c\3\2\2\2"+
		"\u0173\'\3\2\2\2\u0174\u0175\t\2\2\2\u0175)\3\2\2\2\u0176\u0177\5(\25"+
		"\2\u0177\u0178\b\26\1\2\u0178\u017c\3\2\2\2\u0179\u017c\7\16\2\2\u017a"+
		"\u017c\7\34\2\2\u017b\u0176\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017a\3"+
		"\2\2\2\u017c+\3\2\2\2\u017d\u017e\t\3\2\2\u017e-\3\2\2\2\31?AO]_q\u0084"+
		"\u008c\u0098\u00ab\u00b9\u00c0\u00f8\u0107\u010d\u011a\u0129\u012b\u0145"+
		"\u0165\u0167\u0172\u017b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}