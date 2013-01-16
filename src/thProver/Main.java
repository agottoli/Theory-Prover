package thProver;

import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Ragionamento Automatico, I semestre, Anno Accademico 2012-2013
 *
 * Dimostratore di Teoremi mediante Risoluzione Ordinata
 *
 * @author Alessandro Gottoli vr352595
 */
public class Main {

    private static boolean interactive = false;
    private static boolean aLaE = false;

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read and process command line arguments
         */
        /*if (args.length == 0) {
            printUsage();
            System.exit(1);
        }*/
        args = new String[2];
        args[0] = "ThProver";
        args[1] = "-i";

        String fileName = null;
        for (String a : args) {
            if (a.startsWith("-")) {
                // it's an option
                processOption(a);
                continue;
            }

            fileName = a;
        }

        if (fileName == null && !interactive) {
            System.out.println("Please, specify a file name or -i");
            printUsage();
            System.exit(2);
        }

        /*
         * Read the formula
         */
        int choice = 1;
        Reader formulaReader;
        if (interactive) {
            String stringa = 
                    "const: a,b,0,c,d\n" +
                    "prec: P>R>Q>ack>succ>per>piu>f>g>a>c>d>0\n" +
                    "weightVars: 1\n" +
                    "weights: P = 1; ack = 1; succ = 1; 0 = 1; a = 1\n" +
                    //"clauses: P(z,y) | ~P(x,g(u)) | P(x,g(x)) | ~P(u,v) | Q(b)";
                    //"sos: Q(c)" +
                    //"clauses: P(ack(succ(x),succ(y))) | P(ack(x,ack(succ(x),y)))";
                    //"clauses: R(x) | ~P(f(0)) | R(a) | P(f(f(z))) | P(f(z)) \n Q(b)\n";
            //"clauses: P(per(x,piu(y,z))) | P(piu(per(x,y),per(x,z)))";
            // associatività per mul # e per lex > (ok)    
            //"clauses: P(f(f(x,y),z)) | P(f(x,f(y,z))) \n Q(b)\n";
            // distributività se per>piu per mul > e per lex > (ok) 
            //"clauses: P(per(x,piu(y,z))) | P(piu(per(x,y),per(x,z)))";
            // se ack>succ mul > e lex > , weight = 1 KBO1 >
            //"clauses: P(ack(0,y)) | P(succ(y))";
            // se ack>succ mul # e lex > (ok)
            //"clauses: P(ack(succ(x),a)) | P(ack(x,succ(a)))";
            // se ack>succ mul < e lex > (ok)
            //"clauses: P(ack(succ(x),succ(y))) | P(ack(x,ack(succ(x),y)))";
            // esempio trova tutti i risolventi
            "clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            formulaReader = new StringReader(stringa);
            //formulaReader = new StringReader(startInteractive());
        } else {
            formulaReader = new FileReader(fileName);
        }

        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        /* ???? prova ordinamento */
        System.out.println("PROVA ORDINAMENTO:");
        Iterator<Clause> it = f.getClauses().iterator();
        Clause c = it.next();
        Clause othC = it.next();
        System.out.println("c: " + c.toString());
        
        /* Check ORDERING */
        Ordering or = new Ordering();        
        /* Check KBO */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        switch (choice) {
            case 0: or.setLexicographicOrdering(); break;
            case 1: or.setMultiSetOrdering(); break;
            case 2: or.setKBOrdering();
                
        }

        String tipo = or.getTipeOrdering();
        /* Ordinamento letterali * /
        Iterator<Literal> itL = c.getLiterals().iterator();
        
        Literal l1 = itL.next();
        Literal l2 = itL.next();
        if (or.isGreater(l1, l2))
            System.out.println(l1.toString() + " >" + tipo + " " + l2.toString());
        else if (or.isGreater(l2, l1))
            System.out.println(l1.toString() + " <" + tipo + " " + l2.toString());
        else
            System.out.println(l1.toString() + " #" + tipo + " " + l2.toString());
        */
        
        /* Letterali massimali */
        //c.getFactors();
        //List<Literal> maxLits = c.getMaximalLiterals(or);
        //c.getMaximalFactors(or);
         System.out.println(System.nanoTime());
System.out.println(System.nanoTime());
System.out.println(System.nanoTime());
System.out.println(System.nanoTime());System.out.println(System.nanoTime());
System.out.println(System.nanoTime());
System.out.println(System.nanoTime());

         Set<Clause> res = c.allTheResolvents(othC);
        //System.out.println(maxLits.toString());
        //System.out.println(c.getFactorsString());
        //System.out.println(c.getMaximalFactorsString());
        System.out.println("\n\n\n-------------------------" + res.toString() 
                + "-------------------------\n\n\n");
        
        // prova trovare lista di letterali massimali
        //System.out.println("lits massimali: " + or.getMaximalLiterals(c));
        
        // prova letterale è massimale in quella clausola
        /*Literal l3 = c.literals.get(2);
        if (or.isMaxLitInClause(l3, c))
            System.out.println("il letterale " + l3 + " è massimale.");
        else
            System.out.println("il letterale " + l3 + " NON è massimale.");
        */
        /*
        Term t1 = l1.getAtom().getArgs()[0];
        Term t2 = l2.getAtom().getArgs()[0];
        if (or.isGreater(t1, t2))
            System.out.println(t1.toString() + " > " + t2.toString());
        else if (or.isGreater(t2, t1))
            System.out.println(t2.toString() + " > " + t1.toString());
        else
            System.out.println(t1.toString() + " # " + t2.toString());
        */
        
        /* check isTautology();
        String taut = " NON";
        if (c.isTautology())
            taut = "";
        System.out.println("La clausola "+ c.toString() + taut + " è una tautologia");
        */ 
        
        
        
        // PROVA Substitution
        /*
        Substitution sigma = new Substitution();
        Variable x_0 = new Variable("x_0");
        Variable y_0 = new Variable("y_0");
        Variable z_0 = new Variable("z_0");
        Term a = new Constant("a");
        List<Term> argF = new ArrayList<Term>();
        argF.add(x_0);
        Term fx_0 = new Function("f", argF);
        List<Term> argF2 = new ArrayList<Term>();
        argF2.add(y_0);
        Term fy_0 = new Function("f", argF2);
        sigma.addAssignment(x_0, z_0);
        sigma.addAssignment(z_0, fx_0);
        System.out.println(sigma.toString());
        Substitution tau = new Substitution();
        tau.addAssignment(z_0, x_0);
        //System.out.println(sigma.toString());
        System.out.println(tau.toString());
        sigma.compose(tau);
        System.out.println(sigma.toString());
        //System.out.println(c.toString() + " sigma = ");
        
        // PROVA MGU
        InferenceSystem is = new InferenceSystem();
        List<Term> args1 = new ArrayList<>();
        args1.add(x_0);
        args1.add(fx_0);
        Atom at1 = new Atom("P", args1);
        List<Term> args2 = new ArrayList<>();
        args2.add(fy_0);
        args2.add(z_0);
        Atom at2 = new Atom("P", args2);
        System.out.println(at1.toString() + " " + at2.toString());
        Substitution sub = is.mgu(at1, at2);
        if (sub == null)
            System.out.println("non unificabili.");
        else
            System.out.println("sub: " + sub.toString());
        */
        /*
        // MGU literals + applySub to Clause
        Iterator<Literal> it = c.literals.iterator();
        Literal l1 = it.next();
        Literal l2 = it.next();
        InferenceSystem is = new InferenceSystem();
        Substitution sigma = is.mgu(l1, l2, true);
        if (sigma != null) {
            Clause cNuova = c.applySubstitution(sigma);
            System.out.println("unificabili con: " + sigma.toString());
            System.out.println("con risultato: " + cNuova.toString());
        }
        */
        
        /*
        // GETFACTORS di Clause
        c.getFactors();
        System.out.println(c.getFactorsString());
        */
        
        /*
        // prova MultiSet
        List<Term> ar = c.getLiterals().iterator().next().getAtom().getArgs();
        MultiSet ms = new MultiSet((List<Object>) (List<?>) ar);
        System.out.println(ms.toString());
        ms.addElement(c.getLiterals().iterator().next().getAtom().getArgs().get(0));
        ms.addElement(c.getLiterals().iterator().next().getAtom().getArgs().get(0));
        ms.addElement(c.getLiterals().iterator().next().getAtom().getArgs().get(0));
        Variable z_0 = new Variable("z_0");
        ms.addElement(z_0);
        System.out.println(ms.multiset.toString());
        System.out.println(ms.toString());
        
        for (Object t1 : ms.multiset.keySet())
            for (Object t2 : ms.multiset.keySet())
                if (t1.equals(t2))
                    System.out.println(t1 + " = " + t2);
                else
                    System.out.println(t1 + " != " + t2);
        */
        
    }
    
    /******************************************************************/
    
    private static void printUsage() {
        System.out.println(
                "Usage: ThProver [-i | <filename>] [-E]\n\n"
                + "\t-i\tstart interactive mode\n"
                + "\t-E\tuse the E version of the given clause loop\n");
    }
    
    private static void processOption(String o) {
        if (o.equals("-i")) {
            interactive = true;
        } else if (o.equals("-E")) {
            aLaE = true;
        }
    }

    private static String startInteractive() {
        Scanner stdin = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        String line;

        System.out.println("Please, insert the constants (separated by ',')");
        line = stdin.nextLine();
        sb.append("const: ").append(line).append('\n');
        
        System.out.println("Please, insert the precedence (signature symbol separate by '>', ? for help)");
        while (true) {
            line = stdin.nextLine();
            if (line.length() == 0)
                continue;
            if (line.equals("end"))
                break;
            if (line.equals("?")) {
                System.out.println(
                        "Insert a precedence (if partial a furthermore precedence can be inserted in a new line).\n"
                        + "Insert \"end\" in a line to terminate the formula.\n");
                continue;
            }
            sb.append("prec: ").append(line).append('\n');
        }
        
        System.out.println("Please, insert the formula (? for help)");

        while (true) {
            line = stdin.nextLine();

            if (line.equalsIgnoreCase("end")) {
                break;
            }
            if (line.equals("?")) {
                System.out.println(
                        "Insert a set of clauses (separated by whitespaces or line breaks).\n"
                        + "Use '!' or '~' for 'NOT'.\n"
                        + "Use '|' for 'OR'.\n"
                        + "Insert \"end\" in a line to terminate the formula.\n");
                continue;
            }
            if (line.length() == 0) {
                continue;
            }

            sb.append(line).append('\n');
        }

        return sb.toString();
    }

}
