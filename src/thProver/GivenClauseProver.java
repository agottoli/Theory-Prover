package thProver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Class which implements the Given Clause Cicle (à la Otter and à la E)
 * 
 * @author Alessandro Gottoli vr352595
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
    private boolean testAll = false;
    private long nCIniziali;
    private boolean useChangLee = false;
    //private boolean gui = false;

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

    public GivenClauseProver(boolean aLaE, boolean sos, boolean kbo,
            boolean multiSet, boolean uOr, Ordering ord, int limit, boolean testAll) {
        this(aLaE, sos, kbo, multiSet, uOr, ord, limit);
        this.testAll = testAll;
    }

    public void setTimeOut(int seconds) {
        timeout = seconds;
    }
    
    public void setUseChangLee(boolean b) {
        useChangLee = b;
    }
    
    //public void setGui(boolean gui) {
    //    this.gui = gui;
    //}

    /**
     * Se ritorno una clausola è sicuramente quella vuota e posso anche
     * disegnare la prova.
     * Procedure to determine the satisfiability of the formula with Given
     * Clause Cicle
     * Return 
     * 
     *
     * @param f formula processed by parser
     * @return null è soddisfacibile la clausola vuota è insoddisfacibile
     *         null if the formula is satisfiable (no refutation found)
     *         the empty clause if the formula is unsatisfiable
     */
    public Clause satisfiable(CNFFormula f) {
        timeoutWhitoutResponse = false;
        generated = 0;
        deleted = 0;

        nCIniziali = f.getNumClausesAndSOS();
        //System.out.println("-->" + nCIniziali);

        index = new IndexingClauses(nCIniziali);

        // per non rovinare f per ulteriori chiamate
        /*if (!gui) {*/ // non serve più per il reset...
            To_Select.addAll(f.getSOS());
            if (sos && !f.getSOS().isEmpty()) {
                Selected.addAll(f.getClauses());
            } else {
                To_Select.addAll(f.getClauses());
            }
        /*} else {
            // riutilizzo le clausole allora devo copiarle
            for (Clause cla : f.getSOS()) {
                To_Select.add(cla.lazyCopy());
            }
            if (sos && !f.getSOS().isEmpty()) {
                for (Clause cla : f.getClauses()) {
                    Selected.add(cla.lazyCopy());
                }
            } else {
                for (Clause cla : f.getClauses()) {
                    To_Select.add(cla.lazyCopy());
                }
            }
        }*/

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
        
        Set<Clause> alfa = new LinkedHashSet<>();

        startTime = System.nanoTime();
        
       
        // evito di fare un giro a vuoto se Selected è vuoto
        if (Selected.isEmpty()) {
            while (!To_Select.isEmpty()) {
                Clause elemento = To_Select.remove();
                if (elemento.isEmpty()) {
                    elapsedTime = System.nanoTime() - startTime;
                    Selected.add(elemento); // per non perdere un elemento
                    System.out.print(info());
                    return elemento;
                }
                if (elemento.isTautology()) {
                    deleted++;
                    //System.out.print("deleted = " + deleted);
                    continue;
                }
                Selected.add(elemento);
                break;
            }   
        }
        //System.out.println("selected = " + Selected.toString());   

        // given clause cicle
        while (!To_Select.isEmpty()) {

            // extract given clause and find her factors
            Clause given = To_Select.remove(); //.pollFirst(); //remove();
            /* DEBUG inizio */
            //System.out.println("Estraggo " + given);
            //if (given.indiceClausola == 0)
            //    System.out.println("eccolo all'occhio");
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
                    // aggiungo clausole in To_Select
                    // lo faccio solo se a la E 
                    // quindi non c'è il problema che poi le uso per semplificare o sussumere
                    // le alfai (perché lo farò solo con a la Otter) :)
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

            //Set<Clause> alfa = new LinkedHashSet<>();
            alfa.clear();
            if (useOrdering) {
                if (testAll) {
                    alfa.addAll(InferenceSystem.orderedResolutionAll(given, Selected, ord, index));
                } else {
                    // la fattorizzazione si fa prima o dopo la risoluzione
                    // devo scoprire però su cosa devo farla
                    // ipotesi 1 = solo su given
                    // ipotesi 2 = anche sui risultati
                    // suppongo la prima :)
                    // NOTA:
                    // alfa = InferenceSystem.orderedFactorization(given, ord, index));
                    // la riga sopra mi ha fatto veramente uscire pazzo perché 
                    // non capivo il motivo di trovarmi con i fattori cambiati 
                    // dopo la risoluzione...
                    // infatti restituivo proprio il collegamento ai fattori e 
                    // non un nuovo set e poi aggiungendo i risolventi mi si
                    // aggiungevano anche come fattori della clausola data
                    // SEI UN BABBEO :)
                    // in resolution all non dava problemi perché mi dava un
                    // set nuovo di trica...
                    alfa.addAll(InferenceSystem.orderedFactorization(given, ord, index));
                    alfa.addAll(InferenceSystem.orderedResolution(given, Selected, ord, index));
                }
            } else {
                if (testAll) {
                    alfa.addAll(InferenceSystem.resolutionAll(given, Selected, index));
                } else {
                    alfa.addAll(InferenceSystem.factorization(given, index));
                    alfa.addAll(InferenceSystem.resolution(given, Selected, index));
                    /* DEBUG inizio */
                    //if (given.indiceClausola == 0)
                    //    System.out.println("factors: " +given.factors);
                    /* DEBUG fine */
                }
            }
            /* DEBUG inizio */
            //System.out.println("ho generato " + alfa.size() + " risolventi.\n"
            //        + alfa.toString());
            //try {
            //    System.in.read();
            //} catch (IOException ioe) {}
            /* DEBUG fine */
            if (!alfa.isEmpty()) {
                generated += alfa.size();

                Set<Clause> betaprimo = new LinkedHashSet();
                Set<Clause> copy = new LinkedHashSet(alfa);
                for (Clause alfai : copy) {
                    if (alfai.isEmpty()) {
                        elapsedTime = System.nanoTime() - startTime;
                        /* DEBUG inizio */
                        //System.out.println("trovata la clausola vuota.");
                        //System.out.println("given= " + given);
                    /* DEBUG fine */
                        System.out.print(info());
                        Selected.add(given); // per non perdermi una clausola 
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
                        // qui invece non posso aggiungere subito a To_Select
                        // perché siamo in Otter allora uso un
                        //generated += betaprimoAdd.size();
                        //deleted += betaprimoAdd.size();
                        betaprimo.addAll(betaprimoAdd);
                        // la backwardContraction deve calcolare i fattori delle beta
                    /* DEBUG inizio */
                        //System.out.println("dalla backward ho generato: "
                        //        + betaprimo.size() + " clausole.");
                    /* DEBUG fine */
                    }

                }

                To_Select.addAll(alfa);
                if (!betaprimo.isEmpty()) {
                    To_Select.addAll(betaprimo);
                    // se metto qui la conta non conta le eventuali clausole uguali
                    // generate nella backward (se vogio contarle dovrò farlo in 
                    // betaprimoAdd)
                    generated += betaprimo.size();
                    deleted += betaprimo.size();
                }

            }

            Selected.add(given);

            elapsedTime = System.nanoTime() - startTime;

            if (timeout > 0 && elapsedTime / 1000000000.0 > timeout) {
                //System.out.print(info());
                //return null;
                timeoutWhitoutResponse = true;
                break;
            }

            if (stop)
                break;

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
     * Forward contraction.
     * 
     * @param given given clause of the cicle
     * @return null if given is subsumed
     *         semplified clause of given if given is semplificated
     */
    private Clause forwardContraction(Clause given) {
        if (given.isTautology())
            return null;

        if (useChangLee) {
            if (InferenceSystem.subsumedByChangLeeVersion(given, Selected))
                return null;
            if (!aLaE && InferenceSystem.subsumedByChangLeeVersion(given, To_Select))
                return null;
        } else {
            if (InferenceSystem.subsumedBy(given, Selected))
                return null;
            if (!aLaE && InferenceSystem.subsumedBy(given, To_Select))
                return null;
        }
        Clause sempl = InferenceSystem.simplifiedByClause(given, Selected, index);
        boolean flagForCount = false;
        if (sempl != null) {
            // cancello given e aggiungo sempl --> deleted++ generated++
            deleted++;
            generated++;
            flagForCount = true;
            given = sempl; // DA SISTEMARE LE IDEE
        }
        if (!aLaE) {
            sempl = InferenceSystem.simplifiedByClause(given, To_Select, index);
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

    /**
     * Backward Contraction.
     * Remove the clauses in Selected (and To_Select if à la Otter) that are
     * subsumed or semplificated by the given clause.
     * 
     * @param given given clause of the cicle
     * @return set of semplified clauses if given subsume any clause in Selected
     *                                   (and To_Select if à la Otter)
     *         empty set if no clauses are semplificated 
     */
    private Set<Clause> backwardContraction(Clause given) {
        //System.out.println("given da errore: " + given);
        /* DEBUG inizio */
        //int selSize = Selected.size();
        //int toSelSize = To_Select.size();
        /* DEBUG fine */
        if (useChangLee) {
            int numSuss = InferenceSystem.subsumesChangLeeVersion(given, Selected);
            if (!aLaE)
                numSuss += InferenceSystem.subsumesChangLeeVersion(given, To_Select);
            deleted += numSuss;
        } else {
            int numSuss = InferenceSystem.subsumes(given, Selected);
            if (!aLaE)
                numSuss += InferenceSystem.subsumes(given, To_Select);
            deleted += numSuss;
        }
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

        Set<Clause> sempl = InferenceSystem.simplifiesClause(given, Selected, index);

        if (!aLaE) {
            sempl.addAll(InferenceSystem.simplifiesClause(given, To_Select, index));
        }

        return sempl;
    }

    /**
     * Return a string with the computation info.
     * 
     * @return info string of the computation
     */
    public String info() {
        StringBuilder sb = new StringBuilder("/* info */\n"); //finish with: ");
        
        sb.append(nCIniziali).append(" clauses from parsing, \n");
        sb.append(generated).append(" clauses generated, \n");
        sb.append(deleted).append(" clauses deleted, \n");
        sb.append(To_Select.size()).append(" clauses in To_Select, \n");
        sb.append(Selected.size()).append(" clauses in Selected, \n");
        sb.append("in ");
        sb.append(formatoTime(elapsedTime));
        //sb.append(elapsedTime / 1000000000.0).append(" seconds");
        if (timeoutWhitoutResponse)
            sb.append(" (out of max time)"); // ALESSIA // limit)");
        if (stop)
            sb.append(" (stopped by user)"); // ALESSIA // user stop)");
        sb.append(".\n");

        return sb.toString();
    }
    
    /**
     * Transform from nanoTime to a readable format.
     * 
     * @param time nano time to convert
     * @return readable representation of the time
     */
    private String formatoTime(long time) {
            time = time / 1000000;
            if (time < 1000)
                return time + " millisec.";
            else if (time > 60000) {
                long minuti = time / 60000;
                int secondi = (int) ((time / 1000) % 60);
                return minuti + " min. and " + secondi + " sec.";
            } else {
                int secondi = (int) (time / 1000);
                int milli = (int) (time % 1000);
                return secondi + " sec. and " + milli + " millisec.";
            }
        }

    /**
     * Return the elapsed time of the computation
     * @return elapsd time
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Stops the computation
     */
    public void stop() {
        stop = true;
    }

    /**
     * Check if stopped by user.
     * 
     * @return true if the computation is stopped by user
     *         false othewise
     */
    public boolean isStopped() {
        return stop;
    }
    
    /**
     * 
     * @return true if the computation is stopped by timeout
     *         false otherwise
     */
    public boolean isTimeOut() {
        return timeoutWhitoutResponse;
    }

    /**
     * 
     * @param c refutation clause
     * @return 'dot' format source of refutational graph
     */
    public String getFiends(Clause c) {
        return c.getDOT();
    }

    /**
     * Save to disk (if 'dot' is installed) the grafical graph of the refutation
     * in several format.
     * 
     * @param dir directory where save (path)
     * @param name file name 
     * @param format file format (jpg, ps, pdf)
     * @param grafo source file of the graph for 'dot'
     */
    public void exportDot(String dir, String name, String format, String grafo) {
        if (name == null) {
            // interactive mode
            name = "input." + format;
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
            System.out.println(
                    "Error: write permission negated in directory " 
                    + dir);
                    //"Errore: non si ha permessi di scrittura nella"
                    //+ "cartella " + dir);
            return;
        }

        
        /*String cmd = "dot -Tjpg "
                + dir + "/"
                + nameNoExt + ".dot" + " -o " + dir + "/"
                + nameNoExt + ".jpg";
        */

        
        // così non ho problemi degli spazi nel nome del file :)
        String[] cmd = new String[]{"dot", "-T" + format, dir + "/" + nameNoExt + ".dot",
                "-o", dir + "/" + name};
        
        Runtime run = Runtime.getRuntime();
        Process pr = null;
        try {
            pr = run.exec(cmd);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(
                    "Export failed: 'dot' could be not installed. "
                    + "Anyway you can read the graph source file in "
                    + dir + "/" + nameNoExt + ".dot"); // ALESSIA
                    
                    //"Errore nell'esportazione, 'dot' potrebbe non "
                    //+ "essere installato. Il file sorgente del grafo è "
                    //+ "visualizzabile in " + dir + "/" + nameNoExt + ".dot");
            return;
        }
        
        try {
            pr.waitFor();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println(
                    "Warning: 'dot' unexpected exit. "
                    + "The graph could be saved incorrectly."); // ALESSIA
                    //"Errore: 'dot' è terminato in modo inatteso. "
                    //+ "Il grafo potrebbe non essersi salvato correttamente.");
            return;
        }

        System.out.println("Graph exported in " + dir + "/" + name);
        //Picture p = new Picture(fileNameNoExt + ".dot" + ".jpg");
        //p.show();
        //sb.append(grafo);
    }
}
