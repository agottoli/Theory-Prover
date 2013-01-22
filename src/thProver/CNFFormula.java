package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
    private HashMap<Integer, Term> terms; // term.toString(), term
    private HashMap<Integer, Atom> atoms; // atom.toString(), atom
    private HashMap<Integer, Literal> literals;
    private List<Clause> clauses;
    private long nClausesAndSOS;
    private List<Clause> sos;
    
    private List<List<String>> precedences;
    private int nPrecedencesList = -1;
    
    // weight for KBO
    private HashMap<String, Integer> weights;
    private int weightVars;

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
        sos = new ArrayList<>(); // oppure LinkedList<>()
        precedences = new ArrayList<>();
        weights = new HashMap<>();
        weightVars = 1;
        nClausesAndSOS = 0;
    }
    
    public Constant addConstant(String key) {
        Term c;
        Term nuovo = new Constant(key);
        int hC = nuovo.hashCode();
        if ((c = terms.get(hC)) == null) { // hs)) == null) {
            /* DEBUG inizio */
            //System.out.println(nuovo.toString() + " nuova costante");
            /* DEBUG fine */
            
            c = nuovo; // new Constant(key);
            terms.put(hC, c); // hc, c);
        }
        return (Constant) c;
    }
/*    
    public Constant addConstant(Constant cost) {
        Term c;
        int hC = cost.hashCode();
        if ((c = terms.get(hC)) == null) { // hc)) == null) {
            /* DEBUG inizio * /
            System.out.println(cost.toString() + " nuova costante");
            /* DEBUG fine * /
            
            c = cost;
            terms.put(hC, cost); // hc, cost);
        }  
        return (Constant) c;
    }
*/
    
    public boolean isConstant(String name) {
        Term t;
        Term cos = new Constant(name);
        int hC = cos.hashCode();
        return ((t = terms.get(hC)) != null && t instanceof Constant);
    }

/*
    public Variable addVariable(Variable var) {
        Term v;
        int hC = var.hashCode();
        if ((v = terms.get(hC)) == null) {
            v = var;
            terms.put(hC, var);
        }  
        return (Variable) v;
    }
*/    
    public Variable addVariable(String var) {
        Term v;
        String rinNewVar = var + "_" + nClausesAndSOS;
        Term nuovo = new Variable(rinNewVar);
        int hC = nuovo.hashCode();
        if ((v = terms.get(hC)) == null) {
            /* DEBUG inizio */
            //System.out.println(var.toString() + " nuova variabile");
            /* DEBUG fine */
            
            
            v = nuovo; // new Variable(rinNewVar);
            terms.put(hC, v);
        }  
        return (Variable) v;
    }
    
    public Function addFunction(Function fun) {
        Term f;
        int hC = fun.hashCode();
        if ((f = terms.get(hC)) == null) {
            /* DEBUG inizio */
            //System.out.println(fun.toString() + " nuova funzione");
            /* DEBUG fine */
            
            f = fun;
            terms.put(hC, fun);
        }  
        return (Function) f;
    }
    
    public Term addTerm(Term term) {
        Term t;
        int hC = term.hashCode();
        if ((t = terms.get(hC)) == null) {
            /* DEBUG inizio */
            //System.out.println(term.toString() + " nuovo termine");
            /* DEBUG fine */
            
            t = term;
            terms.put(hC, term);
        } 
        /* DEBUG inizio */
        //else
        //    System.out.println(term.toString() + " termine esistente");
        /* DEBUG fine */
        
        return t;
    }
    
    public Literal addLiteral(Literal lit) {
        Literal l;
        int hC = lit.hashCode();
        if ((l = literals.get(hC)) == null) {
            l = lit;
            literals.put(hC, lit);
        }
        return l;
    }
    
    public Atom addAtom(Atom atom) {
        Atom a;
        int hC = atom.hashCode();
        if ((a = atoms.get(hC)) == null) {
            a = atom;
            atoms.put(hC, atom);
        }
        return a;
    }
    
    public void addClause(Clause clause) {
        clauses.add(clause);
        nClausesAndSOS++;
    }
    
    public void addSOS(Clause clause) {
        sos.add(clause);
        nClausesAndSOS++;
    }
    
    public long getClauseIndex() {
        return nClausesAndSOS;
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
        sb.append(getPrecedencesString());
        sb.append(getWeightsString());
        sb.append(getSOSString());
        sb.append(getClausesString());

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
        sb.replace(sb.length()-1, sb.length(), " }\n");
        return sb.toString();
    }
    
    public String getSOSString() {
        if (sos.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("sos: { ");
        for (Clause c : sos)
            sb.append(c).append(" ; ");
        if (!sos.isEmpty())
            sb.replace(sb.length()-2, sb.length(), "");
        sb.append("}\n");
        return sb.toString();
    }
    
    public String getClausesString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nClausesAndSOS);
        if (nClausesAndSOS == 1)
            sb.append(" Clause: { ");
        else
            sb.append(" Clauses: { ");
        for (Clause c : clauses)
            sb.append(c).append(" ; ");
        if (!clauses.isEmpty())
            sb.replace(sb.length()-2, sb.length(), "");
        sb.append("}\n");
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
        sb.replace(sb.length()-3, sb.length(), "\n");
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
    
    public List<List<String>> getPrecedences() {
        return precedences;
    }
    
    public int getNPrec() {
        return nPrecedencesList;
    }
    
    public List<Clause> getClauses() {
        return clauses;
    }
    
    public List<Clause> getSOS() {
        return sos;
    }
    
    /* DEBUG inizio */
    // temporaneo per vedere se ci sono doppi
    public String getTermsString() {
        return terms.toString();
    }
    /* DEBUG fine */
    
    public void setWeight(String sym, String weight) {
        Object o = weights.put(sym, Integer.parseInt(weight));
        if (o != null) {
            // il peso per questo sym è già stato definito
            throw new IllegalArgumentException("Sono stati definiti più pesi per " + sym + ".");
        }
    }
    
    public void setWeightVars(String weight) {
        weightVars = Integer.parseInt(weight);
    }
    
    public String getWeightsString() {
        if (weights.isEmpty())
            return "";
        
        StringBuilder sb = new StringBuilder("weightVars = ");
        sb.append(weightVars);
        sb.append("\nweights: ");
        sb.append(weights.toString());
        sb.append("\n");
        return sb.toString();
    }
    
    public HashMap<String, Integer> getWeights() {
        return weights;
    }
    
    public int getWeightVars() {
        return weightVars;
    }
    
    public long getNumClausesAndSOS() {
        return nClausesAndSOS;
    }
}
