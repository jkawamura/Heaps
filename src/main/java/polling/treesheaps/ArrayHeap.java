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

    /**
     * constructor is used when a user wants to heapify an existing arraylist
     * @param array an existing arraylist
     */
    public ArrayHeap(ArrayList<E> array){
        size = array.size(); //sets the size equal to the size of the arraylist argument
        heap = sortPhase1(array); //uses heap sort to heapify the arraylist
    }

    /**
     * turns an arraylist into an arrayheap using inplace sorting.
     * @param input the arraylist to be sorted
     * @return a sorted arraylist
     */
    private ArrayList<E> sortPhase1(ArrayList<E> input){

        //loops through the entire length of the arraylist
        for(int i = 1; i < size; i++) {
            int index = i; //stores the current index to avoid changing the for-loop

            //while loop lasts until the current element is placed in the root
            // or until it is found to be smaller than its parent
            while (index > 0) {

                //checks if the current element is larger than its parent
                if (input.get(index).compareTo(input.get(parent(index))) > 0) {

                    //swaps the element at the current index with its parent
                    int parentIndex = parent(index);
                    E temp = input.get(parentIndex);
                    input.set(parentIndex, input.get(index));
                    input.set(index, temp);

                    //updates the index to the parent-index where the element was placed.
                    index = parentIndex;

                //if the element is not larger than its parent then the loop is broken and the next element
                //in the arraylist is checked.
                } else{
                    break;
                }
            }
        } return input;
    }

    /**
     * in-place sorts a heap into a sorted array (smallest values to largest) using phase II of heap sort.
     */
    public void sort(){

        //loops through the arraylist from back to front
        for (int i = (size - 1); i > 0; i--){

            //swaps each element with the first element
            swap(i,0);

            //bubbles down the new-first element
            bubbleDown(i);
        }
    }

    /**
     * bubbles down the first element in the arraylist using in-place sorting.
     * @param end the boundary between the sorted elements at the end of the arraylist and the unsorted elements
     */
    private void bubbleDown(int end){

        int index = 0; //resets the index for each new element that is bubbled down

        //loops until the current index has no more children to compare against
        while(leftChild(index) < end) {

            //checks if right child is already sorted and
            // if the current element is greater than the left child
            if (rightChild(index) >= end && heap.get(index).compareTo(heap.get(leftChild(index))) > 0) {

                //breaks the loop as no more bubbling down is needed (left child is smaller
                // and right child is already sorted
                break;

                //checks if right child is already sorted and
                // if the current element is less than the left child
            } else if (rightChild(index) >= end && heap.get(index).compareTo(heap.get(leftChild(index))) < 0) {

                //swaps the current element with the left child and updates the index accordingly
                swap(index, leftChild(index));
                index = leftChild(index);

                //checks if left child is greater than right child and
                //checks if current element is less than left child
            } else if (heap.get(leftChild(index)).compareTo(heap.get(rightChild(index))) >= 0 &&
                    heap.get(index).compareTo(heap.get(leftChild(index))) < 0) {

                //swaps the current element with the left child and updates the index accordingly
                swap(index, leftChild(index));
                index = leftChild(index);

                //checks if right child is greater than left child
                // and if current element is less than right child
            } else if (heap.get(rightChild(index)).compareTo(heap.get(leftChild(index))) > 0 &&
                    heap.get(index).compareTo(heap.get(rightChild(index))) < 0) {

                //swaps the current element with the right child and updates the index accordingly
                swap(index, rightChild(index));
                index = rightChild(index);

            //breaks the loop in all other cases
            } else{
                break;
            }
        }
    }

    @Override
    /**
     * inserts a new element, bubbling up the element to re-heapify the arraylist.
     * @param element the new element to be placed in the heap
     */
    public void insert(E element) {

        //adds the element to the end of the arraylists and increases the size counter of the arraylist
        heap.add(element);
        size++;

        //does nothing else if the arraylist is empty, otherwise it calls the bubbleUp method.
        if(size > 0){
            bubbleUp();
        }
    }

    /**
     * bubbles up larger values to the top of the heap by comparing them to their parent element.
     */
    private void bubbleUp(){

        int index = size - 1; //stores  the index of the current element in the arraylist

        //loop lasts until the element is placed in the root
        // or until it is found to be smaller than its parent
        while(index > 0){

            //checks if the current element is larger than the parent element
            if (heap.get(index).compareTo(heap.get(parent(index))) > 0){

                //swaps the current element with its parent
                swap(index, parent(index));

                //updates the index of the current element
                index = parent(index);

            //breaks out of the loop if the element is smaller than its parent
            } else{
                break;
            }
        }
    }

    /**
     * helper method to swap elements at any two indexes in the arraylist
     * @param childIndex the index of one of the elements to be swapped
     * @param parentIndex the index of one of the elements to be swapped
     */
    private void swap(int childIndex, int parentIndex){

        //stores one of the elements
        E temp = heap.get(parentIndex);

        //places the other element into the stored element's index
        heap.set(parentIndex, heap.get(childIndex));

        //places the stored element into the other element's index
        heap.set(childIndex, temp);
    }

    /**
     * finds the left child index of a given index
     * @param parentIndex the parent index
     * @return the left child index of the given index
     */
    private int leftChild(int parentIndex){
        return 2 * parentIndex + 1;
    }

    /**
     * finds the right child index of a given index
     * @param parentIndex the parent index
     * @return the right child index of the given index
     */
    private int rightChild(int parentIndex){
        return 2 * parentIndex + 2;
    }

    /**
     * finds the parent index of a given index
     * @param childIndex the child index
     * @return the parent index of the given index
     */
    private int parent(int childIndex){
        return (childIndex - 1)/2;
    }

    @Override
    /**
     * returns the max value in the heap (the first element in the arraylist)
     */
    public E max() {
        return heap.get(0);
    }

    @Override
    /**
     * replaces and returns the max value in the heap and calls the bubblesDown
     * function to reheapify the arraylist
     */
    public E removeMax() {
        E output = max();
        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        bubbleDown(size - 1);
        size--;
        return output;
    }

    @Override
    /**
     * getter for the size of the heap
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * checks if the heap is empty
     */
    public boolean isEmpty() {
        if (size == 0){
            return true;
        } else{
            return false;
        }
    }

    /**
     * formats the heap into a binary tree for printing
     * @return a string of all the elements in the heap as a binary tree
     */
    public String toString(){

        //creates a stringbuilder to store the output
        StringBuilder output = new StringBuilder();

        //two counters to split the string into binary tree rows
        int powerCount1 = 0;
        int powerCount2 = 1;

        //appends the first item in the heap and adds a newline
        output.append(heap.get(0)).append("\n");

        //loops through all the elements in the heap
        for(int i = 0; i < size; i++){

            //checks if the counters are equal. Two is raised to the second counter.
            if(powerCount1 == powerTwo(powerCount2)){

                //resets the first counter and adds to the second counter (the next row
                //in a binary tree is 2^n+1)
                powerCount2++;
                powerCount1 = 0;

                //appends the element at the current index and adds a newline
                output.append(heap.get(i)).append("\n");

            //otherwise the element is appended without a newline
            } else if(i != 0){
                output.append(heap.get(i)).append(" ");

            //continually adds to the first power counter until it matches 2^(second counter)
            } powerCount1++;

        //returns the stringbuilder as a string
        } return output.toString();
    }

    /**
     * helper for the toString. Checks if the output should have a newline inserted
     * to represent a new row of a binary tree.
     * @param power the value that two will be raised to
     * @return the index at which a new line should be placed
     */
    private int powerTwo(int power){

        int output = 1; //initial value of output

        //loop to raise two to the specified power
        for(int i = 0; i < power; i++){
            output *= 2;
        } return output;
    }
}
