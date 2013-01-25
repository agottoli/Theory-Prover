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
import java.util.Scanner;
import javax.imageio.ImageIO;
import thProver.parser.CNFParser;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class GivenClauseTest {

    private static boolean answerLiteral = false;
    private static boolean interactive = false;
    private static boolean sos = false;
    private static boolean kbo = false;
    private static boolean multiset = false;
    private static boolean useOrdering = false;
    private static boolean useStandard = false;
    private static boolean aLaE = false;
    private static boolean tptp = false;
    private static int limit = -1;//Integer.MAX_VALUE;
    private static boolean gui = false;
    private static boolean vAll = false;

    public static void main(String[] args) throws FileNotFoundException {

        /*boolean v2 = false;
        String[] arrayT = {"prover",
            "-gui",
            //"-kbo",
            "-usP",
            "/home/ale/NetBeansProjects/RA/Test.Ciclo.Clausola.Data/esempi.miei/blocks.txt"
        };
        args = arrayT;*/
        /*
         * Read and process command line arguments
         */
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        String fileName = null;
        for (String a : args) {
            if (a.startsWith("-")) {
                // it's an option
                processOption(a);
                continue;
            }

            fileName = a;
        }

        if (fileName == null && !interactive && !gui) {
            System.out.println("Please, specify a file name or -i or -gui");
            printUsage();
            System.exit(2);
        }


        if (gui) {
            java.awt.EventQueue.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            new JFileChooserDemo().setVisible(true);
                        }
                    });
        } else {

            /*
             * Read the formula
             */
            Reader formulaReader;

            String stringa = "";

            if (interactive) {
                stringa = startInteractive();
                System.out.println(stringa);
            }

            //String folder = "";//"/home/ale/NetBeansProjects/RA/Test.Ciclo.Clausola.Data/";
            //fileName =
            //        "tptp.file/ALG002-1.p"; // non termina (insodd)
            //"tptp.file/PUZ001-3.p"; // soddisfacibile
            //"tptp.file/MGT031-1.p"; // non si può per =
            //"tptp.file/PUZ023-1.p"; // INSODD
            //"tptp.file/PUZ014-1.p"; // INSODD
            //"tptp.file/PLA001-1.p"; // insodd in 6min 15sec a la Otter :)
            //"tptp.file/PLA031-1.008.p"; // 
            //"esempi.miei/blocks.txt"; // INSODD ok
            //"tptp.file/es1.p"; // SODD
            //"esempi.miei/test.rinomina.txt"; SODD
            //"tptp.file/PUZ003-1.p"; // INSODD ok






            // parserizzo l'input
            CNFFormula f = null;
            File file = null;
            if (!tptp) {
                try {
                    if (interactive) {
                        formulaReader = new StringReader(stringa);
                    } else {
                        System.out.println("Reading file " + fileName + "...");
                        file = new File(fileName);
                        formulaReader = new FileReader(file);
                    }
                    CNFParser parser = new CNFParser(formulaReader);
                    parser.Start();
                    f = parser.getCNFFormula();
                } catch (thProver.parser.ParseException pe) {
                    //System.out.println(pe);
                    tptp = true;
                    useStandard = true;
                } catch (thProver.parser.TokenMgrError tme) {
                    //System.out.println(pe);
                    tptp = true;
                    useStandard = true;
                }
            }
            if (tptp) {
                try {
                    if (interactive) {
                        formulaReader = new StringReader(stringa);
                    } else {
                        //System.out.println("Reading file " + fileName + "...");
                        formulaReader = new FileReader(fileName);
                    }
                    CNFParserTptp parser = new CNFParserTptp(formulaReader);
                    parser.Start();
                    f = parser.getCNFFormula();
                } catch (thProver.parserTptp.ParseException petptp) {
                    //System.out.println(petptp); //"Errore di parsing.");
                    System.out.println("Errore di parsing.");
                }
            }

            if (f == null)
                return;

            // statistiche di parsing
            System.out.println(f.getNumClausesAndSOS() + " clauses.");

            /* ORDERING */
            Ordering or = new Ordering();
            /* precedences */
            //if (f.getPrecedences().isEmpty() && tptp) {
            // con tptp non c'è specificato un ordinamento allora
            // ne scelgo uno io standard
            or.setUseOrdStandard(useStandard);
            //    useStandard = true;
            //}
            or.setPrecedence(f.getPrecedences(), f.getNPrec());
            /* set KBO */
            or.setWeightsKBO(f.getWeights(), f.getWeightVars());

            /* DEBUG inizio */
            //System.out.println(f.getPrecedences().toString());
            /* DEBUG fine */

            
            String grafo = null;
            GivenClauseProver prover = new GivenClauseProver(aLaE, sos, kbo,
                    multiset, useOrdering, or, limit, vAll);

            StringBuilder strb = new StringBuilder("Starting satisfiability proving... ");
            
            if (aLaE)
                strb.append("à la E");
            else
                strb.append("à la Otter");
            if (useOrdering) {
                strb.append(" with");
                if (useStandard)
                    strb.append(" 'standard'");
                else
                    strb.append(" 'user specified'");
                if (kbo) {
                    strb.append(" kbo");
                } else if (multiset) {
                    strb.append(" multiset");
                } else {
                    strb.append(" lexicographic");
                }
                strb.append(" ordering");
            } else {
                strb.append(" with no ordering");
            }
            if (sos) {
                strb.append(" and set of support strategy");
            }


            System.out.println(strb.toString());

            Clause result = prover.satisfiable(f);
            
            StringBuilder sb = new StringBuilder();
          
            if (result == null) {
                
                sb.append("E` SODDISFACIBILE.\n");
                System.out.print(sb.toString());
            } else {
                sb.append("E` INSODDISFACIBILE, stampare la prova? [y,n]: ");
                grafo = result.getDOT();
                //sb.append(grafo);
                System.out.print(sb.toString());



                Scanner stdin = new Scanner(System.in);
                String stamp = stdin.nextLine();
                if (stamp.equalsIgnoreCase("yes") || stamp.equalsIgnoreCase("y"))
                    System.out.println(grafo);

                System.out.print("\nUsare 'dot' per esportare un immagine del grafo della prova? [y,n]: ");
                stamp = stdin.nextLine();
                if (stamp.equalsIgnoreCase("yes") || stamp.equalsIgnoreCase("y")) {
                    String dir = null;
                    String name = null;
                    if (file != null) {
                        dir = file.getParent();
                        name = file.getName();
                    }
                    prover.exportDot(dir, name, "jpg", grafo);
                }
                stdin.close();

            }
        }
    }

    private static void printUsage() {
        System.out.println(
                "Usage: ThProver [-gui | -i | <filename>] [-ans] [-sos] [(-lex | -mul | -kbo) [-usP | -stP]] [-E] [-limit<secs>] [-vAll]\n\n"
                + "\t-gui\tstart graphical user interface mode\n"
                + "\t-i\tstart interactive mode\n"
                + "\t-ans\tdetect answer clause\n"
                + "\t-sos\tuse Set-of-Support strategy\n"
                + "\t-lex\tuse lexicographic ordering (default no ordering is used)\n"
                + "\t-mul\tuse multiset ordering (default no ordering is used)\n"
                + "\t-kbo\tuse knuth-bendix ordering (default no ordering is used)\n"
                + "\t-usP\tuse user defined precedences and weights\n"
                + "\t\tif an ordering type is specified (default in not tptp syntax)\n"
                + "\t-stP\tuse a standard precedences and weights defined in class Ordering "
                + "\t\tif an ordering type is specified (default in tptp syntax)\n"
                + "\t-E\tuse à la E version of the given clause loop (defaulf use à la Otter)\n"
                //+ "\t-tptp\tthe input file is in TPTP cnf format\n"
                + "\t-limit<secs>\tspecify a time limit - in seconds\n"
                + "\t-vAll\tversione sperimentale\n");
    }

    private static void processOption(String o) {
        if (o.equals("-ans")) {
            answerLiteral = true;
        } else if (o.equals("-i")) {
            interactive = true;
        } else if (o.equals("-gui")) {
            gui = true;
        } else if (o.equals("-sos")) {
            sos = true;
        } else if (o.equals("-lex")) {
            useOrdering = true;
            multiset = false;
            kbo = false; // lo è già di default
        } else if (o.equals("-mul")) {
            useOrdering = true;
            multiset = true;
            kbo = false; // lo è già di default
        } else if (o.equals("-kbo")) {
            useOrdering = true;
            kbo = true;
        } else if (o.equals("-usP")) {
            useStandard = false;
        } else if (o.equals("-stP")) {
            useStandard = true;
        } else if (o.equals("-E")) {
            aLaE = true;
            //} else if (o.equals("-tptp")) {
            //    tptp = true;
        } else if (o.startsWith("-limit")) {
            limit = new Integer(o.substring(6));
        } else if (o.equals("-vAll")) {
            vAll = true;
        }
    }

    private static String startInteractive() {
        Scanner stdin = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        String line;

        System.out.println("Please, insert the constants (separated by ',')");
        line = stdin.nextLine();
        sb.append("const: ").append(line).append('\n');

        System.out.println("Please, insert the precedence (signature symbol separate by '>', ? for help)");
        sb.append("prec: ");
        while (true) {
            line = stdin.nextLine();
            if (line.length() == 0)
                continue;
            if (line.equals("end"))
                break;
            if (line.equals("?")) {
                System.out.println(
                        "Insert a precedence (if partial a furthermore precedence can be inserted in a new line or with separator ';').\n"
                        + "Insert \"end\" in a line to terminate the formula.\n");
                continue;
            }
            sb.append(line).append('\n');
        }

        System.out.println("Please, insert the formula (? for help)");
        sb.append("clauses: ");

        while (true) {
            line = stdin.nextLine();

            if (line.equalsIgnoreCase("end")) {
                break;
            }
            if (line.equals("?")) {
                System.out.println(
                        "Insert a set of clauses (separated by whitespaces or line breaks).\n"
                        + "Use '!' or '~' for 'NOT'.\n"
                        + "Use '|' for 'OR'.\n"
                        + "Insert \"end\" in a line to terminate the formula.\n");
                continue;
            }
            if (line.length() == 0) {
                continue;
            }

            sb.append(line).append('\n');
        }

        stdin.close();

        return sb.toString();
    }
}
