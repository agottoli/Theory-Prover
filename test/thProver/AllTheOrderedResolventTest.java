/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Set;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class AllTheOrderedResolventTest {

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
            String stringa = 
                    "prec: P>R>L>f>g\n" +
                    "weightVars: 1\n" +
                    "weights: L = 1; P = 1; ack = 1; succ = 1; 0 = 1; g = 1; f = 1; a = 1\n" +
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; ~R(y) | P(g(x,y),x,y)";
            "clauses: P(z,y) | L(f(f(x,y),z)) ; L(f(x1,f(y1,z1))) | ~P(x1,g(x1))";
                    
                    
            formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA TROVA TUTTI I RISOLVENTI MASSIMALI DI UNA CLAUSOLA:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
        // seleziono le prime 2 clausole
        Iterator<Clause> it = f.getClauses().iterator();
        Clause c = it.next();
        Clause othC = it.next();

        
        StringBuilder sb = new StringBuilder();
        
        for (int choice = 0; choice < 3; choice++) {
       
            switch (choice) {
                case 0: or.setLexicographicOrdering(); break;
                case 1: or.setMultiSetOrdering(); break;
                case 2: or.setKBOrdering();

            }

            String tipo = or.getTipeOrdering();
            sb.append("\nPer ordinamento ").append(tipo);
            Set<Clause> resolvents = c.allTheOrderedResolvents(othC, or);
            sb.append("\nTutti i risolventi massimali tra ");
            sb.append(c.toString()).append(" e ").append(othC.toString()).append(" sono:\n");
            for (Clause riso : resolvents) {
                if (!riso.isTautology())
                    sb.append(riso.toString()).append(";\n");
            }
        }
       
        System.out.println(sb.toString());
        
    }

} 
