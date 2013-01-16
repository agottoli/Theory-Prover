package thProver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ale
 */
public class Function implements Term {

    private String symbol;
    private List<Term> args = new ArrayList();
    // stringa della funzione così la calcolo solo una volta
    private String string;

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
    public String getSymbol() {
        return symbol;
    }
    /*    
     public void setArgs(Term[] args) {
     this.args = args;
     }
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

    public MultiSet getArgsMultiset() {
        return new MultiSet((List<Object>) (List<?>) args);

    }

    public int getNArgs() {
        return args.size();
    }

    @Override
    public Term applySubstitution(Substitution tau) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau));
        if (argsNuovi.equals(args))
            return this;
        return new Function(getSymbol(), argsNuovi);

    }
}
