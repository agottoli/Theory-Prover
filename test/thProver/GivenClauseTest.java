/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import javax.imageio.ImageIO;
import thProver.parser.CNFParser;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class GivenClauseTest {

    public static void main(String[] args) throws FileNotFoundException {

        /*
         * Read the formula
         */
        Reader formulaReader;
        String stringa =
                /*"const: a,b,c \n" +
                 "prec: ON>GREEN \n a>b>c \n" +
                 "weightVars: 1\n" +
                 "weights: ON = 1; GREEN = 1; a = 1; b = 1; c = 1 \n" +
                 "clauses: ON(a,b) ; ON(b,c) ; GREEN(a) ; ~GREEN(c) \n" +
                 "~GREEN(x) | GREEN(y) | ~ON(x,y)";*/
                "cnf(clause_1,culo,on(a,b)).\n"
                + "cnf(clause_2,culo,on(b,c)).\n"
                + "cnf(clause_3,culo,green(a)).\n"
                + "cnf(clause_4,culo,~green(c)).\n"
                + "cnf(clause_5,negated,~green(X)|green(Y)|~on(X,Y)).";
        /*"cnf(mp_positive_number_when_appear_20,axiom,"
         + "( ~ environment(A) "
         + "| greater(number_of_organizations(e,appear(an_organisation,A)),zero) )).\n"
         + "cnf(mp_number_mean_non_empty_21,axiom,"
         + "( ~ environment(A) "
         + "| ~ greater(number_of_organizations(A,B),zero) "
         + "| subpopulation(sk1(B,A),A,B) )).\n"
         + "cnf(mp_number_mean_non_empty_22,axiom, "
         + "( ~ environment(A) "
         + "| ~ greater(number_of_organizations(A,B),zero) "
         + "| greater(cardinality_at_time(sk1(B,A),B),zero) )).\n";
         */





        // parserizzo l'input
        CNFFormula f = null;
        try {
            formulaReader = new StringReader(stringa);
            CNFParser parser = new CNFParser(formulaReader);
            parser.Start();
            f = parser.getCNFFormula();
        } catch (thProver.parser.ParseException pe) {
            try {
                formulaReader = new StringReader(stringa);
                CNFParserTptp parser = new CNFParserTptp(formulaReader);
                parser.Start();
                f = parser.getCNFFormula();
            } catch (thProver.parserTptp.ParseException petptp) {
                System.out.println(petptp); //"Errore di parsing.");
            }
        }
        //f = parser.getCNFFormula();
        if (f == null)
            return;
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
        String grafo = null;
        GivenClauseProver prover = new GivenClauseProver(true, false, false, true, true, or);
        Clause result = prover.satisfiable(f);
        if (result == null)
            sb.append("E` SODDISFACIBILE.");
        else {
            sb.append("E` INSODDISFACIBILE con prova:\n");
            grafo = result.getDOT();
            //sb.append(grafo);
            try {
                FileOutputStream file = new FileOutputStream("DOTfile/file.txt");
                PrintStream Output = new PrintStream(file);
                Output.println(grafo);
            } catch (IOException e) {
                System.out.println("Errore: " + e);
            }
            String cmd = "dot -Tjpg DOTfile/file.txt -O";
            Runtime run = Runtime.getRuntime();
            Process pr = null;
            try {
                pr = run.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                pr.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
             BufferedReader buf = new BufferedReader(
             new InputStreamReader(pr.getInputStream()));
             String line = "";
             try {
             while ((line = buf.readLine()) != null) {
             System.out.println(line);
             }
             } catch (IOException e) {
             e.printStackTrace();
             }
             */
            try {
                // the line that reads the image file
                BufferedImage image = ImageIO.read(new File("DOTfile/file.txt.jpg"));
                // work with the image here ...
                //image.
            } catch (IOException e) {
                // log the exception
                // re-throw if desired
            }
        }


        System.out.println(sb.toString());


    }
}
