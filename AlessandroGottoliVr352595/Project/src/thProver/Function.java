package thProver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Function term.
 * 
 * @author Alessandro Gottoli vr352595
 */
public class Function implements Term {

    private String symbol;
    private List<Term> args = new ArrayList();
    // stringa della funzione così la calcolo solo una volta
    private String string;

    /**
     * Constructs a new function with given symbol and arguments.
     * 
     * @param symbol functionsymbol
     * @param args arguments
     */
    public Function(String symbol, List<Term> args) {
        this.symbol = symbol;
        this.args.addAll(args);
    }
    /*       
     public void setSymbol(String sym) {
     symbol = sym;
     }
     */
    
    @Override
    public void setSymbol(String sym) {
        symbol = sym;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
    /*    
     public void setArgs(Term[] args) {
     this.args = args;
     }
     */

    /**
     * Return the arguments list of the function.
     * 
     * @return arguments
     */
    public List<Term> getArgs() {
        return args;
    }

    @Override
    public Function copy() {
        List<Term> copyArgs = new ArrayList<>();
        for (Term t : args)
            copyArgs.add(t.copy());
        return new Function(symbol, copyArgs);
    }

    @Override
    public String toString() {
        if (string != null)
            return string;

        StringBuilder sb = new StringBuilder();

        sb.append(symbol);

        // controllo inutile perché essendo una funzione
        // ha sicuramente almeno un argomento
        //if (args != null && args.length != 0) {
        sb.append('(');

        for (Term t : args)
            sb.append(t.toString()).append(',');

        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        //}

        return string = sb.toString();
    }

    @Override
    public int symbolsNumber() {
        int n = 1;
        if (args != null)
            for (Term a : args)
                n += a.symbolsNumber();
        return n;
    }

    /**
     * Return a tuple for the lexicographical ordering
     * ONLY arguments
     * 
     * @return tuple
     */
    public List<Term> getArgsTupla() {
        List<Term> l = new ArrayList<>();
        /*for (Term t : args)
         l.add(t);*/
        l.addAll(args);
        return l;
        //return Collections.unmodifiableList(args);
    }

    @Override
    public boolean equals(Object obj) {
        /*// siccome durante il parsing costruisco una solo oggetto
         // posso usare i puntatori per vedere se è uguale
         // proprio come fa Object tra l'altro
         return this == obj;*/

        if (this == obj)
            return true;

        // aggiunta
        if (obj instanceof Function) {
            Function other = (Function) obj;
            /*if (!symbol.equals(other.getSymbol()))
             return false;
             Term[] argsO = other.getArgs();
             for (int i = 0; i < args.length; i++) {
             if (!args[i].equals(argsO[i]))
             return false;
             }
             return true;*/
            return this.symbol.equals(other.symbol)
                    && this.args.equals(other.args);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.symbol);
        hash = 83 * hash + Objects.hashCode(this.args);
        return hash;
    }

    /**
     * Return a multiset for the multiset ordering
     * ONLY arguments
     * 
     * @return multiset
     */
    public MultiSet getArgsMultiset() {
        return new MultiSet((List<Object>) (List<?>) args);

    }

    /**
     * Number of arguments (arity)
     * @return number of arguments
     */
    public int getNArgs() {
        return args.size();
    }

    // serve per la composizione di sostituzioni 
    // e nell'mgu (per sostituire con la sostituzione 
    // temporanea prima di cercare ulteriori assegnamenti)
    @Override
    public Term applySubstitution(Substitution tau) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau));
        if (argsNuovi.equals(args))
            return this;
        return new Function(getSymbol(), argsNuovi);

    }
    
    @Override
    public Term applySubstitution(Substitution tau, Map<String, Variable> vars, long time) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau, vars, time));
        if (argsNuovi.equals(args))
            return this;
        return new Function(getSymbol(), argsNuovi);

    }
    
    // serve quando aggiungo un assegnamento alla sostituzione per aggiornare
    // i valori a destra (da assegnare)
    @Override
    public Term applySubstitution(Variable var, Term ter) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(var, ter));
        if (argsNuovi.equals(args))
            return this;
        return new Function(getSymbol(), argsNuovi);

    }
/*
    @Override
    public Term renameVariables(long num) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.renameVariables(num));
        if (argsNuovi.equals(args))
            return this;
        return new Function(getSymbol(), argsNuovi);
    }
*/
}
