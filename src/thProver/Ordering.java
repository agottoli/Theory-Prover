/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ale
 */
public class Ordering {

    private List<List<String>> prec;
    private int nPrec;
    private boolean statusMultiSet;

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

    /**
     * Passo iniziale dell'ordinamento ricorsivo a cammini (o lessicagrafico)
     *
     * @param a Termine o Atomo o Letterale
     * @param b Termine o Atomo o Letterale
     * @return
     */
    public boolean isGreater(Object a, Object b) {
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
                Multiset<Object> ma = ((Literal) a).getMultiset();
                Multiset<Object> mb;
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
                if (argA.equals((Term) b) || isGreater(argA, b))
                    return true; // caso 1
        } else if (a instanceof Function && b instanceof Term)
            for (Term argA : ((Function) a).getArgs())
                if (argA.equals((Term) b) || isGreater(argA, b))
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
                Multiset<Object> msA, msB;
                if (a instanceof Atom) {
                    msA = ((Atom) a).getArgsMultiset();
                    msB = ((Atom) b).getArgsMultiset();
                } else if (a instanceof Function) {
                    msA = ((Term) a).getArgsMultiset();
                    msB = ((Term) b).getArgsMultiset();
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
                    ltA = ((Term) a).getArgsTupla();
                    ltB = ((Term) b).getArgsTupla();
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
                    if (!isGreater(a, temp = li.next()))
                        return false;
                }
                return true;
            }

        } else if (isGreaterInPrecedence(sA, sB)) {
            // caso 2

            Term[] argsB;
            if (b instanceof Atom)
                argsB = ((Atom) b).getArgs();
            else if (b instanceof Function)
                argsB = ((Function) b).getArgs();
            else
                return true; // non c'è più niente da controllare

            for (Term t : argsB)
                if (!isGreater(a, t))
                    return false;
            return true;
        } else
            return false; // simboli incommensurabili
    }


    public boolean isGreaterMul(Multiset<Object> a, Multiset<Object> b) {
        if (!a.isEmpty() && b.isEmpty()) {
            // regola 1
            return true;
        }
        for (Object o : a) {
            if (b.contains(o)) {
                a.remove(o);
                b.remove(o);
                return isGreaterMul(a, b);
            }
        }
        for (Object oA : a) {
            for (Object oB : b) {
                if (isGreater(oA, oB)) {
                    HashMultiset<Object> copiaB = HashMultiset.create();
                    for (Object c : b)
                        copiaB.add(c);
                    copiaB.remove(oB);
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
}
