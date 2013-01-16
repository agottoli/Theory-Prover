package thProver;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ale
 */
public class InferenceSystem {

    public static Clause resolution(Clause c1, Clause c2, int indexNewClause) {
        return null; // DA FARE
    }

    // if mgu == null --> non unificabili
    public static void mgu(Term x, Term y, Substitution sub) {
        if (x.equals(y))
            return;

        if ((x instanceof Function
                && (y instanceof Function
                && !((Function) x).getSymbol().equals(((Function) y).getSymbol()))
                || (x instanceof Function && y instanceof Constant))
                || (x instanceof Constant && y instanceof Function) //                ||
                //                (x instanceof Constant && y instanceof Constant &&
                //                !((Constant) x).getSymbol().equals(((Constant) y).getSymbol()))
                ) {
            // non sono unificabili ed evito di applicare la sostituzione
            sub.kill();
            return;
        }

        Term xSub = x.applySubstitution(sub);
        Term ySub = y.applySubstitution(sub);
        if (xSub instanceof Variable) {
            sub.addAssignment((Variable) x, y);
            return;
        }
        if (ySub instanceof Variable) {
            sub.addAssignment((Variable) y, x);
            return;
        }
        if (x instanceof Function && y instanceof Function
                && ((Function) x).getSymbol().equals(((Function) y).getSymbol())) {
            mgu(((Function) x).getArgs(), ((Function) y).getArgs(), sub);
            return;
        }

        // se arrivo qua non sono unificabili
        sub.kill();
    }

    public static void mgu(List<Term> x, List<Term> y, Substitution sub) {
        if (sub.isKilled())
            return;

        Iterator<Term> itX = x.iterator();
        Iterator<Term> itY = y.iterator();
        while (itX.hasNext()) {
            mgu(itX.next(), itY.next(), sub);
            if (sub.isKilled())
                return;
        }
    }

    public static void mgu(Atom x, Atom y, Substitution sub) {
        if (x.equals(y))
            return;

        if (x.getSymbol().equals(y.getSymbol())) {
            mgu(x.getArgs(), y.getArgs(), sub);
            return;
        }

        sub.kill();
    }

    public static Substitution mgu(Literal x, Literal y, boolean sameSign) {
        if ((x.isPositive() == y.isPositive() && sameSign)
                || (x.isPositive() != y.isPositive() && !sameSign)) {
            Substitution sub = new Substitution();
            mgu(x.getAtom(), y.getAtom(), sub);
            if (!sub.isKilled() && sub.isWellFormed()) 
                // ammetto solo sostituzioni ben formate
                return sub;
        }
        
        return null;
    }
    /*
     // if mgu == null --> non unificabili
     public static Substitution mgu(Term x, Term y) {
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

     public static Substitution mgu(Atom x, Atom y) {
     if (x.equals(y))
     return new Substitution();

     if (x.getSymbol().equals(y.getSymbol())) {
     return mgu(x.getArgs(), y.getArgs());
     }
     // se arrivo qua sono elementi diversi non unificabili
     return null;
     }
    
     public static Substitution mgu(Literal x, Literal y, boolean sameSign) {
     if ((x.isPositive() == y.isPositive() && sameSign)
     || (x.isPositive() != y.isPositive() && !sameSign))
     return mgu(x.getAtom(), y.getAtom());
     return null;
     }

     public static Substitution mgu(List<Term> x, List<Term> y) {
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
     */
}
