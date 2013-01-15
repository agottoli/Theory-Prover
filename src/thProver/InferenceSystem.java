package thProver;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ale
 */
public class InferenceSystem {

    public Clause resolution(Clause c1, Clause c2, int indexNewClause) {
        return null; // DA FARE
    }

    public Substitution mgu(Term x, Term y) {
        if (x.equals(y))
            return new Substitution();
        if (x instanceof Variable)
            return new Substitution((Variable) x, y);
        if (y instanceof Variable)
            return new Substitution((Variable) y, x);
        if (x instanceof Function && y instanceof Function
                && ((Function) x).getSymbol().equals(((Function) y).getSymbol())) {
            return mgu(((Function) x).getArgs(), ((Function) y).getArgs());
        }
        // se arrivo qua sono elementi diversi non unificabili
        return null;
    }

    public Substitution mgu(Atom x, Atom y) {
        if (x.equals(y))
            return new Substitution();

        if (x.getSymbol().equals(y.getSymbol())) {
            return mgu(x.getArgs(), y.getArgs());
        }
        // se arrivo qua sono elementi diversi non unificabili
        return null;
    }

    public Substitution mgu(List<Term> x, List<Term> y) {
        Iterator<Term> itX = x.iterator();
        Iterator<Term> itY = y.iterator();
        Substitution sigma = new Substitution();
        while (itX.hasNext()) {
            sigma.compose(mgu(itX.next().applySubstitution(sigma), itY.next().applySubstitution(sigma)));
            if (sigma.getAssignemnts() == null)
                return null;
        }
        return sigma;
    }
}
