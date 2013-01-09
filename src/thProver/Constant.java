package thProver;

import java.util.List;

/**
 *
 * @author ale
 */
public class Constant implements Term {
    
    private String symbol;
    
    public Constant(String symbol) {
        this.symbol = symbol;
    }
    
    public void setSymbol(String sym) {
        symbol = sym;
    }
    
    public String getSymbol() {
        return symbol;
    }
        
    @Override
    public String toString() {
        return symbol;
    }   

    @Override
    public int symbolsNumber() {
        return 1;
    }
    
    @Override
    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    public List<Term> getArgsMultiSet() {
        return null;
    }
}
