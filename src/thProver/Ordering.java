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
 *
 * @author ale
 */
public class Ordering {

    private List<List<String>> prec;
    private int nPrec;
    private boolean kbo;
    // in caso di non inserimento di pesi in kbo e di precedenze
    // usare un'ordinamento standard?
    private boolean useOrdStandard = false;
    private int standardWeight = 1; // peso standard per tutto in kbo
    
    /* solo per Ordinamento ricorsivo a cammini o lessicografico */
    private boolean statusMultiSet; // a cammini
    
    /* solo per KBO */
    HashMap<String, Integer> weightFunction;
    int weightVars;
    // struttura dati che mi conta per ogni variabile qualte occorrenze ci sono
    HashMap<String, Integer> countA;
    HashMap<String, Integer> countB;
    boolean errCountVars;
    
    public Ordering() {
        kbo = false;
        statusMultiSet = false;
        countA = new HashMap<>();
        countB = new HashMap<>();
    }
    
    public void setUseOrdStandard(boolean o) {
        useOrdStandard = o;
    }
    
    public void setPrecedence(List<List<String>> prec, int nPrec) {
        this.prec = prec;
        this.nPrec = nPrec;
    }

    public boolean isGreaterInPrecedence(String a, String b) {
        //////// EXPERIMENTAL //////////
        if (useOrdStandard) {
            if (a.equals("Top"))
                return false;
            if (b.equals("Bottom") && !a.equals("Bottom"))
                return true; // perché a non può essere Top
            if (a.compareTo(b) < 0)
                return true;
            return false;
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
    
    public void setWeightsKBO(HashMap<String, Integer> wF, int wV) {
        weightFunction = wF;
        weightVars = wV;
    }
    
    public void setMultiSetOrdering() {
        kbo = false;
        statusMultiSet = true;
    }
    
    public void setLexicographicOrdering() {
        kbo = false;
        statusMultiSet = false;
    }
    
    public void setKBOrdering() {
        kbo = true;
        statusMultiSet = true;
    }
    

    /**
     * Passo iniziale dell'ordinamento ricorsivo a cammini (o lessicagrafico)
     *
     * @param a Termine o Atomo o Letterale
     * @param b Termine o Atomo o Letterale
     * @return
     */
    public boolean isGreaterMulLex(Object a, Object b) {
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

        } else if (isGreaterInPrecedence(sA, sB)) {
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


    public boolean isGreaterMul(MultiSet a, MultiSet b) {
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


    public int isGreaterLex(List<Object> a, List<Object> b) {
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

    public List<Literal> getMaximalLiterals(Clause clause) {
        Object[] lits = clause.getLiterals().toArray();

        for (int i = 0; i < lits.length; i++)
            if (lits[i] != null)
                for (int j = i + 1; j < lits.length; j++)
                    if (lits[j] != null)
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
    
    public boolean isGreaterKBO(Object a, Object b) {

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
                        (useOrdStandard && standardWeight == 0) 
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
            List<Term> args = ((Atom) t).getArgs();
            for (Term term : args)
                argsWeight += weight(term, isA);
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeight + argsWeight;
            }
            ////////////////////
            return weightFunction.get(((Atom) t).getSymbol()) + argsWeight;
        } else
        if (t instanceof Function) {
            int argsWeight = 0;
            List<Term> args = ((Function) t).getArgs();
            for (Term term : args)
                argsWeight += weight(term, isA);
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeight + argsWeight;
            }
            ////////////////////
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
            /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeight;
            }
            ////////////////////
            return weightVars;
        }
         
        // Costanti :)
        /// EXPERIMENTAL ///
            if (useOrdStandard) {
                return standardWeight;
            }
            ////////////////////
        return weightFunction.get(((Term) t).getSymbol());
     
    }
    
    private boolean checkFnToXKBO(Object a, Variable b) {
        if (a instanceof Atom) {
            return ((Atom) a).getArgs().get(0).equals(b);
        }
        // sicuramente a è una Function
        Term arg = (Term) a;
        do {
            arg = ((Function) arg).getArgs().get(0);
        } while (((Term) a).getSymbol().equals(arg.getSymbol()));
        
        return arg.equals(b);
            
    }
    
    public String getTipeOrdering() {
        if (kbo)
            return "kbo";
        if (statusMultiSet)
            return "mul";
        return "lex";
    }
    
    public boolean isGreater(Object a, Object b) {
        if (kbo)
            return isGreaterKBO(a, b);
        
        return isGreaterMulLex(a, b);
    }
    
}
