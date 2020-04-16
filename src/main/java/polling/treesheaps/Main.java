package polling.treesheaps;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;

/**
 * Main driver class for lab 6. Takes in a single command line argument and reads the file
 * into an arrayheap.
 *
 * @author Joseph Kawamura
 * @version 15 April 2020
 */
public class Main {
    public static void main(String[] args) {

        ArrayList<String[]> myEntries; //makes an arraylist to store all the rows in the file
        ArrayHeap<Candidate> candidateHeap = new ArrayHeap<>(); //creates an instance of the arrayheap class

        try {
            //imports the specified file into an arraylist of string arrays
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(args[0]));
            myEntries = new ArrayList<>(reader.readAll());

            //catches errors with import
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return;
        }

        //loops through all the rows in the file and adds each one to the arrayheap
        for (String[] row : myEntries) {
            Candidate input = new Candidate(row);
            candidateHeap.insert(input);
        }

        //sets a counter equal to the initial size because size will decrease as removeMax is called
        int counter = candidateHeap.size();

        //loops through the length of the array and calls removeMax each time
        for(int i = 0; i < counter; i++){

            //prints out the largest value in the heap and removes it
            System.out.println(candidateHeap.removeMax());
        }

        //test for the arrayheap
        heapTest();
    }

    /**
     * tests the arrayheap
     */
    public static void heapTest(){

        System.out.println("\n\n\n -------Tests-------");
        //tests arrayheap with integers
        ArrayHeap<Integer> test = new ArrayHeap<>();
        test.insert(-2);
        test.insert(3);
        test.insert(9);
        test.insert(-7);
        test.insert(1);
        test.insert(2);
        test.insert(6);
        test.insert(-3);

        System.out.println(test);
        if (test.toString().compareTo("9\n1 6\n-3 -2 2 3\n-7") == 0){
            System.out.println("Output correct\n");
        } else{
            System.out.println("Output incorrect\n");
        }

        //testing removemax and then the size
        System.out.println("Max: 9");
        if (test.removeMax() == 9){
            System.out.println("removeMax output correct\n");
        } else{
            System.out.println("removeMax output incorrect\n");
        }

        System.out.println("size: " + test.size());
        if (test.size() == 7){
            System.out.println("Size output correct\n");
        } else{
            System.out.println("Size output incorrect\n");
        }

        //testing importing it as a preexisting arraylist
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(-2,3,9,-7,1,2,6,-3));
        ArrayHeap<Integer> test2 = new ArrayHeap<>(input);
        System.out.println(test2);
        if (test2.toString().compareTo("9\n1 6\n-3 -2 2 3\n-7") == 0){
            System.out.println("Correct\n");
        } else{
            System.out.println("Incorrect\n");
        }

        //testing the sort method
        test2.sort();
        System.out.println(test2);
        if (test2.toString().compareTo("-7\n-3 -2\n1 2 3 6\n9") == 0){
            System.out.println("Sort output Correct\n");
        } else{
            System.out.println("Sort output Incorrect\n");
        }

        //testing size, isempty, and max
        System.out.println("size: " + test2.size());
        if (test2.size() == 8){
            System.out.println("Size output Correct\n");
        } else{
            System.out.println("Size output Incorrect\n");
        }

        System.out.println("Is Empty: " + test2.isEmpty());
        if (!test2.isEmpty()){
            System.out.println("isEmpty output Correct\n");
        } else{
            System.out.println("isEmpty output Incorrect\n");
        }

        System.out.println("Max: " + test2.max());
        if (test2.max() == -7){
            System.out.println("Correct\n");
        } else{
            System.out.println("Incorrect\n");
        }

        //tests the arrayheap insert with characters
        ArrayHeap<Character> letter = new ArrayHeap<Character>();
        letter.insert('A');
        letter.insert('C');
        letter.insert('G');
        letter.insert('B');
        letter.insert('D');
        letter.insert('G'); // inserting again, will still include
        letter.insert('F');
        letter.insert('E');
        letter.insert('H');
        letter.insert('I');

        System.out.println(letter);
        if (letter.toString().compareTo("I\nH G\nE G C F\nA D B") == 0){
            System.out.println("Output correct\n");
        } else{
            System.out.println("Output incorrect\n");
        }

        //testing removeMax multiple times
        int len = letter.size();
        Character[] answers = new Character[]{'I','H','G','G','F','E','D','C','B','A'};
        for(int i = 0; i < len; i++){
            Character check = letter.removeMax();
            if (check.compareTo(answers[i]) == 0){
                System.out.println(check + " is correct");
            } else{
                System.out.println(check + " is incorrect");
            }
        } System.out.println("\n");

        //testing removeMax, size, isempty, and max when heap is empty
        System.out.println("size: " + letter.size());
        if (letter.size() == 0){
            System.out.println("Size output Correct\n");
        } else{
            System.out.println("Size output Incorrect\n");
        }

        System.out.println("Is Empty: " + letter.isEmpty());
        if (letter.isEmpty()){
            System.out.println("isEmpty output Correct\n");
        } else{
            System.out.println("isEmpty output Incorrect\n");
        }

        System.out.println("Max: " + letter.max());
        if (letter.max() == null){
            System.out.println("Correct\n");
        } else{
            System.out.println("Incorrect\n");
        }


        System.out.println("Max: null");
        if (letter.removeMax() == null){
            System.out.println("Correct\n");
        } else{
            System.out.println("Incorrect\n");
        }
    }
}
