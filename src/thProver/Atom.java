/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Predicate with eventually terms as arguments
 * 
 * @author Alessandro Gottoli vr352595
 */
public class Atom {
    
    private String symbol; // Predicate
    private List<Term> args;
    private int weight = 1;
    // mi salvo la stringa
    private String string;
    // e pure il numero dei simboli
    private int symsNum = 0;
    
    /**
     * Constructs a propositional atom, with given predicate.
     * 
     * @param symbol predicate
     */
    public Atom(String symbol) {
        this.symbol = symbol;
        args = new ArrayList<>();
    }
    
    /**
     * Constructs an atom with given predicate and arguments
     * 
     * @param symbol predicate
     * @param args arguments
     */
    public Atom(String symbol, List<Term> args) {
        this.symbol = symbol;
        this.args = args;
    }
    
    /**
     * Constructs an atom with given predicate and arguments
     * and set a weight.
     * 
     * @param symbol predicate
     * @param args arguments
     * @param weight weight
     */
    public Atom(String symbol, List<Term> args, int weight) {
        this.symbol = symbol;
        this.args = args;
        this.weight = weight;
    }
    
    /*// per il parser (inizio)
    public Atom() {
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public void setArgs(Term... args) {
        this.args = new ArrayList<>();
        for (int i = 0; i < args.length; i++)
            this.args.add(args[i]);
        //this.args = args;
    }
    // per il parser (fine)*/
    
    /**
     * Set the weight.
     * 
     * @param w weight
     */
    public void setWeight(int w) {
        weight = w;
    }
    
    /**
     * Return the predicate symbol.
     * 
     * @return predicate
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Return the argument terms. 
     * @return list of argumets
     */
    public List<Term> getArgs() {
        return args;
    }
    
    /**
     * Retutn the weight
     * @return the weght
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Return a deep copy
     * 
     * @return a copy
     */
    public Atom copy() {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term t : args)
            argsNuovi.add(t.copy());
        return new Atom(symbol, argsNuovi, weight);
    }
    
    @Override
    public String toString() {
        if (string != null)
            return string;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(symbol);

        // il controllo lo faccio perché posso trovare
        // P senza argomenti che è ground
        if (args != null && args.size() != 0) {
            sb.append('(');

            for (Term t : args) {
                sb.append(t).append(',');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append(')');
        }
        
        return string = sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj instanceof Atom) {
            Atom a = (Atom) obj;
            return symbol.equals(a.symbol) &&
                    args.equals(a.args);
        }
        
        return false;
            
        /*
        Atom other = (Atom) obj;
        if (symbol != other.symbol) {
            return false;
        }
        
        boolean eq = true;
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                eq = eq && args[i].equals(other.args[i]);
            }
        }
        return eq;
        */
        //return this == obj;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.symbol);
        hash = 97 * hash + Objects.hashCode(this.args);
        return hash;
    }

    /**
     * Return the number of symbols.
     * 
     * @return number of symbols
     */
    public int symbolsNumber() {
        if (symsNum != 0)
            return symsNum;
        
        int n = weight;
        //if (args != null) {
            for (Term a : args) {
                n += a.symbolsNumber();
            }
        //}
        return symsNum = n;
    }
    
    /**
     * Return a tuple for the lexicographical ordering
     * 
     * @return tuple
     */
    public List<Object> getTupla() {
        List<Object> l = new ArrayList<>();
        l.add(this);
        return l;
        
    }
    
    /**
     * Return a tuple for the lexicographical ordering
     * ONLY arguments
     * 
     * @return tuple
     */
    public List<Term> getArgsTupla() {
        //if (args == null)
        //    return null;
        return args;
        // non modifico la tupla quindi posso passare direttamente gli argomenti
        // evitando di copiare tutto
        //List<Term> l = new ArrayList<>();
        /*for (Term t : args)
            l.add(t);*/
        //l.addAll(args);
        //return l;
        //return Collections.unmodifiableList(args);
    }

    /**
     * Return a multiset for the multiset ordering
     * ONLY arguments
     * 
     * @return multiset
     */
    public MultiSet getArgsMultiset() {
        return new MultiSet((List<Object>) (List<?>) args);
    }
    
    /**
     * Return a multiset for the multiset ordering
     * 
     * @return multiset
     */
    MultiSet getMultiset() {
        return new MultiSet(this);
    }

    /**
     * Number of arguments (arity)
     * @return number of arguments
     */
    int getNArgs() {
        return args.size();
    }
/*    
    public Atom applySubstitution(Substitution tau) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau));
        if (argsNuovi.equals(args))
            return this;
        return new Atom(symbol, argsNuovi);
    }
*/   
     /**
     * Constructs a new atom from the application of the given substitution 
     * to this atom.
     * (Note: apply ' to distinguish variables with equals name 
     * (and different index)
     * example: in case of x_3 and x_4 --> x_newIndex and x'_newIndex
     * 
     * @param tau substitution
     * @param vars map of variables and new name given in the results
     * @param time index of the new clause
     * @return the new atom if the substitution modify the atom
     *         this if the substitution has no effects.
     */
    public Atom applySubstitution(Substitution tau, Map<String, Variable> vars, long time) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau, vars, time));
        if (argsNuovi.equals(args))
            return this;
        return new Atom(symbol, argsNuovi);
    }
/*    
    public Atom renameVariables(long num) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.renameVariables(num));
        if (argsNuovi.equals(args))
            return this;
        return new Atom(symbol, argsNuovi);
    }
*/
}
