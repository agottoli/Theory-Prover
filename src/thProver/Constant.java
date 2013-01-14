package thProver;

import com.google.common.collect.HashMultiset;
import java.util.ArrayList;
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
       
        // aggiunta
        if (obj instanceof Constant) {
            return symbol.equals((Constant) obj);
        }
        
        return false;
    }

    @Override
    public List<Term> getArgsTupla() {
        return new ArrayList<Term>();
    }

    @Override
    public HashMultiset<Object> getArgsMultiset() {
        return  HashMultiset.create();
    }

    @Override
    public int getNArgs() {
        return 0;
    }
    
    @Override
    public Term[] getArgs() {
        return null;
    }
        
}
