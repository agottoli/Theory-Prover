/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

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
    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    public List<Term> getArgsMultiSet() {
        return null;
    }

}
