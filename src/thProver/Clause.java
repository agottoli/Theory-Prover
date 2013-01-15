package thProver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Clause - a set of literals.
 */
public class Clause implements Comparable<Clause> {

    // NOTA : meglio mettere Set<Literal> perché non ci interessa l'ordine
    List<Literal> literals; // perché non interessa l'ordine e se ho due letterali uguali me ne tiene solo uno
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
    // lista dei letterali massimali
    private List<Literal> maximalLits;

    /**
     * Constructs an empty clause.
     */
    public Clause() {
        literals = new ArrayList<>();
        posLits = new ArrayList<>();
        negLits = new ArrayList<>();
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

    public List<Literal> getLiterals() {
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
        
        return ord.getMaximalLiterals(this);
    }

    public Set<Clause> getFactors() {
        if (factors == null)
            calculateFactors();
        return factors;
    }

    private void calculateFactors() {
        factors = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        Map<Variable, Term> theta = new HashMap<Variable, Term>();
        List<Literal> lits = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            lits.clear();
            if (i == 0)
                // Look at the positive literals
                lits.addAll(posLits);
            else
                // Look at the negative literals
                lits.addAll(negLits);
            /* DA FARE 
             for (int x = 0; x < lits.size(); x++)
             for (int y = x + 1; y < lits.size(); y++) {
             Atom atomX = lits.get(x);
             Atom atomY = lits.get(y);

             theta.clear();
                    
             Map<Variable, Term> substitution = unify(atomX, atomY, theta);
                    
             if (null != substitution) {
             List<Literal> posLits = new ArrayList<Literal>();
             List<Literal> negLits = new ArrayList<Literal>();
             if (i == 0)
             posLits.add(_substVisitor.subst(substitution, litX));
             else
             negLits.add(_substVisitor.subst(substitution, litX));
             for (Literal pl : positiveLiterals) {
             if (pl == litX || pl == litY)
             continue;
             posLits.add(_substVisitor.subst(substitution, pl));
             }
             for (Literal nl : negativeLiterals) {
             if (nl == litX || nl == litY)
             continue;
             negLits.add(_substVisitor.subst(substitution, nl));
             }
             // Ensure the non trivial factor is standardized apart
             Map<Variable, Term> renameSubst = _standardizeApart
             .standardizeApart(posLits, negLits,
             _saIndexical);
             Clause c = new Clause(posLits, negLits);
             c.setProofStep(new ProofStepClauseFactor(c, this, litX,
             litY, substitution, renameSubst));
             if (isImmutable())
             c.setImmutable();
             if (!isStandardizedApartCheckRequired())
             c.setStandardizedApartCheckNotRequired();
             if (null == parentFactors) {
             c.calculateFactors(nonTrivialFactors);
             nonTrivialFactors.addAll(c.getFactors());
             } else
             if (!parentFactors.contains(c)) {
             c.calculateFactors(nonTrivialFactors);
             nonTrivialFactors.addAll(c.getFactors());
             }
             }
             }
             */
        }
    }
    
    public Set<Clause> orderedResolvents(Clause othC) {
        // DA FARE
        return null;
    }
    
    public boolean subsumes(Clause othC) {
        // DA FARE
        return false;
    }
}
