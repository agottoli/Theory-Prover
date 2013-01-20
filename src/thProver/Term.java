package thProver;

import java.util.Map;

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
    
    @Override
    public int hashCode();
    
    // serve per la composizione di sostituzioni 
    // e nell'mgu (per sostituire con la sostituzione 
    // temporanea prima di cercare ulteriori assegnamenti)
    public Term applySubstitution(Substitution tau);
    
    public Term applySubstitution(Substitution tau, Map<String, Variable> vars, long time);
     
    // serve quando aggiungo un assegnamento alla sostituzione per aggiornare
    // i valori a destra (da assegnare)
    Term applySubstitution(Variable var, Term ter);
    
    //public Term renameVariables(long num);

}
