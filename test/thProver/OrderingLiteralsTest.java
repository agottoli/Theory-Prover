/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class OrderingLiteralsTest {

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
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
            
            formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());
        
        System.out.println("PROVA ORDINAMENTO SU LETTERALI:");       
        /* ORDERING */
        Ordering or = new Ordering();        
        /* precedences */       
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());
        
        // seleziono i primi 2 letterali dalla prima clausola
        Iterator<Clause> it = f.getClauses().iterator();
        Clause c = it.next();
        Clause othC = it.next();
        Iterator<Literal> itL = c.getLiterals().iterator();
        Literal l1 = itL.next();
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

} 
