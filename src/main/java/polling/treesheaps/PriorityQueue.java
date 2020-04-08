package polling.treesheaps;

/**
 * Interface for PriorityQueues.
 * @param <E> the type of data in the queue
 */
public interface PriorityQueue<E extends Comparable<E>> {
    
    void insert(E element);
    
    E max();
    
    E removeMax();
    
    int size();
    
    boolean isEmpty();
}
