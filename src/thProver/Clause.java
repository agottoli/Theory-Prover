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

    List<Literal> literals;
    // per velocizzare le operazioni mi divido i letterali positivi dai negativi
    // NOTA: tengo sono gli atomi, visto che so il segno
    List<Atom> posLits;
    List<Atom> negLits;
    // mi salvo tutti i fattori di una clausola per non doverli rigenerare
    // ad ogni risoluzione
    Set<Clause> factors;

    /**
     * Constructs an empty clause.
     */
    public Clause() {
        literals = new ArrayList<>();
        posLits = new ArrayList<>();
        negLits = new ArrayList<>();
    }

    /**
     * Constructs a clause containing the given literals.
     *
     * @param lits the list of literals
     */
    Clause(Literal... lits) {
        literals = new ArrayList<>(Arrays.asList(lits));
        posLits = new ArrayList<>();
        negLits = new ArrayList<>();
        for (Literal l : lits)
            if (l.isPositive())
                posLits.add(l.getAtom());
            else
                negLits.add(l.getAtom());
    }

    /**
     * Adds a literal to this clause.
     *
     * @param literal the literal to add
     */
    public void addLiteral(Literal literal) {
        literals.add(literal);
        if (literal.isPositive())
            posLits.add(literal.getAtom());
        else
            negLits.add(literal.getAtom());
    }

    public List<Literal> getLiterals() {
        return literals;
    }

    public List<Atom> getPosiviveLiterals() {
        return posLits;
    }

    public List<Atom> getNegativeLiterals() {
        return negLits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Literal l : literals)
            sb.append(l).append(" | ");

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
        int n = 0;
        for (Literal l : literals)
            n += l.symbolsNumber();
        return n;
    }

    @Override
    public boolean equals(Object obj) {
        Clause other = (Clause) obj;
        if (literals.size() != other.literals.size())
            return false;
        for (Literal l : literals)
            if (!other.literals.contains(l))
                return false;

        return true;
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
        /*if (posLits.size() <= negLits.size()) {*/
        // try for each positive literal...
        for (Atom a : posLits)
            if (negLits.contains(a))
                return true;
        /*} else {
         // try for each negative literal
         for (Atom a : negLits) {
         if (posLits.contains(a))
         return true;
         }*/

        // no complementary literal found
        return false;
    }

    public Set<Clause> getFactors() {
        if (factors == null)
            calculateFactors();
        return factors;
    }

    private void calculateFactors() {
        factors = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet
 
        Map<Variable, Term> theta = new HashMap<Variable, Term>();
        List<Atom> lits = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            lits.clear();
            if (i == 0)
                // Look at the positive literals
                lits.addAll(posLits);
            else
                // Look at the negative literals
                lits.addAll(negLits);
            
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
        }
    }
}
