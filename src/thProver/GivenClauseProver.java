/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author ale
 */
public class GivenClauseProver {
    
    PriorityQueue<Clause> To_Select;
    List<Clause> Selected;
    private Ordering ord;
    private final boolean aLaE, sos, multiSet;
    private int generated, deleted;
    
    private long elapsedTime;
    private long startTime;
    
    public GivenClauseProver(boolean aLaE, boolean sos, boolean multiSet) {
        this.aLaE = aLaE;
        this.sos = sos;
        this.multiSet = multiSet;
        To_Select = new PriorityQueue<>();
        Selected = new ArrayList<>();
        ord = new Ordering();
    }
    
    public boolean isSatisfiable(CNFFormula f) {
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
        ord.setPrecedence(f.getPrecedences(), f.getNPrec(), multiSet);
        
        // given clause cicle
        while (!To_Select.isEmpty()) {
            
            // extract given clause and find her factors
            Clause given = To_Select.remove();
            if (given.isTautology()) {
                deleted++;
                continue;
            }
// DA GUARDARE DOMANI!!!!!            
            if (aLaE) {
                if (forwardContraction(given)) {
                    // devo fare contrazione in avanti
                    // rispetto a Selezionate
                    deleted++;
                    continue;
                }
                List<Clause> betaprimo = backwardContraction(given);
                if (betaprimo != null && !betaprimo.isEmpty())
                    To_Select.addAll(betaprimo);
            }
            
            List<Clause> alfa = new ArrayList<>();
            for (Clause clause : Selected) {
                alfa.addAll(findAllResolvent(given, clause));
            }
            
            List<Clause> betaprimo = new ArrayList<>();
            for (Clause alfai : alfa) {
                if (forwardContraction(alfai)) {
                    deleted++;
                    continue;
                }
                if (!aLaE) {
                    betaprimo.addAll(backwardContraction(given));
                    
                }
            }
            To_Select.addAll(alfa);
            if (betaprimo != null && !betaprimo.isEmpty())
                To_Select.addAll(betaprimo);
            
            
        }
        
        return true; // DA FARE
    }

    private boolean forwardContraction(Clause given) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private List<Clause> backwardContraction(Clause given) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Collection<? extends Clause> findAllResolvent(Clause given, Clause clause) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
