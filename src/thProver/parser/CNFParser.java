/* Generated By:JavaCC: Do not edit this line. CNFParser.java */
package thProver.parser;

import thProver.CNFFormula;
import thProver.Clause;
import thProver.Literal;
import thProver.Term;
import thProver.Atom;
import thProver.Constant;
import thProver.Variable;
import thProver.Function;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class CNFParser implements CNFParserConstants {

    private static CNFFormula f = new CNFFormula();

    public CNFFormula getCNFFormula() {
        CNFFormula currentFormula = f;
        f = new CNFFormula();
        return currentFormula;
    }

/*
* Productions
*/
  static final public void Start() throws ParseException {
    Clause c;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CONST:
      Declaration();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREC:
      Precedences();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WEIGHTVARS:
      WeightVars();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WEIGHTS:
      Weights();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SOS:
      Sos();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CLAUSES:
      Clauses();
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(0);
  }

// constants declaration
  static final public void Declaration() throws ParseException {
    jj_consume_token(CONST);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
    case SYMBOL:
      Consts();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  static final public void Consts() throws ParseException {
    Token c;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SYMBOL:
      c = jj_consume_token(SYMBOL);
      break;
    case INTEGER:
      c = jj_consume_token(INTEGER);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          f.addConstant(c.image); f.addArity(c.image, 1);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SYMBOL:
        c = jj_consume_token(SYMBOL);
        break;
      case INTEGER:
        c = jj_consume_token(INTEGER);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
              f.addConstant(c.image); f.addArity(c.image, 1);
    }
  }

// precedences specification
  static final public void Precedences() throws ParseException {
    jj_consume_token(PREC);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
      case PREDICATE:
      case SYMBOL:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_2;
      }
      Precedence();
                            f.addPrecedence("Bottom"); f.addPrecedence("Top");
    }
  }

  static final public void Precedence() throws ParseException {
    Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREDICATE:
      t = jj_consume_token(PREDICATE);
      break;
    case SYMBOL:
      t = jj_consume_token(SYMBOL);
      break;
    case INTEGER:
      t = jj_consume_token(INTEGER);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                                                         f.incrNPrecList(); f.addPrecedence(t.image);
    label_3:
    while (true) {
      jj_consume_token(RANGLEPAR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREDICATE:
        t = jj_consume_token(PREDICATE);
        break;
      case SYMBOL:
        t = jj_consume_token(SYMBOL);
        break;
      case INTEGER:
        t = jj_consume_token(INTEGER);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                                       f.addPrecedence(t.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RANGLEPAR:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_3;
      }
    }
  }

// weight for KBO
  static final public void Weights() throws ParseException {
    jj_consume_token(WEIGHTS);
    Weight();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
      case PREDICATE:
      case SYMBOL:
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        jj_consume_token(SEMICOLON);
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      Weight();
    }
  }

  static final public void Weight() throws ParseException {
    Token s, i;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SYMBOL:
      s = jj_consume_token(SYMBOL);
      break;
    case PREDICATE:
      s = jj_consume_token(PREDICATE);
      break;
    case INTEGER:
      s = jj_consume_token(INTEGER);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(EQUALS);
    i = jj_consume_token(INTEGER);
          f.setWeight(s.image, i.image);
  }

  static final public void WeightVars() throws ParseException {
    Token i;
    jj_consume_token(WEIGHTVARS);
    i = jj_consume_token(INTEGER);
                                 f.setWeightVars(i.image);
  }

// SOS
  static final public void Sos() throws ParseException {
    Clause c;
    jj_consume_token(SOS);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREDICATE:
      case NOT:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_5;
      }
      c = Clause();
                           f.addSOS(c);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEMICOLON:
          ;
          break;
        default:
          jj_la1[18] = jj_gen;
          break label_6;
        }
        jj_consume_token(SEMICOLON);
        c = Clause();
                                                                       f.addSOS(c);
      }
    }
  }

// clauses
  static final public void Clauses() throws ParseException {
    Clause c;
    jj_consume_token(CLAUSES);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREDICATE:
      case NOT:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_7;
      }
      c = Clause();
                            f.addClause(c);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEMICOLON:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_8;
        }
        jj_consume_token(SEMICOLON);
        c = Clause();
                                                                           f.addClause(c);
      }
    }
  }

// a clause
  static final public Clause Clause() throws ParseException {
    Clause c = new Clause();
    Literal l;
    l = Literal();
          c.addLiteral(l);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_9;
      }
      jj_consume_token(OR);
      l = Literal();
              c.addLiteral(l);
    }
          {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

// a literal
  static final public Literal Literal() throws ParseException {
    boolean pos = true;
    Atom a;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
                  pos = false;
      break;
    default:
      jj_la1[22] = jj_gen;
      ;
    }
    a = Atom();
          {if (true) return f.addLiteral(new Literal(pos, a));}
    throw new Error("Missing return statement in function");
  }

  static final public Atom Atom() throws ParseException {
    Atom a;
    List<Term> terms = null;
    Token p;
    p = jj_consume_token(PREDICATE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
      jj_consume_token(LPAR);
      terms = Terms();
      jj_consume_token(RPAR);
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
          boolean ok;
          if (terms != null) {
             a = new Atom(p.image, terms);
             ok = f.checkArity(p.image, terms.size());
          } else {
             a = new Atom(p.image);
             ok = f.checkArity(p.image, 0);
          }
          if (!ok)
             {if (true) throw new IllegalArgumentException("Errore di ariet\u00e0: " + p.image + "\u005cn");}

          {if (true) return f.addAtom(a);}
    throw new Error("Missing return statement in function");
  }

  static final public List<Term> Terms() throws ParseException {
    List<Term> tl = new LinkedList<Term>();
    Term t;
    t = Term();
                      tl.add(t);
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[24] = jj_gen;
        break label_10;
      }
      jj_consume_token(COMMA);
      t = Term();
                                                          tl.add(t);
    }
          {if (true) return tl;}
    throw new Error("Missing return statement in function");
  }

  static final public Term Term() throws ParseException {
    boolean noArgs = true;
    Token s;
    Term t;
    List<Term> terms = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SYMBOL:
      s = jj_consume_token(SYMBOL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAR:
                    noArgs = false;
        jj_consume_token(LPAR);
        terms = Terms();
        jj_consume_token(RPAR);
        break;
      default:
        jj_la1[25] = jj_gen;
        ;
      }
      break;
    case INTEGER:
      s = jj_consume_token(INTEGER);
                              if (!f.isConstant(s.image)) {if (true) throw new ParseException(s.image + " must be constant.");}
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
           if (noArgs) { // constant or variable
                if (!f.isConstant(s.image)) {
                    boolean ok = f.checkArity(s.image, 1);
                    if (!ok) {
                        {if (true) throw new IllegalArgumentException("Errore di ariet\u00e0: " + s.image + "\u005cn");}
                    }
                    t = f.addVariable(s.image);
                } else
                    t = f.addConstant(s.image); // per recuperare il puntatore alla costante
            } else { // function
                boolean ok = f.checkArity(s.image, terms.size());
                if (!ok) {
                    {if (true) throw new IllegalArgumentException("Errore di ariet\u00e0: " + s.image + "\u005cn");}
                }
                t = new Function(s.image, terms);
                //t.setSymbol(s.image);
                //((Function) t).setArgs(terms);
                t = f.addTerm(t);
           }
           {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CNFParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[27];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x8000,0x10000,0x200000,0x100000,0x40000,0x80000,0x280,0x280,0x400,0x280,0x380,0x380,0x380,0x20000,0xb80,0x800,0x380,0x4100,0x800,0x4100,0x800,0x40,0x4000,0x1000,0x400,0x1000,0x280,};
   }

  /** Constructor with InputStream. */
  public CNFParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CNFParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CNFParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CNFParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CNFParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CNFParser(CNFParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CNFParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 27; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
