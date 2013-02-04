package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class implementing a substitution Varialble <-- Term
 * 
 * @author Alessandro Gottoli vr352595
 */
public class Substitution {

    private Map<Variable, Term> assignments;

    /**
     * Constructs an empty substitution.
     */
    public Substitution() {
        assignments = new LinkedHashMap<>();
    }

    /**
     * Constructs a substitution containing the given assignment.
     *
     * @param varSymbol the variable symbol
     * @param t the term to assign
     */
    Substitution(Variable var, Term t) {
        this();
        assignments.put(var, t);
    }

    /**
     * Adds an assignment to this substitution.
     *
     * @param v the variable
     * @param t the term to assign
     * @exception IllegalArgumentException if v is in keyset --> bad substitution,
     *                           or if v appears in t --> substitution non well formed
     */
    public void addAssignment(Variable v, Term t) throws IllegalArgumentException {
        /*if (v.equals(t)) {
            // sono diventati uguali dopo l'assegnamento
            return;
        }*/
        if (assignments.containsKey(v)) {
            //System.out.println();
            throw new IllegalArgumentException("Substitution: " + v
                    + " ha già un assegnamento.");
        }
        
        if (checkVarInTerm(v, t)) {
            throw new IllegalArgumentException("Substitution: " + v
                    + " appare in " + t);
        }

        for (Variable var : assignments.keySet()) {
            Term term = assignments.get(var);
            Term termSub = term.applySubstitution(v, t);
            if (!term.equals(termSub))
                assignments.put(var, termSub);

        }

        assignments.put(v, t);
    }
    
    /**
     * Check if the variable occours in term.
     * 
     * @param v variable
     * @param t term
     * @return true is occours, false otherwise.
     */
    private boolean checkVarInTerm(Variable v, Term t) {
        if (t instanceof Function) {
            for (Term arg : ((Function) t).getArgs()) {
                if (checkVarInTerm(v, arg))
                    return true;
            }
        } else if (v.equals(t))
            return true;
        
        return false;
    }

    /**
     * Return the Map of the assignments.
     * 
     * @return the assignemnts
     */
    public Map<Variable, Term> getAssignments() {
        return assignments;
    }
    
    /**
     * Set the Map assignment.
     * 
     * @param map assignment to set
     */
    public void setAssignments(Map<Variable, Term> map) {
        assignments = map;
    }
    
    /**
     * Check if the substitution is empty.
     * 
     * @return true iff the substitution is empty
     */
    public boolean isEmpty() {
        return assignments.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;

        if (o instanceof Substitution)
            return assignments.equals(((Substitution) o).assignments);

        return false;
    }

    /**
     * Composes this substitution with the given one.
     *
     * @param tau a substitution
     */
    void compose(Substitution tau) {
        if (tau.assignments.isEmpty())
            return;
        if (assignments.isEmpty()) {
            assignments = tau.assignments;
            return;
        }


        // devo copiare perché se cancello una entry
        // succede la ConcurrentModificationException
        Map<Variable, Term> assignmentsCopy = new LinkedHashMap<>();
        assignmentsCopy.putAll(assignments);

        // apply tau to all terms in sigma * /
        for (Variable var : assignmentsCopy.keySet()) {
            Term t = assignmentsCopy.get(var);
            Term tnuovo = t.applySubstitution(tau);
            // if after the substitution the term equals his variable delete the assignment * /
            if (tnuovo.equals(var)) {
                // sicuramente è una variabile e della forma x <- x
                assignments.remove(var);
                continue;
            }
            if (t.equals(tnuovo)) // la sostituzione non ha avuto effetto
                continue;
            else
                // aggiorno il valore
                assignments.put(var, tnuovo);
        }

        // add tau's assignments * /
        for (Variable var : tau.assignments.keySet())
            // ... only if not already in original substitution * /
            if (!assignmentsCopy.containsKey(var))
                assignments.put(var, tau.assignments.get(var));
    }
    
    
    /*
     // qua creo nuovi termini e non guardo se già ci sono
     // quindi posso introdurre termini doppi
     public Term applySubstitution(Term t, Substitution tau) {
     if (t instanceof Function) {
     List<Term> args = ((Function) t).getArgs();
     List<Term> argsNuovi = new ArrayList<>();
     / *for (int i = 0; i < args.length; i++)
     argsNuovi[i] = applySubstitution(args[i], tau);* /
     for (Term te : args)
     argsNuovi.add(applySubstitution(te, tau));
     return new Function(t.getSymbol(), argsNuovi);
     }
     if (t instanceof Variable) {
     Term term = tau.getTerm((Variable) t);
     if (term != null)
     return term;
     } 

     return t;
     }
    
     public Atom applySubstitution(Atom a, Substitution tau) {
     List<Term> args = a.getArgs();
     List<Term> argsNuovi = new ArrayList<>();
     / *for (int i = 0; i < args.length; i++)
     argsNuovi[i] = applySubstitution(args[i], tau);* /
     for (Term te : args)
     argsNuovi.add(applySubstitution(te, tau));
     return new Atom(a.getSymbol(), argsNuovi);
     }
     */

    /**
     * Return the term associated the given variable.
     * 
     * @param var variable
     * @return term associated, null if no term associated
     */
    public Term getTerm(Variable var) {
        return assignments.get(var);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");

        for (Variable var : assignments.keySet())
            sb.append(var.toString()).append(" <- ")
                    .append(assignments.get(var).toString()).append(", ");

        if (!assignments.isEmpty())
            sb.deleteCharAt(sb.length() - 2);
        sb.append("}");

        return sb.toString();
    }
    
    /**
     * Clean the assignment of the substitution
     * after this call a call to isEmpty() return true
     * (rende la sostituzione vuota)
     */
    public void clear() {
        assignments.clear();
    }
/*
    public void kill() {
        assignments = null;
    }
    
    public boolean isKilled() {
        return assignments == null;
    }
*/    
    /**
     * Deep copy.
     * 
     * @return a copy of this substitution
     */
    public Substitution copy() {
        Substitution s = new Substitution();
        Map<Variable, Term> m = new LinkedHashMap<>();
        m.putAll(assignments);
        s.setAssignments(m);
        return s;
    }
    
    /**
     * Check if this substitution is well formed.
     * 
     * @return true iff is well formed
     */
    public boolean isWellFormed() {
        // DA MIGLIORARE ma giusta di sicuro
        /*Substitution copia = this.copy();
        copia.compose(this);
        return copia.equals(this);*/
        
        // dovrei fare Dom(sustituzione) intersezione Ran(sostituzione) = vuoto
        Collection<Term> val = assignments.values();
        for (Term t : val) {
            if (!isWellFormed2(t))
                return false;
        }
        
        // nessun conflitto trovato
        return true;
    }
    
    private boolean isWellFormed2(Term term) {
        if (term instanceof Constant)
            return true;
        if (term instanceof Variable) {
            return !(assignments.containsKey((Variable) term));
        }
        // è per forza una funzione
        for (Term arg : ((Function) term).getArgs()) {
            if (!isWellFormed2(arg))
                return false;
        }
        // nessun conflitto
        return true;
    }
    
    /**
     * Rename the variable symbols with a new index
     * 
     * @param time new index
     * @return a new map with the variables renamed
     */
    public Map<String, Variable> renameVariables(long time) {
        /*Map<Variable, Term> temp = assignments;
        assignments = new LinkedHashMap<>(temp.size());
        for (Variable key : temp.keySet()) {
            Term el = temp.get(key).renameVariables(time);
            assignments.put(key, el);
        }*/
        
        // EXPERIMENTAL PER RISOLVERE
        // x_0 <- y_1 ; x_1 <- g(y_0) ==> x_0 <- y_time ; x_1 <- g(y_time)
        Map<Variable, Term> temp = assignments;
        assignments = new LinkedHashMap<>(temp.size());
        Map<String, Variable> vars = new LinkedHashMap<>();
        Term t;
        for (Variable key : temp.keySet()) {
            Term el = temp.get(key);
            if (el instanceof Constant) {
                t = el;
            } else if (el instanceof Function) {
                t = renameVariables((Function) el, vars, time);
            } else {
                // variabile
                t = checkPresence4NewName(vars, (Variable) el, time);
            }
            assignments.put(key, t);
        }
        
        return vars;
    }
    
    private Term renameVariables(Function f, Map<String, Variable> vars, long time) {
        Term t;
        List<Term> nuoviArgs = new ArrayList<>();
        for (Term arg : f.getArgs()) {
            if (arg instanceof Constant) {
                t = arg;
            } else if (arg instanceof Function) {
                t = renameVariables((Function) arg, vars, time);
            } else {
                // è una variabile e devo controllarla
                t = checkPresence4NewName(vars, (Variable) arg, time);
            }
            nuoviArgs.add(t);
        }
        
        return new Function(f.getSymbol(), nuoviArgs);
    }
    
    // serve solo se metto il toString che non stampa gli indici
    private Variable checkPresence4NewName( 
            Map<String, Variable> vars, Variable var, long time) {
        
        String symVar = var.getSymbol();
        int index = symVar.lastIndexOf('_');
        String s = symVar.substring(0, index);
        Variable get;
        boolean flag = true;
        do {
            if ((get = vars.get(s)) == null) {
                // devo aggiungere quella variabile
                vars.put(s, var);
                flag = false;
            } else {
                // c'è già la variabile e allora devo controllare se sono la stessa
                if (get.equals(var)) {
                    // stesso nome e stessa variabile
                    flag = false;
                } else {
                    // stesso nome ma time diverso ==> devo distinguerle
                    // aumento il nome, ma devo essere certo che non ce ne sia 
                    // già un'altra
                    s += "'";
                    // rifaccio il ciclo
                }
            }
        } while (flag);
        /* si può fare senza generarne di uguali? PROVERO' */
        //if (get == null) {
            return new Variable(s + "_" + time);
        //} else {
        //    return get;
        //}
    }
 }
