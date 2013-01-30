/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class SemplClausTest {
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
            // esempio dell'esercizio 4 del compito parziale
            //"Q(w) ; ~R(f(u)) | Q(f(u))";
                    "clauses: P(f(x)) ; Q(x,y) | P(r) | ~P(f(g(a)))";
            
            formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA SEMPLIFICAZIONE CLAUSALE:");          
        
        StringBuilder sb = new StringBuilder();
        
        // nuovo inizio
        IndexingClauses indexingC = new IndexingClauses(f.getNumClausesAndSOS());
        // nuovo fine
        
        for (Clause c : f.getClauses()) {
            for (Clause othC : f.getClauses()) {
                
                if (c == othC) continue;
                
                sb.append("\nLa clausola ");
                sb.append(c.toString());
                Clause semplificata;
                if ((semplificata = c.simplifies(othC, indexingC)) == null)
                    sb.append(" NON");
                sb.append(" semplifica ");
                sb.append(othC.toString());
                if (semplificata != null) {
                    sb.append(" originando ");
                    sb.append(semplificata.toString());
                }
                
            }
        }
        
       
        System.out.println(sb.toString());
        
    }    
}
