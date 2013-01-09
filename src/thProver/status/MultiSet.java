/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver.status;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ale
 */
public abstract class MultiSet {
    
    
    public static boolean greater(String[] m, String[] n) { // ???? DA FARE
        if (m == null || m.length == 0) {
            return false;
        }
        // caso 1
        if (n == null || n.length == 0) {
            return true;
        }
        // caso 2
        String common;
        if ((common = findCommon(m,n)) != null) {
            return greater(m,n);
        }
        // caso 3
        if (true) return true;
        else return false;
    }
    
    public static String findCommon(String[] m, String[] n) {
        // alla chiamata si controlla già se m o n è vuoto
        // ???? meglio selezionare un elemento dall'insieme più grande
        //      e controllare se è presente in uno più piccolo o viceversa?
        return "DA FARE";
    }
    
    
    
    
    
}
