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
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import thProver.CNFFormula;
import thProver.Clause;
import thProver.Literal;
import thProver.Ordering;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class MaximalLiteralsTest {

    private static void printUsage() {
        System.out.println(
                "Usage: MaximalLiteralsTest <filename>\n");
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
            
            //formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA TROVA LETTERALI MASSIMALI:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
            
        // trovo i letterali massimali in ogni clausola
        for (int choice = 0; choice < 3; choice++) {
       
            switch (choice) {
                case 0: or.setLexicographicOrdering(); break;
                case 1: or.setMultiSetOrdering(); break;
                case 2: or.setKBOrdering();

            }

            String tipo = or.getTipeOrdering();
            System.out.println("\n-----------------------------------"
                    + "\nTrovo i letterali massimali con " + tipo);
            for (Clause c : f.getClauses()) {
                System.out.println("per la clausola " + c.toString() + " sono:");
                List<Literal> maxLits = c.getMaximalLiterals(or);
                c.resetForOtherOrdering();
                System.out.println(maxLits.toString());
                
            }
        }

    }

} 

