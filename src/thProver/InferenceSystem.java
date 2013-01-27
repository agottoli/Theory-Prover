package thProver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ale
 */
public class InferenceSystem {


    public static Set<Clause> orderedResolution(Clause c1, Clause c2, Ordering ord
            , IndexingClauses indexingC) {
        return c1.allTheOrderedResolvents(c2, ord, indexingC);
    }

    public static Set<Clause> resolution(Clause c1, List<Clause> selected
            , IndexingClauses indexingC) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        for (Clause c2 : selected) {
            // resolvents.addAll(c1.allTheResolvents(c2, indexingC));
            // siccome i risolventi devono avere come padri la clausola data
            // e una clausola in usable, non posso usare il metodo
            // allTheResolvent perché mi trova anche i fattori e mi fa la
            // risoluzione anche su di essi quindi uso la semplice risoluzione
            resolvents.addAll(c1.resolvents(c2, indexingC));
            /* DEBUG inizio */
            /*for (Clause cla : resolvents) {
                if (cla.toString().equals("GREEN(b)")) {
                    System.out.println("eccolo: " + c1.toString() + " + " + c2.toString());
                }
            }*/
            /* DEBUG fine */
        }
        return resolvents;
    }

    public static Set<Clause> orderedResolution(Clause c1, List<Clause> selected, Ordering ord
            , IndexingClauses indexingC) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        c1.getMaximalLiterals(ord);
        for (Clause c2 : selected) {
            // resolvents.addAll(c1.allTheOrderedResolvents(c2, ord, indexingC));
            // siccome i risolventi devono avere come padri la clausola data
            // e una clausola in usable, non posso usare il metodo
            // allTheResolvent perché mi trova anche i fattori e mi fa la
            // risoluzione anche su di essi quindi uso la semplice risoluzione
            
            // devo però prima trovare i letterali massimali
            c2.getMaximalLiterals(ord);
            resolvents.addAll(c1.orderedResolvents(c2, indexingC));
            /* DEBUG inizio */
            //for (Clause cla : resolvents) {
            //    if (cla.toString().equals("GREEN(b)")) {
            //        System.out.println("eccolo: " + c1.toString() + " + " + c2.toString());
            //    }
            //}
            /* DEBUG fine */
        }
        return resolvents;
    }
    
    public static Set<Clause> resolutionAll(Clause c1, List<Clause> selected
            , IndexingClauses indexingC) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        for (Clause c2 : selected) {
            resolvents.addAll(c1.allTheResolvents(c2, indexingC));
            // siccome i risolventi devono avere come padri la clausola data
            // e una clausola in usable, non posso usare il metodo
            // allTheResolvent perché mi trova anche i fattori e mi fa la
            // risoluzione anche su di essi quindi uso la semplice risoluzione
            //resolvents.addAll(c1.resolvents(c2, indexingC));
        }
        return resolvents;
    }
    
    public static Set<Clause> orderedResolutionAll(Clause c1, List<Clause> selected, Ordering ord
            , IndexingClauses indexingC) {
        Set<Clause> resolvents = new LinkedHashSet<>();
        //c1.getMaximalLiterals(ord);
        for (Clause c2 : selected) {
             resolvents.addAll(c1.allTheOrderedResolvents(c2, ord, indexingC));
            // siccome i risolventi devono avere come padri la clausola data
            // e una clausola in usable, non posso usare il metodo
            // allTheResolvent perché mi trova anche i fattori e mi fa la
            // risoluzione anche su di essi quindi uso la semplice risoluzione
            
            // devo però prima trovare i letterali massimali
            //c2.getMaximalLiterals(ord);
            //resolvents.addAll(c1.orderedResolvents(c2, indexingC));
        }
        return resolvents;
    }

    public static Set<Clause> factorization(Clause c
            , IndexingClauses indexingC) {
        /* DEBUG inizio */
        /*if (c.toString().equals("~GREEN(x_0) | GREEN(y_0) | ~ON(x_0,y_0)"))
            System.out.println("eccolo: " + c.toString());
        Set<Clause> res = c.getFactors(indexingC);
            for (Clause cla : res) {
                if (cla.toString().equals("GREEN(b)")) {
                    System.out.println("eccolo: " + c.toString());
                }
            }
        return res;*/
        /* DEBUG fine */
        
        // la fattorizzazione non calcola i fattori dei fattori allora
        // lo chiamo con all = false
        return c.getFactors(indexingC, false); 
    }

    public static Set<Clause> orderedFactorization(Clause c, Ordering ord
            , IndexingClauses indexingC) {
        /* DEBUG inizio */
        //Set<Clause> res = c.getMaximalFactors(ord, indexingC);
        //    for (Clause cla : res) {
        //        if (cla.toString().equals("GREEN(b)")) {
        //            System.out.println("eccolo: " + c.toString());
        //        }
        //    }
        //return res;
            /* DEBUG fine */
        return c.getMaximalFactors(ord, indexingC, false);
    }

    public static int tautologyElimination(List<Clause> clauses) {
        int nElim = 0;
        for (Clause c : clauses) {
            if (c.isTautology()) {
                clauses.remove(c);
                nElim++;
            }
        }
        return nElim;
    }

    public static Clause semplificClause(Clause c, Clause sempl
            , IndexingClauses indexingC) {
        return c.semplClaus(sempl, indexingC);
    }

    public static Set<Clause> semplificClause(Clause c, Collection<Clause> sempl
            , IndexingClauses indexingC) {
        Set<Clause> nuove = new LinkedHashSet<>();
        Set<Clause> semplificate = new LinkedHashSet<>();
        for (Clause c2 : sempl) {
            Clause nuova = c.semplClaus(c2, indexingC);
            if (nuova != null) {
                /* DEBUG inizio */
                //System.out.println("contr. INDIETRO");
                //System.out.println(c2 + " semplificata da " + c + " e diventa = " + nuova);
                /* DEBUG fine */
                nuove.add(nuova);
                semplificate.add(c2);
            }
        }
        if (semplificate.size() > 0) {
            sempl.removeAll(semplificate);
        }

        return nuove;

    }

    public static Clause semplificatedClause(Clause c, Collection<Clause> clauses
            , IndexingClauses indexingC) {
        //Set<Clause> nuove = new LinkedHashSet<>();
        Clause semplificata = null;
        //Iterator<Clause> it = clauses.iterator();
        //while (it.hasNext()) {
        for (Clause c1 : clauses) {
            Clause nuova = c1.semplClaus(c, indexingC);
            if (nuova != null) {
                //nuove.add(nuova);
                /* DEBUG inizio */
                //System.out.println("contr. AVANTI");
                //System.out.println(c1 + " semplifica " + c + " e diventa = " + nuova);
                /* DEBUG fine */
                semplificata = nuova;
                c = nuova; // così continua a cercare di semplificarla
                //return nuova;
            }
        }

        return semplificata; //nuove;

    }

    public static boolean subsumedBy(Clause c, Collection<Clause> clauses) {
        // clauses <. c --> cancello c
        //Iterator<Clause> it = clauses.iterator();
        //while (it.hasNext()) {
        for (Clause c1 : clauses) {
            if (c1.subsumes(c) != null) {
                /* DEBUG inizio */
                //System.out.println("contr. AVANTI");
                //System.out.println(c1 + " sussume " + c);
                /* DEBUG fine */
                return true;
            }
        }
        return false;
    }

    public static int subsumes(Clause c, Collection<Clause> clauses) {
        // c <. clauses --> cancello clauses
        // NOTA: modifica clauses ????
        int numClausSuss = 0;
        List<Clause> copy = new ArrayList<>(clauses);
        for (Clause c2 : copy) {
            if (c.subsumes(c2) != null) {
                /* DEBUG inizio */
                //System.out.println("contr. INDIETRO");
                //System.out.println(c2 + " sussunta da " + c);
                /* DEBUG fine */
                numClausSuss++;
                clauses.remove(c2);
            }
        }
        /* DEBUG inizio */
        //System.out.println("Sussunte: " + numClausSuss);
        //try {
        //    System.in.read();
        //} catch (IOException ioe) {    
        //}
        /* DEBUG fine */
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
        
        // NOTA: possono essere diventati = dopo l'assegnamento
        if (xSub.equals(ySub))
            return true;
        
        if (xSub instanceof Variable) {
            try {
                sub.addAssignment((Variable) xSub, ySub);
            } catch (IllegalArgumentException iae) {
                return false;
            }
            return true;
        }
        // NOTA: in caso di trovare l'mgu per la sussunzione non devo guarade 
        //       questo caso 
        if (!forSubsumptionOrSemplification && ySub instanceof Variable) {
            try {
                sub.addAssignment((Variable) ySub, xSub);
            } catch (IllegalArgumentException iae) {
                return false;
            }
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
            //try { 
                
                if (mgu(x.getAtom(), y.getAtom(), sub, forSubsumptionOrSemplification)
                        && sub.isWellFormed()) {
                    // ammetto solo sostituzioni ben formate

                    // devo però controllare anche di non aver assegnato le variabili
                    // di l2...
                    if (forSubsumptionOrSemplification && !sub.isEmpty()) {
                        Set<Variable> keys = sub.getAssignments().keySet();
                        Iterator<Variable> itV = keys.iterator();
                        String sym = itV.next().getSymbol();
                        int index = sym.lastIndexOf('_');
                        /* DEBUG inizio */
                        //System.out.println("sym variabile che non ha _: " + sym);
                        /* DEBUG fine */
                        long daConfrontare = Long.parseLong(sym.substring(index + 1));
                        /* DEBUG inizio */
                        //System.err.print("la sostituzione da controllare è: " + sub.toString());
                        //System.err.println("CONTROLLO PER LA SUSSUNZIONE O SEMPLIFICAZIONE: daConfrontare: " + daConfrontare);
                        /* DEBUG fine */
                        while (itV.hasNext()) {
                            String sym2 = itV.next().getSymbol();
                            int index2 = sym2.lastIndexOf('_');
                            long daConfrontare2 = Long.parseLong(sym2.substring(index2 + 1));
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

            //} catch (IllegalArgumentException ie) {
            //    /*System.out.println("Errore mgu tra:\n\t" + x.toString() + "\ne\n\t" 
            //            + y.toString() + "\n\nsub: " + sub.toString());*/
            //    return false;

            //}
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
