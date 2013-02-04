package thProver;

import java.util.Map;

/**
 * A term.
 */
public interface Term {
    
    int weight = 1;
    
    //public void setSymbol(String sym);
    /**
     * Return the number of symbols.
     * 
     * @return number of symbols
     */
    public int symbolsNumber();
    
    /**
     * Set the given symbol to the term.
     * 
     * @param sym symbol
     */
    public void setSymbol(String sym);
    /**
     * Return the symbol.
     * 
     * @return predicate
     */
    public String getSymbol();
    
    /**
     * Return a deep copy
     * 
     * @return a copy
     */
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
    
    /**
     * Constructs a new term from the application of the given substitution 
     * to this term.
     * Need for substitutions composition.
     * 
     * @param tau the substitution
     * @return the new term if the substitution modify the term
     *         this if the substitution has no effects.
     */
    // serve per la composizione di sostituzioni 
    // e nell'mgu (per sostituire con la sostituzione 
    // temporanea prima di cercare ulteriori assegnamenti)
    public Term applySubstitution(Substitution tau);
    
     /**
     * Constructs a new term from the application of the given substitution 
     * to this term.
     * (Note: apply ' to distinguish variables with equals name 
     * (and different index)
     * example: in case of x_3 and x_4 --> x_newIndex and x'_newIndex
     * 
     * @param tau substitution
     * @param vars map of variables and new name given in the results
     * @param time index of the new clause
     * @return the new term if the substitution modify the term
     *         this if the substitution has no effects.
     */
    public Term applySubstitution(Substitution tau, Map<String, Variable> vars, long time);
     
    /**
     * Constructs a new term from the application of the given substitution 
     * to this term. (Replace the given variable with the given term)
     * Need for substitutions for update the terms to assign when a new entry
     * in Substitution is added.
     * 
     * @param var variable
     * @param ter term to assign
     * @return the new term if the substitution modify the term
     *         this if the substitution has no effects.
     */
    // serve quando aggiungo un assegnamento alla sostituzione per aggiornare
    // i valori a destra (da assegnare)
    Term applySubstitution(Variable var, Term ter);
    
    //public Term renameVariables(long num);

}
