package thProver;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param symbol the predicate symbol
     * @param args the list of term arguments
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
    
    boolean isPositive() {
        return positive;
    }

    public Atom getAtom() {
        return atom;
    }
    
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
    
    public int symbolsNumber() {
        return atom.symbolsNumber();
    }
    
    public List<Object> getTupla() {
        List<Object> l = new ArrayList<>();
        l.add(this.atom);
        if (isPositive())
            l.add(new Atom("Top"));
        else
            l.add(new Atom("Bottom"));
        return l;
    }

    Multiset<Object> getMultiset() {
        HashMultiset<Object> ms = HashMultiset.create();
        ms.add(this.atom);
        if (isPositive())
            ms.add(new Atom("Top"));
        else
            ms.add(new Atom("Bottom"));
        return ms;
    }

}
