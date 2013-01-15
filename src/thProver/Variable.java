/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import com.google.common.collect.HashMultiset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ale
 */
public class Variable implements Term {
    
    private String symbol;
    
    public Variable() {
    }
    
    public Variable(String s) {
        symbol = s;
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
        if (obj instanceof Variable) {
            return symbol.equals((Variable) obj);
        }
        
        return false;
    }
/*
    @Override
    public List<Term> getArgsTupla() {
        return new ArrayList<Term>();
    }

    @Override
    public HashMultiset<Object> getArgsMultiset() {
        return HashMultiset.create();
    }

    @Override
    public int getNArgs() {
        return 0;
    }

    @Override
    public List<Term> getArgs() {
        return null;
    }
*/
    @Override
    public Term copy() {
        return new Variable(symbol);
    }

    @Override
    public Term applySubstitution(Substitution tau) {
        Term term = tau.getTerm(this);
        if (term != null)
            return term;
        
        return this;

    }

}
