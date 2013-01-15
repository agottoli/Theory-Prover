/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Predicate
 * 
 * @author ale
 */
public class Atom {
    
    private String symbol; // Predicate
    private List<Term> args;
    private int weight = 1;
    // mi salvo la stringa
    private String string;
    // e pure il numero dei simboli
    private int symsNum = 0;
    
    public Atom(String symbol) {
        this.symbol = symbol;
        args = new ArrayList<>();
    }
    
    public Atom(String symbol, List<Term> args) {
        this.symbol = symbol;
        this.args = args;
    }
    
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
    
    public void setWeight(int w) {
        weight = w;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public List<Term> getArgs() {
        return args;
    }
    
    public int getWeight() {
        return weight;
    }
    
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
    
    
    public List<Object> getTupla() {
        List<Object> l = new ArrayList<>();
        l.add(this);
        return l;
        
    }
    
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

    public Multiset<Object> getArgsMultiset() {
        //if (args == null)
        //    return null;
        
        HashMultiset<Object> ms = HashMultiset.create();
        /*for (Term t : args)
            ms.add(t);*/
        ms.addAll(args);
        return ms;
    }

    Multiset<Object> getMultiset() {
        //if (args == null)
        //    return null;
        HashMultiset<Object> ms = HashMultiset.create();
        ms.add(this);
        return ms;
    }

    int getNArgs() {
        return args.size();
    }
    
    public Atom applySubstitution(Substitution tau) {
        List<Term> argsNuovi = new ArrayList<>();
        for (Term te : args)
            argsNuovi.add(te.applySubstitution(tau));
        if (argsNuovi.equals(args))
            return this;
        return new Atom(symbol, argsNuovi);
    }
}
