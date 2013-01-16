/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thProver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ale
 */
public class MultiSet {
    
    // occhio: hashCode fondamentale
    public Map<Object, Integer> multiset = new HashMap<>();
    
    public MultiSet(List<Object> elements) {
        Integer count;
        for (Object t : elements) {
            if ((count = multiset.get(t)) != null)
                multiset.put(t, count+1);
            else
                multiset.put(t, 1);
        }
    }
    
    public MultiSet(Object element) {
        multiset.put(element, 1);
    }
    
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
    public void addElement(Object t) {
        Integer count;
        if ((count = multiset.get(t)) != null)
                multiset.put(t, count+1);
            else
                multiset.put(t, 1);
    }
    
    public void removeElement(Object t) {
        Integer count;
        if ((count = multiset.get(t)) == null)
            throw new IllegalStateException("MultiSet: si vuole rimuovere un elemento che non c'è.");
        if (count == 1)
            multiset.remove(t);
        else
            multiset.put(t, count-1);
    }
    
    public void removeElement(Object t, int n) {
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
    
    public int count(Object o) {
        Integer n;
        if ((n = multiset.get(o)) == null)
            return 0;
        return n;
    }
    
    public Set<Object> getDistintElements() {
        return multiset.keySet();
    }    
    
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
    
    public boolean isEmpty() {
        return multiset.isEmpty();
    }
    
    public MultiSet copy() {
        return new MultiSet(multiset); 
    }
}
