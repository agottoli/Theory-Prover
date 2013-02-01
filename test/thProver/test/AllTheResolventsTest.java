/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import thProver.CNFFormula;
import thProver.Clause;
import thProver.IndexingClauses;
import thProver.Ordering;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class AllTheResolventsTest {
    private static void printUsage() {
        System.out.println(
                "Usage: AllTheResolventsTest <filename>\n");
    }
    
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        if (args.length < 1) {
            printUsage();
            System.exit(1);
        }
        
        String fileName = args[0];
        
        /*
         * Read the formula
         */
        Reader formulaReader = new FileReader(fileName);
            String stringa =                    
                    "prec: L>P>R>f>g\n" +
                    "weightVars: 1\n" +
                    "weights: L = 1; P = 1; ack = 1; succ = 1; 0 = 1; g = 1; f = 1; a = 1\n" +
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; ~R(y) | P(g(x,y),x,y)";
            "clauses: P(z,y) | L(f(f(x,y),z)) ; L(f(x,f(y,z))) | ~P(x,g(x))";
            
            //formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA TROVA TUTTI I RISOLVENTI TRA 2 CLAUSOLE:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
        // seleziono le prime 2 clausole
        Iterator<Clause> it = f.getClauses().iterator();
        if (!it.hasNext()) {
            System.out.println("Il file deve contenere 2 clausole.");
            System.exit(2);
        }
        Clause c = it.next();
        if (!it.hasNext()) {
            System.out.println("Il file deve contenere 2 clausole.");
            System.exit(2);
        }
        Clause othC = it.next();

        // nuovo inizio
        IndexingClauses indexingC = new IndexingClauses(f.getNumClausesAndSOS());
        // nuovo fine
         Set<Clause> resolvents = c.allTheResolvents(othC, indexingC);
         
        StringBuilder sb = new StringBuilder("Tutti i risolventi tra ");
        sb.append(c.toString()).append(" e ").append(othC.toString()).append(" sono:\n");
        for (Clause riso : resolvents) {
            if (!riso.isTautology())
                sb.append(riso.toString()).append(";\n");
        }
       
        System.out.println(sb.toString());
        
    }

} 