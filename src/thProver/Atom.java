/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

/**
 *
 * @author ale
 */
public class Atom {
    
    private String symbol; // Predicate
    private Term[] args;
    
    public Atom(String symbol, Term... args) {
        this.symbol = symbol;
        this.args = args;
    }
    
    public Atom() {
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public void setArgs(Term... args) {
        this.args = args;
    }
    
    public String getSymbol() {
        return symbol;
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
    public boolean equals(Object obj) {
       
        Atom other = (Atom) obj;
        if (symbol != other.symbol) {
            return false;
        }
        
        boolean eq = true;
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                eq = eq && args[i].equals(other.args[i]);
            }
        }
        return eq;

    }

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
