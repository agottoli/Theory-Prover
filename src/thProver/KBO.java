package thProver;

import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author ale
 */
public class KBO {

    HashMap<String, Integer> weightFunction;
    int weightVars;
    private List<List<String>> prec;
    private int nPrec;
    private boolean statusMultiSet; // inutile in kbo
    // struttura dati che mi conta per ogni variabile qualte occorrenze ci sono
    HashMap<String, Integer> countA;
    HashMap<String, Integer> countB;
    boolean errCountVars;
    
    public KBO() {
        
    }

    /* ****************** COPIA e INCOLLA da Ordering ************************************** */
    public void setPrecedence(List<List<String>> prec, int nPrec, boolean multiSet) {
        this.prec = prec;
        this.nPrec = nPrec;
        statusMultiSet = multiSet;
    }

    public boolean isGreaterInPrecedence(String a, String b) {
        if (prec == null || prec.isEmpty())
            return false;
        for (List<String> ls : prec) {
            int i, j;
            if ((i = ls.indexOf(a)) != -1 && (j = ls.indexOf(b)) != -1
                    && i < j)
                return true;
        }
        return false;
    }
    /* ******************************************************** */

    public void setWeights(HashMap<String, Integer> wF, int wV) {
        weightFunction = wF;
        weightVars = wV;
    }

    public boolean isGreater(Object a, Object b) {

        if (a instanceof Literal) {
            if (((Literal) a).getAtom().equals(((Literal) b).getAtom())
                    && !((Literal) a).isPositive() && ((Literal) b).isPositive())
                return true;

            a = ((Literal) a).getAtom();
            b = ((Literal) b).getAtom();
        }

        // struttura dati che mi conta per ogni variabile qualte occorrenze ci sono
        //HashMap<String, Integer> countA = new HashMap<>();
        //HashMap<String, Integer> countB = new HashMap<>();

        int wA;
        int wB;
        /* init strutture per contare le occorrenze delle variabili */
        errCountVars = false;
        countA = new HashMap<>();
        countB = new HashMap<>();
        try {
            wA = weight(a, true);
            wB = weight(b, false);
        } catch (NullPointerException npe) {
            System.err.print("Ordinamento KBO: la funzione peso non è definita per tutti i simboli delle clausole.\n");
            return false;
        }

        if (wA >= wB) {

            // controllo se il numero di occorenze di ogni variabile è maggiore in a
            if (errCountVars)
                return false;
            
            if (wA == wB) {
                // KBO2
                String symA, symB;
                int arity;
                if (a instanceof Atom) {
                    symA = ((Atom) a).getSymbol();
                    arity = ((Atom) a).getNArgs();
                } else {
                    symA = ((Term) a).getSymbol();
                    arity = ((Term) a).getNArgs();
                }
                if (b instanceof Atom) {
                    symB = ((Atom) b).getSymbol();
                } else {
                    symB = ((Term) b).getSymbol();
                }
                if (weightFunction.get(symA) == 0 && arity == 1) {
                    // KBO2a
                } else if (symA.equals(symB)) {
                    // BKO2c
                    List<Object> argsA, argsB;
                    if (a instanceof Atom) {
                        argsA = (List<Object>) (List<?>) ((Atom) a).getArgsTupla();
                        argsB = (List<Object>) (List<?>) ((Atom) b).getArgsTupla();
                    } else {
                        argsA = (List<Object>) (List<?>) ((Term) a).getArgsTupla();
                        argsB = (List<Object>) (List<?>) ((Term) b).getArgsTupla();
                    }

                    return isGreater(argsA, argsB) != -1;
                    
                } else if (isGreaterInPrecedence(symA, symB)) {
                    // KBO2b
                    return true;
                } else {
                    // non posso applicare nessuna KBO2 + a,b o c
                    return false;
                }
            }

            // KBO1
            return true;
        }

        // se arrivo qua w(a) < w(b)
        return false;
    }

    public int weight(Object t, boolean isA) throws NullPointerException {
        if (t instanceof Atom) {
            int argsWeight = 0;
            Term[] args = ((Atom) t).getArgs();
            for (int i = 0; i < args.length; i++)
                argsWeight += weight(args[i], isA);
            return weightFunction.get(((Atom) t).getSymbol()) + argsWeight;
        } else
        if (t instanceof Function) {
            int argsWeight = 0;
            Term[] args = ((Function) t).getArgs();
            for (int i = 0; i < args.length; i++)
                argsWeight += weight(args[i], isA);
            return weightFunction.get(((Function) t).getSymbol()) + argsWeight;
        } else
        
        if (t instanceof Variable) {
            // faccio subito il controllo del numero di occorrenze delle variabili
            int nOccInA = 0;
            Object nA = countA.get(t.toString());
            if (nA != null)
                nOccInA = ((Integer) nA).intValue();
            if (isA) {
                countA.put(t.toString(), nOccInA + 1);
            } else {
                int nOccInB = 0;
                Object nB = countB.get(t.toString());
                if (nB != null)
                    nOccInB = ((Integer) nB).intValue();
                if (nOccInB < nOccInA) {
                    // anche se aggiungo questa occorrenza va bene
                    countB.put(t.toString(), nOccInB + 1);
                } else {
                    errCountVars = true;
                }    
            }
            return weightVars;
        }
         
        // Costanti :)
        return weightFunction.get(((Term) t).getSymbol());
     
    }
    
    
    
    /* ******************* COPIA e INCOLLA da Ordering ************************* */
    public int isGreater(List<Object> a, List<Object> b) {
        int i = 0;
        ListIterator<Object> iA = a.listIterator();
        ListIterator<Object> iB = b.listIterator();
        Object tA, tB;
        while (iA.hasNext())
            if ((tA = iA.next()).equals((tB = iB.next())))
                i++;
            else if (isGreater((Term) tA, (Term) tB))
                // devo controllare che da i+1 
                return i; // t > si+1 ?
            else
                return -1;

        return -1;
    }
    
        public List<Literal> getMaximalLiterals(Clause clause) {
        Object[] lits = clause.getLiterals().toArray();

        for (int i = 0; i < lits.length; i++)
            if (lits[i] != null)
                for (int j = i + 1; j < lits.length; j++)
                    if (lits[j] != null)
                        if (isGreater(lits[i], lits[j])) {
                            System.out.println(lits[i].toString() + " > " + lits[j].toString()
                                    + " quindi cancello l2.");
                            lits[j] = null;
                        } else if (isGreater(lits[j], lits[i])) {
                            System.out.println(lits[i].toString() + " < " + lits[j].toString()
                                    + " quindi cancello l1.");
                            lits[i] = null;
                            break; // esce dal while annidato perché ho eliminato 
                            //        l'elemento del while esterno
                        } else
                            System.out.println(lits[i].toString() + " # " + lits[j].toString()
                                    + " quindi li lascio.");
        
        List<Literal> maxLits = new ArrayList<>();
        for (int i = 0; i < lits.length; i++) {
            if (lits[i] != null)
                maxLits.add((Literal) lits[i]);
        }
        
        return maxLits;
    }
    
    public boolean isMaxLitInClause(Literal l, Clause c) {
        // l massimale in c se > o # 
        for (Literal l2 : c.getLiterals()) {
            // quindi controllo se uno degli altri è > allora do subito false
            if (!l.equals(l2) && isGreater(l2,l))
                return false;
        }
        // se nessuno degli altri lo batte allora lui è uno dei massimali
        return true;
    }
    /* ******************************************** */
    
}
