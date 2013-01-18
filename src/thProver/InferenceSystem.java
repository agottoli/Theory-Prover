package thProver;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ale
 */
public class InferenceSystem {

    public static Set<Clause> resolution(Clause c1, Clause c2) {
        return c1.allTheResolvents(c2);
    }
    
    public static Set<Clause> orderedResolution(Clause c1, Clause c2, Ordering ord) {
        return c1.allTheOrderedResolvents(c2, ord);
    }
    
    public static Set<Clause> resolution(Clause c1, List<Clause> selected, Ordering ord) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        for (Clause c2: selected) 
            resolvents.addAll(c1.allTheResolvents(c2));
        return resolvents;
    }
    
    public static Set<Clause> orderedResolution(Clause c1, List<Clause> selected, Ordering ord) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        for (Clause c2: selected) 
            resolvents.addAll(c1.allTheOrderedResolvents(c2, ord));
        return resolvents;
    }
    
    public static Set<Clause> factorization(Clause c) {
        return c.getFactors();
    }
    
    public static Set<Clause> orderedFactorization(Clause c, Ordering ord) {
        return c.getMaximalFactors(ord);
    }
    
    public static void tautologyElimination(Clause c) {
        return; // BOOOOOOOOO
    }
    
    public static void tautologyElimination(List<Clause> c) {
        return; // BOOOOOOOOO
    }
    
    public static void semplificClause(Clause c, Clause sempl) {
        // DA FARE
    }
    
    public static void subsumedBy(Clause c, List<Clause> clauses) {
        // DA FARE
    }
    
    public static void subsume(Clause c, List<Clause> clauses) {
        // DA FARE
    }

    // if mgu == null --> non unificabili
    private static boolean mgu(Term x, Term y, Substitution sub, 
            boolean forSubsumptionOrSemplification) {
        if (x.equals(y))
            return true;

        if ((x instanceof Function
                && (y instanceof Function
                && !((Function) x).getSymbol().equals(((Function) y).getSymbol()))
                || (x instanceof Function && y instanceof Constant))
                || (x instanceof Constant && y instanceof Function) //                ||
                //                (x instanceof Constant && y instanceof Constant &&
                //                !((Constant) x).getSymbol().equals(((Constant) y).getSymbol()))
                ) {
            // non sono unificabili ed evito di applicare la sostituzione
            //sub.kill();
            return false;
        }

        Term xSub = x.applySubstitution(sub);
        Term ySub = y.applySubstitution(sub);
        if (xSub instanceof Variable) {
            sub.addAssignment((Variable) xSub, ySub);
            return true;
        }
        // NOTA: in caso di trovare l'mgu per la sussunzione non devo guarade 
        //       questo caso 
        if (!forSubsumptionOrSemplification && ySub instanceof Variable) {
            sub.addAssignment((Variable) ySub, xSub);
            return true;
        }
        if (xSub instanceof Function && ySub instanceof Function
                && ((Function) xSub).getSymbol().equals(((Function) ySub).getSymbol())) {
            return mgu(((Function) xSub).getArgs(), ((Function) ySub).getArgs(), sub,
                    forSubsumptionOrSemplification);
            //return;
        }

        // se arrivo qua non sono unificabili
        return false; //sub.kill();
    }

    public static boolean mgu(List<Term> x, List<Term> y, Substitution sub,
            boolean forSubsumptionOrSemplification) {
        //if (sub.isKilled())
        //    return;

        Iterator<Term> itX = x.iterator();
        Iterator<Term> itY = y.iterator();
        while (itX.hasNext()) {
            //mgu(itX.next(), itY.next(), sub);
            if (!mgu(itX.next(), itY.next(), sub, forSubsumptionOrSemplification))
                return false;
        }
        // tutti i termini sono unificabili
        return true;
    }

    private static boolean mgu(Atom x, Atom y, Substitution sub, 
            boolean forSubsumptionOrSemplification) {
        if (x.equals(y))
            return true;

        if (x.getSymbol().equals(y.getSymbol())) {
            return mgu(x.getArgs(), y.getArgs(), sub, forSubsumptionOrSemplification);
            //return;
        }

        return false; //sub.kill();
    }

    public static boolean mgu(Literal x, Literal y, boolean sameSign, 
            Substitution sub, boolean forSubsumptionOrSemplification) {
        if ((x.isPositive() == y.isPositive() && sameSign)
                || (x.isPositive() != y.isPositive() && !sameSign)) {
            //Substitution sub = new Substitution();
            if (mgu(x.getAtom(), y.getAtom(), sub, forSubsumptionOrSemplification) 
                    && sub.isWellFormed()) 
                // ammetto solo sostituzioni ben formate
                return true;
        }
        
        return false;
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
