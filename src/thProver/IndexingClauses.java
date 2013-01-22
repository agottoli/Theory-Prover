/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

/**
 *
 * @author ale
 */
public class IndexingClauses {
    public long nClauses;
    
    public IndexingClauses(long l) {
        nClauses = l;
    }
    
    public long getIndexAndIncrement() {
        long l = nClauses;
        nClauses++;
        return l;
    }
    
    public long getNClauses() {
        return nClauses;
    }
}
