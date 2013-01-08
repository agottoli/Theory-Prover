package thProver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Literal is an Atom with sign.
 */
public class Literal {

    private boolean positive = true;
    private Atom atom;

    /**
     * Constructs a literal.
     *
     * @param positive is this a positive literal?
     * @param symbol the predicate symbol
     * @param args the list of term arguments
     */
    Literal(boolean positive, Atom atom) {
        this.positive = positive;
        this.atom = atom;
    }

    public Literal() {
    }

    public void setPositive(boolean p) {
        positive = p;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }
    
    boolean isPositive() {
        return positive;
    }

    public Atom getAtom() {
        return atom;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!positive) {
            sb.append('~');
        }

        sb.append(atom.toString());

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
       
        Literal other = (Literal) obj;
        if (positive != other.positive || !atom.equals(other.getAtom())) {
            return false;
        }
        return true;

    }
    
    public int symbolsNumber() {
        return atom.symbolsNumber();
    }

}
