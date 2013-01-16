package thProver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A Clause - a set of literals.
 */
public class Clause implements Comparable<Clause> {

    // NOTA : meglio mettere Set<Literal> perché non ci interessa l'ordine
    Set<Literal> literals; // perché non interessa l'ordine e se ho due letterali uguali me ne tiene solo uno
    // per velocizzare le operazioni mi divido i letterali positivi dai negativi
    // NOTA: tengo sono gli atomi, visto che so il segno
    List<Literal> posLits;
    List<Literal> negLits;
    // stringa per la stampa così non devo ricalcolarla
    private String string;
    // mi tengo il numero dei simboli per non ricalcolarli
    private int numSymbs = 0;
    // mi salvo tutti i fattori di una clausola per non doverli rigenerare
    // ad ogni risoluzione
    Set<Clause> factors;
    Set<Clause> maximalFactors;
    // lista dei letterali massimali
    private List<Literal> maximalLits;
    List<Literal> posMaximalLits;
    List<Literal> negMaximalLits;

    /**
     * Constructs an empty clause.
     */
    public Clause() {
        literals = new LinkedHashSet<>();
        posLits = new ArrayList<>();
        negLits = new ArrayList<>();
    }

    public Clause(Set<Literal> lits) {
        this();
        literals = lits;
        for (Literal l : literals)
            if (l.isPositive())
                this.posLits.add(l);
            else
                this.negLits.add(l);
    }

    // per quando creo una clausola da una regola di espansione
    // C or L  -L or D --> C or D
    public Clause(List<Literal> lits1, List<Literal> lits2) {
        this();
        literals.addAll(lits1);
        literals.addAll(lits2);
        for (Literal l : literals)
            if (l.isPositive())
                this.posLits.add(l);
            else
                this.negLits.add(l);
    }
    /*
     /* *
     * Constructs a clause containing the given literals.
     *
     * @param lits the list of literals
     * /
     Clause(Literal... lits) {
     literals = new ArrayList<>(Arrays.asList(lits));
     posLits = new ArrayList<>();
     negLits = new ArrayList<>();
     for (Literal l : lits)
     if (l.isPositive())
     posLits.add(l);
     else
     negLits.add(l);
     }
     */

    /**
     * Adds a literal to this clause.
     *
     * @param literal the literal to add
     */
    public void addLiteral(Literal literal) {
        int nLiterals = literals.size();
        literals.add(literal);

        // questo serve se uso Set perché non ammette oggetti ripetuti
        if (nLiterals < literals.size())
            // è un nuovo letterale quindi lo devo aggiungere anche alle liste
            // dei letterali positivi e negativi
            if (literal.isPositive())
                posLits.add(literal);
            else
                negLits.add(literal);
    }

    public Set<Literal> getLiterals() {
        return literals;
    }

    public List<Literal> getPosiviveLiterals() {
        return posLits;
    }

    public List<Literal> getNegativeLiterals() {
        return negLits;
    }

    @Override
    public String toString() {
        if (string != null)
            return string;

        StringBuilder sb = new StringBuilder();

        for (Literal l : literals)
            sb.append(l.toString()).append(" | ");

        if (!literals.isEmpty())
            sb.replace(sb.length() - 3, sb.length(), "");

        return sb.toString();
    }

    /**
     * Compares clauses using the number of symbols
     *
     * @param c the other clause
     */
    @Override
    public int compareTo(Clause c) {
        int diff = symbolsNumber() - c.symbolsNumber();
        if (diff == 0 && !equals(c))
            return -1; // keep consistency with equals()
        return diff;
    }

    private int symbolsNumber() {
        if (numSymbs != 0)
            return numSymbs;

        int n = 0;
        for (Literal l : literals)
            n += l.symbolsNumber();
        return n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        // DA MIGLIORARE
        if (obj instanceof Clause) {
            Clause other = (Clause) obj;
            if (literals.size() != other.literals.size())
                return false;
            for (Literal l : literals)
                if (!other.literals.contains(l))
                    return false;
            // tutti i letterali sono presenti in other ma
            // non so niente del contrario
            for (Literal l : other.literals)
                if (!this.literals.contains(l))
                    return false;
            // tutti i letterali sono in entrambe le clausole                            
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.literals);
        return hash;
    }

    boolean isEmpty() {
        return literals.isEmpty();
    }

    /**
     * Determines if this clause is a tautology.
     *
     * @return <code>true</code> iff this clause is a tautology
     */
    boolean isTautology() {

        for (Literal l1 : posLits) {
            Atom a1 = l1.getAtom();
            for (Literal l2 : negLits) {
                Atom a2 = l2.getAtom();
                if (a1.equals(a2))
                    return true;
            }
        }

        // no complementary literal found
        return false;
    }

    public List<Literal> getMaximalLiterals(Ordering ord) {
        if (maximalLits != null)
            return maximalLits;

        calculateMaximalLits(ord);
        posMaximalLits = new ArrayList<>();
        negMaximalLits = new ArrayList<>();
        for (Literal l : maximalLits) {
            if (l.isPositive())
                posMaximalLits.add(l);
            else
                negMaximalLits.add(l);
        }
        return maximalLits;
    }
    
    public void calculateMaximalLits(Ordering ord) {
        maximalLits = ord.getMaximalLiterals(this);
    }

    public Set<Clause> getFactors() {
        if (factors == null)
           calculateFactors();
        
        return factors; //=
    }
    
    public Set<Clause> getMaximalFactors() {
        if (maximalFactors == null)
           calculateMaximalFactors();
        
        return maximalFactors; //=
    }

    private void calculateFactors() {
        factors = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution;

        List<Literal> lits = new ArrayList<>();

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            lits.clear();
            if (i == 0)
                // Look at the positive literals
                lits.addAll(posLits);
            else
                // Look at the negative literals
                lits.addAll(negLits);
            /* DA FARE */
            for (int x = 0; x < lits.size(); x++)
                for (int y = x + 1; y < lits.size(); y++) {
                    Literal litX = lits.get(x);
                    Literal litY = lits.get(y);
                    // initializzo la sostituzione
                    //theta.clear();
                    //substitution.getAssignemnts().clear();

                    //Map<Variable, Term> substitution = unify(litX, litY, theta);
                    substitution = InferenceSystem.mgu(litX, litY, true);

                    if (null != substitution)
                        factors.add(this.applySubstitution(substitution));
                }

        }
    }
    
    // prima di chiamarlo devo calcolare i letterali massimali dato un ordinamento
    private void calculateMaximalFactors() {
        maximalFactors = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution;

        List<Literal> lits = new ArrayList<>();

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            lits.clear();
            if (i == 0)
                // Look at the positive literals
                lits.addAll(posMaximalLits);
            else
                // Look at the negative literals
                lits.addAll(negMaximalLits);
            /* DA FARE */
            for (int x = 0; x < lits.size(); x++)
                for (int y = x + 1; y < lits.size(); y++) {
                    Literal litX = lits.get(x);
                    Literal litY = lits.get(y);
                    // initializzo la sostituzione
                    //theta.clear();
                    //substitution.getAssignemnts().clear();

                    //Map<Variable, Term> substitution = unify(litX, litY, theta);
                    substitution = InferenceSystem.mgu(litX, litY, true);

                    if (null != substitution && substitution.isWellFormed())
                        maximalFactors.add(this.applySubstitution(substitution));
                }

        }
    }

    public Set<Clause> orderedResolvents(Clause othC) {
        // DA FARE
        return new LinkedHashSet<>();
    }

    public boolean subsumes(Clause othC) {
        // DA FARE
        return false;
    }

    public Clause applySubstitution(Substitution tau) {
        Set<Literal> lits = new LinkedHashSet<>();
        for (Literal l : literals)
            lits.add(l.applySubstitution(tau));
        if (lits.equals(literals))
            return this;
        return new Clause(lits);
    }
    
    public String getFactorsString() {
        StringBuilder sb = new StringBuilder("factors: ");
        if (factors == null)
            calculateFactors();
        
        boolean flag = true;
        for (Clause c : factors)
            if (flag) {
                flag = false;
                sb.append(c.toString());
            } else {
                sb.append(" ; ").append(c.toString());
            }
        return sb.toString(); 
    }
    
    public String getMaximalFactorsString() {
        StringBuilder sb = new StringBuilder("maximalFactors: ");
        if (maximalFactors == null)
            calculateMaximalFactors();
        
        boolean flag = true;
        for (Clause c : maximalFactors)
            if (flag) {
                flag = false;
                sb.append(c.toString());
            } else {
                sb.append(" ; ").append(c.toString());
            }
        return sb.toString(); 
    }
}
