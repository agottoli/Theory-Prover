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
        for (Clause c2 : selected)
            resolvents.addAll(c1.allTheResolvents(c2));
        return resolvents;
    }

    public static Set<Clause> orderedResolution(Clause c1, List<Clause> selected, Ordering ord) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        for (Clause c2 : selected)
            resolvents.addAll(c1.allTheOrderedResolvents(c2, ord));
        return resolvents;
    }

    public static Set<Clause> factorization(Clause c) {
        return c.getFactors();
    }

    public static Set<Clause> orderedFactorization(Clause c, Ordering ord) {
        return c.getMaximalFactors(ord);
    }

    public static void tautologyElimination(List<Clause> clauses) {
        for (Clause c : clauses) {
            if (c.isTautology())
                clauses.remove(c);
        }
    }

    public static Clause semplificClause(Clause c, Clause sempl) {
        return c.semplClaus(sempl);
    }

    public static Set<Clause> semplificClause(Clause c, List<Clause> sempl) {
        Set<Clause> nuove = new LinkedHashSet<>();
        Set<Clause> semplificate = new LinkedHashSet<>();
        for (Clause c2 : sempl) {
            Clause nuova = c.semplClaus(c2);
            if (nuova != null) {
                nuove.add(nuova);
                semplificate.add(c2);
            }
        }
        if (semplificate.size() > 0) {
            sempl.removeAll(semplificate);
        }

        return nuove;

    }

    public static Clause semplificatedClause(Clause c, List<Clause> sempl) {
        //Set<Clause> nuove = new LinkedHashSet<>();
        for (Clause c1 : sempl) {
            Clause nuova = c1.semplClaus(c);
            if (nuova != null) {
                //nuove.add(nuova);
                return nuova;
            }
        }

        return null; //nuove;

    }

    public static boolean subsumedBy(Clause c, List<Clause> clauses) {
        // clauses <. c --> cancello c
        for (Clause c1 : clauses) {
            if (c1.subsumes(c) != null) {
                return true;
            }
        }
        return false;
    }

    public static int subsumes(Clause c, List<Clause> clauses) {
        // c <. clauses --> cancello clauses
        // NOTA: modifica clauses ????
        int numClausSuss = 0;
        for (Clause c2 : clauses) {
            if (c.subsumes(c2) != null) {
                numClausSuss++;
                clauses.remove(c2);
            }
        }
        return numClausSuss;
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

        // non posso applicare la sostituzione a y se sono 
        // in sussunzione o semplificazione!!!
        Term ySub;
        if (forSubsumptionOrSemplification)
            ySub = y;
        else
            ySub = y.applySubstitution(sub);

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
                    && sub.isWellFormed()) {
                // ammetto solo sostituzioni ben formate

                // devo però controllare anche di non aver assegnato le variabili
                // di l2...
                if (forSubsumptionOrSemplification && !sub.isEmpty()) {
                    Set<Variable> keys = sub.getAssignments().keySet();
                    Iterator<Variable> itV = keys.iterator();
                    String sym = itV.next().getSymbol();
                    int index = sym.indexOf('_');
                    int daConfrontare = Integer.parseInt(sym.substring(index + 1));
                    /* DEBUG inizio */
                    //System.err.print("la sostituzione da controllare è: " + sub.toString());
                    //System.err.println("CONTROLLO PER LA SUSSUNZIONE O SEMPLIFICAZIONE: daConfrontare: " + daConfrontare);
                    /* DEBUG fine */
                    while (itV.hasNext()) {
                        String sym2 = itV.next().getSymbol();
                        int index2 = sym2.indexOf('_');
                        int daConfrontare2 = Integer.parseInt(sym2.substring(index2 + 1));
                        if (daConfrontare2 != daConfrontare) {
                            /* DEBUG inizio */
                            //System.err.println(" NON valida :(");
                            /* DEBUG fine */
                            return false; // non è una sostituzione valida
                        }
                    }
                    /* DEBUG inizio */
                    //System.err.println(" valida :)");
                    /* DEBUG fine */
                }

                return true;
            }
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
