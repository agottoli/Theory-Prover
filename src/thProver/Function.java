package thProver;

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
    
}
