package thProver;

import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ragionamento Automatico, I semestre, Anno Accademico 2012-2013
 *
 * Dimostratore di Teoremi mediante Risoluzione Ordinata
 *
 * @author Alessandro Gottoli vr352595
 */
public class Main {

    private static boolean interactive = false;
    private static boolean aLaE = false;

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        /*
         * Read and process command line arguments
         */
        /*if (args.length == 0) {
            printUsage();
            System.exit(1);
        }*/
        args = new String[2];
        args[0] = "ThProver";
        args[1] = "-i";

        String fileName = null;
        for (String a : args) {
            if (a.startsWith("-")) {
                // it's an option
                processOption(a);
                continue;
            }

            fileName = a;
        }

        if (fileName == null && !interactive) {
            System.out.println("Please, specify a file name or -i");
            printUsage();
            System.exit(2);
        }

        /*
         * Read the formula
         */
        Reader formulaReader;
        if (interactive) {
            formulaReader = new StringReader(startInteractive());
        } else {
            formulaReader = new FileReader(fileName);
        }

        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        System.out.println(f.toString());
    }
    
    /******************************************************************/
    
    private static void printUsage() {
        System.out.println(
                "Usage: ThProver [-i | <filename>] [-E]\n\n"
                + "\t-i\tstart interactive mode\n"
                + "\t-E\tuse the E version of the given clause loop\n");
    }
    
    private static void processOption(String o) {
        if (o.equals("-i")) {
            interactive = true;
        } else if (o.equals("-E")) {
            aLaE = true;
        }
    }

    private static String startInteractive() {
        Scanner stdin = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        String line;

        System.out.println("Please, insert the constants (separated by ',')");
        line = stdin.nextLine();
        sb.append("const: ").append(line).append('\n');

        System.out.println("Please, insert the formula (? for help)");

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

        return sb.toString();
    }

}
