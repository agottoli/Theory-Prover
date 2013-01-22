package thProver;

import thProver.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
          
        String folder = "/home/ale/NetBeansProjects/RA/Test.Ciclo.Clausola.Data/";
        String fileName = 
                //"tptp.file/ALG002-1.p"; // non termina
                //"tptp.file/PUZ001-3.p"; // soddisfacibile
                //"tptp.file/MGT031-1.p"; // non si può per =
                //"tptp.file/PUZ023-1.p"; // INSODD
                //"tptp.file/PUZ014-1.p"; // INSODD
                //"tptp.file/PLA001-1.p"; // non termina
                //"tptp.file/PLA031-1.008.p"; // 
                //"esempi.miei/blocks.txt"; // INSODD ok
                //"tptp.file/es1.p"; // SODD
                "esempi.miei/test.rinomina.txt";

        boolean tptpFormat = false;

        // parserizzo l'input
        CNFFormula f = null;
        if (!tptpFormat) {
            try {
                //formulaReader = new StringReader(stringa);
                formulaReader = new FileReader(folder+fileName);
                CNFParser parser = new CNFParser(formulaReader);
                parser.Start();
                f = parser.getCNFFormula();
            } catch (thProver.parser.ParseException pe) {
                //System.out.println(pe);
                tptpFormat = true;
            } catch (thProver.parser.TokenMgrError tme) {
                //System.out.println(pe);
                tptpFormat = true;
            }
        } 
        if (tptpFormat) {
            try {
                //formulaReader = new StringReader(stringa);
                formulaReader = new FileReader(folder+fileName);
                CNFParserTptp parser = new CNFParserTptp(formulaReader);
                parser.Start();
                f = parser.getCNFFormula();
            } catch (thProver.parserTptp.ParseException petptp) {
                //System.out.println(petptp); //"Errore di parsing.");
                System.out.println("Errore di parsing.");
            }
        }
        //f = parser.getCNFFormula();
        if (f == null)
            return;
        //System.out.println(f.toString());
        //System.out.println(f.getTermsString());

        /* ORDERING */
        Ordering or = new Ordering();
        /* precedences */
        or.setPrecedence(f.getPrecedences(), f.getNPrec());
        /* set KBO */
        or.setWeightsKBO(f.getWeights(), f.getWeightVars());


        StringBuilder sb = new StringBuilder();
        String grafo = null;
        GivenClauseProver prover = new GivenClauseProver(true, false, false, true, true, or);
        Clause result = prover.satisfiable(f);
        if (result == null)
            sb.append("E` SODDISFACIBILE.");
        else {
            sb.append("E` INSODDISFACIBILE con prova:\n");
            grafo = result.getDOT();
            sb.append(grafo);

            try {
                FileOutputStream file = new FileOutputStream(folder + fileName + ".dot");
                PrintStream Output = new PrintStream(file);
                Output.println(grafo);
            } catch (IOException e) {
                System.out.println("Errore: " + e);
            }
            String cmd = "dot -Tjpg " + folder + fileName + ".dot" + " -O";
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
            //Picture p = new Picture(folder + fileName + ".dot" + ".jpg");
            //p.show();
            //sb.append(grafo);

        }


        System.out.println(sb.toString());

    }
}
