package thProver;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A Clause - a set of literals.
 */
public class Clause implements Comparable<Clause> {

    // NOTA : meglio mettere Set<Literal> perché non ci interessa l'ordine
    Set<Literal> literals; // perché non interessa l'ordine e se ho due letterali uguali me ne tiene solo uno
    // per velocizzare le operazioni mi divido i letterali positivi dai negativi
    // NOTA: tengo sono gli atomi, visto che so il segno
    List<Literal> posLits;
    List<Literal> negLits;
    // stringa per la stampa così non devo ricalcolarla
    private String string;
    // mi tengo il numero dei simboli per non ricalcolarli
    private int numSymbs = 0;
    // mi salvo tutti i fattori di una clausola per non doverli rigenerare
    // ad ogni risoluzione
    Set<Clause> factors;
    Set<Clause> maximalFactors;
    // lista dei letterali massimali
    private List<Literal> maximalLits;
    List<Literal> posMaximalLits;
    List<Literal> negMaximalLits;

    /**
     * Constructs an empty clause.
     */
    public Clause() {
        literals = new LinkedHashSet<>();
        posLits = new ArrayList<>();
        negLits = new ArrayList<>();
    }

    public Clause(Set<Literal> lits) {
        this();
        literals = lits;
        for (Literal l : literals)
            if (l.isPositive())
                this.posLits.add(l);
            else
                this.negLits.add(l);
    }

    // per quando creo una clausola da una regola di espansione
    // C or L  -L or D --> C or D
    public Clause(List<Literal> lits1, List<Literal> lits2) {
        this();
        literals.addAll(lits1);
        literals.addAll(lits2);
        for (Literal l : literals)
            if (l.isPositive())
                this.posLits.add(l);
            else
                this.negLits.add(l);
    }
    /*
     /* *
     * Constructs a clause containing the given literals.
     *
     * @param lits the list of literals
     * /
     Clause(Literal... lits) {
     literals = new ArrayList<>(Arrays.asList(lits));
     posLits = new ArrayList<>();
     negLits = new ArrayList<>();
     for (Literal l : lits)
     if (l.isPositive())
     posLits.add(l);
     else
     negLits.add(l);
     }
     */

    /**
     * Adds a literal to this clause.
     *
     * @param literal the literal to add
     */
    public void addLiteral(Literal literal) {
        int nLiterals = literals.size();
        literals.add(literal);

        // questo serve se uso Set perché non ammette oggetti ripetuti
        if (nLiterals < literals.size())
            // è un nuovo letterale quindi lo devo aggiungere anche alle liste
            // dei letterali positivi e negativi
            if (literal.isPositive())
                posLits.add(literal);
            else
                negLits.add(literal);
    }

    public Set<Literal> getLiterals() {
        return literals;
    }

    public List<Literal> getPosiviveLiterals() {
        return posLits;
    }

    public List<Literal> getNegativeLiterals() {
        return negLits;
    }

    public int size() {
        return literals.size();
    }

    @Override
    public String toString() {
        if (string != null)
            return string;

        StringBuilder sb = new StringBuilder();

        for (Literal l : literals)
            sb.append(l.toString()).append(" | ");

        if (!literals.isEmpty())
            sb.replace(sb.length() - 3, sb.length(), "");

        return sb.toString();
    }

    /**
     * Compares clauses using the number of symbols
     *
     * @param c the other clause
     */
    @Override
    public int compareTo(Clause c) {
        int diff = symbolsNumber() - c.symbolsNumber();
        if (diff == 0 && !equals(c))
            return -1; // keep consistency with equals()
        return diff;
    }

    private int symbolsNumber() {
        if (numSymbs != 0)
            return numSymbs;

        int n = 0;
        for (Literal l : literals)
            n += l.symbolsNumber();
        return n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        // DA MIGLIORARE
        if (obj instanceof Clause) {
            Clause other = (Clause) obj;
            if (literals.size() != other.literals.size())
                return false;
            for (Literal l : literals)
                if (!other.literals.contains(l))
                    return false;
            // tutti i letterali sono presenti in other ma
            // non so niente del contrario
            for (Literal l : other.literals)
                if (!this.literals.contains(l))
                    return false;
            // tutti i letterali sono in entrambe le clausole                            
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.literals);
        return hash;
    }

    boolean isEmpty() {
        return literals.isEmpty();
    }

    /**
     * Determines if this clause is a tautology.
     *
     * @return <code>true</code> iff this clause is a tautology
     */
    boolean isTautology() {

        for (Literal l1 : posLits) {
            Atom a1 = l1.getAtom();
            for (Literal l2 : negLits) {
                Atom a2 = l2.getAtom();
                if (a1.equals(a2))
                    return true;
            }
        }

        // no complementary literal found
        return false;
    }

    public List<Literal> getMaximalLiterals(Ordering ord) {
        if (maximalLits == null) {
            calculateMaximalLits(ord);
            posMaximalLits = new ArrayList<>();
            negMaximalLits = new ArrayList<>();
            for (Literal l : maximalLits)
                if (l.isPositive())
                    posMaximalLits.add(l);
                else
                    negMaximalLits.add(l);
        }
        return maximalLits;
    }

    public void calculateMaximalLits(Ordering ord) {
        maximalLits = ord.getMaximalLiterals(this);
    }

    public Set<Clause> getFactors() {
        if (factors == null)
            calculateFactors();

        return factors; //=
    }

    public Set<Clause> getMaximalFactors(Ordering ord) {
        if (maximalFactors == null)
            calculateMaximalFactors(ord);

        return maximalFactors; //=
    }

    private void calculateFactors() {
        factors = new LinkedHashSet<>();

        // DEBUG inizio //
        //System.out.println("I fattori di " + toString() + " sono:");
        // DEBUG fine

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution = new Substitution();

        List<Literal> lits = new ArrayList<>();

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            lits.clear();
            if (i == 0)
                // Look at the positive literals
                lits.addAll(posLits);
            else
                // Look at the negative literals
                lits.addAll(negLits);

            for (int x = 0; x < lits.size(); x++) {
                Literal litX = lits.get(x);
                for (int y = x + 1; y < lits.size(); y++) {
                    Literal litY = lits.get(y);
                    // initializzo la sostituzione
                    //theta.clear();
                    substitution.clear();

                    /* DEBUG inizio */
                    //System.out.println("\tvoglio unificare: " + litX + " con " + litY);
                    /* DEBUG fine */
                    //Map<Variable, Term> substitution = unify(litX, litY, theta);
                    //substitution = InferenceSystem.mgu(litX, litY, true, substitution);

                    if (InferenceSystem.mgu(litX, litY, true, substitution, false)) {
                        long time = System.nanoTime();
                        substitution.renameVariables(time);
                        Clause nuova = this.applySubstitution(substitution, time);
                        /* DEBUG inizio */
                        //System.out.println("\te ottengo " + nuova + "\n\tda sub " + substitution.toString());
                        /* DEBUG fine */
                        if (!nuova.isTautology())
                            //nuova.renameVariables();
                            factors.add(nuova); //} else {
                        /* DEBUG inizio */ //System.out.println("\ttautologia, quindi la cancello.");
                        /* DEBUG fine */
                    }
                }
            }
        }
        // DEBUG inizio //
        //System.out.println(factors + "\n");
        // DEBUG fine
        // ??? devo trovare anche i fattori dei fattori? se lo faccio calcolo 2 volte gli stessi risolventi...
        Set<Clause> aggiuntivi = new LinkedHashSet<>();
        for (Clause c : factors)
            aggiuntivi.addAll(c.getFactors());
        factors.addAll(aggiuntivi);
        // DEBUG inizio //
        //System.out.println("più aggiunti anche i fattori dei sui fattori ricorsivamente\nottengo per " + toString() + "\n->" + factors + "\n");
        // DEBUG fine
    }

    private void calculateMaximalFactors(Ordering ord) {
        getMaximalLiterals(ord); // così se non già calcolati non da problemi

        // DEBUG inizio //
        //System.out.println("I fattori MASSIMALI di " + toString() + " sono:");
        // DEBUG fine

        maximalFactors = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution = new Substitution();

        List<Literal> maxLits = new ArrayList<>();
        List<Literal> lits = new ArrayList<>();

        Set<Literal> maxAlreadySel = new LinkedHashSet<>(maximalLits);

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            maxLits.clear();
            lits.clear();
            if (i == 0) {
                // Look at the positive literals
                maxLits.addAll(posMaximalLits);
                lits.addAll(posLits);
            } else {
                // Look at the negative literals
                maxLits.addAll(negMaximalLits);
                lits.addAll(negLits);
            }

            for (int x = 0; x < maxLits.size(); x++) {
                Literal litX = maxLits.get(x);

                maxAlreadySel.add(litX); // mi permette di fare solo 1 volta tra 2 massimali :)

                for (int y = 0; y < lits.size(); y++) {
                    Literal litY = lits.get(y);

                    // se già presente vuol dire che litY è litX (se stesso)
                    // oppure che litY è un letterale massimale con cui ho
                    // già sicuramente unificato (o provato ad unificare)
                    if (maxAlreadySel.contains(litY))
                        continue;

                    // initializzo la sostituzione
                    //theta.clear();
                    substitution.clear();

                    /* DEBUG inizio */
                    //System.out.println("\tvoglio unificare: " + litX + " con " + litY);
                    /* DEBUG fine */
                    //Map<Variable, Term> substitution = unify(litX, litY, theta);
                    //substitution = InferenceSystem.mgu(litX, litY, true);

                    if (InferenceSystem.mgu(litX, litY, true, substitution, false)) {
                        long time = System.nanoTime();
                        substitution.renameVariables(time);
                        Clause nuova = this.applySubstitution(substitution, time);

                        /* DEBUG inizio */
                        //System.out.println("\te ottengo " + nuova + "\n\tda sub " + substitution.toString());
                        /* DEBUG fine */
                        if (!nuova.isTautology())
                            //nuova.renameVariables();
                            maximalFactors.add(nuova); //} else {
                        /* DEBUG inizio */ //System.out.println("\ttautologia, quindi la cancello.");
                        /* DEBUG fine */
                    }
                }
            }
        }
        // DEBUG inizio //
        //System.out.println(maximalFactors + "\n");
        // DEBUG fine
        // ??? devo trovare anche i fattori dei fattori?
        Set<Clause> aggiuntivi = new LinkedHashSet<>();
        for (Clause c : maximalFactors)
            aggiuntivi.addAll(c.getMaximalFactors(ord));
        maximalFactors.addAll(aggiuntivi);
        // DEBUG inizio //
        //System.out.println("più aggiunti anche i fattori dei sui fattori ricorsivamente\nottengo per " + toString() + "\n->" + maximalFactors + "\n");
        // DEBUG fine
    }

    /*
     public Clause applySubstitution(Substitution tau) {
     Set<Literal> lits = new LinkedHashSet<>();
     for (Literal l : literals)
     lits.add(l.applySubstitution(tau));
     if (lits.equals(literals))
     return this;
     return new Clause(lits);
     }
     */
    public Clause applySubstitution(Substitution tau, long time) {
        //long time = System.nanoTime();
        Set<Literal> lits = new LinkedHashSet<>();
        for (Literal l : literals)
            lits.add(l.applySubstitution(tau, time));
        if (lits.equals(literals))
            return this;
        return new Clause(lits);
    }

    public String getFactorsString() {
        if (factors == null)
            calculateFactors();
        if (factors.isEmpty())
            return "non ci sono fattori.";

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (Clause c : factors)
            if (flag) {
                flag = false;
                sb.append(c.toString());
            } else
                sb.append(";\n").append(c.toString());
        return sb.toString();
    }

    public String getMaximalFactorsString() {
        if (maximalFactors == null)
            //calculateMaximalFactors();
            return "maximalFactors: non ancora calcolati.";
        if (maximalFactors.isEmpty())
            return "non ci sono fattori.";

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (Clause c : maximalFactors)
            if (flag) {
                flag = false;
                sb.append(c.toString());
            } else
                sb.append(";\n").append(c.toString());
        return sb.toString();
    }

    public Set<Clause> resolvents(Clause othC) {
        Set<Clause> resolvents = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution = new Substitution();

        List<Literal> lits1, lits2;

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            //lits.clear();
            if (i == 0) {
                // Look at the positive literals
                lits1 = posLits;
                lits2 = othC.negLits;
            } else {
                // Look at the negative literals
                lits1 = negLits;
                lits2 = othC.posLits;
            }

            for (int x = 0; x < lits1.size(); x++) {
                Literal litX = lits1.get(x);
                for (int y = 0; y < lits2.size(); y++) {
                    Literal litY = lits2.get(y);

                    substitution.clear();

                    if (InferenceSystem.mgu(litX, litY, false, substitution, false)) {
                        Set<Literal> set = new LinkedHashSet<>();
                        long time = System.nanoTime();
                        substitution.renameVariables(time);
                        for (Literal l : literals) {
                            if (l.equals(litX))
                                continue;
                            set.add(l.applySubstitution(substitution, time));
                        }
                        for (Literal l : othC.literals) {
                            if (l.equals(litY))
                                continue;
                            set.add(l.applySubstitution(substitution, time));
                        }

                        Clause nuova = new Clause(set);
                        //nuova.renameVariables();
                        resolvents.add(nuova);
                    }
                }
            }
        }
        return resolvents;
    }

    public Set<Clause> allTheResolvents(Clause othC) {
        Set<Clause> resolvents = new LinkedHashSet<>();

        resolvents.addAll(this.resolvents(othC));
        for (Clause c : othC.getFactors())
            resolvents.addAll(this.resolvents(c));
        for (Clause c : getFactors())
            resolvents.addAll(c.resolvents(othC));
        for (Clause c1 : getFactors())
            for (Clause c2 : othC.getFactors())
                resolvents.addAll(c1.resolvents(c2));

        return resolvents;
    }

    public Set<Clause> orderedResolvents(Clause othC) {
        //getMaximalLiterals(ord); // così se non già calcolati non da problemi

        Set<Clause> resolvents = new LinkedHashSet<>(); // oppure LinkedHashSet<>(); che non ha il problema dell'incremento dei costi di TreeSet

        //Map<Variable, Term> theta = new HashMap<Variable, Term>();
        Substitution substitution = new Substitution();

        List<Literal> lits1, lits2;

        // provvisorio per mgu
        //InferenceSystem is = new InferenceSystem();

        for (int i = 0; i < 2; i++) {
            //lits.clear();
            if (i == 0) {
                // Look at the positive literals
                lits1 = posMaximalLits;
                lits2 = othC.negMaximalLits;
            } else {
                // Look at the negative literals
                lits1 = negMaximalLits;
                lits2 = othC.posMaximalLits;
            }

            for (int x = 0; x < lits1.size(); x++) {
                Literal litX = lits1.get(x);
                for (int y = 0; y < lits2.size(); y++) {
                    Literal litY = lits2.get(y);

                    substitution.clear();

                    if (InferenceSystem.mgu(litX, litY, false, substitution, false)) {
                        Set<Literal> set = new LinkedHashSet<>();
                        long time = System.nanoTime();
                        substitution.renameVariables(time);
                        for (Literal l : literals) {
                            if (l.equals(litX))
                                continue;
                            set.add(l.applySubstitution(substitution, time));
                        }
                        for (Literal l : othC.literals) {
                            if (l.equals(litY))
                                continue;
                            set.add(l.applySubstitution(substitution, time));
                        }

                        Clause nuova = new Clause(set);
                        //nuova.renameVariables();
                        resolvents.add(nuova);
                    }
                }
            }
        }
        return resolvents;
    }

    public Set<Clause> allTheOrderedResolvents(Clause othC, Ordering ord) {
        Set<Clause> resolvents = new LinkedHashSet<>();

        // per essere sicuro che siano stati calcolati i letterali massimali
        getMaximalLiterals(ord);
        othC.getMaximalLiterals(ord);
        /////////////////////////////////////////

        resolvents.addAll(this.orderedResolvents(othC));
        for (Clause c : othC.getMaximalFactors(ord))
            resolvents.addAll(this.orderedResolvents(c));
        for (Clause c : getMaximalFactors(ord))
            resolvents.addAll(c.orderedResolvents(othC));
        for (Clause c1 : getMaximalFactors(ord))
            for (Clause c2 : othC.getMaximalFactors(ord))
                resolvents.addAll(c1.orderedResolvents(c2));

        return resolvents;
    }

    public void renameVariables() {
        long num = System.nanoTime();
        Set<Literal> lits = literals;
        /*for (Literal l : literals)
         lNuovi.add(l.renameVariables(num));
         if (!lNuovi.equals(literals))
         literals = lNuovi;
         */
        posLits.clear();
        negLits.clear();
        literals = new LinkedHashSet<>(lits.size());
        for (Literal l : lits)
            literals.add(l.renameVariables(num));
        for (Literal l : literals)
            if (l.isPositive())
                posLits.add(l);
            else
                negLits.add(l);

    }

    public Substitution subsumes(Clause othC) {
        boolean subsumes = false;

        // Equality is not subsumption
        if (!(this == othC))
            // Ensure this has less literals total and that
            // it is a subset of the other clauses positive and negative counts
            if (this.literals.size() < othC.literals.size()
                    && this.posLits.size() <= othC.posLits.size()
                    && this.negLits.size() <= othC.negLits.size()) {

                // PROVO SUSSUNZIONE PROPRIA
                Map<String, List<Literal>> thisToTry = collectLikeLiterals(this.literals);
                Map<String, List<Literal>> othCToTry = collectLikeLiterals(othC.literals);
                // Ensure all like literals from this clause are a subset
                // of the other clause.
                if (othCToTry.keySet().containsAll(thisToTry.keySet())) {
                    /*boolean isAPossSubset = true;*/
                    // Ensure that each set of same named literals
                    // from this clause is a subset of the other
                    // clauses same named literals.
                    /*
                     for (String pk : thisToTry.keySet())
                     if (thisToTry.get(pk).size() > othCToTry.get(pk).size()) {
                     isAPossSubset = false;
                     break;
                     }
                     if (isAPossSubset) {
                     // At this point I know this this Clause's
                     // literal/arity names are a subset of the
                     // other clauses literal/arity names
                     //subsumes = checkSubsumes(othC, thisToTry, othCToTry);
                     //Substitution sub = new Substitution();
                     //for (String key : thisToTry.keySet()) {*/
                    return checkSub(thisToTry, othCToTry,
                            new Substitution());

                    /*
                     // se arrivo qui vuol dire che nessun letterale
                     // presente in litT unifica per la suss con uno in litO
                     //return null;
                     //}
                     }*/
                }
            } else if (this.literals.size() == othC.literals.size()
                    && this.posLits.size() == othC.posLits.size()
                    && this.negLits.size() == othC.negLits.size()) {
                // PROVO CON LA SUSSUNZIONE DI VARIANTI
                // ?????
            }

        return null; //subsumes;
    }

    private Map<String, List<Literal>> collectLikeLiterals(Set<Literal> literals) {
        Map<String, List<Literal>> likeLiterals = new LinkedHashMap<String, List<Literal>>();
        for (Literal l : literals) {
            // Want to ensure P(a, b) is considered different than P(a, b, c)
            // i.e. consider an atom's arity P/#.
            String literalName = (l.isPositive() ? "" : "~")
                    + l.getAtom().getSymbol();

            List<Literal> like = likeLiterals.get(literalName);
            if (null == like) {
                // è il primo letterale che incontro con questo segno e questo predicato
                // allora creo la lista
                like = new ArrayList<Literal>();
                likeLiterals.put(literalName, like);
            }
            // inserisco il letterale a questa lista
            like.add(l);
        }
        return likeLiterals;
    }

    public Substitution checkSub(
            Map<String, List<Literal>> thisToTry,
            Map<String, List<Literal>> othCToTry,
            Substitution sigma) {

        //boolean subsumes = false;

        Substitution sub = sigma.copy();

        Iterator<String> it = thisToTry.keySet().iterator();
        if (it.hasNext()) {
            String key = it.next();
        //for (String key : thisToTry.keySet()) {
            // che è più piccolo
            List<Literal> litsT = thisToTry.get(key);
            List<Literal> litsO = othCToTry.get(key);
            
            if (!litsT.isEmpty()) {
                Literal lT = litsT.get(0);
            //for (Literal lT : litsT) {
                for (Literal lO : litsO) {
                    Substitution copy = sub.copy();
                    if (InferenceSystem.mgu(lT, lO, true, copy, true)) {
                        // if sub.assignments.keySet() contains variabile di othC
                        // allora non è un unificazione giusta
                        // ????
                        // OK, modifico l'mgu per la sussunzione così 
                        // se gli passo true non mi fa gli assegnamenti
                        //subsumes = true;//return sub; // subsumes = true;
                        //break;

                        // sub è stata aggiornata per continuare l'unificazione

                        Map<String, List<Literal>> thisToTryCopy = new LinkedHashMap<>(thisToTry);
                        List<Literal> get = thisToTryCopy.get(key);
                        if (get.size() == 1)
                            thisToTryCopy.remove(key);
                        else
                            get.remove(lT);

                        //Map<String, List<Literal>> othCToTryCopy = new LinkedHashMap<>(othCToTry);
                        //othCToTryCopy.remove(key);

                        if (thisToTryCopy.isEmpty())
                            return copy; // tutti i letterali in this unificano contemporaneamente

                        //Substitution copy = sub.copy();
                        Substitution nuova;
                        nuova = checkSub(thisToTryCopy, othCToTry, copy);
                        if (nuova != null)
                            return nuova; // credo di aver unificato tutto
                        
                        // else prova con il prossimo lO
                            
                        // la copy è stata rovinata ma non c'è problema perché
                        // si ricostruisce al prossimo passo
                    }
                }

                // se arrivo qua non sono riuscito ad unificare con nessun lit0
                return null;
            }
        }

System.out.println("babbeo");
        return sigma; // DA FARE     
    }
}
