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
            String stringa = 
                    "const: a,b,c,d\n" +
                    "prec: P>R>f>g>c>d\n" + 
                   // "sos: Q(c)" +
                    "clauses: P(f(f(x,y),z)) | P(f(x,f(y,z))) \n Q(b)\n";
            formulaReader = new StringReader(stringa);
            //formulaReader = new StringReader(startInteractive());
        } else {
            formulaReader = new FileReader(fileName);
        }

        CNFFormula f;
        CNFParser parser = new CNFParser(formulaReader);
        parser.Start();
        f = parser.getCNFFormula();
        
        System.out.println(f.toString());
        
        // ???? prova ordinamento
        System.out.println("PROVA ORDINAMENTO LETTERALI:");
        Clause c = f.getClauses().iterator().next();
        System.out.println("c: " + c.toString());
        Ordering or = new Ordering();
        or.setPrecedence(f.getPrecedences(), f.getNPrec(), false);
        Literal l1 = c.literals.get(0);
        Literal l2 = c.literals.get(1);
        /*if (or.isGreater(l1, l2))
            System.out.println(l1.toString() + " > " + l2.toString());
        else if (or.isGreater(l2, l1))
            System.out.println(l2.toString() + " > " + l1.toString());
        else
            System.out.println(l1.toString() + " # " + l2.toString());
        */
        Term t1 = l1.getAtom().getArgs()[0];
        Term t2 = l2.getAtom().getArgs()[0];
        if (or.isGreater(t1, t2))
            System.out.println(t1.toString() + " > " + t2.toString());
        else if (or.isGreater(t2, t1))
            System.out.println(t2.toString() + " > " + t1.toString());
        else
            System.out.println(t1.toString() + " # " + t2.toString());
        
        
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
        
        System.out.println("Please, insert the precedence (signature symbol separate by '>', ? for help)");
        while (true) {
            line = stdin.nextLine();
            if (line.length() == 0)
                continue;
            if (line.equals("end"))
                break;
            if (line.equals("?")) {
                System.out.println(
                        "Insert a precedence (if partial a furthermore precedence can be inserted in a new line).\n"
                        + "Insert \"end\" in a line to terminate the formula.\n");
                continue;
            }
            sb.append("prec: ").append(line).append('\n');
        }
        
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
