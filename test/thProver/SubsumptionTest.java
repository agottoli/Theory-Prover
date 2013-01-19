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
public class SubsumptionTest {
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
            String stringa =  
            "const: a \n" + 
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            //"clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(g(f(z),x),f(v),w) | P(u,z,w) ; R(y) | ~P(g(x,y),x,y) \n" +
            // + esempio dell'esercizio 4 del compito parziale
            //"Q(w) ; ~R(f(u)) | Q(f(u))";
                    "clauses: P(x) | R(x,y) ; P(x) | R(z,w) | Q(z)";
            formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA SE UNA CLAUSOLA SUSSUME UN ALTRA:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
        // seleziono le prime 2 clausole
        //Iterator<Clause> it = f.getClauses().iterator();
        //Clause c = it.next();
        //Clause othC = it.next();
        
        
        StringBuilder sb = new StringBuilder();
        
        for (Clause c : f.getClauses()) {
            for (Clause othC : f.getClauses()) {
                
                if (c == othC) continue;
                
                String siNo = "";
                Substitution sigma;

                if ((sigma = c.subsumes(othC)) == null) //if (!c.subsumes(othC))
                    siNo = " NON";

                sb.append("\nLa clausola ");
                sb.append(c.toString());
                sb.append(siNo);
                sb.append(" sussume ");
                sb.append(othC.toString());
                if (sigma != null) {
                    sb.append(" con sostituzione ");
                    sb.append(sigma.toString());
                }
            }
        }
        
       
        System.out.println(sb.toString());
        
    }
    
}
