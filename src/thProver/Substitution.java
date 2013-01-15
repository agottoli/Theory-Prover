/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.HashMap;

/**
 *
 * @author ale
 */
public class Substitution {

    private HashMap<String, Term> assignments;

    /**
     * Constructs an empty substitution.
     */
    public Substitution() {
        assignments = new HashMap<String, Term>();
    }

    /**
     * Constructs a substitution containing the given assignment.
     *
     * @param varSymbol the variable symbol
     * @param t the term to assign
     */
    Substitution(String varSymbol, Term t) {
        this();
        addAssignment(varSymbol, t);
    }

    /**
     * Adds an assignment to this substitution.
     *
     * @param varSymbol the variable symbol
     * @param t the term to assign
     */
    final void addAssignment(String varSymbol, Term t) {
        if (assignments.containsKey(varSymbol))
            throw new IllegalArgumentException("Substitution: " + varSymbol
                    + " ha già un assegnamento.");

        assignments.put(varSymbol, t);
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
        HashMap<String, Term> assignmentsCopy = assignments; // new HashMap<String, Term>();
        //assignmentsCopy.putAll(assignments);

        /* apply tau to all terms in sigma */
        for (String varSymbol : assignmentsCopy.keySet()) {
            Term t = assignmentsCopy.get(varSymbol);
            Term tnuovo = applySubstitution(t, tau);
            /* if after the substitution the term equals his variable delete the assignment */
            if (t.equals(tnuovo))
                continue;
            if (t.getSymbol().equals(varSymbol))
                // sicuramente è una variabile e della forma x <- x
                assignments.remove(varSymbol);
            else
                // aggiorno il valore
                assignments.put(varSymbol, tnuovo);
        }

        /* add tau's assignments */
        for (String varSymbol : tau.assignments.keySet())
            /* ... only if not already in original substitution */
            if (!assignmentsCopy.containsKey(varSymbol))
                assignments.put(varSymbol, tau.assignments.get(varSymbol));
    }

    // qua creo nuovi termini e non guardo se già ci sono
    // quindi posso introdurre termini doppi
    public Term applySubstitution(Term t, Substitution tau) {
        if (t instanceof Function) {
            Term[] args = t.getArgs();
            Term[] argsNuovi = new Term[args.length];
            for (int i = 0; i < args.length; i++)
                argsNuovi[i] = applySubstitution(args[i], tau);
            return new Function(t.getSymbol(), argsNuovi);
        }
        //if (t instanceof Variable) {
        Term term = tau.getTerm(t.getSymbol());
        if (term != null)
            return term;
        //} 

        return t;
    }

    public Term getTerm(String varSymbol) {
        return assignments.get(varSymbol);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");

        for (String varSymbol : assignments.keySet())
            sb.append(varSymbol).append(" <- ").append(assignments.get(varSymbol)).append(", ");
        /*
         if (!assignments.isEmpty()) {
         sb.deleteCharAt(sb.length() - 2);
         }
         */
        sb.append(" }");

        return sb.toString();
    }
}
