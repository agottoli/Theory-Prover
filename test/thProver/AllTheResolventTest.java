/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class AllTheResolventTest {

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
            String stringa =                    
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            "clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; ~R(y) | P(g(x,y),x,y)";
            
            formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA TROVA TUTTI I RISOLVENTI DI UNA CLAUSOLA:");       
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

         Set<Clause> resolvents = c.allTheResolvents(othC);
         
        StringBuilder sb = new StringBuilder("Tutti i risolventi tra ");
        sb.append(c.toString()).append(" e ").append(othC.toString()).append(" sono:\n");
        for (Clause riso : resolvents) {
            if (!riso.isTautology())
                sb.append(riso.toString()).append(";\n");
        }
       
        System.out.println(sb.toString());
        
    }

} 