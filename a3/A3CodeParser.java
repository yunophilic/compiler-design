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
		RULE_literal = 20, RULE_eqOp = 21, RULE_marker = 22, RULE_markerTemp = 23, 
		RULE_markerGoto = 24, RULE_forMarker = 25;
	public static final String[] ruleNames = {
		"prog", "field_decls", "field_decl", "inited_field_decl", "method_decls", 
		"method_decl", "params", "nextParams", "block", "var_decls", "var_decl", 
		"statements", "statement", "methodCall", "args", "someArgs", "calloutArgs", 
		"expr", "location", "num", "literal", "eqOp", "marker", "markerTemp", 
		"markerGoto", "forMarker"
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
		STR, CHAR, INVALID
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

		String getOp() {
			return op;
		}

		int getSrc1() {
			return src1;
		}

		void setSrc1(int s1) {
			src1 = s1;
		}

		int getSrc2() {
			return src2;
		}

		void setSrc2(int s2) {
			src2 = s2;
		}

		void Print() {
			switch (op) {
				case "=": //assignment operation
					System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
						+ op + " " + symTabStack.GetName(src1));
					break;

				case "!": //negation operation
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

				case "if":
					System.out.println("L_" + label + ": if " + symTabStack.GetName(src1) 
						+ " goto L_" + symTabStack.GetName(src2));
					break;

				case "ifFalse":
					System.out.println("L_" + label + ": ifFalse " + symTabStack.GetName(src1) 
						+ " goto L_" + symTabStack.GetName(src2));
					break;

				case "goto":
					System.out.println("L_" + label + ": goto L_" + symTabStack.GetName(src1));
					break;

				case "end_of_meth":
					System.out.println("L_" + label + ":");
					break;				

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

		QuadTab () {
			qt = new ArrayList<>();
		}

		int Add(int dst, int src1, int src2, String op) {
			qt.add(new Quad(qt.size(), dst, src1, src2, op));
			return (qt.size()-1);
		}

		void Set(int label, int dst, int src1, int src2, String op) {
			qt.set(label, new Quad(label, dst, src1, src2, op));
		}

		Quad Get(int label) {
			return qt.get(label);
		}

		void backpatch(List<Integer> list, int targetLabel) {
			int targetLabelId = symTabStack.getLast().insert(targetLabel + "", DataType.INT);
			System.out.println("targetLabel: " + targetLabel);

			for (int item : list) {
				System.out.println("item: " + item);
				Quad q = qt.get(item);

				//only patch if haven't been patched
				switch(q.getOp()) {
					case "goto":
						if (q.getSrc1() == -1) {
							q.setSrc1(targetLabelId);
						}
						break;
					case "if":
					case "ifFalse":
						if (q.getSrc2() == -1)  {
							q.setSrc2(targetLabelId);
						}
						break;
				}
			}
		}

		int NextInstr() {
			return (qt.size());
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
			setState(52);
			match(Class);
			setState(53);
			match(Program);
			setState(54);
			match(T__0);
			setState(55);
			field_decls(0);
			setState(56);
			method_decls(0);
			setState(57);
			match(T__1);

				//Print
				//symTabStack.Print();
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
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(69);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new Field_declsContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decls);
						setState(61);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(62);
						field_decl(0);
						setState(63);
						match(T__2);
						}
						break;
					case 2:
						{
						_localctx = new Field_declsContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decls);
						setState(65);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(66);
						inited_field_decl();
						setState(67);
						match(T__2);
						}
						break;
					}
					} 
				}
				setState(73);
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
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(75);
				((Field_declContext)_localctx).Type = match(Type);
				setState(76);
				((Field_declContext)_localctx).Ident = match(Ident);

					((Field_declContext)_localctx).t =  DataType.valueOf((((Field_declContext)_localctx).Type!=null?((Field_declContext)_localctx).Type.getText():null).toUpperCase());
					symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t);

				}
				break;
			case 2:
				{
				setState(78);
				((Field_declContext)_localctx).Type = match(Type);
				setState(79);
				((Field_declContext)_localctx).Ident = match(Ident);
				setState(80);
				match(T__4);
				setState(81);
				((Field_declContext)_localctx).num = num();
				setState(82);
				match(T__5);

					((Field_declContext)_localctx).t =  DataType.valueOf((((Field_declContext)_localctx).Type!=null?((Field_declContext)_localctx).Type.getText():null).toUpperCase());
					symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t, (((Field_declContext)_localctx).num!=null?_input.getText(((Field_declContext)_localctx).num.start,((Field_declContext)_localctx).num.stop):null));

				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(99);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new Field_declContext(_parentctx, _parentState);
						_localctx.f = _prevctx;
						_localctx.f = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_field_decl);
						setState(87);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(88);
						match(T__3);
						setState(89);
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
						setState(91);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(92);
						match(T__3);
						setState(93);
						((Field_declContext)_localctx).Ident = match(Ident);
						setState(94);
						match(T__4);
						setState(95);
						((Field_declContext)_localctx).num = num();
						setState(96);
						match(T__5);

						          	((Field_declContext)_localctx).t =  ((Field_declContext)_localctx).f.t;
						          	symTabStack.getFirst().insert((((Field_declContext)_localctx).Ident!=null?((Field_declContext)_localctx).Ident.getText():null), _localctx.t, (((Field_declContext)_localctx).num!=null?_input.getText(((Field_declContext)_localctx).num.start,((Field_declContext)_localctx).num.stop):null));
						          
						}
						break;
					}
					} 
				}
				setState(103);
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
			setState(104);
			((Inited_field_declContext)_localctx).Type = match(Type);
			setState(105);
			((Inited_field_declContext)_localctx).Ident = match(Ident);
			setState(106);
			match(T__6);
			setState(107);
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
			setState(119);
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
					setState(113);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(114);
					method_decl();

					          
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

	public static class Method_declContext extends ParserRuleContext {
		public Token Type;
		public Token Ident;
		public MarkerTempContext markerTemp;
		public Token Void;
		public TerminalNode Type() { return getToken(A3CodeParser.Type, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public MarkerTempContext markerTemp() {
			return getRuleContext(MarkerTempContext.class,0);
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
			setState(140);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((Method_declContext)_localctx).Type = match(Type);
				setState(123);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(124);
				match(T__7);
				setState(125);
				params();
				setState(126);
				match(T__8);
				setState(127);
				((Method_declContext)_localctx).markerTemp = markerTemp();
				setState(128);
				block();

					//System.out.println("resolve method");
					int id = symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Type!=null?((Method_declContext)_localctx).Type.getText():null).toUpperCase()));

					q.Set(((Method_declContext)_localctx).markerTemp.label, -1, id, -1, "method_decl"); //fill in temp slot
					q.Add(-1, -1, -1, "end_of_meth");

				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				((Method_declContext)_localctx).Void = match(Void);
				setState(132);
				((Method_declContext)_localctx).Ident = match(Ident);
				setState(133);
				match(T__7);
				setState(134);
				params();
				setState(135);
				match(T__8);
				setState(136);
				((Method_declContext)_localctx).markerTemp = markerTemp();
				setState(137);
				block();

					//System.out.println("resolve method");
					int id = symTabStack.getFirst()
						.insert((((Method_declContext)_localctx).Ident!=null?((Method_declContext)_localctx).Ident.getText():null), DataType.valueOf((((Method_declContext)_localctx).Void!=null?((Method_declContext)_localctx).Void.getText():null).toUpperCase()));	

					q.Set(((Method_declContext)_localctx).markerTemp.label, -1, id, -1, "method_decl"); //fill in temp slot
					q.Add(-1, -1, -1, "end_of_meth");

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
			setState(146);
			switch (_input.LA(1)) {
			case Type:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(Type);
				setState(143);
				match(Ident);
				setState(144);
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
			setState(155);
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
					setState(149);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(150);
					match(T__3);
					setState(151);
					match(Type);
					setState(152);
					match(Ident);
					}
					} 
				}
				setState(157);
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
		public List<Integer> nextList;
		public StatementsContext statements;
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
			setState(158);
			match(T__0);
			setState(159);
			var_decls(0);
			setState(160);
			((BlockContext)_localctx).statements = statements();
			setState(161);
			match(T__1);

				((BlockContext)_localctx).nextList =  ((BlockContext)_localctx).statements.nextList;

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
			setState(174);
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
					setState(167);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(168);
					((Var_declsContext)_localctx).var_decl = var_decl(0);
					setState(169);
					match(T__2);

					          	((Var_declsContext)_localctx).st =  ((Var_declsContext)_localctx).v.st;
					          	for (AbstractMap.SimpleEntry<String, DataType> p : ((Var_declsContext)_localctx).var_decl.symbols) {
					          		_localctx.st.insert(p.getKey(), p.getValue());
					          	}
					          
					}
					} 
				}
				setState(176);
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
			setState(178);
			((Var_declContext)_localctx).Type = match(Type);
			setState(179);
			((Var_declContext)_localctx).Ident = match(Ident);

				((Var_declContext)_localctx).t =  DataType.valueOf((((Var_declContext)_localctx).Type!=null?((Var_declContext)_localctx).Type.getText():null).toUpperCase());
				((Var_declContext)_localctx).symbols =  new ArrayList<>();
				_localctx.symbols.add(new AbstractMap.SimpleEntry<String, DataType>((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));

			}
			_ctx.stop = _input.LT(-1);
			setState(188);
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
					setState(182);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(183);
					match(T__3);
					setState(184);
					((Var_declContext)_localctx).Ident = match(Ident);

					          	((Var_declContext)_localctx).t =  ((Var_declContext)_localctx).v.t;
					          	((Var_declContext)_localctx).symbols =  ((Var_declContext)_localctx).v.symbols;
					          	_localctx.symbols.add(new AbstractMap.SimpleEntry<String, DataType>((((Var_declContext)_localctx).Ident!=null?((Var_declContext)_localctx).Ident.getText():null), _localctx.t));
					          
					}
					} 
				}
				setState(190);
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
		public List<Integer> nextList;
		public StatementContext statement;
		public MarkerContext m;
		public StatementsContext t;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MarkerContext marker() {
			return getRuleContext(MarkerContext.class,0);
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
			setState(197);
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
				setState(191);
				((StatementsContext)_localctx).statement = statement();
				setState(192);
				((StatementsContext)_localctx).m = marker();
				setState(193);
				((StatementsContext)_localctx).t = statements();

					//((StatementsContext)_localctx).nextList =  ((StatementsContext)_localctx).t.nextList;
					((StatementsContext)_localctx).nextList =  ((StatementsContext)_localctx).statement.nextList;
					q.backpatch(_localctx.nextList, ((StatementsContext)_localctx).m.label);

				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{

					((StatementsContext)_localctx).nextList =  new ArrayList<>();
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
		public List<Integer> nextList;
		public LocationContext location;
		public EqOpContext eqOp;
		public ExprContext expr;
		public MarkerContext m;
		public BlockContext block;
		public MarkerContext m1;
		public BlockContext b1;
		public MarkerGotoContext n;
		public MarkerContext m2;
		public BlockContext b2;
		public Token Ident;
		public ExprContext e1;
		public ExprContext e2;
		public ForMarkerContext forMarker;
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
		public List<MarkerContext> marker() {
			return getRuleContexts(MarkerContext.class);
		}
		public MarkerContext marker(int i) {
			return getRuleContext(MarkerContext.class,i);
		}
		public TerminalNode Else() { return getToken(A3CodeParser.Else, 0); }
		public MarkerGotoContext markerGoto() {
			return getRuleContext(MarkerGotoContext.class,0);
		}
		public TerminalNode For() { return getToken(A3CodeParser.For, 0); }
		public TerminalNode Ident() { return getToken(A3CodeParser.Ident, 0); }
		public ForMarkerContext forMarker() {
			return getRuleContext(ForMarkerContext.class,0);
		}
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
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				((StatementContext)_localctx).location = location();
				setState(200);
				((StatementContext)_localctx).eqOp = eqOp();
				setState(201);
				((StatementContext)_localctx).expr = expr(0);
				setState(202);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();

					int srcId = ((StatementContext)_localctx).expr.id;

					if (((StatementContext)_localctx).expr.id == -1) { //&& and ||
						SymTab st = symTabStack.getLast();
						srcId = st.Add(symTabStack.GetType(((StatementContext)_localctx).expr.id));

						int trueId = st.insert("true", DataType.BOOLEAN);
						int tLabel = q.Add(srcId, trueId, -1, "=");
						q.backpatch(((StatementContext)_localctx).expr.trueList, tLabel);

						List<Integer> temp = new ArrayList<>();
						temp.add(q.Add(-1, -1, -1, "goto"));

						int falseId = st.insert("false", DataType.BOOLEAN);
						int fLabel = q.Add(srcId, falseId, -1, "=");
						q.backpatch(((StatementContext)_localctx).expr.falseList, fLabel);

						q.backpatch(temp, fLabel + 1);
					}


					switch ((((StatementContext)_localctx).eqOp!=null?_input.getText(((StatementContext)_localctx).eqOp.start,((StatementContext)_localctx).eqOp.stop):null))
					{
						case "=":
							if (((StatementContext)_localctx).location.isArrAccess){
								q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, srcId, "[]w");
							} else {
								q.Add(((StatementContext)_localctx).location.id, srcId, -1, "=");
							}
							break;

						case "+=":
							if (((StatementContext)_localctx).location.isArrAccess){
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, "[]r");

								int secondTempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(secondTempId, tempId, srcId, "+");

								q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, secondTempId, "[]w");
							} else {
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(tempId, ((StatementContext)_localctx).location.id, srcId, "+");

								q.Add(((StatementContext)_localctx).location.id, tempId, -1, "=");
							}
							break;

						case "-=":
							if (((StatementContext)_localctx).location.isArrAccess){
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, "[]r");

								int secondTempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(secondTempId, tempId, ((StatementContext)_localctx).expr.id, "-");

								q.Add(((StatementContext)_localctx).location.id, ((StatementContext)_localctx).location.offset, secondTempId, "[]w");
							} else {
								int tempId = symTabStack.getLast().Add(symTabStack.GetType(((StatementContext)_localctx).location.id));
								q.Add(tempId, ((StatementContext)_localctx).location.id, ((StatementContext)_localctx).expr.id, "-");

								q.Add(((StatementContext)_localctx).location.id, tempId, -1, "=");
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
				setState(205);
				match(If);
				setState(206);
				match(T__7);
				setState(207);
				((StatementContext)_localctx).expr = expr(0);
				setState(208);
				match(T__8);
				setState(209);
				((StatementContext)_localctx).m = marker();
				setState(210);
				((StatementContext)_localctx).block = block();

					q.backpatch(((StatementContext)_localctx).expr.trueList, ((StatementContext)_localctx).m.label);
					
					List<Integer> merged = new ArrayList<>();
					merged.addAll(((StatementContext)_localctx).expr.falseList);
					merged.addAll(((StatementContext)_localctx).block.nextList);
					((StatementContext)_localctx).nextList =  merged;

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				match(If);
				setState(214);
				match(T__7);
				setState(215);
				((StatementContext)_localctx).expr = expr(0);
				setState(216);
				match(T__8);
				setState(217);
				((StatementContext)_localctx).m1 = marker();
				setState(218);
				((StatementContext)_localctx).b1 = block();
				setState(219);
				((StatementContext)_localctx).n = markerGoto();
				setState(220);
				match(Else);
				setState(221);
				((StatementContext)_localctx).m2 = marker();
				setState(222);
				((StatementContext)_localctx).b2 = block();

					q.backpatch(((StatementContext)_localctx).expr.trueList, ((StatementContext)_localctx).m1.label);
					q.backpatch(((StatementContext)_localctx).expr.falseList, ((StatementContext)_localctx).m2.label);

					List<Integer> merged = new ArrayList<>();
					merged.addAll(((StatementContext)_localctx).b1.nextList);
					merged.addAll(((StatementContext)_localctx).n.nextList);
					merged.addAll(((StatementContext)_localctx).b2.nextList);

					((StatementContext)_localctx).nextList =  merged;

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(225);
				match(For);
				setState(226);
				((StatementContext)_localctx).Ident = match(Ident);
				setState(227);
				match(T__6);
				setState(228);
				((StatementContext)_localctx).e1 = expr(0);
				setState(229);
				match(T__3);
				setState(230);
				((StatementContext)_localctx).e2 = expr(0);
				setState(231);
				((StatementContext)_localctx).forMarker = forMarker();
				setState(232);
				block();

					((StatementContext)_localctx).nextList =  new ArrayList<>();

					int iteratorId = symTabStack.Find((((StatementContext)_localctx).Ident!=null?((StatementContext)_localctx).Ident.getText():null));

					SymTab st = symTabStack.getLast();
					
					int incrId = st.insert("1", DataType.INT);

					q.Add(iteratorId, iteratorId, incrId, "+");

					int loopRedoLabelId = st.insert(((StatementContext)_localctx).forMarker.label2 + "", DataType.INT);
					q.Add(-1, loopRedoLabelId, -1, "goto");

					// manual back patch

					q.Set(((StatementContext)_localctx).forMarker.label1, iteratorId, ((StatementContext)_localctx).e1.id, -1, "=");
					q.Set(((StatementContext)_localctx).forMarker.label2, ((StatementContext)_localctx).forMarker.tmpId, iteratorId, ((StatementContext)_localctx).e1.id, "<");

					int loopEnterLabelId = st.insert(((StatementContext)_localctx).forMarker.label4 + 1 + "", DataType.INT);
					int loopExitLabelId = st.insert(q.NextInstr() + "", DataType.INT);
					q.Set(((StatementContext)_localctx).forMarker.label3, -1, ((StatementContext)_localctx).forMarker.tmpId, loopEnterLabelId, "if");
					q.Set(((StatementContext)_localctx).forMarker.label4, -1, ((StatementContext)_localctx).forMarker.tmpId, loopExitLabelId, "ifFalse");

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(235);
				match(Ret);
				setState(236);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();
					q.Add(-1, -1, -1, "ret");

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(238);
				match(Ret);
				setState(239);
				match(T__7);
				setState(240);
				((StatementContext)_localctx).expr = expr(0);
				setState(241);
				match(T__8);
				setState(242);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();
					q.Add(-1, ((StatementContext)_localctx).expr.id, -1, "ret");

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(245);
				match(Brk);
				setState(246);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(248);
				match(Cnt);
				setState(249);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(251);
				((StatementContext)_localctx).block = block();

					((StatementContext)_localctx).nextList =  ((StatementContext)_localctx).block.nextList;

				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(254);
				((StatementContext)_localctx).methodCall = methodCall();
				setState(255);
				match(T__2);

					((StatementContext)_localctx).nextList =  new ArrayList<>();
					q.Add(-1, ((StatementContext)_localctx).methodCall.id, ((StatementContext)_localctx).methodCall.argsCount, "call");

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
			setState(273);
			switch (_input.LA(1)) {
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				((MethodCallContext)_localctx).Ident = match(Ident);
				setState(261);
				match(T__7);
				setState(262);
				((MethodCallContext)_localctx).args = args();
				setState(263);
				match(T__8);

					((MethodCallContext)_localctx).id =  symTabStack.Find((((MethodCallContext)_localctx).Ident!=null?((MethodCallContext)_localctx).Ident.getText():null));
					((MethodCallContext)_localctx).argsCount =  ((MethodCallContext)_localctx).args.count;

				}
				break;
			case Callout:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(Callout);
				setState(267);
				match(T__7);
				setState(268);
				((MethodCallContext)_localctx).Str = match(Str);
				setState(269);
				((MethodCallContext)_localctx).calloutArgs = calloutArgs(0);
				setState(270);
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
			setState(279);
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
				setState(275);
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
			setState(282);
			expr(0);

				((SomeArgsContext)_localctx).count =  1;

			}
			_ctx.stop = _input.LT(-1);
			setState(292);
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
					setState(285);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(286);
					match(T__3);
					setState(287);
					expr(0);

					          	((SomeArgsContext)_localctx).count =  ((SomeArgsContext)_localctx).t.count + 1;
					          
					}
					} 
				}
				setState(294);
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
			setState(309);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(307);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new CalloutArgsContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_calloutArgs);
						setState(298);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(299);
						match(T__3);
						setState(300);
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
						setState(303);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(304);
						match(T__3);
						setState(305);
						((CalloutArgsContext)_localctx).Str = match(Str);

						          	int id = symTabStack.getLast().insert((((CalloutArgsContext)_localctx).Str!=null?((CalloutArgsContext)_localctx).Str.getText():null), DataType.STR);
						          	q.Add(-1, id, -1, "param");

						          	((CalloutArgsContext)_localctx).count =  ((CalloutArgsContext)_localctx).c.count + 1;
						          
						}
						break;
					}
					} 
				}
				setState(311);
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
		public boolean isLiteral;
		public boolean isLocation;
		public List<Integer> trueList;
		public List<Integer> falseList;
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
		public MarkerContext m;
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
		public MarkerContext marker() {
			return getRuleContext(MarkerContext.class,0);
		}
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
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(313);
				((ExprContext)_localctx).literal = literal();

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).literal.id;

					if (symTabStack.GetType(_localctx.id) == DataType.BOOLEAN) {
						((ExprContext)_localctx).trueList =  new ArrayList<>();
						((ExprContext)_localctx).falseList =  new ArrayList<>();
					}

					((ExprContext)_localctx).isLiteral =  true;

				}
				break;
			case 2:
				{
				setState(316);
				((ExprContext)_localctx).location = location();

					DataType type = symTabStack.GetType(((ExprContext)_localctx).location.id);

					if (type == DataType.BOOLEAN) {
						((ExprContext)_localctx).trueList =  new ArrayList<>();
						((ExprContext)_localctx).falseList =  new ArrayList<>();
					}

					if (((ExprContext)_localctx).location.isArrAccess) {
						((ExprContext)_localctx).id =  symTabStack.getLast().Add(type);
						q.Add(_localctx.id, ((ExprContext)_localctx).location.id, ((ExprContext)_localctx).location.offset, "[]r");
					} else {
						((ExprContext)_localctx).id =  ((ExprContext)_localctx).location.id;
					}

					((ExprContext)_localctx).isLocation =  true;

				}
				break;
			case 3:
				{
				setState(319);
				match(T__7);
				setState(320);
				((ExprContext)_localctx).e = expr(0);
				setState(321);
				match(T__8);

					((ExprContext)_localctx).id =  ((ExprContext)_localctx).e.id;
					((ExprContext)_localctx).trueList =  ((ExprContext)_localctx).e.trueList;
					((ExprContext)_localctx).falseList =  ((ExprContext)_localctx).e.falseList;

				}
				break;
			case 4:
				{
				setState(324);
				((ExprContext)_localctx).SubOp = match(SubOp);
				setState(325);
				((ExprContext)_localctx).e = expr(9);

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e.id));
					int zeroId = st.insert("0", DataType.INT);
					q.Add(_localctx.id, zeroId, ((ExprContext)_localctx).e.id, (((ExprContext)_localctx).SubOp!=null?((ExprContext)_localctx).SubOp.getText():null));

				}
				break;
			case 5:
				{
				setState(328);
				match(T__9);
				setState(329);
				((ExprContext)_localctx).e = expr(8);

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e.id));
					q.Add(_localctx.id, ((ExprContext)_localctx).e.id, -1, "!");

					((ExprContext)_localctx).trueList =  ((ExprContext)_localctx).e.falseList;
					((ExprContext)_localctx).falseList =  ((ExprContext)_localctx).e.trueList;

				}
				break;
			case 6:
				{
				setState(332);
				((ExprContext)_localctx).methodCall = methodCall();

					SymTab st = symTabStack.getLast();
					((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).methodCall.id));
					int argsNumId = st.insert(Integer.toString(((ExprContext)_localctx).methodCall.argsCount), DataType.INT);
					q.Add(_localctx.id, ((ExprContext)_localctx).methodCall.id, ((ExprContext)_localctx).methodCall.argsCount, "call");

				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(371);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(369);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(337);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(338);
						((ExprContext)_localctx).MulDiv = match(MulDiv);
						setState(339);
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
						setState(342);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(343);
						((ExprContext)_localctx).AddOp = match(AddOp);
						setState(344);
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
						setState(347);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(348);
						((ExprContext)_localctx).SubOp = match(SubOp);
						setState(349);
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
						setState(352);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(353);
						((ExprContext)_localctx).RelOp = match(RelOp);
						setState(354);
						((ExprContext)_localctx).e2 = expr(5);

						          	SymTab st = symTabStack.getLast();
						          	((ExprContext)_localctx).id =  st.Add(symTabStack.GetType(((ExprContext)_localctx).e1.id));
						          	q.Add(_localctx.id, ((ExprContext)_localctx).e1.id, ((ExprContext)_localctx).e2.id, (((ExprContext)_localctx).RelOp!=null?((ExprContext)_localctx).RelOp.getText():null));

						          	((ExprContext)_localctx).trueList =  new ArrayList<>();
						          	_localctx.trueList.add(q.Add(-1, _localctx.id, -1, "if"));

						          	((ExprContext)_localctx).falseList =  new ArrayList<>();
						          	_localctx.falseList.add(q.Add(-1, _localctx.id, -1, "ifFalse"));
						          
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(357);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(358);
						match(AndOp);
						setState(359);
						((ExprContext)_localctx).m = marker();
						setState(360);
						((ExprContext)_localctx).e2 = expr(4);

						          	((ExprContext)_localctx).id =  -1;

						          	q.backpatch(((ExprContext)_localctx).e1.trueList, ((ExprContext)_localctx).m.label);
						          	
						          	List<Integer> merged = new ArrayList<>();
						          	merged.addAll(((ExprContext)_localctx).e1.falseList);
						          	merged.addAll(((ExprContext)_localctx).e2.falseList);
						          	((ExprContext)_localctx).falseList =  merged;

						          	((ExprContext)_localctx).trueList =  ((ExprContext)_localctx).e2.trueList;

						          	//literal operands
						          
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(363);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(364);
						match(OrOp);
						setState(365);
						((ExprContext)_localctx).m = marker();
						setState(366);
						((ExprContext)_localctx).e2 = expr(3);

						          	((ExprContext)_localctx).id =  -1;

						          	q.backpatch(((ExprContext)_localctx).e1.falseList, ((ExprContext)_localctx).m.label);

						          	List<Integer> merged = new ArrayList<>();
						          	merged.addAll(((ExprContext)_localctx).e1.trueList);
						          	merged.addAll(((ExprContext)_localctx).e2.trueList);
						          	((ExprContext)_localctx).trueList =  merged;

						          	((ExprContext)_localctx).falseList =  ((ExprContext)_localctx).e2.falseList;
						          
						}
						break;
					}
					} 
				}
				setState(373);
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
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				((LocationContext)_localctx).Ident = match(Ident);

					((LocationContext)_localctx).id =  symTabStack.Find((((LocationContext)_localctx).Ident!=null?((LocationContext)_localctx).Ident.getText():null));
					((LocationContext)_localctx).isArrAccess =  false;

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				((LocationContext)_localctx).Ident = match(Ident);
				setState(377);
				match(T__4);
				setState(378);
				((LocationContext)_localctx).expr = expr(0);
				setState(379);
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
			setState(384);
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
		public Token Char;
		public Token BoolLit;
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
			setState(393);
			switch (_input.LA(1)) {
			case DecNum:
			case HexNum:
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				((LiteralContext)_localctx).num = num();

					((LiteralContext)_localctx).id =  symTabStack.getLast().insert((((LiteralContext)_localctx).num!=null?_input.getText(((LiteralContext)_localctx).num.start,((LiteralContext)_localctx).num.stop):null), DataType.INT);

				}
				break;
			case Char:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				((LiteralContext)_localctx).Char = match(Char);

					((LiteralContext)_localctx).id =  symTabStack.getLast().insert((((LiteralContext)_localctx).Char!=null?((LiteralContext)_localctx).Char.getText():null), DataType.CHAR);

				}
				break;
			case BoolLit:
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				((LiteralContext)_localctx).BoolLit = match(BoolLit);

					((LiteralContext)_localctx).id =  symTabStack.getLast().insert((((LiteralContext)_localctx).BoolLit!=null?((LiteralContext)_localctx).BoolLit.getText():null), DataType.BOOLEAN);

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
			setState(395);
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

	public static class MarkerContext extends ParserRuleContext {
		public int label;
		public MarkerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMarker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMarker(this);
		}
	}

	public final MarkerContext marker() throws RecognitionException {
		MarkerContext _localctx = new MarkerContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_marker);
		try {
			enterOuterAlt(_localctx, 1);
			{

				((MarkerContext)_localctx).label =  q.NextInstr();

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

	public static class MarkerTempContext extends ParserRuleContext {
		public int label;
		public MarkerTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_markerTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMarkerTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMarkerTemp(this);
		}
	}

	public final MarkerTempContext markerTemp() throws RecognitionException {
		MarkerTempContext _localctx = new MarkerTempContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_markerTemp);
		try {
			enterOuterAlt(_localctx, 1);
			{

				((MarkerTempContext)_localctx).label =  q.Add(-1, -1, -1, null);

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

	public static class MarkerGotoContext extends ParserRuleContext {
		public List<Integer> nextList;
		public MarkerGotoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_markerGoto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterMarkerGoto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitMarkerGoto(this);
		}
	}

	public final MarkerGotoContext markerGoto() throws RecognitionException {
		MarkerGotoContext _localctx = new MarkerGotoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_markerGoto);
		try {
			enterOuterAlt(_localctx, 1);
			{

				((MarkerGotoContext)_localctx).nextList =  new ArrayList();
				_localctx.nextList.add(q.Add(-1, -1, -1, "goto"));

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

	public static class ForMarkerContext extends ParserRuleContext {
		public int tmpId;
		public int label1;
		public int label2;
		public int label3;
		public int label4;
		public ForMarkerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forMarker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).enterForMarker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof A3CodeListener ) ((A3CodeListener)listener).exitForMarker(this);
		}
	}

	public final ForMarkerContext forMarker() throws RecognitionException {
		ForMarkerContext _localctx = new ForMarkerContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_forMarker);
		try {
			enterOuterAlt(_localctx, 1);
			{

				((ForMarkerContext)_localctx).tmpId =  symTabStack.getLast().Add(DataType.INT);
				((ForMarkerContext)_localctx).label1 =  q.Add(-1, -1, -1, null);
				((ForMarkerContext)_localctx).label2 =  q.Add(-1, -1, -1, null);
				((ForMarkerContext)_localctx).label3 =  q.Add(-1, -1, -1, null);
				((ForMarkerContext)_localctx).label4 =  q.Add(-1, -1, -1, null);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u0198\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4X\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4f\n\4\f\4\16\4i\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\7\6x\n\6\f\6\16\6{\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u008f\n\7\3\b\3\b\3\b\3\b\5\b"+
		"\u0095\n\b\3\t\3\t\3\t\3\t\3\t\7\t\u009c\n\t\f\t\16\t\u009f\13\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00af"+
		"\n\13\f\13\16\13\u00b2\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00bd"+
		"\n\f\f\f\16\f\u00c0\13\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c8\n\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\5\16\u0105\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u0114\n\17\3\20\3\20\3\20\3\20\5\20\u011a\n"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0125\n\21\f\21"+
		"\16\21\u0128\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\7\22\u0136\n\22\f\22\16\22\u0139\13\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\5\23\u0152\n\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0174"+
		"\n\23\f\23\16\23\u0177\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5"+
		"\24\u0181\n\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u018c"+
		"\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\2\13\4\6"+
		"\n\20\24\26 \"$\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\2\4\3\2\32\33\4\2\t\t  \u01a5\2\66\3\2\2\2\4>\3\2\2\2\6W\3\2\2"+
		"\2\bj\3\2\2\2\np\3\2\2\2\f\u008e\3\2\2\2\16\u0094\3\2\2\2\20\u0096\3\2"+
		"\2\2\22\u00a0\3\2\2\2\24\u00a6\3\2\2\2\26\u00b3\3\2\2\2\30\u00c7\3\2\2"+
		"\2\32\u0104\3\2\2\2\34\u0113\3\2\2\2\36\u0119\3\2\2\2 \u011b\3\2\2\2\""+
		"\u0129\3\2\2\2$\u0151\3\2\2\2&\u0180\3\2\2\2(\u0182\3\2\2\2*\u018b\3\2"+
		"\2\2,\u018d\3\2\2\2.\u018f\3\2\2\2\60\u0191\3\2\2\2\62\u0193\3\2\2\2\64"+
		"\u0195\3\2\2\2\66\67\7\20\2\2\678\7\21\2\289\7\3\2\29:\5\4\3\2:;\5\n\6"+
		"\2;<\7\4\2\2<=\b\2\1\2=\3\3\2\2\2>I\b\3\1\2?@\f\5\2\2@A\5\6\4\2AB\7\5"+
		"\2\2BH\3\2\2\2CD\f\4\2\2DE\5\b\5\2EF\7\5\2\2FH\3\2\2\2G?\3\2\2\2GC\3\2"+
		"\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\5\3\2\2\2KI\3\2\2\2LM\b\4\1\2MN\7"+
		"\35\2\2NO\7\36\2\2OX\b\4\1\2PQ\7\35\2\2QR\7\36\2\2RS\7\7\2\2ST\5(\25\2"+
		"TU\7\b\2\2UV\b\4\1\2VX\3\2\2\2WL\3\2\2\2WP\3\2\2\2Xg\3\2\2\2YZ\f\6\2\2"+
		"Z[\7\6\2\2[\\\7\36\2\2\\f\b\4\1\2]^\f\5\2\2^_\7\6\2\2_`\7\36\2\2`a\7\7"+
		"\2\2ab\5(\25\2bc\7\b\2\2cd\b\4\1\2df\3\2\2\2eY\3\2\2\2e]\3\2\2\2fi\3\2"+
		"\2\2ge\3\2\2\2gh\3\2\2\2h\7\3\2\2\2ig\3\2\2\2jk\7\35\2\2kl\7\36\2\2lm"+
		"\7\t\2\2mn\5*\26\2no\b\5\1\2o\t\3\2\2\2pq\b\6\1\2qr\b\6\1\2ry\3\2\2\2"+
		"st\f\4\2\2tu\5\f\7\2uv\b\6\1\2vx\3\2\2\2ws\3\2\2\2x{\3\2\2\2yw\3\2\2\2"+
		"yz\3\2\2\2z\13\3\2\2\2{y\3\2\2\2|}\7\35\2\2}~\7\36\2\2~\177\7\n\2\2\177"+
		"\u0080\5\16\b\2\u0080\u0081\7\13\2\2\u0081\u0082\5\60\31\2\u0082\u0083"+
		"\5\22\n\2\u0083\u0084\b\7\1\2\u0084\u008f\3\2\2\2\u0085\u0086\7\22\2\2"+
		"\u0086\u0087\7\36\2\2\u0087\u0088\7\n\2\2\u0088\u0089\5\16\b\2\u0089\u008a"+
		"\7\13\2\2\u008a\u008b\5\60\31\2\u008b\u008c\5\22\n\2\u008c\u008d\b\7\1"+
		"\2\u008d\u008f\3\2\2\2\u008e|\3\2\2\2\u008e\u0085\3\2\2\2\u008f\r\3\2"+
		"\2\2\u0090\u0091\7\35\2\2\u0091\u0092\7\36\2\2\u0092\u0095\5\20\t\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0090\3\2\2\2\u0094\u0093\3\2\2\2\u0095\17\3\2\2"+
		"\2\u0096\u009d\b\t\1\2\u0097\u0098\f\4\2\2\u0098\u0099\7\6\2\2\u0099\u009a"+
		"\7\35\2\2\u009a\u009c\7\36\2\2\u009b\u0097\3\2\2\2\u009c\u009f\3\2\2\2"+
		"\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\21\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u00a0\u00a1\7\3\2\2\u00a1\u00a2\5\24\13\2\u00a2\u00a3\5\30\r"+
		"\2\u00a3\u00a4\7\4\2\2\u00a4\u00a5\b\n\1\2\u00a5\23\3\2\2\2\u00a6\u00a7"+
		"\b\13\1\2\u00a7\u00a8\b\13\1\2\u00a8\u00b0\3\2\2\2\u00a9\u00aa\f\4\2\2"+
		"\u00aa\u00ab\5\26\f\2\u00ab\u00ac\7\5\2\2\u00ac\u00ad\b\13\1\2\u00ad\u00af"+
		"\3\2\2\2\u00ae\u00a9\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\25\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\b\f\1"+
		"\2\u00b4\u00b5\7\35\2\2\u00b5\u00b6\7\36\2\2\u00b6\u00b7\b\f\1\2\u00b7"+
		"\u00be\3\2\2\2\u00b8\u00b9\f\4\2\2\u00b9\u00ba\7\6\2\2\u00ba\u00bb\7\36"+
		"\2\2\u00bb\u00bd\b\f\1\2\u00bc\u00b8\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\27\3\2\2\2\u00c0\u00be\3\2\2"+
		"\2\u00c1\u00c2\5\32\16\2\u00c2\u00c3\5.\30\2\u00c3\u00c4\5\30\r\2\u00c4"+
		"\u00c5\b\r\1\2\u00c5\u00c8\3\2\2\2\u00c6\u00c8\b\r\1\2\u00c7\u00c1\3\2"+
		"\2\2\u00c7\u00c6\3\2\2\2\u00c8\31\3\2\2\2\u00c9\u00ca\5&\24\2\u00ca\u00cb"+
		"\5,\27\2\u00cb\u00cc\5$\23\2\u00cc\u00cd\7\5\2\2\u00cd\u00ce\b\16\1\2"+
		"\u00ce\u0105\3\2\2\2\u00cf\u00d0\7\23\2\2\u00d0\u00d1\7\n\2\2\u00d1\u00d2"+
		"\5$\23\2\u00d2\u00d3\7\13\2\2\u00d3\u00d4\5.\30\2\u00d4\u00d5\5\22\n\2"+
		"\u00d5\u00d6\b\16\1\2\u00d6\u0105\3\2\2\2\u00d7\u00d8\7\23\2\2\u00d8\u00d9"+
		"\7\n\2\2\u00d9\u00da\5$\23\2\u00da\u00db\7\13\2\2\u00db\u00dc\5.\30\2"+
		"\u00dc\u00dd\5\22\n\2\u00dd\u00de\5\62\32\2\u00de\u00df\7\24\2\2\u00df"+
		"\u00e0\5.\30\2\u00e0\u00e1\5\22\n\2\u00e1\u00e2\b\16\1\2\u00e2\u0105\3"+
		"\2\2\2\u00e3\u00e4\7\25\2\2\u00e4\u00e5\7\36\2\2\u00e5\u00e6\7\t\2\2\u00e6"+
		"\u00e7\5$\23\2\u00e7\u00e8\7\6\2\2\u00e8\u00e9\5$\23\2\u00e9\u00ea\5\64"+
		"\33\2\u00ea\u00eb\5\22\n\2\u00eb\u00ec\b\16\1\2\u00ec\u0105\3\2\2\2\u00ed"+
		"\u00ee\7\26\2\2\u00ee\u00ef\7\5\2\2\u00ef\u0105\b\16\1\2\u00f0\u00f1\7"+
		"\26\2\2\u00f1\u00f2\7\n\2\2\u00f2\u00f3\5$\23\2\u00f3\u00f4\7\13\2\2\u00f4"+
		"\u00f5\7\5\2\2\u00f5\u00f6\b\16\1\2\u00f6\u0105\3\2\2\2\u00f7\u00f8\7"+
		"\27\2\2\u00f8\u00f9\7\5\2\2\u00f9\u0105\b\16\1\2\u00fa\u00fb\7\30\2\2"+
		"\u00fb\u00fc\7\5\2\2\u00fc\u0105\b\16\1\2\u00fd\u00fe\5\22\n\2\u00fe\u00ff"+
		"\b\16\1\2\u00ff\u0105\3\2\2\2\u0100\u0101\5\34\17\2\u0101\u0102\7\5\2"+
		"\2\u0102\u0103\b\16\1\2\u0103\u0105\3\2\2\2\u0104\u00c9\3\2\2\2\u0104"+
		"\u00cf\3\2\2\2\u0104\u00d7\3\2\2\2\u0104\u00e3\3\2\2\2\u0104\u00ed\3\2"+
		"\2\2\u0104\u00f0\3\2\2\2\u0104\u00f7\3\2\2\2\u0104\u00fa\3\2\2\2\u0104"+
		"\u00fd\3\2\2\2\u0104\u0100\3\2\2\2\u0105\33\3\2\2\2\u0106\u0107\7\36\2"+
		"\2\u0107\u0108\7\n\2\2\u0108\u0109\5\36\20\2\u0109\u010a\7\13\2\2\u010a"+
		"\u010b\b\17\1\2\u010b\u0114\3\2\2\2\u010c\u010d\7\31\2\2\u010d\u010e\7"+
		"\n\2\2\u010e\u010f\7\17\2\2\u010f\u0110\5\"\22\2\u0110\u0111\7\13\2\2"+
		"\u0111\u0112\b\17\1\2\u0112\u0114\3\2\2\2\u0113\u0106\3\2\2\2\u0113\u010c"+
		"\3\2\2\2\u0114\35\3\2\2\2\u0115\u0116\5 \21\2\u0116\u0117\b\20\1\2\u0117"+
		"\u011a\3\2\2\2\u0118\u011a\b\20\1\2\u0119\u0115\3\2\2\2\u0119\u0118\3"+
		"\2\2\2\u011a\37\3\2\2\2\u011b\u011c\b\21\1\2\u011c\u011d\5$\23\2\u011d"+
		"\u011e\b\21\1\2\u011e\u0126\3\2\2\2\u011f\u0120\f\4\2\2\u0120\u0121\7"+
		"\6\2\2\u0121\u0122\5$\23\2\u0122\u0123\b\21\1\2\u0123\u0125\3\2\2\2\u0124"+
		"\u011f\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127!\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012a\b\22\1\2\u012a\u012b"+
		"\b\22\1\2\u012b\u0137\3\2\2\2\u012c\u012d\f\5\2\2\u012d\u012e\7\6\2\2"+
		"\u012e\u012f\5$\23\2\u012f\u0130\b\22\1\2\u0130\u0136\3\2\2\2\u0131\u0132"+
		"\f\4\2\2\u0132\u0133\7\6\2\2\u0133\u0134\7\17\2\2\u0134\u0136\b\22\1\2"+
		"\u0135\u012c\3\2\2\2\u0135\u0131\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135"+
		"\3\2\2\2\u0137\u0138\3\2\2\2\u0138#\3\2\2\2\u0139\u0137\3\2\2\2\u013a"+
		"\u013b\b\23\1\2\u013b\u013c\5*\26\2\u013c\u013d\b\23\1\2\u013d\u0152\3"+
		"\2\2\2\u013e\u013f\5&\24\2\u013f\u0140\b\23\1\2\u0140\u0152\3\2\2\2\u0141"+
		"\u0142\7\n\2\2\u0142\u0143\5$\23\2\u0143\u0144\7\13\2\2\u0144\u0145\b"+
		"\23\1\2\u0145\u0152\3\2\2\2\u0146\u0147\7#\2\2\u0147\u0148\5$\23\13\u0148"+
		"\u0149\b\23\1\2\u0149\u0152\3\2\2\2\u014a\u014b\7\f\2\2\u014b\u014c\5"+
		"$\23\n\u014c\u014d\b\23\1\2\u014d\u0152\3\2\2\2\u014e\u014f\5\34\17\2"+
		"\u014f\u0150\b\23\1\2\u0150\u0152\3\2\2\2\u0151\u013a\3\2\2\2\u0151\u013e"+
		"\3\2\2\2\u0151\u0141\3\2\2\2\u0151\u0146\3\2\2\2\u0151\u014a\3\2\2\2\u0151"+
		"\u014e\3\2\2\2\u0152\u0175\3\2\2\2\u0153\u0154\f\t\2\2\u0154\u0155\7!"+
		"\2\2\u0155\u0156\5$\23\n\u0156\u0157\b\23\1\2\u0157\u0174\3\2\2\2\u0158"+
		"\u0159\f\b\2\2\u0159\u015a\7\"\2\2\u015a\u015b\5$\23\t\u015b\u015c\b\23"+
		"\1\2\u015c\u0174\3\2\2\2\u015d\u015e\f\7\2\2\u015e\u015f\7#\2\2\u015f"+
		"\u0160\5$\23\b\u0160\u0161\b\23\1\2\u0161\u0174\3\2\2\2\u0162\u0163\f"+
		"\6\2\2\u0163\u0164\7\37\2\2\u0164\u0165\5$\23\7\u0165\u0166\b\23\1\2\u0166"+
		"\u0174\3\2\2\2\u0167\u0168\f\5\2\2\u0168\u0169\7$\2\2\u0169\u016a\5.\30"+
		"\2\u016a\u016b\5$\23\6\u016b\u016c\b\23\1\2\u016c\u0174\3\2\2\2\u016d"+
		"\u016e\f\4\2\2\u016e\u016f\7%\2\2\u016f\u0170\5.\30\2\u0170\u0171\5$\23"+
		"\5\u0171\u0172\b\23\1\2\u0172\u0174\3\2\2\2\u0173\u0153\3\2\2\2\u0173"+
		"\u0158\3\2\2\2\u0173\u015d\3\2\2\2\u0173\u0162\3\2\2\2\u0173\u0167\3\2"+
		"\2\2\u0173\u016d\3\2\2\2\u0174\u0177\3\2\2\2\u0175\u0173\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176%\3\2\2\2\u0177\u0175\3\2\2\2\u0178\u0179\7\36\2\2"+
		"\u0179\u0181\b\24\1\2\u017a\u017b\7\36\2\2\u017b\u017c\7\7\2\2\u017c\u017d"+
		"\5$\23\2\u017d\u017e\7\b\2\2\u017e\u017f\b\24\1\2\u017f\u0181\3\2\2\2"+
		"\u0180\u0178\3\2\2\2\u0180\u017a\3\2\2\2\u0181\'\3\2\2\2\u0182\u0183\t"+
		"\2\2\2\u0183)\3\2\2\2\u0184\u0185\5(\25\2\u0185\u0186\b\26\1\2\u0186\u018c"+
		"\3\2\2\2\u0187\u0188\7\16\2\2\u0188\u018c\b\26\1\2\u0189\u018a\7\34\2"+
		"\2\u018a\u018c\b\26\1\2\u018b\u0184\3\2\2\2\u018b\u0187\3\2\2\2\u018b"+
		"\u0189\3\2\2\2\u018c+\3\2\2\2\u018d\u018e\t\3\2\2\u018e-\3\2\2\2\u018f"+
		"\u0190\b\30\1\2\u0190/\3\2\2\2\u0191\u0192\b\31\1\2\u0192\61\3\2\2\2\u0193"+
		"\u0194\b\32\1\2\u0194\63\3\2\2\2\u0195\u0196\b\33\1\2\u0196\65\3\2\2\2"+
		"\31GIWegy\u008e\u0094\u009d\u00b0\u00be\u00c7\u0104\u0113\u0119\u0126"+
		"\u0135\u0137\u0151\u0173\u0175\u0180\u018b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}