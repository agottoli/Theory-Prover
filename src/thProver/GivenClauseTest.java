package thProver;

import thProver.gui.JFileChooserDemo;
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
    private static boolean useStandard = true; //false;
    private static boolean aLaE = false;
    private static boolean tptp = false;
    private static int limit = -1;//Integer.MAX_VALUE;
    private static boolean gui = false;
    private static boolean vAll = false;
    private static boolean test = false;

    public static void main(String[] args) throws FileNotFoundException {

        /** /
        String[] arrayT = new String[]{"prover",
            "-gui",
            //"-kbo",
            //"-usP",
            "-sos",
            "/home/ale/NetBeansProjects/RA/Test.Ciclo.Clausola.Data/tptp.altro/COL123-2.p"
        };
        args = arrayT;
        / **/
        
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
            System.out.println("Parsing is starting..."); // ALESSIA //"Starting parsing...");
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
                    //useStandard = true;
                } catch (thProver.parser.TokenMgrError tme) {
                    //System.out.println(pe);
                    tptp = true;
                    //useStandard = true;
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
                    System.out.println("Parsing error: wrong sintax"); // ALESSIA
                                //"Errore di parsing.");
                }
            }

            if (f == null)
                return;

            // statistiche di parsing
            System.out.println("Parsing finished. " 
                    + f.getNumClausesAndSOS() + " clauses found."); // ALESSIA
            
            
            // Setting up ordering... // ALESSIA

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
            
            // Ordering setted // ALESSIA

            
            String grafo = null;
            GivenClauseProver prover = new GivenClauseProver(aLaE, sos, kbo,
                    multiset, useOrdering, or, limit, vAll);

            StringBuilder strb = new StringBuilder(
                    "Satisfiability proving is starting... ");
                    //"Starting satisfiability proving... ");
            
            if (aLaE)
                strb.append("à la E version");
            else
                strb.append("à la Otter version");
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
                
                sb.append("response: SATISFIABLE.\n"); // ALESSIA // "E` SODDISFACIBILE.\n");
                System.out.print(sb.toString());
            } else {
                sb.append("response: UNSATISFIABLE.\n");
                if (!test)
                    sb.append("  show the prove? [y,n]: "); // ALESSIA
                        //"E` INSODDISFACIBILE, stampare la prova? [y,n]: ");
                grafo = result.getDOT();
                //sb.append(grafo);
                System.out.print(sb.toString());



                Scanner stdin = new Scanner(System.in);
                String stamp = "n"; 
                if (!test)
                    stamp = stdin.nextLine();
                if (stamp.equalsIgnoreCase("yes") || stamp.equalsIgnoreCase("y"))
                    System.out.println(grafo);

                
                        // "\nUsare 'dot' per esportare un immagine del grafo della prova? [y,n]: ");
                stamp = "y"; 
                if (!test) {
                    System.out.print("\nUse 'dot' to export the prove graph as image? [y,n]: "); // ALESSIA
                    stamp = stdin.nextLine();
                }
                    
                if (stamp.equalsIgnoreCase("yes") || stamp.equalsIgnoreCase("y")) {
                    if (!test)
                        System.out.print("\nSelect export file format: [1=jpg, 2=ps, 3=pdf]: "); // ALESSIA
                            //"\nSelezionare il formato di esportazione: [1=jpg, 2=ps, 3=pdf]: ");
                    boolean flag = true;
                    String format = null;
                    while (flag) {
                        stamp = "3"; 
                        if (!test)
                            stamp = stdin.nextLine();
                        if (stamp.equals("1") || stamp.equals("jpg")) {
                            flag = false;
                            format = "jpg";
                        } else if (stamp.equals("2") || stamp.equals("ps")) {
                            flag = false;
                            format = "ps";
                        } else if (stamp.equals("3") || stamp.equals("pdf")) {
                            flag = false;
                            format = "pdf";
                        } else {
                            System.out.print("Bad choice, type 1, 2 or 3: "); // ALESSIA
                                    //"Scelta non corretta, digitare 1, 2 o 3: ");
                        }
                    }
                    String dir = null;
                    String name = null;
                    String nameNoExt = null;
                    if (file != null) {
                        dir = file.getParent();
                        name = file.getName();
                        int index = name.lastIndexOf('.');
                        if (index == -1) {
                            // il carattere . non 'è nel nome del file
                            nameNoExt = name;
                        } else {
                            nameNoExt = name.substring(0, index);
                        }
                    }
                                       
                    String sosString = "";
                    String versione = "-Otter";
                    if (aLaE) {
                        versione = "-E";
                    }
                    if (sos)
                        sosString = "-sos";
                    String ordString = "";
                    String precString = "";
                    if (useOrdering) {
                        if (kbo)
                            ordString = "-kbo";
                        else if (multiset)
                            ordString = "-mul";
                        else
                            ordString = "-lex";
                        
                        if (useStandard)
                            precString = "-stP";
                        else
                            precString = "-usP";
                    }
                    
                    
                    if (test) {
                        dir += "/risultati/" + name;
                        // stampo anche quello che ho dato a schermo su un file
                        try {
                            FileOutputStream outputRisTest = new FileOutputStream(dir + "/"
                                    + nameNoExt + versione + sosString + ordString + precString + ".txt");
                            PrintStream output = new PrintStream(outputRisTest);
                            output.println(strb.toString() + "\n"
                                    + prover.info() + "\n"
                                    + "\n" + sb.toString());
                        } catch (IOException e) {
                            System.out.println(
                                    "Error: write permission negated in directory " 
                                    + dir);
                                    //"Errore: non si ha permessi di scrittura nella"
                                    //+ "cartella " + dir);
                            return;
                        }
                        
                    }
                    
                    prover.exportDot(dir, 
                            nameNoExt + versione + sosString + ordString + precString + "." + format, 
                            format, grafo);
                }
                stdin.close();

            }
        }
    }

    /**
     * Print the usage instructions.
     */
    private static void printUsage() {
        System.out.println(
                "Usage: ThProver [-gui | -i | <filename>]"// [-ans]"
                + " [-sos] [(-lex | -mul | -kbo) [-usP | -stP]] [-E] [-limit<secs>] [-vAll]\n\n"
                + "\t-gui\tstart graphical user interface mode\n"
                + "\t-i\tstart interactive mode\n"
                //+ "\t-ans\tdetect answer clause\n"
                + "\t-sos\tuse Set-of-Support strategy\n"
                + "\t-lex\tuse lexicographic ordering (default no ordering is used)\n"
                + "\t-mul\tuse multiset ordering (default no ordering is used)\n"
                + "\t-kbo\tuse knuth-bendix ordering (default no ordering is used)\n"
                + "\t-usP\tuse user defined precedences and weights\n"
                + "\t\tif an ordering type is specified\n" // (default in not tptp syntax)\n"
                + "\t-stP\tuse a standard precedences and weights defined in class Ordering "
                + "\t\tif an ordering type is specified (default)\n"
                + "\t-E\tuse à la E version of the given clause loop (defaulf use à la Otter)\n"
                //+ "\t-tptp\tthe input file is in TPTP cnf format\n"
                + "\t-limit<secs>\tspecify a time limit - in seconds\n"
                + "\t-vAll\tversione sperimentale\n");
    }

    /**
     * Process the string in input to set up the parameter of the prover
     * 
     * @param o string with parameter for setting up
     */
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
        } else if (o.equals("-test")) {
            test = true;
        }
    }

    /**
     * Permit to insert the formula by standard input
     * 
     * @return string representing the input for parser
     */
    private static String startInteractive() {
        Scanner stdin = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        String line;

        System.out.println("Please, insert the constants (separated by ',')");
        line = stdin.nextLine();
        sb.append("const: ").append(line).append('\n');

        System.out.println("Please, insert the precedence (symbols separate by '>', ? for help)");
        sb.append("prec: ");
        while (true) {
            line = stdin.nextLine();
            if (line.length() == 0)
                continue;
            if (line.equals("end"))
                break;
            if (line.equals("?")) {
                System.out.println(
                        "Insert a precedence (if partial a further precedence can be inserted in a new line or with separator ';').\n"
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
