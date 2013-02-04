package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A CNF formula - a set of clauses.
 * Contains clauses, literals, atoms and terms of the given formula
 * It's the class used by parser to construct the graph representing the formula
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
    
    // permette di resettare i parametri delle clausole se è già stata chiamata
    // una satisfiabile nel prover
    private boolean primoUsoSOS = true;
    private boolean primoUsoClauses = true;

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
    
    /**
     * Constructs a new constant if the symbol representing the constant is never
     * used yet and return it, otherwise return the constant costructed before
     * with that symbol and return it (note: save space)
     * In case of new constant save it in terms for the future.
     * 
     * @param key string representing the constant
     * @return the constant represented by the key
     */
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
    /**
     * Check if the string represents a constant.
     * 
     * @param name symbol token processed by parser 
     *             (it could represent predicate, function, variable or constant) 
     * @return true if represent a constant, false otherwise.
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
    /**
     * Costruct a new variable if the symbol representing the variable is never
     * used yet and return it, otherwise return the variable costructed before
     * with that symbol and return it (note: save space)
     * In case of new variable save it in terms for the future.
     * 
     * @param var string representing the variable
     * @return the variable represented by the string
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
    
    /**
     * Check if the function is constucted and saved yet
     * if yes get the 'old' function and return it
     * if no add the function in terms (for the future) and return it.
     * 
     * @param fun the function
     * @return the older function equals or it if it's the first
     */
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
    
    /**
     * Check if the term is constucted and saved yet
     * if yes get the 'old' term and return it
     * if no add the term in terms (for the future) and return it.
     * @param term the term
     * @return the older term equals or it if it's the first
     */
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
    
    /**
     * Check if the literal is constucted and saved yet
     * if yes get the 'old' literal and return it
     * if no add the literal in literals (for the future) and return it. 
     * @param lit the literal
     * @return the older literal equals or it if it's the first
     */
    public Literal addLiteral(Literal lit) {
        Literal l;
        int hC = lit.hashCode();
        if ((l = literals.get(hC)) == null) {
            l = lit;
            literals.put(hC, lit);
        }
        return l;
    }
    
    /**
     * Check if the atom is constucted and saved yet
     * if yes get the 'old' atom and return it
     * if no add the atom in atoms (for the future) and return it.
     * @param atom the atom
     * @return the older atom equals or it if it's the first
     */
    public Atom addAtom(Atom atom) {
        Atom a;
        int hC = atom.hashCode();
        if ((a = atoms.get(hC)) == null) {
            a = atom;
            atoms.put(hC, atom);
        }
        return a;
    }
    
    /**
     * Add the clause in clauses.
     * 
     * @param clause the clause
     */
    public void addClause(Clause clause) {
        clauses.add(clause);
        nClausesAndSOS++;
    }
    
    /**
     * Add the clause in sos.
     * 
     * @param clause the clause
     */
    public void addSOS(Clause clause) {
        sos.add(clause);
        nClausesAndSOS++;
    }
    
    /**
     * Index of the current clause
     * (start from 0)
     * 
     * @return index of the current clause
     */
    public long getClauseIndex() {
        return nClausesAndSOS;
    }
    
    /**
     * Check Map<symbol, arity> arities to garanty the same symbol has always the
     * sane number of parameters.
     * 
     * @param name symbol to check
     * @param nArgs arity found
     * @return true if the arity is equals, false otherwise
     */
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
    
    /**
     * Add an entry in Map symbol-arity
     * @param name symbol name
     * @param nArgs arity to assign to the symbol
     */
    public void addArity(String name, int nArgs) {
        arities.put(name, nArgs);
    }

    /**
     * Rapresentation of precedences, weights, clauses saved
     * @return a string that rapresent the precedences, weights, clauses saved
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getConstantString());
        sb.append(getPrecedencesString());
        sb.append(getWeightsString());
        sb.append(getSOSString());
        sb.append(getClausesString());

        return sb.toString();
    }
    
    /**
     * Rapresentation of all the constants saved
     * @return a string that rapresent the constants saved
     */
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
    
    /**
     * Rapresentation of all the clauses saved in sos
     * @return a string that rapresent the clauses saved in sos
     */
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
    
    /**
     * Rapresentation of all the clauses saved in clauses
     * @return a string that rapresent the clauses saved in clauses
     */
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
    
    /**
     * Rapresentation of all the precendences saved
     * @return a string that rapresent the precedences saved
     */
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
    
    /**
     * Add precedences and increment the number of them
     */
    public void incrNPrecList() {
        nPrecedencesList++;
        precedences.add(new ArrayList<String>());
    }
    
    /**
     * Add a symbol in the current precedence
     * @param s the symbol
     */
    public void addPrecedence(String s) {
        precedences.get(nPrecedencesList).add(s);
    }
    
    /**
     * Return all the precedences
     * @return the precedences
     */
    public List<List<String>> getPrecedences() {
        return precedences;
    }
    
    /**
     * Return the number of precedences
     * @return the number of precedences
     */
    public int getNPrec() {
        return nPrecedencesList;
    }
    
    /**
     * Return the list of all the clauses saved in clauses
     * @return the list of all the clauses saved in clauses
     */
    public List<Clause> getClauses() {
        if (!primoUsoClauses) {
            for (Clause c : clauses)
                c.resetForOtherOrdering();
        }
        primoUsoClauses = false;
        
        return clauses;
    }
    
    /**
     * Return the list of all the clauses saved in sos
     * @return the list of all the clauses saved in sos
     */
    public List<Clause> getSOS() {
        if (!primoUsoSOS) {
            for (Clause c : sos)
                c.resetForOtherOrdering();
        }
        primoUsoSOS = false;
        
        return sos;
    }
    
    /* DEBUG inizio */
    // temporaneo per vedere se ci sono doppi
    public String getTermsString() {
        return terms.toString();
    }
    /* DEBUG fine */
    
    /**
     * Set the KBO weight of the symbol
     * @param sym symbol
     * @param weight weight of the symbol
     */
    public void setWeight(String sym, String weight) {
        Object o = weights.put(sym, Integer.parseInt(weight));
        if (o != null) {
            // il peso per questo sym è già stato definito
            throw new IllegalArgumentException("Sono stati definiti più pesi per " + sym + ".");
        }
    }
    
    /**
     * Set the KBO weight of the variables
     */
    public void setWeightVars(String weight) {
        weightVars = Integer.parseInt(weight);
    }
    
    /**
     * Return a rapresentation of all the couple symbol-weight
     * @return a rapresentation of all the couple symbol-weight
     */
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
    
    /**
     * Return the map of all the weight (note: search efficiency)
     * @return the map of all the weight
     */
    public HashMap<String, Integer> getWeights() {
        return weights;
    }
    
    /**
     * Integer weight of the variables
     * @return weight of the variables
     */
    public int getWeightVars() {
        return weightVars;
    }
    
    /**
     * Complessive number of all the clauses (sum of clauses in clauses and sos)
     * @return number of all the clauses
     */
    public long getNumClausesAndSOS() {
        return nClausesAndSOS;
    }
}
