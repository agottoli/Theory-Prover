package thProver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A Literal is an Atom with sign.
 */
public class Literal {

    private boolean positive = true;
    private Atom atom;
    // stringa
    private String string;

    /**
     * Constructs a literal.
     *
     * @param positive is this a positive literal?
     * @param atom the atom
     */
    public Literal(boolean positive, Atom atom) {
        this.positive = positive;
        this.atom = atom;
    }

    /*// per il parser (inizio)
    public Literal() {       
    }
    
    public void setSign(boolean p) {
        positive = p;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }
    // per il parser (fine)*/
    
    /**
     * Check the sign of the literal.
     * @return true iff the literal is positive
     */
    boolean isPositive() {
        return positive;
    }

    /**
     * Return the literal atom.
     * @return the atom
     */
    public Atom getAtom() {
        return atom;
    }
    
    /**
     * Deep copy of the literal
     * @return a new copy
     */
    public Literal copy() {
        return new Literal(positive, atom.copy());
    }

    @Override
    public String toString() {
        if (string != null)
            return string;
        
        StringBuilder sb = new StringBuilder();

        if (!positive) {
            sb.append('~');
        }

        sb.append(atom.toString());

        return string = sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj)
           return true;
       
       // aggiunta
       if (obj instanceof Literal) {
            Literal other = (Literal) obj;
            return positive == other.positive &&
                    atom.equals(other.getAtom());
       }
       
       return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.positive ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.atom);
        return hash;
    }
    
    protected int symbolsNumber() {
        return atom.symbolsNumber();
    }
    
    /* * 2013-02-05 6:05
     * Return a tuple for the lexicographical ordering
     * 
     * @return tuple
     * /
    public List<Object> getTupla() {
        List<Object> l = new ArrayList<>();
        l.add(this.atom);
        if (isPositive())
            l.add(new Atom("*Top*"));
        else
            l.add(new Atom("*Bottom*"));
        return l;
    }*/

    /**
     * Return a multiset for the multiset ordering
     * 
     * @return multiset
     */
    MultiSet getMultiset() {
        MultiSet ms = new MultiSet(this.atom);
        if (isPositive())
            ms.addElement(new Atom("*Top*"));
        else
            ms.addElement(new Atom("*Bottom*"));
        return ms;
    }
    /*
    public Literal applySubstitution(Substitution tau) {
        Atom a = atom.applySubstitution(tau);
        if (a.equals(atom))
            return this;
        return new Literal(positive, a);
    }*/
    
     /**
     * Constructs a new literal from the application of the given substitution 
     * to this literal.
     * (Note: apply ' to distinguish variables with equals name 
     * (and different index)
     * example: in case of x_3 and x_4 --> x_newIndex and x'_newIndex
     * 
     * @param tau substitution
     * @param vars map of variables and new name given in the results
     * @param time index of the new clause
     * @return the new literal if the substitution modify the literal
     *         this if the substitution has no effects.
     */
    public Literal applySubstitution(Substitution tau, Map<String, Variable> vars, long time) {
        Atom a = atom.applySubstitution(tau, vars, time);
        if (a.equals(atom))
            return this;
        return new Literal(positive, a);
    }
/*    
    public Literal renameVariables(long num) {
        Atom a = atom.renameVariables(num);
        if (a.equals(atom))
            return this;
        return new Literal(positive, a);
    }
*/
}
