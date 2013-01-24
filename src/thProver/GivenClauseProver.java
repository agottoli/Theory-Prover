/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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

    PriorityQueue<Clause> To_Select; // ???? PriorityQueue vs TreeSet (vs SortedSet)
    List<Clause> Selected;
    private Ordering ord;
    private final boolean aLaE, sos, kbo, multiSet;
    private int generated, deleted;
    IndexingClauses index;
    private long elapsedTime;
    private long startTime;
    private double timeout; // = -1;
    private boolean timeoutWhitoutResponse = false;
    private boolean useOrdering;
    private boolean stop = false;

    public GivenClauseProver(boolean aLaE, boolean sos, boolean kbo,
            boolean multiSet, boolean uOr, Ordering ord, int limit) {
        this.aLaE = aLaE;
        this.sos = sos;
        this.kbo = kbo;
        this.multiSet = multiSet;
        To_Select = new PriorityQueue<>();//TreeSet<>();
        Selected = new ArrayList<>();
        this.ord = ord;
        useOrdering = uOr;
        timeout = limit;
    }

    public void setTimeOut(int seconds) {
        timeout = seconds;
    }

    /**
     * Se ritorno una clausola è sicuramente quella vuota e posso anche
     * disegnare la prova.
     *
     * @param f
     * @return null è soddisfacibile la clausola vuota è insoddisfacibile
     */
    public Clause satisfiable(CNFFormula f) {
        timeoutWhitoutResponse = false;
        generated = 0;
        deleted = 0;

        long nCIniziali = f.getNumClausesAndSOS();

        index = new IndexingClauses(nCIniziali);



        To_Select.addAll(f.getSOS());
        if (sos && !f.getSOS().isEmpty()) {
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

        startTime = System.nanoTime();

        // given clause cicle
        while (!To_Select.isEmpty()) {

            // extract given clause and find her factors
            Clause given = To_Select.remove(); //.pollFirst(); //remove();
            /* DEBUG inizio */
            //System.out.println("Estraggo " + given);
            /* DEBUG fine */

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
                if (!betaprimo.isEmpty()) {
                    To_Select.addAll(betaprimo);
                    generated += betaprimo.size();
                    deleted += betaprimo.size();
                }
            } else {
                // à la Otter devo lo stesso controllare che non sia una tautologia
                // e in realtà serve solo per le clausole di ingresso perché le 
                // altre sono già controllate
                if (given.indiceClausola < nCIniziali
                        && given.isTautology()) {
                    deleted++;
                    continue;
                }
            }

            /*
             List<Clause> alfa = new ArrayList<>();
             for (Clause clause : Selected) {
             alfa.addAll(findAllResolvent(given, clause));
             }*/
            // diventa

            /* DEBUG inizio */
            //System.out.println("Cominicio la risoluzione...");
            /* DEBUG fine */

            Set<Clause> alfa;
            if (useOrdering) {
                // la fattorizzazione si fa prima o dopo la risoluzione
                // devo scoprire però su cosa devo farla
                // ipotesi 1 = solo su given
                // ipotesi 2 = anche sui risultati
                // suppongo la prima :)
                alfa = InferenceSystem.orderedFactorization(given, ord, index);
                alfa.addAll(InferenceSystem.orderedResolution(given, Selected, ord, index));
            } else {
                alfa = InferenceSystem.factorization(given, index);
                alfa.addAll(InferenceSystem.resolution(given, Selected, index));
            }
            /* DEBUG inizio */
            //System.out.println("ho generato " + alfa.size() + " risolventi.\n"
            //        + alfa.toString());
            /* DEBUG fine */
            generated += alfa.size();

            Set<Clause> betaprimo = new LinkedHashSet();
            Set<Clause> copy = new LinkedHashSet(alfa);
            for (Clause alfai : copy) {
                if (alfai.isEmpty()) {
                    elapsedTime = System.nanoTime() - startTime;
                    /* DEBUG inizio */
                    //System.out.println("trovata la clausola vuota.");
                    /* DEBUG fine */
                    System.out.print(info());
                    return alfai; // insoddisfacibile NOTA: se ritorno alfai potrei generare la prova :)
                }
                Clause alfaiSempl;
                if ((alfaiSempl = forwardContraction(alfai)) == null) {
                    deleted++;
                    alfa.remove(alfai);
                    /* DEBUG inizio */
                    //System.out.println("clausola sussunta.");
                    /* DEBUG fine */
                    continue;
                } else {
                    // alfai potrebbe essere stato semplificato
                    // è un problema? Sì, da alfa devo cancellare alfai ed
                    // inserire alfaiSempl
                    if (alfai != alfaiSempl) {
                        alfa.remove(alfai);
                        alfa.add(alfaiSempl);
                        //deleted++; già conteggiato in forwardContraction
                        //generated++; come sopra
                        /* DEBUG inizio */
                        //System.out.println("clausola semplificata.");
                        /* DEBUG fine */
                    }
                }

                if (!aLaE) {
                    //betaprimo.addAll(new HashSet<Clause>());
                    Set<Clause> betaprimoAdd = backwardContraction(alfaiSempl);
                    generated += betaprimoAdd.size();
                    deleted += betaprimoAdd.size();
                    betaprimo.addAll(betaprimoAdd);
                    // la backwardContraction deve calcolare i fattori delle beta
                    /* DEBUG inizio */
                    //System.out.println("dalla backward ho generato: "
                    //        + betaprimo.size() + " clausole.");
                    /* DEBUG fine */
                }

            }

            elapsedTime = System.nanoTime() - startTime;

            if (timeout > 0 && elapsedTime / 1000000000.0 > timeout) {
                //System.out.print(info());
                //return null;
                timeoutWhitoutResponse = true;
                break;
            }
            
            if (stop)
                break;

            To_Select.addAll(alfa);
            if (!betaprimo.isEmpty())
                To_Select.addAll(betaprimo);

            Selected.add(given);

            /* DEBUG inizio */
            //System.out.println("fine di questa iterazione.");
            /*try {
                System.in.read();
            } catch (IOException ioe) {
            }*/
            /* DEBUG fine */
        }
        
        System.out.print(info());
        return null; //true; 
    }

    /**
     *
     * @param given
     * @return null se given viene cancellata clause semplificata se possibile
     */
    private Clause forwardContraction(Clause given) {
        if (given.isTautology())
            return null;

        if (InferenceSystem.subsumedBy(given, Selected))
            return null;
        if (!aLaE && InferenceSystem.subsumedBy(given, To_Select))
            return null;
        Clause sempl = InferenceSystem.semplificatedClause(given, Selected, index);
        boolean flagForCount = false;
        if (sempl != null) {
            // cancello given e aggiungo sempl --> deleted++ generated++
            deleted++;
            generated++;
            flagForCount = true;
            given = sempl; // DA SISTEMARE LE IDEE
        }
        if (!aLaE) {
            sempl = InferenceSystem.semplificatedClause(given, To_Select, index);
            if (sempl != null) {
                // come prima ma non so se ho già contato...
                if (!flagForCount) {
                    deleted++;
                    generated++;
                }
                given = sempl;
            }
        }

        return given;

    }

    public Set<Clause> backwardContraction(Clause given) {
        //System.out.println("given da errore: " + given);
        /* DEBUG inizio */
        //int selSize = Selected.size();
        //int toSelSize = To_Select.size();
        /* DEBUG fine */
        int numSuss = InferenceSystem.subsumes(given, Selected);
        if (!aLaE)
            numSuss += InferenceSystem.subsumes(given, To_Select);
        deleted += numSuss;
        /* DEBUG inizio */
        //if (numSuss == 40)
        //    System.out.println("eccolo");
        //if (numSuss != 0) {
        //    System.out.println("selSize: " + selSize + " -> " + Selected.size());
        //    System.out.println("toSelSize: " + toSelSize + " -> " + To_Select.size());
        //    System.out.println(given + " -> ha sussunto "
        //            + numSuss + " clausole in backward.");
        //}
        /* DEBUG fine */

        Set<Clause> sempl = InferenceSystem.semplificClause(given, Selected, index);

        if (!aLaE) {
            sempl.addAll(InferenceSystem.semplificClause(given, To_Select, index));
        }

        return sempl;
    }
    
    public String info() {
        StringBuilder sb = new StringBuilder("finish with: ");
        
        sb.append(generated).append(" clauses generated, ");
        sb.append(deleted).append(" clauses subsumed, ");
        sb.append("in ");sb.append(elapsedTime/1000000000.0).append(" seconds");
        if (timeoutWhitoutResponse)
            sb.append(" (out of time limit)");
        if (stop)
            sb.append(" (user stop)");
        sb.append(".\n");
        
        return sb.toString();
    }
    
    public long getElapsedTime() {
        return elapsedTime;
    }
    
    public void stop() {
        stop = true;
    }
    
    public boolean isStopped() {
        return stop;
    }
    
    public String getFiends(Clause c) {
        return c.getDOT();
    }
    
    public void exportDot(String dir, String name, String grafo) {
        if (name == null) {
            // interactive mode
            name = "input.txt";
        }
        if (dir == null) {
            dir = ".";
        }
        int index = name.lastIndexOf('.'); 
        String nameNoExt;
        if (index == -1) {
            // il carattere . non 'è nel nome del file
            nameNoExt = name;
        } else {
           nameNoExt = name.substring(0, index);
        }
        
        try {
            FileOutputStream file = new FileOutputStream(dir + "/" 
                    + nameNoExt + ".dot");
            PrintStream output = new PrintStream(file);
            output.println(grafo);
        } catch (IOException e) {
            System.out.println("Errore: " + e);
        }
        
        String cmd = "dot -Tjpg " 
                    + dir + "/" 
                    + nameNoExt + ".dot" + " -o" + dir + "/" 
                    + nameNoExt + ".jpg";
        Runtime run = Runtime.getRuntime();
        Process pr = null;
        try {
            pr = run.exec(cmd);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Errore nell'esportazione, 'dot' potrebbe non "
                    + "essere installato. Il file sorgente del grafo è "
                    + "visualizzabile in " + dir + "/" + nameNoExt + ".dot");
        }
        try {
            pr.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Grafo esportato in "+  dir + "/" + nameNoExt + ".jpg");
        //Picture p = new Picture(fileNameNoExt + ".dot" + ".jpg");
        //p.show();
        //sb.append(grafo);
    }
}
