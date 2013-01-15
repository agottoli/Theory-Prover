package thProver;

import com.google.common.collect.HashMultiset;
import java.util.List;

/**
 * A term.
 */
public interface Term {
    
    int weight = 1;
    
    //public void setSymbol(String sym);
    public int symbolsNumber();

    public String getSymbol();
    
    public Term copy();
/*
    public List<Term> getArgsTupla();
    public HashMultiset<Object> getArgsMultiset();
    public List<Term> getArgs();

    public int getNArgs();
*/    
    @Override
    public boolean equals(Object o);

}
