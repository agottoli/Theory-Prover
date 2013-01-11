package thProver;

import com.google.common.collect.HashMultiset;
import java.util.List;

/**
 * A term.
 */
public interface Term {
    
    public void setSymbol(String sym);
    public int symbolsNumber();

    public String getSymbol();

    public List<Term> getArgsTupla();
    public HashMultiset<Object> getArgsMultiset();
}
