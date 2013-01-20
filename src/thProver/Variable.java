/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        int i = symbol.lastIndexOf('_');
        String s = symbol.substring(0, i);
        return s;
        //return symbol;
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
            return symbol.equals(((Variable) obj).symbol);
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.symbol);
        return hash;
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

    // serve per la composizione di sostituzioni 
    // e nell'mgu (per sostituire con la sostituzione 
    // temporanea prima di cercare ulteriori assegnamenti)
    @Override
    public Term applySubstitution(Substitution tau) {
        Term term = tau.getTerm(this);
        if (term != null)
            return term;
        
        return this;
    }

    
    @Override
    public Term applySubstitution(Substitution tau, Map<String, Variable> vars, long time) {
        Term term = tau.getTerm(this);
        if (term != null)
            return term;
        
        return checkPresence4NewName(vars, time);
    }

    // serve quando aggiungo un assegnamento alla sostituzione per aggiornare
    // i valori a destra (da assegnare)
    @Override
    public Term applySubstitution(Variable var, Term ter) {
        if (this.equals(var))
            return ter;
        return this;
    }

/*    
    @Override
    public Term renameVariables(long num) {
        int i = symbol.indexOf("_");
        String s = symbol.substring(0, i+1) + num;
        return new Variable(s);
    }
*/    
    private Variable checkPresence4NewName( 
            Map<String, Variable> vars, long time) {
        
        String symVar = this.getSymbol();
        int index = symVar.lastIndexOf('_');
        String s = symVar.substring(0, index);
        Variable get;
        boolean flag = true;
        do {
            if ((get = vars.get(s)) == null) {
                // devo aggiungere quella variabile
                vars.put(s, this);
                flag = false;
            } else {
                // c'è già la variabile e allora devo controllare se sono la stessa
                if (get.equals(this)) {
                    // stesso nome e stessa variabile
                    flag = false;
                } else {
                    // stesso nome ma time diverso ==> devo distinguerle
                    // aumento il nome, ma devo essere certo che non ce ne sia 
                    // già un'altra
                    s += "'";
                    // rifaccio il ciclo
                }
            }
        } while (flag);
        /* si può fare senza generarne di uguali? PROVERO' */
        //if (get == null) {
            return new Variable(s + "_" + time);
        //} else {
        //    return get;
        //}
    }

}
