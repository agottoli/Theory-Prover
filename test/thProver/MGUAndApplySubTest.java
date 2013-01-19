/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;

/**
 *
 * @author ale
 */
public class MGUAndApplySubTest {

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
        String stringa =
                "const: a,b,0,c,d\n"
                + "prec: P>R>Q>ack>succ>per>piu>f>g>a>c>d>0\n"
                + "weightVars: 1\n"
                + "weights: P = 1; ack = 1; succ = 1; 0 = 1; a = 1\n"
                + "clauses: P(z,y) | ~P(x,g(u)) | P(x,g(x)) | ~P(u,v) | Q(b)";
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
                //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y) | P(v,y,y)";

        formulaReader = new StringReader(stringa);



        // parserizzo l'input
        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();

        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());

        System.out.println("PROVA TROVA MGU TRA TUTTI I LETTERALI DELLA PRIMA CLAUSOLA:");
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
        Substitution sigma = new Substitution();
        /*
         for (int choice = 0; choice < 3; choice++) {
       
         switch (choice) {
         case 0: or.setLexicographicOrdering(); break;
         case 1: or.setMultiSetOrdering(); break;
         case 2: or.setKBOrdering();

         }

         String tipo = or.getTipeOrdering();
         sb.append("\nPer ordinamento ").append(tipo);
         */
        for (Clause c : f.getClauses()) {
            sb.append("\n").append(c.toString());
            List<Literal> lits = new ArrayList<>(c.getLiterals());
            for (int x = 0; x < lits.size(); x++) {
                Literal litX = lits.get(x);
                for (int y = 0; y < lits.size(); y++) { // y = x + 1
                    Literal litY = lits.get(y);
                    sigma.clear();

                    sb.append("\nMGU tra ");
                    sb.append(litX.toString()).append(" e ").append(litY.toString()).append(" è: ");
                    if (InferenceSystem.mgu(litX, litY, true, sigma, false)) {
                        sb.append(sigma.toString());
                        long time = System.nanoTime();
                        sigma.renameVariables(time);
                        sb.append(" e ottengo ").append(c.applySubstitution(sigma, time));
                    } else
                        sb.append(" non esiste.");
                }
            }
        }

        System.out.println(sb.toString());
    }
}
