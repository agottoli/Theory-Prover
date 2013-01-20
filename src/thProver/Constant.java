package thProver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author ale
 */
public class Constant implements Term {
    
    private String symbol;
    
    public Constant(String symbol) {
        this.symbol = symbol;
    }
/*    
    public void setSymbol(String sym) {
        symbol = sym;
    }
*/   
    public String getSymbol() {
        return symbol;
    }
    
    public Constant copy() {
        return new Constant(symbol);
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
            return symbol.equals(((Constant) obj).symbol);
        }
        
        return false;
    }
/*
    @Override
    public List<Term> getArgsTupla() {
        return null;
    }

    @Override
    public HashMultiset<Object> getArgsMultiset() {
        return null;
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
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    // serve per la composizione di sostituzioni 
    // e nell'mgu (per sostituire con la sostituzione 
    // temporanea prima di cercare ulteriori assegnamenti)
    @Override
    public Term applySubstitution(Substitution tau) {
        return this;
    }
    
    @Override
    public Term applySubstitution(Substitution tau, Map<String, Variable> vars, long time) {
        return this;
    }

    // serve quando aggiungo un assegnamento alla sostituzione per aggiornare
    // i valori a destra (da assegnare)
    @Override
    public Term applySubstitution(Variable var, Term ter) {
        return this;
    }
/*
    @Override
    public Term renameVariables(long num) {
        return this;
    }
*/
}
