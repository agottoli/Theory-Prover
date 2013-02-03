/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that implements three type of ordering:
 * - lexicographic
 * - multiset
 * - Knuth-Bendix (kbo)
 * 
 * @author Alessandro Gottoli vr352595
 */
public class Ordering {

    private List<List<String>> prec;
    private int nPrec;
    private boolean kbo;
    // in caso di non inserimento di pesi in kbo e di precedenze
    // usare un'ordinamento standard?
    private boolean useOrdStandard = false;
    private int standardWeight = 1; // peso standard per tutto in kbo
    private int standardWeightAtom = 1; // peso standard kbo
    private int standardWeightFunction = 1; // peso standard kbo
    private int standardWeightVariable = 1; // peso standard kbo
    private int standardWeightConstant = 2; // peso standard kbo
    
    /* solo per Ordinamento ricorsivo a cammini o lessicografico */
    private boolean statusMultiSet; // a cammini
    
    /* solo per KBO */
    HashMap<String, Integer> weightFunction;
    int weightVars;
    /* */
    int weightNoDefinedAtom = 1;
    int weightNoDefinedFunction = 1;
    int weightNoDefinedConstant = 1;
    /* */
    // struttura dati che mi conta per ogni variabile qualte occorrenze ci sono
    HashMap<String, Integer> countA;
    HashMap<String, Integer> countB;
    boolean errCountVars;
    
    /**
     * Construct a new empty ordering 
     * to set up with setPrecedence and setWeights
     */
    public Ordering() {
        kbo = false;
        statusMultiSet = false;
        countA = new HashMap<>();
        countB = new HashMap<>();
    }
    
    /**
     * Set to use the standard ordering implemented if the argument is true.
     * Otherwise use the user defined precedence.
     * 
     * @param o true for use standart, false for use user defined
     */
    public void setUseOrdStandard(boolean o) {
        useOrdStandard = o;
    }
    
    /**
     * Set the precedences defined by the user.
     * 
     * @param prec precedences
     * @param nPrec number of precedences
     */
    public void setPrecedence(List<List<String>> prec, int nPrec) {
        this.prec = prec;
        this.nPrec = nPrec;
    }

    /**
     * Check if the first element symbol is greater in precedences 
     * respect to the second element symbol
     * 
     * @param a first element symbol
     * @param b second element symbol
     * @param objA first element (for standard)
     * @param objB second element (for standard)
     * @return true if the first is greater in precedences respect to the second
     *         false otherwise.
     */
    private boolean isGreaterInPrecedence(String a, String b, Object objA, Object objB) {
        //////// EXPERIMENTAL //////////
        if (useOrdStandard) {
            if (objA instanceof Variable)
                return false; // le variabili sono le ultime
            if (objB instanceof Variable) {// adesso a not var
                //if (objA instanceof Atom &&
                //        (a.equals("*Top*") || a.equals("*Bottom*")) )
                //    return false;
                //return true;
                // non si può dire che è più grande di una variabile
                return false;
            }
            if (objA instanceof Constant) {
                if (objB instanceof Constant)
                    return a.compareTo(b) < 0;
                else if (objB instanceof Function)
                    return false;
                else // Atom (può capitare?)
                    return b.equals("*Top*") || b.equals("*Bottom*");
            }
            if (objA instanceof Function) {
                if (objB instanceof Constant)
                    return true;
                else if (objB instanceof Function)
                    return a.compareTo(b) < 0;
                else // Atom (può capitare)
                    return b.equals("*Top*") || b.equals("*Bottom*");
            }
            // objA sicuramente è un atomo
            if (a.equals("*Top*"))
                return false;
            if (a.equals("*Bottom*")) {
                if (objB instanceof Atom) {
                    if (b.equals("*Top*"))
                        return true;
                    return false;
                }
                return false;
            }
            // objA non è un atomo Top o Bottom
            if (objB instanceof Constant || objB instanceof Function)
                return true;
            // anche objB è un atomo
            if (b.equals("*Bottom*") || b.equals("*Top*"))
                return true; // perché a non può essere Top o Bottom
            // entrambi atomi non Top o Bottom
            return a.compareTo(b) < 0;
                //return true;
            //return false;
        }
        ////////////////////////////////
        
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
    
    /**
     * Set the weight for kbo
     * 
     * @param wF map of the weights of other symbols
     * @param wV weight of the variables
     */
    public void setWeightsKBO(HashMap<String, Integer> wF, int wV) {
        weightFunction = wF;
        weightVars = wV;
    }
    
    /**
     * Set tu use the multiset ordering
     */
    public void setMultiSetOrdering() {
        kbo = false;
        statusMultiSet = true;
    }
    
    /**
     * Set to use the lexicographic ordering
     */
    public void setLexicographicOrdering() {
        kbo = false;
        statusMultiSet = false;
    }
    
    /**
     * Set to use the Knuth-Bendix ordering
     */
    public void setKBOrdering() {
        kbo = true;
        statusMultiSet = true;
    }
    

    /**
     * Passo iniziale dell'ordinamento ricorsivo a cammini (o lessicagrafico).
     *
     * @param a Termine o Atomo o Letterale
     * @param b Termine o Atomo o Letterale
     * @return true if a result greater of b
     */
    private boolean isGreaterMulLex(Object a, Object b) {
        /* NON SI CONFRONTANO CLAUSOLE, ma si comincia dai LETTERALI DI  UNA CLAUSOLA
         if (a instanceof Clause) {
         List<Object> la = (List<Object>)(List<?>) ((Clause) a).getMultiSet();
         List<Object> lb;
         if (b instanceof Clause)
         lb = (List<Object>)(List<?>)((Clause) b).getMultiSet();
         // spero non servano ma li ho messi lo stesso
         else if (b instanceof Literal)
         lb = (List<Object>)(List<?>)((Literal) b).getMultiSet();
         else 
         lb = (List<Object>)(List<?>)((Atom) b).getMultiSet();
         return isGreaterLexL(la, lb) != -1;
         }
         */

        if (a instanceof Literal) { 
            // anche b sarà sicuramente Literal
            // ma mettiamo il controllo che non si sa mai
            
            if (statusMultiSet) {
                MultiSet ma = ((Literal) a).getMultiset();
                MultiSet mb;
                if (b instanceof Literal)
                    mb = ((Literal) b).getMultiset();
                else // è un atomo (e probabilmente si tratta di Top o Bottom
                    mb = ((Atom) b).getMultiset();
                
                return isGreaterMul(ma, mb);
            } else {
                List<Object> la = ((Literal) a).getTupla();
                List<Object> lb;
                if (b instanceof Literal)
                    lb = ((Literal) b).getTupla();
                else // è un atomo (e probabilmente si tratta di Top o Bottom
                    lb = ((Atom) b).getTupla();
                
                int i = isGreaterLex(la, lb);
                if (i == -1)
                    return false;
                /* // ???? quando si estende a letterali si guarda solo 
                 //      lo status e non la condizione aggiuntiva
                 ListIterator<Object> li = lb.listIterator(i + 1);
                 while (li.hasNext())
                 if (!isGreater(a, li.next()))
                 return false;
                 */
                return true;
            }

        }

        //   if (a instanceof Atom) {
        if (a instanceof Atom && b instanceof Term) {
            for (Term argA : ((Atom) a).getArgs())
                if (argA.equals(b) || isGreaterMulLex(argA, b))
                    return true; // caso 1
        } else if (a instanceof Function && b instanceof Term)
            for (Term argA : ((Function) a).getArgs())
                if (argA.equals(b) || isGreaterMulLex(argA, b))
                    return true; // caso 1

        // b può essere Atom, Function, Variable, Constant
        String sA;
        if (a instanceof Atom)
            sA = ((Atom) a).getSymbol();
        else
            sA = ((Term) a).getSymbol();
        String sB;
        if (b instanceof Atom)
            sB = ((Atom) b).getSymbol();
        else
            sB = ((Term) b).getSymbol();

        if (sA.equals(sB)) {
            // caso 3

            
            if (statusMultiSet) {
                MultiSet msA, msB;
                if (a instanceof Atom) {
                    msA = ((Atom) a).getArgsMultiset();
                    msB = ((Atom) b).getArgsMultiset();
                } else if (a instanceof Function) {
                    msA = ((Function) a).getArgsMultiset();
                    msB = ((Function) b).getArgsMultiset();
                } else
                    // si tratta di costanti o variabili (che non hanno argomenti)
                    // identiche quindi non può essere una maggiore
                    return false;

                return isGreaterMul(msA, msB);
            }
            else {
                List<Term> ltA, ltB;
                if (a instanceof Atom) {
                    ltA = ((Atom) a).getArgsTupla();
                    ltB = ((Atom) b).getArgsTupla();
                } else if (a instanceof Function) {
                    ltA = ((Function) a).getArgsTupla();
                    ltB = ((Function) b).getArgsTupla();
                } else
                    // si tratta di costanti o variabili (che non hanno argomenti)
                    // identiche quindi non può essere una maggiore
                    return false;
                               
                //int i = isGreaterLex(ltA, ltB);
                // proviamo a castare per fare un solo metodo
                int i = isGreaterLex((List<Object>) (List<?>) ltA, (List<Object>) (List<?>) ltB);
                if (i == -1)
                    return false;
                if (i + 1 > ltB.size())
                    return true; // a > vuoto :)

                ListIterator<Term> li = ltB.listIterator(i + 1);
                while (li.hasNext()) {
                    Term temp;
                    if (!isGreaterMulLex(a, temp = li.next()))
                        return false;
                }
                return true;
            }

        } else if (isGreaterInPrecedence(sA, sB, a, b)) {
            // caso 2

            List<Term> argsB;
            if (b instanceof Atom)
                argsB = ((Atom) b).getArgs();
            else if (b instanceof Function)
                argsB = ((Function) b).getArgs();
            else
                return true; // non c'è più niente da controllare

            for (Term t : argsB)
                if (!isGreaterMulLex(a, t))
                    return false;
            return true;
        } else
            return false; // simboli incommensurabili
    }

    /**
     * Sottoprocedura per mul.
     * Confronta due multiinsiemi.
     * 
     * @param a multiset a
     * @param b multiset b
     * @return true if a>b, false otherwise.
     */
    private boolean isGreaterMul(MultiSet a, MultiSet b) {
        if (!a.isEmpty() && b.isEmpty()) {
            // regola 1
            return true;
        }
        int count;
        for (Object o : a.getDistintElements()) {
            //if (b.contains(o)) {
            if ((count = b.count(o)) > 0) {
                int countT;
                if (count != 1 && (countT = a.count(o)) < count) {
                    count = countT;
                }
                a.removeElement(o, count); // non crea problemi perché non itero più sul ciclo
                b.removeElement(o, count);
                return isGreaterMul(a, b);
            }
        }
        for (Object oA : a.getDistintElements()) {
            for (Object oB : b.getDistintElements()) {
                if (isGreaterMulLex(oA, oB)) {
                    MultiSet copiaB = b.copy();
                    copiaB.removeElement(oB);
                    if (isGreaterMul(a, copiaB))
                        return true;
                }
            }
        }
        
        // se arrivo qua non ho potuto applicare nessuna regola
        return false; // multiinsiemi incommensurabili
            
    }
    /*
     public int isGreaterLex(List<Term> a, List<Term> b) {
     int i = 0;
     ListIterator<Term> iA = a.listIterator();
     ListIterator<Term> iB = b.listIterator();
     Term tA, tB;
     while (iA.hasNext())
     if (iB.hasNext()) {
     if ((tA = iA.next()).equals((tB = iB.next())))
     i++;
     else if (isGreater(tA, tB))
     // devo controllare che da i+1 
     return i; // t > si+1 ?
     else return -1;
     } else
     return i; // b sottotupla di a (t > si+1 che non esiste?)
     return -1;
     }
     */

    /**
     * Sottoprocedura per lex.
     * Confronta 2 tuple.
     * 
     * @param a tupla a
     * @param b tupla b
     * @return -1 se a non è maggiore di b
     *         indice dell'elemento che mi renda a maggiore di b.
     */
    private int isGreaterLex(List<Object> a, List<Object> b) {
        int i = 0;
        ListIterator<Object> iA = a.listIterator();
        ListIterator<Object> iB = b.listIterator();
        Object tA, tB;
        while (iA.hasNext())
            if (iB.hasNext())
                if ((tA = iA.next()).equals((tB = iB.next())))
                    i++;
                else if (isGreater(tA, tB))
                    // devo controllare che da i+1 
                    return i; // t > si+1 ?
                else
                    return -1;
            else
                return i; // b sottotupla di a (t > si+1 che non esiste?)

        return -1;
    }

    /**
     * Return the list of maximal literals of the given clause.
     * 
     * @param clause clause
     * @return list of maximal literals of clause
     */
    public List<Literal> getMaximalLiterals(Clause clause) {
        Object[] lits = clause.getLiterals().toArray();

        for (int i = 0; i < lits.length; i++)
            if (lits[i] != null)
                for (int j = i + 1; j < lits.length; j++)
                    if (lits[j] != null) {
                        if (isGreater(lits[i], lits[j])) {
                            // DEBUG inizio //
                            //System.out.println(lits[i].toString() + " > " + lits[j].toString()
                            //        + " quindi cancello l2.");
                            // DEBUG fine //
                            lits[j] = null;
                        } else if (isGreater(lits[j], lits[i])) {
                            // DEBUG inizio //
                            //System.out.println(lits[i].toString() + " < " + lits[j].toString()
                            //        + " quindi cancello l1.");
                            // DEBUG fine //
                            lits[i] = null;
                            break; // esce dal while annidato perché ho eliminato 
                            //        l'elemento del while esterno
                        } // DEBUG inizio //
                            //else
                            //System.out.println(lits[i].toString() + " # " + lits[j].toString()
                            //        + " quindi li lascio.");
                            // DEBUG fine //
                    }
        List<Literal> maxLits = new ArrayList<>(lits.length);
        for (int i = 0; i < lits.length; i++) {
            if (lits[i] != null)
                maxLits.add((Literal) lits[i]);
        }
        
        return maxLits;
    }
    
    /**
     * Check if the given literal is maximal in clause (never used)
     * 
     * @param l literal
     * @param c clause
     * @return true if l is maximal in c, false otherwise.
     */
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
    
    /**
     * Controlla se l'oggetto a è maggiore di b nell'ordinamento kbo
     * 
     * @param a oggetto a
     * @param b oggetto b
     * @return true se a è maggiore di b, false altrimenti.
     */
    private boolean isGreaterKBO(Object a, Object b) {

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
        countA.clear();
        countB.clear();
        //try {
            wA = weight(a, true);
            wB = weight(b, false);
        //} catch (NullPointerException npe) {
        //    System.err.print("Ordinamento KBO: la funzione peso non è definita per tutti i simboli delle clausole.\n");
        //    return false;
        //}

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
                    if (a instanceof Function)
                        arity = ((Function) a).getNArgs();
                    else 
                        arity = 0;
                }
                
                if (b instanceof Atom) {
                    symB = ((Atom) b).getSymbol();
                } else {
                    symB = ((Term) b).getSymbol();
                }
                if (arity == 1 
                        && 
                        (
                        (useOrdStandard && standardWeightFunction == 0) 
                        || (weightFunction.get(symA) != null && weightFunction.get(symA) == 0) 
                        ) 
                        && b instanceof Variable
                        && checkFnToXKBO(a, (Variable) b)) {
                    // KBO2a
                    return true;
                } else if (symA.equals(symB)) {
                    // BKO2c
                    List<Object> argsA, argsB;
                    if (a instanceof Atom) {
                        argsA = (List<Object>) (List<?>) ((Atom) a).getArgsTupla();
                        argsB = (List<Object>) (List<?>) ((Atom) b).getArgsTupla();
                    } else if (a instanceof Function) {
                        argsA = (List<Object>) (List<?>) ((Function) a).getArgsTupla();
                        argsB = (List<Object>) (List<?>) ((Function) b).getArgsTupla();
                    } else
                        return false; // stessa variabile o costante

                    return isGreaterLex(argsA, argsB) != -1; // KBO usa Lex
                    
                } else if (isGreaterInPrecedence(symA, symB, a, b)) {
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
    
    /**
     * Sottoprocedura per kbo
     * 
     * @param t oggetto da pesare
     * @param isA true se l'oggetto sta in a (trucco per contare le variabili)
     * @return il peso dell'oggetto passato
     */
    private int weight(Object t, boolean isA) { // throws NullPointerException {
        if (t instanceof Atom) {
            int argsWeight = 0;
            List<Term> args = ((Atom) t).getArgs();
            for (Term term : args)
                argsWeight += weight(term, isA);
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeightAtom + argsWeight;
            }
            ////////////////////
            if (weightFunction.containsKey(((Atom) t).getSymbol()))
                return weightFunction.get(((Atom) t).getSymbol()) + argsWeight;
            else
                return weightNoDefinedAtom + argsWeight;
        } else
        if (t instanceof Function) {
            int argsWeight = 0;
            List<Term> args = ((Function) t).getArgs();
            for (Term term : args)
                argsWeight += weight(term, isA);
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeightFunction + argsWeight;
            }
            ////////////////////
            if (weightFunction.containsKey(((Function) t).getSymbol()))
                return weightFunction.get(((Function) t).getSymbol()) + argsWeight;
            else
                return weightNoDefinedFunction + argsWeight;
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
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeightVariable;
            }
            ////////////////////
            return weightVars;
        }
         
        // Costanti :)
        /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeightConstant;
            }
            ////////////////////
            if (weightFunction.containsKey(((Term) t).getSymbol()))
                return weightFunction.get(((Term) t).getSymbol());
            else
                return weightNoDefinedConstant;
     
    }
    
    /**
     * Sottoprocedura per kbo
     * controlla la condizione KBO2a
     * 
     * @param a funzione
     * @param b argomento
     * @return true se si può applicare la regola kbo2a
     */
    private boolean checkFnToXKBO(Object a, Variable b) {
        if (a instanceof Atom) {
            return ((Atom) a).getArgs().get(0).equals(b);
        }
        // sicuramente a è una Function
        // e ha arietà 1 perché controllata prima di chiamarla
        Term arg = (Term) a;
        do {
            arg = ((Function) arg).getArgs().get(0);
        } while (((Term) a).getSymbol().equals(arg.getSymbol()));
        
        return arg.equals(b);
            
    }
    
    /**
     * ritorna il tipo di ordinamento settato
     * 
     * @return il tipo di ordinamento settato
     */
    public String getTipeOrdering() {
        if (kbo)
            return "kbo";
        if (statusMultiSet)
            return "mul";
        return "lex";
    }
    
    /**
     * Procedure to determine if the object a is greater of the object b.
     * The ordering used has to be choose before call this.
     * 
     * @param a object a
     * @param b object b
     * @return true if a>b, false othewise
     */
    public boolean isGreater(Object a, Object b) {
        if (kbo)
            return isGreaterKBO(a, b);
        
        return isGreaterMulLex(a, b);
    }
    
}
