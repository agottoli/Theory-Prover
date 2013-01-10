/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
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

        if (a instanceof Literal) { // anche b sarà sicuramente Literal
            List<Object> la = ((Literal) a).getMultiSet();
            List<Object> lb;
            if (b instanceof Literal)
                lb = ((Literal) b).getMultiSet();
            else // è un atomo (e probabilmente si tratta di Top o Bottom
                lb = ((Atom) b).getMultiSet();
            
            if (statusMultiSet)
                return isGreaterMulL(la, lb);
            else {
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
        } else if (a instanceof Function && b instanceof Term) {
            for (Term argA : ((Function) a).getArgs())
                if (argA.equals((Term) b) || isGreater(argA, b))
                    return true; // caso 1
        }

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

            List<Term> ltA, ltB;
            if (a instanceof Atom) {
                ltA = ((Atom) a).getArgsMultiSet();
                ltB = ((Atom) b).getArgsMultiSet();
            } else if (a instanceof Function) {
                ltA = ((Term) a).getArgsMultiSet();
                ltB = ((Term) b).getArgsMultiSet();
            } else
                // si tratta di costanti o variabili
                // identiche quindi non può essere una maggiore
                return false;

            if (statusMultiSet)
                return isGreaterMul(ltA, ltB);
            else {
                //int i = isGreaterLex(ltA, ltB);
                // proviamo a castare per fare un solo metodo
                int i = isGreaterLex((List<Object>)(List<?>) ltA, (List<Object>)(List<?>) ltB);
                if (i == -1)
                    return false;
                if (i+1 > ltB.size()) return true; // a > vuoto :)
                
                ListIterator<Term> li = ltB.listIterator(i+1);
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

    public boolean isGreater(List<Object> a, List<Object> b) {
        return true;
    }

    public boolean isGreaterMul(List<Term> a, List<Term> b) {
        return true;
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
    public boolean isGreaterMulL(List<Object> a, List<Object> b) {
        return true;
    }
    
    public int isGreaterLex(List<Object> a, List<Object> b) {
        int i = 0;
        ListIterator<Object> iA = a.listIterator();
        ListIterator<Object> iB = b.listIterator();
        Object tA, tB;
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
    
    public List<Literal> getMaximalLiterals(Clause clause) {
        ListIterator<Literal> li1 = clause.getLiterals().listIterator();
        return null; // DA FARE
    }
    
}
