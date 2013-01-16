package thProver;

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
    
    public Term applySubstitution(Substitution tau);
    
    public Term applySubstitution(Variable var, Term ter);
    
    public Term renameVariables(long num);

}
