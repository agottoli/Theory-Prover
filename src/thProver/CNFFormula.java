package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * A CNF formula - a set of clauses.
 */
public class CNFFormula {

    //private boolean tree;
    private HashMap<String, Integer> arities; // nome funzione o Predicato o variabile o costante, arietà
    //private HashMap<String, Constant> constants; // c.toString(), c 
    private HashMap<String, Term> terms; // term.toString(), term
    private HashMap<String, Atom> atoms; // atom.toString(), atom
    private HashMap<String, Literal> literals;
    private List<Clause> clauses;
    
    private List<List<String>> precedences;
    private int nPrecedencesList = -1;

    /* questi sotto si faranno nel prover */
    //private TreeSet<Clause> To_Select_tree; //clauses; poll() <-- ritorna e rimuove la testa
    //private PriorityQueue<Clause> To_Select_queue; // poolFirst() <-- ritorna e rimuove la testa
    
    public CNFFormula() {
        arities = new HashMap<>();
        //constants = new HashMap<>();
        terms = new HashMap<>();
        atoms = new HashMap<>();
        literals = new HashMap<>();
        clauses = new ArrayList<>(); // oppure LinkedList<>()
        precedences = new ArrayList<>();
    }
    
    public Constant addConstant(String key) {
        Term c;
        if ((c = terms.get(key)) == null) {
            c = new Constant(key);
            terms.put(key, c);
        }  
        return (Constant) c;
    }
    
    public Constant addConstant(Constant cost) {
        Term c;
        if ((c = terms.get(cost.toString())) == null) {
            c = cost;
            terms.put(cost.toString(), cost);
        }  
        return (Constant) c;
    }
    
    public boolean isConstant(String name) {
        Term t;
        return ((t = terms.get(name)) != null && t instanceof Constant);
    }
    
    public Variable addVariable(Variable var) {
        Term v;
        if ((v = terms.get(var.toString())) == null) {
            v = var;
            terms.put(var.toString(), var);
        }  
        return (Variable) v;
    }
    
    public Function addFunction(Function fun) {
        Term f;
        if ((f = terms.get(fun.toString())) == null) {
            f = fun;
            terms.put(fun.toString(), fun);
        }  
        return (Function) f;
    }
    
    public Term addTerm(Term term) {
        Term t;
        if ((t = terms.get(term.toString())) == null) {
            t = term;
            terms.put(term.toString(), term);
        }  
        return term;
    }
    
    public Literal addLiteral(Literal lit) {
        Literal l;
        if ((l = literals.get(lit.toString())) == null) {
            l = lit;
            literals.put(lit.toString(), lit);
        }
        return l;
    }
    
    public Atom addAtom(Atom atom) {
        Atom a;
        if ((a = atoms.get(atom.toString())) == null) {
            a = atom;
            atoms.put(atom.toString(), atom);
        }
        return a;
    }
    
    public void addClause(Clause clause) {
        clauses.add(clause);
    }
    
    public boolean checkArity(String name, int nArgs) {
        Integer n;
        if ((n = arities.get(name)) == null) {
            addArity(name, nArgs);
            return true;
        }    
        if (n.intValue() == nArgs) {
            return true;
        }
        
        return false;
    }
    
    public void addArity(String name, int nArgs) {
        arities.put(name, nArgs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getConstantString());
        sb.append("\n").append(getPrecedencesString());
        sb.append("\n").append(getClauseString());

        return sb.toString();
    }
    
    public String getConstantString() { // DA MIGLIORARE
        StringBuilder sb = new StringBuilder("Constants: { ");
        Collection<Term> cost = terms.values();
        for (Iterator<Term> it = cost.iterator(); it.hasNext();) {
            Term t = it.next();
            if (t instanceof Constant)
                sb.append(t.toString()).append(",");
        }
        sb.replace(sb.length()-1, sb.length(), " }");
        return sb.toString();
    }
    
    public String getClauseString() {
        StringBuilder sb = new StringBuilder("Clauses: { ");
        for (Clause c : clauses)
            sb.append(c).append(" ; ");
        if (!clauses.isEmpty())
            sb.replace(sb.length()-2, sb.length(), "");
        sb.append("}");
        return sb.toString();
    }
    
    public String getPrecedencesString() {
        StringBuilder sb = new StringBuilder();
        if (nPrecedencesList < 1)
            sb.append("Precedence: ");
        else 
            sb.append("Precedences: ");
        
        for (List<String> l : precedences) {
            for (String s : l)
                sb.append(s).append(" > ");
            sb.replace(sb.length()-2, sb.length(), "; ");
        }
        sb.replace(sb.length()-3, sb.length(), "");
        //sb.append(precedences.toString());
        return sb.toString();
    }
    
    public void incrNPrecList() {
        nPrecedencesList++;
        precedences.add(new ArrayList<String>());
    }
    
    public void addPrecedence(String s) {
        precedences.get(nPrecedencesList).add(s);
    }
}
