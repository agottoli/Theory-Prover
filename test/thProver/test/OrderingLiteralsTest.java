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
import thProver.CNFFormula;
import thProver.Clause;
import thProver.Literal;
import thProver.Ordering;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class OrderingLiteralsTest {

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
            //String stringa = 
                    //"const: a,b,0,c,d\n" +
                    //"prec: P>R>Q>ack>succ>per>piu>f>g>a>c>d>0\n" +
                    //"weightVars: 1\n" +
                    //"weights: P = 1; ack = 1; succ = 1; per = 1; piu = 1; f = 1; g = 1; 0 = 1; a = 1\n" +
                    //"clauses: P(z,y) | ~P(x,g(u)) | P(x,g(x)) | ~P(u,v) | Q(b)";
                    //"clauses: P(z,y) | P(x,g(x))";
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
        
        System.out.println("PROVA ORDINAMENTO SU LETTERALI (i primi 2 della prima clausola):");       
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
        
        // seleziono i primi 2 letterali dalla prima clausola
        Iterator<Clause> it = f.getClauses().iterator();
        if (!it.hasNext()) {
            System.out.println("Il file deve contenere una clausola con 2 letterali.");
            System.exit(2);
        }
        Clause c = it.next();
        //Clause othC = it.next();
        Iterator<Literal> itL = c.getLiterals().iterator();
        if (!itL.hasNext()) {
            System.out.println("Il file deve contenere una clausola con 2 letterali.");
            System.exit(2);
        }
        Literal l1 = itL.next();
        if (!itL.hasNext()) {
            System.out.println("Il file deve contenere una clausola con 2 letterali.");
            System.exit(2);
        }
        Literal l2 = itL.next();
        System.out.println("c: " + c.toString());
        
        for (int choice = 0; choice < 3; choice++) {
       
            switch (choice) {
                case 0: or.setLexicographicOrdering(); break;
                case 1: or.setMultiSetOrdering(); break;
                case 2: or.setKBOrdering();

            }

            String tipo = or.getTipeOrdering();
            
            /* Ordinamento letterali */
            if (or.isGreater(l1, l2))
                System.out.println(l1.toString() + " >" + tipo + " " + l2.toString());
            else if (or.isGreater(l2, l1))
                System.out.println(l1.toString() + " <" + tipo + " " + l2.toString());
            else
                System.out.println(l1.toString() + " #" + tipo + " " + l2.toString());
        }
   
    }
    
    private static void printUsage() {
        System.out.println(
                "Usage: OrderingLiteralsTest <filename> [x_standard_prec]\n");
    }

} 
