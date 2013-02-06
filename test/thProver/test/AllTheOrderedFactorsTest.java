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
import java.util.Iterator;
import java.util.Set;
import thProver.CNFFormula;
import thProver.Clause;
import thProver.IndexingClauses;
import thProver.Ordering;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class AllTheOrderedFactorsTest {
    private static void printUsage() {
        System.out.println(
                "Usage: AllTheOrderedFactorsTest <filename>  [x_standard_prec]\n");
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
            //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            "clauses: ~P(x,y,u) | R(x) | R(f(z)) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w)";
            
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
        
        System.out.println("PROVA TROVA FATTORI MASSIMALI:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
        boolean useStandard = false;
        if (args.length > 1)
            useStandard = true;
        or.setUseOrdStandard(useStandard);
     
        StringBuilder sb = new StringBuilder();
        
        for (int choice = 0; choice < 3; choice++) {
       
            switch (choice) {
                case 0: or.setLexicographicOrdering(); break;
                case 1: or.setMultiSetOrdering(); break;
                case 2: or.setKBOrdering();

            }

            String tipo = or.getTipeOrdering();
            
            sb.append("\nPer ordinamento ").append(tipo);
            
            // nuovo inizio
        IndexingClauses indexingC = new IndexingClauses(f.getNumClausesAndSOS());
        // nuovo fine
            
            for (Clause c : f.getClauses()) {
                sb.append("\n-------\nTutti i fattori massimali non tautologie di " + c.toString() + " sono:\n");
                c.orderedFactorization(or, indexingC, true);
                sb.append(c.getMaximalFactorsString());
                c.resetForOtherOrdering();
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
        
    }
    
}
