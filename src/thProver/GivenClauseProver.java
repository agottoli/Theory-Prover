/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author ale
 */
public class GivenClauseProver {
    
    TreeSet<Clause> To_Select; // ???? PriorityQueue vs TreeSet (vs SortedSet)
    List<Clause> Selected;
    private Ordering ord;
    private final boolean aLaE, sos, kbo, multiSet;
    private int generated, deleted;
    
    private long elapsedTime;
    private long startTime;
    
    private boolean useOrdering;
    
    public GivenClauseProver(boolean aLaE, boolean sos, boolean kbo, 
            boolean multiSet, boolean uOr, Ordering ord) {
        this.aLaE = aLaE;
        this.sos = sos;
        this.kbo = kbo;
        this.multiSet = multiSet;
        To_Select = new TreeSet<>();
        Selected = new ArrayList<>();
        this.ord = ord;
        useOrdering = uOr;
    }
    
    /**
     * Se ritorno una clausola è sicuramente quella vuota e posso anche disegnare
     * la prova.
     * 
     * @param f
     * @return null è soddisfacibile
     *         la clausola vuota è insoddisfacibile
     */
    public Clause satisfiable(CNFFormula f) {
        generated = 0;
        deleted = 0;

        startTime = System.nanoTime();

        To_Select.addAll(f.getSOS());
        if (!f.getSOS().isEmpty()) {
            Selected.addAll(f.getClauses());
        } else {
            To_Select.addAll(f.getClauses());
        }
        
        // setto l'ordinamento
        ord.setPrecedence(f.getPrecedences(), f.getNPrec());
        ord.setWeightsKBO(f.getWeights(), f.getWeightVars());
        if (kbo) {
            ord.setKBOrdering();
        } else if (multiSet) {
            ord.setMultiSetOrdering();
        } else {
            ord.setLexicographicOrdering();
        }
        
        // given clause cicle
        while (!To_Select.isEmpty()) {
            
            // extract given clause and find her factors
            Clause given = To_Select.pollFirst(); //remove();
            if (given.isTautology()) {
                deleted++;
                continue;
            }
          
            if (aLaE) {
                if ((given = forwardContraction(given)) == null) {
                    // devo fare contrazione in avanti
                    // rispetto a Selezionate
                    deleted++;
                    continue;
                }
                // given può essere stata semplificata ma non c'è problema
                // oppure essere semplicemente sopravvissuta non c'è problema
                
                Set<Clause> betaprimo = backwardContraction(given);
                if (betaprimo != null && !betaprimo.isEmpty())
                    To_Select.addAll(betaprimo);
            }
            /*
            List<Clause> alfa = new ArrayList<>();
            for (Clause clause : Selected) {
                alfa.addAll(findAllResolvent(given, clause));
            }*/
            // diventa
            Set<Clause> alfa;
            if (useOrdering)
                alfa = InferenceSystem.orderedResolution(given, Selected, ord);
            else
                alfa = InferenceSystem.resolution(given, Selected);
            
            Set<Clause> betaprimo = new LinkedHashSet();
            Set<Clause> copy = new LinkedHashSet(alfa);
            for (Clause alfai : copy) {
                if (alfai.isEmpty())
                    return alfai; // insoddisfacibile NOTA: se ritorno alfai potrei generare la prova :)
                Clause alfaiSempl;
                if ((alfaiSempl = forwardContraction(alfai)) == null) {
                    deleted++;
                    alfa.remove(alfai);
                    continue;
                } else {
                    // alfai potrebbe essere stato semplificato
                    // è un problema? Sì, da alfa devo cancellare alfai ed
                    // inserire alfaiSempl
                    if (alfai != alfaiSempl) {
                        alfa.remove(alfai);
                        alfa.add(alfaiSempl);
                    }
                }
                
                if (!aLaE) {
                    //betaprimo.addAll(new HashSet<Clause>());
                    betaprimo.addAll(backwardContraction(given));
                    // la backwardContraction deve calcolare i fattori delle beta
                }
                
            }
            To_Select.addAll(alfa);
            if (betaprimo != null && !betaprimo.isEmpty())
                To_Select.addAll(betaprimo);
            
            Selected.add(given);
        }
        
        return null; //true; // DA FARE
    }

    /**
     * 
     * @param given
     * @return null se given viene cancellata
     *         clause semplificata se possibile
     */
    private Clause forwardContraction(Clause given) {
        if (given.isTautology())
            return null;
        
        if (InferenceSystem.subsumedBy(given, Selected))
            return null;
        if (!aLaE && InferenceSystem.subsumedBy(given, To_Select))
            return null;
        Clause sempl = InferenceSystem.semplificatedClause(given, Selected);
        if (sempl != null)
            given = sempl; // DA SISTEMARE LE IDEE
        
        if (!aLaE) {
            sempl = InferenceSystem.semplificatedClause(given, To_Select);
            if (sempl != null)
                given = sempl;
        }
        
        return given;
    
    }

    public Set<Clause> backwardContraction(Clause given) {
        System.out.println("given da errore: " + given);
        int numSuss = InferenceSystem.subsumes(given, Selected);
        if (!aLaE)
            numSuss += InferenceSystem.subsumes(given, To_Select);
        /* DEBUG inizio */
        System.out.println("numero clausole sussunte in backwardContraction: "
                + numSuss);
        /* DEBUG fine */
        
        Set<Clause> sempl = InferenceSystem.semplificClause(given, Selected);
              
        if (!aLaE) {
            sempl.addAll(InferenceSystem.semplificClause(given, To_Select));
        }
        
        return sempl;
    }

}
