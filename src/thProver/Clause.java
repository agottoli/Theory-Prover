package thProver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A Clause - a set of literals.
 */
public class Clause implements Comparable<Clause> {

    ArrayList<Literal> literals;

    /**
     * Constructs an empty clause.
     */
    public Clause() {
        literals = new ArrayList<>();
    }

    /**
     * Constructs a clause containing the given literals.
     * @param lits the list of literals
     */
    Clause(Literal... lits) {
        literals = new ArrayList<>(Arrays.asList(lits));
        //literals = (ArrayList) Arrays.asList(lits);
    }

    /**
     * Adds a literal to this clause.
     * @param literal the literal to add
     */
    public void addLiteral(Literal literal) {
        literals.add(literal);
    }
    
    public ArrayList<Literal> getLiterals() {
        return literals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Literal l : literals) {
            sb.append(l).append(" | ");
        }

        if (!literals.isEmpty()) {
            sb.replace(sb.length()-3,sb.length(), "");
        }

        return sb.toString();
    }

    /**
     * Compares clauses using the number of symbols
     * @param c the other clause
     */
    @Override
    public int compareTo(Clause c) {
        int diff = symbolsNumber() - c.symbolsNumber();
        if (diff == 0 && !equals(c)) {
            return -1;  // keep consistency with equals()
        }
        return diff;
    }
    
    private int symbolsNumber() {
        int n = 0;
        for (Literal l : literals) {
            n += l.symbolsNumber();
        }
        return n;
    }



    @Override
    public boolean equals(Object obj) {
        Clause other = (Clause) obj;
        if (literals.size() != other.literals.size()) {
            return false;
        }
        for (Literal l : literals) {
            if (!other.literals.contains(l)) {
                return false;
            }
        }

        return true;
    }

    boolean isEmpty() {
        return literals.isEmpty();
    }

    public List<Literal> getMultiSet() {
        return literals;
    }
}
