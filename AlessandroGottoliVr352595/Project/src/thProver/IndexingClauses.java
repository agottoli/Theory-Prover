package thProver;

/**
 * Index for clauses
 * 
 * @author Alessandro Gottoli vr352595
 */
public class IndexingClauses {
    public long nClauses;
    
    /**
     * Constructs a new indexer.
     * @param l index of the first clause
     */
    public IndexingClauses(long l) {
        nClauses = l;
    }
    
    /**
     * Return the current index and update it for the future call.
     * 
     * @return current index
     */
    public long getIndexAndIncrement() {
        long l = nClauses;
        nClauses++;
        return l;
    }
    
    /**
     * The number of clauses indexed (index of the next clause)
     * 
     * @return numeber of clauses
     */
    public long getNClauses() {
        return nClauses;
    }
}
