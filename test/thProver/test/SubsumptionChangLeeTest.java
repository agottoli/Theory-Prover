/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import thProver.Atom;
import thProver.CNFFormula;
import thProver.Clause;
import thProver.Constant;
import thProver.Function;
import thProver.Literal;
import thProver.Ordering;
import thProver.Substitution;
import thProver.Term;
import thProver.Variable;
import thProver.parser.CNFParser;
import thProver.parser.ParseException;
import thProver.parserTptp.CNFParserTptp;

/**
 *
 * @author ale
 */
public class SubsumptionChangLeeTest {
    private static void printUsage() {
        System.out.println(
                "Usage: SubsumptionTest <filename>\n");
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
            "const: a \n" + 
            // esempio trova tutti i risolventi
            //"clauses: ~P(x,y,u) | ~P(y,z,v) | ~P(x,v,w) | P(u,z,w) ; P(g(x,y),x,y)";
            //"clauses: ~P(x,y,u) | R(x) | ~P(y,z,v) | ~P(g(f(z),x),f(v),w) | P(u,z,w) ; R(y) | ~P(g(x,y),x,y) \n" +
            // + esempio dell'esercizio 4 del compito parziale
            //"Q(w) ; ~R(f(u)) | Q(f(u))";
                    //"clauses: P(x) | R(x,y) ; P(x) | R(z,w) | Q(z)";
                    
                    // prova sussunzione propria
                    "clauses: P(x) | R(x,y) ; P(x) | R(z,w)";
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
        
        for (Clause c : f.getClauses()) {
            for (Clause othC : f.getClauses()) {
                
                if (c == othC) continue;
                
                String siNo = "";
                //Substitution sigma;

                if (!c.subsumesChangLee(othC)) //if (!c.subsumes(othC))
                    siNo = " NON";

                sb.append("\nLa clausola ");
                sb.append(c.toString());
                sb.append(siNo);
                sb.append(" sussume ");
                sb.append(othC.toString());
                /*if (sigma != null) {
                    sb.append(" con sostituzione ");
                    sb.append(sigma.toString());
                }*/
            }
        }
        
       
        System.out.println(sb.toString());
        
    }
    
    /*
     public static void main(String[] args) throws ParseException, FileNotFoundException {

        Constant cost1 = new Constant("cost1");
        Constant cost2 = new Constant("cost2");
        Variable var1 = new Variable("var1");
        Variable var2 = new Variable("var2");
        Variable var3 = new Variable("var3");
        List<Term> argsFun1 = new ArrayList();
        argsFun1.add(cost1);
        argsFun1.add(var1);
        argsFun1.add(var1);
        argsFun1.add(cost2);
        Function fun1 = new Function("fun1", argsFun1);
        List<Term> argsAtom1 = new ArrayList();
        argsAtom1.add(var2);
        argsAtom1.add(fun1);
        argsAtom1.add(cost2);
        Atom atom1 = new Atom("atom1", argsAtom1);
        Literal lit1 = new Literal(false, atom1);
        
        List<Term> argsAtom2 = new ArrayList();
        argsAtom2.add(fun1);
        argsAtom2.add(var2);
        argsAtom2.add(var3);
        Atom atom2 = new Atom("atom2", argsAtom2);
        Literal lit2 = new Literal(false, atom2);
        
        Clause c1 = new Clause(0);
        c1.addLiteral(lit1);
        c1.addLiteral(lit2);
        
        System.out.println(c1.toString());
        System.out.println("dopo fase1:");
        System.out.println(c1.fase1(c1).toString());
        
    }
    */
}
