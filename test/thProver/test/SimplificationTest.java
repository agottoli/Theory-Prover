/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import thProver.CNFFormula;
import thProver.Clause;
import thProver.IndexingClauses;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class SimplificationTest {
    private static void printUsage() {
        System.out.println(
                "Usage: SimplificationTest <filename>\n");
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
            "const: a \n" + 
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            //"clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(g(f(z),x),f(v),w) | P(u,z,w) ; R(y) | ~P(g(x,y),x,y) \n" +
            // esempio dell'esercizio 4 del compito parziale
            //"Q(w) ; ~R(f(u)) | Q(f(u))";
                    "clauses: P(f(x)) ; Q(x,y) | P(r) | ~P(f(g(a)))";
            
            //formulaReader = new StringReader(stringa);



        // parserizzo l'input
        boolean tptp = false;
        CNFFormula f = null;
        File file = null;
        System.out.println("Parsing is starting..."); // ALESSIA //"Starting parsing...");
        if (!tptp) {
            try {
                System.out.println("Reading file " + fileName + "...");
                file = new File(fileName);
                formulaReader = new FileReader(file);
                CNFParser parser = new CNFParser(formulaReader);
                parser.Start();
                f = parser.getCNFFormula();
            } catch (thProver.parser.ParseException pe) {
                //System.out.println(pe);
                tptp = true;
                //useStandard = true;
            } catch (thProver.parser.TokenMgrError tme) {
                //System.out.println(pe);
                tptp = true;
                //useStandard = true;
            }
        }
        if (tptp) {
            try {
                //System.out.println("Reading file " + fileName + "...");
                formulaReader = new FileReader(fileName);
                CNFParserTptp parser = new CNFParserTptp(formulaReader);
                parser.Start();
                f = parser.getCNFFormula();
            } catch (thProver.parserTptp.ParseException petptp) {
                //System.out.println(petptp); //"Errore di parsing.");
                System.out.println("Parsing error: wrong sintax"); // ALESSIA
                //"Errore di parsing.");
            }
        }

        if (f == null)
            return;
        
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
