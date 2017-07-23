// Generated from A3Code.g4 by ANTLR 4.5.3


import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class A3CodeLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "Delim", "Letter", "Digit", "HexDigit", "Alpha", "AlphaNum", "WhiteSpace", 
		"Char", "Str", "Class", "Program", "Void", "If", "Else", "For", "Ret", 
		"Brk", "Cnt", "Callout", "DecNum", "HexNum", "BoolLit", "Type", "Ident", 
		"RelOp", "AssignOp", "MulDiv", "AddOp", "SubOp", "AndOp", "OrOp"
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


	public A3CodeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "A3Code.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u011c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\5\17r\n\17\3\20\3\20\5\20v\n"+
		"\20\3\21\3\21\5\21z\n\21\3\22\6\22}\n\22\r\22\16\22~\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u008b\n\23\3\24\3\24\3\24\3\24"+
		"\7\24\u0091\n\24\f\24\16\24\u0094\13\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\37\6\37\u00d6\n\37\r\37\16\37\u00d7\3 \3 \3 \3 \6 \u00de\n \r"+
		" \16 \u00df\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u00eb\n!\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\5\"\u00f7\n\"\3#\3#\7#\u00fb\n#\f#\16#\u00fe\13#"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0109\n$\3%\3%\3%\3%\5%\u010f\n%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\2\31\2\33\2\35\2\37\2!\2#\r%\16\'\17)\20+\21-\22/\23"+
		"\61\24\63\25\65\26\67\279\30;\31=\32?\33A\34C\35E\36G\37I K!M\"O#Q$S%"+
		"\3\2\n\4\2\13\f\"\"\4\2C\\c|\3\2\62;\4\2CHch\3\2^^\4\2$$^^\4\2>>@@\5\2"+
		"\'\',,\61\61\u0126\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\3U\3\2\2\2\5W\3\2\2\2\7Y\3\2\2\2\t[\3\2\2\2\13]\3\2\2\2\r_\3"+
		"\2\2\2\17a\3\2\2\2\21c\3\2\2\2\23e\3\2\2\2\25g\3\2\2\2\27i\3\2\2\2\31"+
		"k\3\2\2\2\33m\3\2\2\2\35q\3\2\2\2\37u\3\2\2\2!y\3\2\2\2#|\3\2\2\2%\u008a"+
		"\3\2\2\2\'\u008c\3\2\2\2)\u0097\3\2\2\2+\u009d\3\2\2\2-\u00a5\3\2\2\2"+
		"/\u00aa\3\2\2\2\61\u00ad\3\2\2\2\63\u00b2\3\2\2\2\65\u00b6\3\2\2\2\67"+
		"\u00bd\3\2\2\29\u00c3\3\2\2\2;\u00cc\3\2\2\2=\u00d5\3\2\2\2?\u00d9\3\2"+
		"\2\2A\u00ea\3\2\2\2C\u00f6\3\2\2\2E\u00f8\3\2\2\2G\u0108\3\2\2\2I\u010e"+
		"\3\2\2\2K\u0110\3\2\2\2M\u0112\3\2\2\2O\u0114\3\2\2\2Q\u0116\3\2\2\2S"+
		"\u0119\3\2\2\2UV\7}\2\2V\4\3\2\2\2WX\7\177\2\2X\6\3\2\2\2YZ\7=\2\2Z\b"+
		"\3\2\2\2[\\\7.\2\2\\\n\3\2\2\2]^\7]\2\2^\f\3\2\2\2_`\7_\2\2`\16\3\2\2"+
		"\2ab\7?\2\2b\20\3\2\2\2cd\7*\2\2d\22\3\2\2\2ef\7+\2\2f\24\3\2\2\2gh\7"+
		"#\2\2h\26\3\2\2\2ij\t\2\2\2j\30\3\2\2\2kl\t\3\2\2l\32\3\2\2\2mn\t\4\2"+
		"\2n\34\3\2\2\2or\5\33\16\2pr\t\5\2\2qo\3\2\2\2qp\3\2\2\2r\36\3\2\2\2s"+
		"v\5\31\r\2tv\7a\2\2us\3\2\2\2ut\3\2\2\2v \3\2\2\2wz\5\37\20\2xz\5\33\16"+
		"\2yw\3\2\2\2yx\3\2\2\2z\"\3\2\2\2{}\5\27\f\2|{\3\2\2\2}~\3\2\2\2~|\3\2"+
		"\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\b\22\2\2\u0081$\3\2\2"+
		"\2\u0082\u0083\7)\2\2\u0083\u0084\n\6\2\2\u0084\u008b\7)\2\2\u0085\u0086"+
		"\7)\2\2\u0086\u0087\7^\2\2\u0087\u0088\3\2\2\2\u0088\u0089\13\2\2\2\u0089"+
		"\u008b\7)\2\2\u008a\u0082\3\2\2\2\u008a\u0085\3\2\2\2\u008b&\3\2\2\2\u008c"+
		"\u0092\7$\2\2\u008d\u0091\n\7\2\2\u008e\u008f\7^\2\2\u008f\u0091\13\2"+
		"\2\2\u0090\u008d\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0094\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2"+
		"\2\2\u0095\u0096\7$\2\2\u0096(\3\2\2\2\u0097\u0098\7e\2\2\u0098\u0099"+
		"\7n\2\2\u0099\u009a\7c\2\2\u009a\u009b\7u\2\2\u009b\u009c\7u\2\2\u009c"+
		"*\3\2\2\2\u009d\u009e\7R\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7q\2\2\u00a0"+
		"\u00a1\7i\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4\7o\2\2"+
		"\u00a4,\3\2\2\2\u00a5\u00a6\7x\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7k\2"+
		"\2\u00a8\u00a9\7f\2\2\u00a9.\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7"+
		"h\2\2\u00ac\60\3\2\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7n\2\2\u00af\u00b0"+
		"\7u\2\2\u00b0\u00b1\7g\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\7h\2\2\u00b3\u00b4"+
		"\7q\2\2\u00b4\u00b5\7t\2\2\u00b5\64\3\2\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8"+
		"\7g\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7w\2\2\u00ba\u00bb\7t\2\2\u00bb"+
		"\u00bc\7p\2\2\u00bc\66\3\2\2\2\u00bd\u00be\7d\2\2\u00be\u00bf\7t\2\2\u00bf"+
		"\u00c0\7g\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7m\2\2\u00c28\3\2\2\2\u00c3"+
		"\u00c4\7e\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7v\2\2"+
		"\u00c7\u00c8\7k\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca\7w\2\2\u00ca\u00cb"+
		"\7g\2\2\u00cb:\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf"+
		"\7n\2\2\u00cf\u00d0\7n\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7w\2\2\u00d2"+
		"\u00d3\7v\2\2\u00d3<\3\2\2\2\u00d4\u00d6\5\33\16\2\u00d5\u00d4\3\2\2\2"+
		"\u00d6\u00d7\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8>\3"+
		"\2\2\2\u00d9\u00da\7\62\2\2\u00da\u00db\7z\2\2\u00db\u00dd\3\2\2\2\u00dc"+
		"\u00de\5\35\17\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3"+
		"\2\2\2\u00df\u00e0\3\2\2\2\u00e0@\3\2\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3"+
		"\7t\2\2\u00e3\u00e4\7w\2\2\u00e4\u00eb\7g\2\2\u00e5\u00e6\7h\2\2\u00e6"+
		"\u00e7\7c\2\2\u00e7\u00e8\7n\2\2\u00e8\u00e9\7u\2\2\u00e9\u00eb\7g\2\2"+
		"\u00ea\u00e1\3\2\2\2\u00ea\u00e5\3\2\2\2\u00ebB\3\2\2\2\u00ec\u00ed\7"+
		"k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00f7\7v\2\2\u00ef\u00f0\7d\2\2\u00f0\u00f1"+
		"\7q\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7g\2\2\u00f4"+
		"\u00f5\7c\2\2\u00f5\u00f7\7p\2\2\u00f6\u00ec\3\2\2\2\u00f6\u00ef\3\2\2"+
		"\2\u00f7D\3\2\2\2\u00f8\u00fc\5\37\20\2\u00f9\u00fb\5!\21\2\u00fa\u00f9"+
		"\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd"+
		"F\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\7>\2\2\u0100\u0109\7?\2\2\u0101"+
		"\u0102\7@\2\2\u0102\u0109\7?\2\2\u0103\u0109\t\b\2\2\u0104\u0105\7?\2"+
		"\2\u0105\u0109\7?\2\2\u0106\u0107\7#\2\2\u0107\u0109\7?\2\2\u0108\u00ff"+
		"\3\2\2\2\u0108\u0101\3\2\2\2\u0108\u0103\3\2\2\2\u0108\u0104\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0109H\3\2\2\2\u010a\u010b\7-\2\2\u010b\u010f\7?\2\2\u010c"+
		"\u010d\7/\2\2\u010d\u010f\7?\2\2\u010e\u010a\3\2\2\2\u010e\u010c\3\2\2"+
		"\2\u010fJ\3\2\2\2\u0110\u0111\t\t\2\2\u0111L\3\2\2\2\u0112\u0113\7-\2"+
		"\2\u0113N\3\2\2\2\u0114\u0115\7/\2\2\u0115P\3\2\2\2\u0116\u0117\7(\2\2"+
		"\u0117\u0118\7(\2\2\u0118R\3\2\2\2\u0119\u011a\7~\2\2\u011a\u011b\7~\2"+
		"\2\u011bT\3\2\2\2\21\2quy~\u008a\u0090\u0092\u00d7\u00df\u00ea\u00f6\u00fc"+
		"\u0108\u010e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}