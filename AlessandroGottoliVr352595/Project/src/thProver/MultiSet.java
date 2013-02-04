package thProver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a multiset.
 * 
 * @author Alessandro Gottoli vr352595
 */
public class MultiSet {
    
    // occhio: hashCode fondamentale
    public Map<Object, Integer> multiset = new HashMap<>();
    
    /**
     * Constructs a new multiset with the given elements.
     * 
     * @param elements elements of the multiset
     */
    public MultiSet(List<Object> elements) {
        Integer count;
        for (Object t : elements) {
            if ((count = multiset.get(t)) != null)
                multiset.put(t, count+1);
            else
                multiset.put(t, 1);
        }
    }
    
    /**
     * Constructs a new multiset with the given element.
     * 
     * @param element element of the multiset
     */
    public MultiSet(Object element) {
        multiset.put(element, 1);
    }
    
    /**
     * Constructs a new multiset from a map of another multiset
     * 
     * @param map map table of another multiset
     */
    public MultiSet(Map<Object, Integer> map) {
        multiset.putAll(map);
    }
    
/*    
    public MultiSet(Set<Literal> elements) {
        Integer count;
        for (Object t : elements) {
            if ((count = multiset.get(t)) != null)
                multiset.put(t, count+1);
            else
                multiset.put(t, 1);
        }
    }
  */ 
    /**
     * Add the given element in the multiset.
     * 
     * @param t element to add
     */
    public void addElement(Object t) {
        Integer count;
        if ((count = multiset.get(t)) != null)
                multiset.put(t, count+1);
            else
                multiset.put(t, 1);
    }
    
    /**
     * Remove the given element if it is present.
     * 
     * @param t element to remove
     * @throws IllegalStateException if the element to remove is not present in the multiset
     */
    public void removeElement(Object t) throws IllegalStateException {
        Integer count;
        if ((count = multiset.get(t)) == null)
            throw new IllegalStateException("MultiSet: si vuole rimuovere un elemento che non c'è.");
        if (count == 1)
            multiset.remove(t);
        else
            multiset.put(t, count-1);
    }
    
    /**
     * Remove the number of the given elements if it is present.
     * 
     * @param t element to remove
     * @param n number to remove
     * @throws IllegalStateException if the element to remove is not present in the multiset
     *                               or if it is present in fewer number
     */
    public void removeElement(Object t, int n) throws IllegalStateException {
        Integer count;
        if ((count = multiset.get(t)) == null)
            throw new IllegalStateException("MultiSet: si vuole rimuovere un elemento che non c'è.");
        if (count < n)
            throw new IllegalStateException("MultiSet: si vuole rimuovere più istanze dello stesso elemento di quante ce ne sono.");
        if (count == n)
            multiset.remove(t);
        else
            multiset.put(t, count-1);
    }
    
    /**
     * Return the number of the given element present in the multiset
     * @param o element
     * @return the number of occurrence of the element, 0 if non present
     */
    public int count(Object o) {
        Integer n;
        if ((n = multiset.get(o)) == null)
            return 0;
        return n;
    }
    
    /**
     * Return the set of dinstinct elements in the multiset
     * @return the set of dinstinct elements in the multiset
     */
    public Set<Object> getDistintElements() {
        return multiset.keySet();
    }    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MultiSet: { ");
        boolean flag = true;
        for (Object o : multiset.keySet()) {
            int i = 0;
            if (flag) {
                sb.append(o.toString()).append(" ").append(o.hashCode());
                i = 1;
                flag = false;
            }

            for (; i < multiset.get(o); i++) {
                sb.append(", ").append(o.toString()).append(" ").append(o.hashCode());
            }
        }
        
        sb.append(" }");
        return sb.toString();
    }
    
    /**
     * Check if the multiset is empty.
     * 
     * @return true if ther is at least one element in the multiset, false otherwise 
     */
    public boolean isEmpty() {
        return multiset.isEmpty();
    }
    
    /**
     * Return a deep copy of the multiset.
     * 
     * @return copy of the multiset 
     */
    public MultiSet copy() {
        return new MultiSet(multiset); 
    }
}
