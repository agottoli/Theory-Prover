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
public class GivenClauseTest {
    
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
            String stringa =  
                    "const: a,b,c \n" +
                    "prec: ON>GREEN \n a>b>c \n" +
                    "clauses: ON(a,b) ; ON(b,c) ; GREEN(a) ; ~GREEN(c) \n" +
                    "~GREEN(x) | GREEN(y) | ~ON(x,y)";
            
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
        
        GivenClauseProver prover = new GivenClauseProver(true, false, false, true, true);
        Clause result = prover.satisfiable(f);
        if (result == null)
            sb.append("E` SODDISFACIBILE.");
        else {
            sb.append("E` INSODDISFACIBILE con prova:\n");
            sb.append(result.getDOT());
            
        }
        
       
        System.out.println(sb.toString());
        
    }
    
}
