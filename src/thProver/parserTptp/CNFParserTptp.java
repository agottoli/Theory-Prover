/* Generated By:JavaCC: Do not edit this line. CNFParserTptp.java */
package thProver.parserTptp;

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

public class CNFParserTptp implements CNFParserTptpConstants {

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
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOWER_WORD:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      Clause();
    }
    jj_consume_token(0);
  }

// a clause
  static final public void Clause() throws ParseException {
    Clause c = new Clause(f.getClauseIndex());
    Literal l;
    Token cnf;
    Token role;
    cnf = jj_consume_token(LOWER_WORD);
                         if (!cnf.image.equals("cnf"))
                            {if (true) throw new ParseException("una clausola deve cominciare con cnf.");}
    jj_consume_token(LPAR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOWER_WORD:
      jj_consume_token(LOWER_WORD);
      break;
    case SINGLE_QUOTED:
      jj_consume_token(SINGLE_QUOTED);
      break;
    case INTEGER:
      jj_consume_token(INTEGER);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(COMMA);
    role = jj_consume_token(LOWER_WORD);
    jj_consume_token(COMMA);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
      jj_consume_token(LPAR);
      l = Literal();
                                      c.addLiteral(l);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VLINE:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(VLINE);
        l = Literal();
                                              c.addLiteral(l);
      }
      jj_consume_token(RPAR);
      break;
    case NOT:
    case LOWER_WORD:
    case SINGLE_QUOTED:
      l = Literal();
                               c.addLiteral(l);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VLINE:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_3;
        }
        jj_consume_token(VLINE);
        l = Literal();
                                              c.addLiteral(l);
      }
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(RPAR);
    jj_consume_token(DOT);
          if (role.image.equals("negated_conjecture")) {
             f.addSOS(c);
          } else {
             f.addClause(c);
          }
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
      jj_la1[5] = jj_gen;
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
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOWER_WORD:
      p = jj_consume_token(LOWER_WORD);
      break;
    case SINGLE_QUOTED:
      p = jj_consume_token(SINGLE_QUOTED);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
      jj_consume_token(LPAR);
      terms = Terms();
      jj_consume_token(RPAR);
      break;
    default:
      jj_la1[7] = jj_gen;
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
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_4;
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
    boolean variabile = false;
    Token s;
    Term t;
    List<Term> terms = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOWER_WORD:
    case SINGLE_QUOTED:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOWER_WORD:
        s = jj_consume_token(LOWER_WORD);
        break;
      case SINGLE_QUOTED:
        s = jj_consume_token(SINGLE_QUOTED);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAR:
                      noArgs = false;
        jj_consume_token(LPAR);
        terms = Terms();
        jj_consume_token(RPAR);
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
    case UPPER_WORD:
      s = jj_consume_token(UPPER_WORD);
                                      variabile = true;
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
           if (noArgs) { // constant or variable
                if (variabile) {
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
  static public CNFParserTptpTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x1005000,0x40,0x40,0x5280,0x80,0x5000,0x200,0x100,0x5000,0x200,0x7000,};
   }

  /** Constructor with InputStream. */
  public CNFParserTptp(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CNFParserTptp(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CNFParserTptpTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CNFParserTptp(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CNFParserTptpTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CNFParserTptp(CNFParserTptpTokenManager tm) {
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
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CNFParserTptpTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[31];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 31; i++) {
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
