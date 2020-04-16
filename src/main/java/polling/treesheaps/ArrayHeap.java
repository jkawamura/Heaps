package polling.treesheaps;

import java.util.ArrayList;

/**
 * Creates an arrayheap that implements the priorityqueue interface. Adds some additional methods to
 * assist with inserting and sorting.
 *
 * @author Joseph Kawamura
 * @version 15 April 2020
 */
public class ArrayHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    ArrayList<E> heap; //creates an arraylist that will act as the heap
    int size; //stores the size of the heap

    /**
     * default constructor when no premade arraylist is given
     */
    public ArrayHeap(){
        heap = new ArrayList<>(); //instantiates an arraylist
        size = 0; //sets size to zero since it is empty by default
    }

    public ArrayHeap(ArrayList<E> array){
        size = array.size();
        heap = sortPhase1(array);
    }

    private ArrayList<E> sortPhase1(ArrayList<E> input){
        for(int i = 1; i < size; i++) {
            int index = i;
            while (index > 0) {
                //System.out.println("HERE");
                if (input.get(index).compareTo(input.get(parent(index))) > 0) {
                    int parentIndex = parent(index);
                    E temp = input.get(parent(index));
                    input.set(parentIndex, input.get(index));
                    input.set(index, temp);
                    index = parentIndex;
                } else{
                    break;
                }
            }
        } return input;
    }

    public void sort(){
        for (int i = (size - 1); i > 0; i--){
            //System.out.println(heap);
            swap(i,0);
            //System.out.println(heap);
            int end = i;
            //System.out.println(end);
            //System.out.println("\n");
            int index;
            bubbleDown(end);
        }
    }

    private void bubbleDown(int end){
        int index = 0;
        while(leftChild(index) < end) {

            //checks if right element is already sorted and
            // if the current element is greater than the left child
            if (rightChild(index) >= end && heap.get(index).compareTo(heap.get(leftChild(index))) > 0) {
                break;

                //checks if right child is already sorted and
                // if the current element is less than the left child
            } else if (rightChild(index) >= end && heap.get(index).compareTo(heap.get(leftChild(index))) < 0) {
                swap(index, leftChild(index));
                index = leftChild(index);

                //checks if left child is greater than right child and
                // checks if current element is less than left child
            } else if (heap.get(leftChild(index)).compareTo(heap.get(rightChild(index))) >= 0 &&
                    heap.get(index).compareTo(heap.get(leftChild(index))) < 0) {
                swap(index, leftChild(index));
                index = leftChild(index);

                //checks if right child is greater than left child
                // and if current element is less than right child
            } else if (heap.get(rightChild(index)).compareTo(heap.get(leftChild(index))) > 0 &&
                    heap.get(index).compareTo(heap.get(rightChild(index))) < 0) {
                swap(index, rightChild(index));
                index = rightChild(index);
            } else{
                break;
            }
        }
    }

    @Override
    public void insert(E element) {
        heap.add(element);
        //System.out.println(heap.toString());
        size++;
        if(!isEmpty()){
            int index = size - 1;
            //System.out.println("here");
            //System.out.println(element);
            //System.out.println(index);
            //System.out.println(heap.get(index));
            bubbleUp(index);
        }
    }


    private void bubbleUp(int index){
        while(index > 0){
            //System.out.println(index);
            //System.out.println(heap.get(parent(index)));
            //System.out.println(heap.get(index).compareTo(heap.get(parent(index))));
            if (heap.get(index).compareTo(heap.get(parent(index))) > 0){
                swap(index, parent(index));
                index = parent(index);
                //System.out.println(index);
            } else{
                break;
            }
        }
    }

    private void swap(int childIndex, int parentIndex){
        E temp = heap.get(parentIndex);
        heap.set(parentIndex, heap.get(childIndex));
        heap.set(childIndex, temp);
    }

    private int leftChild(int parentIndex){
        return 2 * parentIndex + 1;
    }

    private int rightChild(int parentIndex){
        return 2 * parentIndex + 2;
    }

    private int parent(int childIndex){
        return (childIndex - 1)/2;
    }

    @Override
    public E max() {
        return heap.get(0);
    }

    @Override
    public E removeMax() {
        E output = max();
        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        bubbleDown(size - 1);
        size--;
        return output;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        } else{
            return false;
        }
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        int powerCount1 = 0;
        int powerCount2 = 1;
        output.append(heap.get(0)).append("\n");
        for(int i = 0; i < size; i++){
            if(powerCount1 == powerTwo(powerCount2)){
                powerCount2++;
                powerCount1 = 0;
                //System.out.println(i);
                //System.out.println("here");
                output.append(heap.get(i)).append("\n");
            } else if(i != 0){
                output.append(heap.get(i)).append(" ");
            } powerCount1++;
        } return output.toString();
    }

    private int powerTwo(int power){
        int output = 1;
        for(int i = 0; i < power; i++){
            output *= 2;
        } return output;
    }
}
