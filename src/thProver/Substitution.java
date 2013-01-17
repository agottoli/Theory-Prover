/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ale
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
     * @param varSymbol the variable symbol
     * @param t the term to assign
     */
    public void addAssignment(Variable v, Term t) {
        if (assignments.containsKey(v))
            throw new IllegalArgumentException("Substitution: " + v
                    + " ha già un assegnamento.");

        for (Variable var : assignments.keySet()) {
            Term term = assignments.get(var);
            Term termSub = term.applySubstitution(v, t);
            if (!term.equals(termSub))
                assignments.put(var, termSub);

        }

        assignments.put(v, t);
    }

    public Map<Variable, Term> getAssignments() {
        return assignments;
    }
    
    public void setAssignments(Map<Variable, Term> map) {
        assignments = map;
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

        /* apply tau to all terms in sigma */
        for (Variable var : assignmentsCopy.keySet()) {
            Term t = assignmentsCopy.get(var);
            Term tnuovo = t.applySubstitution(tau);
            /* if after the substitution the term equals his variable delete the assignment */
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

        /* add tau's assignments */
        for (Variable var : tau.assignments.keySet())
            /* ... only if not already in original substitution */
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
    public Substitution copy() {
        Substitution s = new Substitution();
        Map<Variable, Term> m = new LinkedHashMap<>();
        m.putAll(assignments);
        s.setAssignments(m);
        return s;
    }
    
    public boolean isWellFormed() {
        // DA MIGLIORARE
        Substitution copia = this.copy();
        copia.compose(this);
        return copia.equals(this);
    }
}
