package thProver;

import com.google.common.collect.HashMultiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ale
 */
public class Function implements Term {
    
    private String symbol;
    //private List<Term> args;
    private Term[] args;
    
    public Function(String symbol, Term... args) {
        this.symbol = symbol;
        this.args = args;
    }
    
    public Function() {
    }
    
    public void setSymbol(String sym) {
        symbol = sym;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setArgs(Term[] args) {
        this.args = args;
    }
    
    public Term[] getArgs() {
        return args;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(symbol);

        if (args != null && args.length != 0) {
            sb.append('(');

            for (Term t : args) {
                sb.append(t).append(',');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append(')');
        }

        return sb.toString();
    }
    
    @Override
    public int symbolsNumber() {
        int n = 1;
        if (args != null) {
            for (Term a : args) {
                n += a.symbolsNumber();
            }
        }
        return n;
    }
    
    public List<Term> getArgsTupla() {
        List<Term> l = new ArrayList<>();
        for (Term t : args)
            l.add(t);
        return l;
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
            if (!symbol.equals(other.getSymbol()))
                return false;
            Term[] argsO = other.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (!args[i].equals(argsO[i]))
                    return false;
            }
            return true;
        }
        
        return false;
    }

    @Override
    public HashMultiset<Object> getArgsMultiset() {
        HashMultiset<Object> ms = HashMultiset.create();
        for (int i = 0; i < args.length; i++) {
            ms.add(args[i]);
        }
        return ms;
    }

    @Override
    public int getNArgs() {
        return args.length;
    }
}
